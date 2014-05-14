/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import com.jc.tracker.dao.ProjectDao;
import com.jc.tracker.dao.TaskDao;
import com.jc.tracker.dao.TaskLogDao;
import com.jc.tracker.domain.Project;
import com.jc.tracker.domain.Task;
import com.jc.tracker.domain.User;
import com.jc.tracker.vo.Result;
import com.jc.tracker.vo.ResultFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author campitos
 */
public class TaskServiceImpl extends AbstractService implements TaskService {
  
    @Autowired
    protected TaskDao taskDao;
    
    @Autowired
    protected TaskLogDao taskLogDao;
    
    @Autowired
    protected ProjectDao projectDao;
    public TaskServiceImpl(){
        super();
    }
    
    @Transactional(readOnly=true,
            propagation=Propagation.SUPPORTS)
    @Override
    public Result<Task> find(Integer idTask, String actionUsername){
        if(isValidUser(actionUsername)){
            return ResultFactory.getSuccessResult(taskDao.find(idTask));
        }else{
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    @Override
    public Result<Task> store(Integer idTask,
            Integer idProject,
            String taskName,
            String actionUsername){
        User actionUser=userDao.find(actionUsername);
        if(!actionUser.isAdmin()){
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
        }
        Project project=projectDao.find(idProject);
        if(project==null){
            return ResultFactory.getFailResult("Unable to "
                    + "store task without a valid project [idProject= "+idProject +"]");
        }
        Task task;
        if(idTask==null){
            task=new Task();
            task.setProject(project);
            project.getList().add(task);
        }else{
            task=taskDao.find(idTask);
            if(task==null){
                return ResultFactory.getFailResult("Unable to find task instance with idTask=" +idTask);
            }else{
                if(!task.getProject().equals(project)){
                    Project currentProject =task.getProject(); //reasigna a nuevo proyecto
                    task.setProject(project);
                    project.getList().add(task);
//remover from previas
                    currentProject.getList().remove(task);
                }
            }
        }
        task.setTaskName(taskName);
        if(task.getId()==null){
            taskDao.persist(task);
        }else{
            task=taskDao.merge(task);
        }
        return ResultFactory.getSuccessResult(task);
    }
    
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    @Override
    public Result<Task> remove(Integer idTask, String actionUsername){
        User actionUser=userDao.find(actionUsername);
        if(!actionUser.isAdmin()){
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
        }
        if(idTask==null){
            return ResultFactory.getFailResult("Unable to remove Task [null idTask]);");
        }else{
            Task task=taskDao.find(idTask);
            long taskLogCount=
                    taskLogDao.findTaskLogCountByTask(task);
            if(task==null){
                return ResultFactory.getFailResult("Unable to"
                        + "load Task for removal with idTask="+idTask);
            }else if(taskLogCount >0){
                return ResultFactory.getFailResult("Unable to remove Task with idTask="
                        + idTask+" as valid task logs are assigned");
            }else{
                Project project=task.getProject();
                taskDao.remove(task);
                project.getList().remove(task);
                String msg= "Task "+task.getTaskName() + " was deleted by "+actionUsername;
                logger.info(msg);
                return ResultFactory.getSuccessResultMsg(msg);
            }
        }
    
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    @Override
    public Result<List<Task>> findAll(String actionUsername){
        if(isValidUser(actionUsername)){
            return ResultFactory.getSuccessResult(taskDao.findAll());
        }else{
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
}
