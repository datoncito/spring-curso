/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import com.jc.tracker.vo.Result;
import java.util.List;
import org.eclipse.persistence.sessions.Project;

/**
 *
 * @author campitos
 */
public interface ProjectService {
    
    public Result<Project> store(
            Integer idProject,
            Integer idCompany,
            String projectName,
            String actionUsername);
    public Result<Project> remove(Integer idProject,
                 String actionUsername);
    
    public Result<Project> find(Integer idProject,
            String actionUsername);
    public Result<List<Project>> findAll(String actionUsername);
    
}
