package org.walnut.service;


import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.walnut.entity.User;

import java.util.List;

@Singleton
public class UserService {
    @Transactional
    public boolean save(User user){
        if(user != null){
            User.persist(user);
            return user.isPersistent();
        }
        return false;
    }

    public List<User> getUsers(){
        return User.listAll();
    }

    public User getUser(Long id){
        return User.findById(id);
    }
}
