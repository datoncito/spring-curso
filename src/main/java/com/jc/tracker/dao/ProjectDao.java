/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.Project;
import java.util.List;


/**
 *
 * @author campitos
 */
public interface ProjectDao extends GenericDao<Project, Integer>{

    public List<Project> findAll();
}
 
