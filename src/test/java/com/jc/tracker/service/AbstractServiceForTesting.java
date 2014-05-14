/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import com.jc.tracker.dao.CompanyDao;
import com.jc.tracker.dao.ProjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author campitos
 */
@ContextConfiguration("/testingContext.xml")
public abstract class AbstractServiceForTesting extends AbstractTransactionalJUnit4SpringContextTests {
    

   final protected Logger logger=LoggerFactory.getLogger(this.getClass());
    
}
