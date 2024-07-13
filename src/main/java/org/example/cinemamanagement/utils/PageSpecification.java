package org.example.cinemamanagement.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PageSpecification<T> implements Specification<T> {
    private final transient String mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;
    private final transient Object searchingTerm;

    public PageSpecification(String mainFieldName, CursorBasedPageable cursorBasedPageable, Object searchingTerm) {
        this.mainFieldName = mainFieldName;
        this.cursorBasedPageable = cursorBasedPageable;
        this.searchingTerm = searchingTerm;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate paginationFilter = applyPaginationFilter(root, criteriaBuilder);
        predicates.add(paginationFilter);

        if (mainFieldName.contains("Time") && searchingTerm != null ) {
            Predicate searchTermFilter = criteriaBuilder
                    .greaterThanOrEqualTo(root.get(mainFieldName), (Timestamp) searchingTerm);
            predicates.add(searchTermFilter);
        } else if (searchingTerm != null) {
            Predicate searchTermFilter = criteriaBuilder
                    .like(root.get(mainFieldName), "%" + searchingTerm + "%");
            predicates.add(searchTermFilter);
        }

        query.orderBy(criteriaBuilder.asc(root.get(mainFieldName)));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();
        System.out.println(searchValue);

        if (mainFieldName.contains("Time")) {
            if (searchValue == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(mainFieldName), Timestamp.valueOf("1970-01-01 00:00:00"));
            return criteriaBuilder.greaterThanOrEqualTo(root.get(mainFieldName), (Timestamp) searchValue);
        } else {
            if (searchValue == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(mainFieldName), "");
            return criteriaBuilder.greaterThanOrEqualTo(root.get(mainFieldName), String.valueOf(searchValue));
        }

//        return cursorBasedPageable.hasPrevPageCursor()
//                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
//                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);
//        return cursorBasedPageable.hasPrevPageCursor()
//                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
//                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);

    }
}