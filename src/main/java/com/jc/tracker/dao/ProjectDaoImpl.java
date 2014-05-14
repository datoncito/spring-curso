/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.Project;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author campitos
 */
@Repository("projectDao")
@Transactional
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements ProjectDao {

    public ProjectDaoImpl(){
        super(Project.class);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    @Override
    public List<Project> findAll() {
     return  em.createNamedQuery("Projects.findAll").getResultList();
    }
 
    
}
