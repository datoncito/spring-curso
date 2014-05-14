/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.domain;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author campitos
 */
public interface JsonItem {
    public JsonObject toJSon();
    public void addJson(JsonObjectBuilder builder);
}
