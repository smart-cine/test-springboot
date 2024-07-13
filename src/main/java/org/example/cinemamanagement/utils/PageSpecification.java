package org.example.cinemamanagement.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PageSpecification<T> implements Specification<T> {
    private final transient String mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;
    private final transient Object searchingTerm;
    private final transient Map<String, Object> searchValueMap;

    public PageSpecification(String mainFieldName,
                             CursorBasedPageable cursorBasedPageable,
                             Object searchingTerm,
                             Map<String, Object> searchValueMap) {

        this.mainFieldName = mainFieldName;
        this.cursorBasedPageable = cursorBasedPageable;
        this.searchingTerm = searchingTerm;
        this.searchValueMap = searchValueMap;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate paginationFilter = applyPaginationFilter(root, criteriaBuilder);
        predicates.add(paginationFilter);

        if (searchingTerm != null) {
            Predicate searchTermFilter = filterBaseOnParams(root, criteriaBuilder);
            predicates.add(searchTermFilter);
        }

        query.orderBy(criteriaBuilder.asc(root.get(mainFieldName)));
        // filter base on id
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();

        if (mainFieldName.contains("Time")) {
            if (searchValue == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(mainFieldName), Timestamp.valueOf("1970-01-01 00:00:00"));

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(String.valueOf(searchValue));
                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                return criteriaBuilder.greaterThan(root.get(mainFieldName), timestamp);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } else {
            if (searchValue == null)
                return criteriaBuilder.greaterThan(root.get(mainFieldName), "");
            return criteriaBuilder.greaterThan(root.get(mainFieldName), String.valueOf(searchValue));
        }

    }

    private Predicate filterBaseOnParams(Root<T> root, CriteriaBuilder criteriaBuilder) {
        Predicate searchTermFilter;
        if (mainFieldName.contains("Time")) {
            searchTermFilter = criteriaBuilder
                    .greaterThanOrEqualTo(root.get(mainFieldName), (Timestamp) searchingTerm);
        } else if (mainFieldName.equals("cinema")) {
            searchTermFilter = criteriaBuilder
                    .equal(root.get(mainFieldName).get("id"), searchingTerm);
        } else {
            searchTermFilter = criteriaBuilder
                    .like(root.get(mainFieldName), "%" + searchingTerm + "%");
        }
        return searchTermFilter;
    }
}