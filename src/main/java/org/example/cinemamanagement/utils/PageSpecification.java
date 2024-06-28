package org.example.cinemamanagement.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PageSpecification<T> implements Specification<T> {
    private final transient String mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;
    private final transient  String searchingTerm;

    public PageSpecification( CursorBasedPageable cursorBasedPageable, String mainFieldName, String searchingTerm ) {
        this.cursorBasedPageable = cursorBasedPageable;
        this.mainFieldName = mainFieldName;
        this.searchingTerm = searchingTerm;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // adding sql into criteriaBuilder instance
        Predicate paginationFilter  = applyPaginationFilter(root, criteriaBuilder);
        predicates.add(paginationFilter);

        if(searchingTerm != null && !searchingTerm.isEmpty())
        { Predicate searchTermFilter = criteriaBuilder
                    .like(root.get(mainFieldName), "%" + searchingTerm + "%");
            predicates.add(searchTermFilter);
        }
        query.orderBy(criteriaBuilder.asc(root.get(mainFieldName)));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();

        return cursorBasedPageable.hasPrevPageCursor()
                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);
    }
}