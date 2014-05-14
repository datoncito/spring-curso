/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import com.jc.tracker.dao.ProjectDao;
import com.jc.tracker.domain.Company;
import com.jc.tracker.domain.Project;
import com.jc.tracker.vo.Result;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author campitos
 */
public class CompanyServiceTest extends AbstractServiceForTesting {
  protected final String TEST_USERNAME ="campitos";
     @Autowired(required=true)
    protected CompanyService companyService;
   
    @Autowired(required=true)
    protected ProjectDao projectDao;
    

  

  @Test
  public void testFind() throws Exception{
      logger.debug("\n STARTED testFind() \n");
      Result<List<Company>> allItems=companyService.findAll(TEST_USERNAME);
      assertTrue(allItems.getData().size() >0);
      //get the first item in the list
      Company c1=allItems.getData().get(0);
      int id=c1.getId();
      Result<Company> c2=companyService.find(id, TEST_USERNAME);
      assertTrue(c1.equals(c2.getData()));
      logger.debug("\n FINISHED TESTfIND() \n");
  }
  
  @Test
  public void testFindAll() throws Exception {
      logger.debug("\n STARTED TESTfINDaLL() \n");
      int rowCount =countRowsInTable("ttt_company");
      if(rowCount >0){
          Result<List<Company>> allItems=companyService.findAll(TEST_USERNAME);
          assertTrue("Company.findAll list not equal to rwo count of table ttt_company", rowCount==
                  allItems.getData().size());
      }else{
          throw new IllegalStateException("INVALID TESTING SCENARIO: Company table es empty");
      }
      logger.debug("\n FINISHED testFindAll() \n");
  }
  
  @Test
  public void testAddNew() throws Exception{
      logger.debug("\n STARTED testAddNew() \n");
      //Company c=new Company();
      final String NEW_NAME="New Test Company name";
      //c.setCompanyName(NEW_NAME);
      Result<Company> c2=companyService.store(null, NEW_NAME, TEST_USERNAME);
     // assertTrue("No se pero algo aslio mal",c2.getData().getId()!=null);
     // assertTrue(c2.getData().getCompanyName().equals(NEW_NAME));
      logger.debug("\n FINISHED testAddNew() \n");
  }
  
  @Test
  public void testUpdate() throws Exception{
      logger.debug("\n STARTED testUpdate() \n");
      final String NEW_NAME="Update test Company New Name";
      Result<List<Company>> ar1=companyService.findAll(TEST_USERNAME);
      Company c=ar1.getData().get(0);
      companyService.store(c.getIdCompany(), NEW_NAME, TEST_USERNAME);
      Result<Company> ar2=companyService.find(c.getIdCompany(), TEST_USERNAME);
   // assertTrue(ar2.getData().getCompanyName().equals(NEW_NAME));
    logger.debug("\n FINISHED testMerge() \n");
  }
  
  @Test
  public void testRemove() throws Exception{
      logger.debug("\n STARTED testRemove()\n");
      Result<List<Company>> ar1=companyService.findAll(TEST_USERNAME);
      Company c=ar1.getData().get(0);
      
      Result<Company> ar=companyService.remove(c.getIdCompany(), TEST_USERNAME);
      Result<Company> ar2=companyService.find(c.getIdCompany(), TEST_USERNAME);
      //should fail as projects are assigned
      assertTrue(!ar.isSuccess());
      //finder still works
      //assertTrue(ar2.getData()!=null);
      logger.debug("\n testRemove() -UNABLE TO DELETE TESTS PASSSED \n");
      //remove all the projects
      c=ar2.getData();
      for(Project p: c.getProjects()){
        projectDao.remove(p);
      }
      c.getProjects().clear();
      logger.debug("\ntestRemove() - removed all Projects \n");
      ar=companyService.remove(c.getIdCompany(), TEST_USERNAME);
      //remove should have succeeded
      assertTrue(ar.isSuccess());
      
      ar2=companyService.find(c.getIdCompany(), TEST_USERNAME);
      //shlound not have been found
      assertTrue(ar2.getData()==null);
      assertTrue(ar2.isSuccess());
      logger.debug("\n FINISHED testRemove() \n");
  }
  
  
}
