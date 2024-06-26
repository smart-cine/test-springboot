package org.example.cinemamanagement.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PageSpecification<T> implements Specification<T> {
    private final transient String mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;

    public PageSpecification( CursorBasedPageable cursorBasedPageable, String mainFieldName) {
        this.cursorBasedPageable = cursorBasedPageable;
        this.mainFieldName = mainFieldName;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicate = applyPaginationFilter(root, criteriaBuilder);
        query.orderBy(criteriaBuilder.asc(root.get(mainFieldName)));
        return predicate;
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();

        return cursorBasedPageable.hasPrevPageCursor()
                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);
    }
}