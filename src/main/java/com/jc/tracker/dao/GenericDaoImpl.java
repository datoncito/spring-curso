/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author campitos
 */
public class GenericDaoImpl<T,ID extends Serializable> implements GenericDao<T,ID> {
 final protected Logger logger=LoggerFactory.getLogger(this.getClass());

 @PersistenceContext(unitName="tttPU")
 protected EntityManager em;
 private Class<T> type;
 
 public GenericDaoImpl(Class<T> type1){
     this.type=type1;
 }
 @Override
 @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
 public T find(ID id) {
      return (T)em.find(type, id);
    }

    @Override
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public void persist(T obj) {
     em.persist(obj);
    }

    @Override
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public T merge(T obj) {
       obj=em.merge(obj);
     //  em.flush();
       return obj;
    }

    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public void remove(T obj) {
         obj=merge(obj);
         em.remove(obj);
    }
    
}
