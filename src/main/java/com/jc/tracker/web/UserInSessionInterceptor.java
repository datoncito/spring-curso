/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.web;

import com.jc.tracker.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author campitos
 */
public class UserInSessionInterceptor extends HandlerInterceptorAdapter {
   private final Logger logger=LoggerFactory.getLogger(this.getClass());
    
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception{
     logger.info("calling preHanlder with url="+request.getRequestURI());
     //User sessionUser=getSessionUser(request);
     return true;
 }
}
