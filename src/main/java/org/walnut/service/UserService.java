package org.walnut.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.walnut.entity.User;
import org.walnut.repository.UserRepository;

import java.util.List;

@Singleton
public class UserService {

    @Inject
    UserRepository userRepository;
    @Transactional
    public boolean save(User user){
        if(user != null){
            userRepository.persist(user);
            return userRepository.isPersistent(user);
        }
        return false;
    }

    public List<User> getUsers(String sortOrder, String sortColumn, int pageSize,
                               int pageNumber, String filterColumn, String filterValue) throws Exception {
        List<User> retList = null ;

        //Get result list based on provided criteria
        if(filterColumn.equalsIgnoreCase("ID") ){
            if(!filterValue.equalsIgnoreCase("0")){
                {
                    Long id = 0L;
                    if(filterColumn.equalsIgnoreCase("ID")){
                        try{
                            id = Long.parseLong(filterValue);
                        }
                        catch (NumberFormatException ne){
                            throw new Exception("Wrong filter criteria provided.");
                        }
                        return retList = userRepository.getByFilter(filterColumn, id);
                    }
                    return  retList = userRepository.getByFilter(filterColumn, filterValue);
                }
            }
        }
        else {
            return  retList = userRepository.getByFilter(filterColumn, filterValue);
        }

        //Get result list based on the sorting
        retList = userRepository.getBySortColumn(sortColumn, sortOrder);
        //Get the result list based on pagination
        int fromIndex = (pageNumber-1) * pageSize;
        int toIndex = fromIndex + pageSize;

        retList = retList.subList(fromIndex, toIndex);
        //retList = userRepository.getByPagination(fromIndex, pageSize);
        return retList;
    }

    public User getUser(Long id){
        return userRepository.findById(id);
    }
}
