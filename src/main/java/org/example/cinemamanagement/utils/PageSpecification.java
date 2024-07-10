package org.example.cinemamanagement.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@RequiredArgsConstructor
public class PageSpecification<T> implements Specification<T> {
    private final transient Object mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;
    private final transient String searchingTerm;

    public PageSpecification(CursorBasedPageable cursorBasedPageable, Object mainFieldName, String searchingTerm) {
        this.cursorBasedPageable = cursorBasedPageable;
        this.mainFieldName = mainFieldName;
        this.searchingTerm = searchingTerm;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // adding sql into criteriaBuilder instance
        Predicate paginationFilter = applyPaginationFilter(root, criteriaBuilder);
        predicates.add(paginationFilter);

        if (searchingTerm != null && !searchingTerm.isEmpty() && mainFieldName.getClass().equals(String.class)) {
            Predicate searchTermFilter = criteriaBuilder
                    .like(root.get((String) mainFieldName), "%" + searchingTerm + "%");
            predicates.add(searchTermFilter);
        } else if (searchingTerm != null && !searchingTerm.isEmpty() && mainFieldName.getClass().equals(Time.class))
        {
            Predicate searchTermFilter = criteriaBuilder
                    .equal(root.get((String) mainFieldName), Time.valueOf(searchingTerm));
            predicates.add(searchTermFilter);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();

        return cursorBasedPageable.hasPrevPageCursor()
                ? criteriaBuilder.lessThan(root.get((String) mainFieldName), searchValue)
                : criteriaBuilder.greaterThan(root.get((String) mainFieldName), searchValue);
    }
}