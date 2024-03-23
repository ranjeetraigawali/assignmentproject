package org.walnut.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.walnut.entity.User;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public List<User> getBySortColumn(String sortColumn, String  sortOrder){
        return  list("order by "+sortColumn+" "+sortOrder);
    }

    public List<User> getByFilter(String filterColumn, Object filterValue, String filterCriteria) {
        if (filterValue instanceof String)
                return list(filterColumn + filterCriteria + "'" + filterValue + "'");
        return  list(filterColumn + filterCriteria + filterValue);
    }

    public List<User> getByPagination(int fromIndex, int pageSize) {
        return list("LIMIT "+pageSize+" OFFSET "+fromIndex);
    }
}
