/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.Task;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author campitos
 */
@Repository("taskDao")
@Transactional
public class TaskDaoImpl extends GenericDaoImpl<Task,Integer> implements TaskDao{

    
    public TaskDaoImpl(){
        super(Task.class);
    }
    @Override
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Task> findAll() {
     return em.createNamedQuery("Task.findAll").getResultList();
    }
    
}
