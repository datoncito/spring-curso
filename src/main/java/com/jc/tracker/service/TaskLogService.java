/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import com.jc.tracker.domain.TaskLog;
import com.jc.tracker.vo.Result;
import java.util.Date;
import java.util.List;

/**
 *
 * @author campitos
 */
public interface TaskLogService {
    public Result<TaskLog> store(Integer idTaskLog,
            Integer idTask,
            String username,
            String taskDescription,
            Date taskLogDate,
            int taskMinutes,
            String actionUsername);
    public Result<TaskLog> remove(Integer idTaskLog,
            String actionUsername);
    
    public Result<TaskLog> find(Integer idTaskLog, String actionUsername);
    
    public Result<List<TaskLog>> findByUser(String username, Date starrDate, Date endDate, 
            String actionUsername);
    
}
