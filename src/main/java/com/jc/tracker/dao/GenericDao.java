/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.dao;

/**
 *
 * @author campitos
 */
public interface GenericDao<T,ID> {
    
    public T find(ID id);
    public void persist(T obj);
    public T merge(T obj);
    public void remove(T obj);
    
    
}
