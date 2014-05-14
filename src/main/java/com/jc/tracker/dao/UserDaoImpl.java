/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.User;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author campitos
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User,String> implements UserDao {

    public UserDaoImpl(){
        super(User.class);
    }
    @Override
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<User> findAll() {
       return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    @Transactional()
    public User findByUsernamePassword(String username, String password) {
       List<User>  users=em.createNamedQuery("User.findByUsernamePassword").setParameter("username", username).setParameter("password", password).getResultList();
    return (users.size()==1?users.get(0):null);
    }

    @Override
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public User findByUsername(String username) {
      List<User> users=em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
    return (users.size()==1 ? users.get(0):null);
    }

    @Override
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public User findByEmail(String email) {
        
      User user=(User)em.createNamedQuery("User.findByEmail").setParameter("email", email).getSingleResult();
     return user;
    }
    
}
