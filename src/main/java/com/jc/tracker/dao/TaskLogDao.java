/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.Task;
import com.jc.tracker.domain.TaskLog;
import com.jc.tracker.domain.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author campitos
 */
public interface TaskLogDao extends GenericDao<TaskLog, Integer> {

public List<TaskLog> findByUser(User user, Date startDate, Date endDate);
public long findTaskLogCountByTask(Task task);
public long findTaskLogCountUser(User user);
   
   

}
