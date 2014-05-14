/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

import com.jc.tracker.domain.Company;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author campitos
 */
public class CompanyDaoTest extends AbstractDaoForTesting {
  
    public CompanyDaoTest(){}
    
    @Test
    public void testFind()throws Exception{
        logger.debug("\nSTARTED testFind() \n");
        List<Company> allItems= companyDao.findAll();
        assertTrue(allItems.size()>0);
        //obtener el primer items en la lista
        Company c1=allItems.get(0);
        
        int id=c1.getId();
        Company c2=companyDao.find(id);
        assertTrue(c1.equals(c2));
        logger.debug("\n Finished testFind() \n");
        logger.debug("\n  el nombre de la compañia es:"+c2.getCompanyName()+"\n");
    }
    
    @Test
    public void testFindAll() throws Exception {
        logger.debug("\n STARTED testfindAll() \n");
        int rowCount =countRowsInTable("ttt_company");
        if(rowCount > 0){
            List<Company> allItems =companyDao.findAll();
            assertTrue("Company.findAll list not igual to contedo de rows en tabla"
                    + "ttt-company", rowCount==allItems.size());
        }else{
            throw new IllegalStateException("INVALID TESTING SCENARIO: Company table is emy :'(");
        }
        logger.debug("\n FINISHED TestFindAll() \n");
    }
    
    @Test
    public void testPersist() throws Exception{   
        logger.debug("\n STARTED testPersist() \n");
        Company c=new Company();
        final String NEW_NAME="Persist Test Company name";
        final String WHO_CREATE="CAMPITOS";
        final String WHO_UPDATED="campitos";
        c.setCompanyName(NEW_NAME);
        c.setWhoCreated(WHO_CREATE);
        c.setWhoUpdated(WHO_UPDATED);
        companyDao.persist(c);
        assertTrue("No se guardo ese puto registro",companyDao.findAll().contains(c));
        assertTrue("por alguna razon no se inserto"+c.getId(),c.getId()!=null);
        assertTrue(c.getCompanyName().equals(NEW_NAME));
        
        logger.debug("\n FINISHED testPersist() \n");
    }
    
    @Test
    public void testMerge() throws Exception{
        logger.debug("\n STARTED testMerge(9 \n");
        final String NEW_NAME="Merge Test Company New Name";
        
        Company c=companyDao.findAll().get(0);
        c.setCompanyName(NEW_NAME);
        c=companyDao.merge(c);
        assertTrue("Se volo el test de actualizar",c.getCompanyName().equals(NEW_NAME));
        
        logger.debug("\n FINISHED testMerge() \n");
    }
    
    @Test
    public void testRemove() throws Exception {
        logger.debug("\n STARTED testRemove() \n");
        Company c=companyDao.findAll().get(0);
        
        companyDao.remove(c);
        
        List<Company> allItems =companyDao.findAll();
        assertTrue("Deleted company may not be in findAll List", !allItems.contains(c));
        logger.debug("\n FINISHED TestRemoveE() \n");
    }
    
}
