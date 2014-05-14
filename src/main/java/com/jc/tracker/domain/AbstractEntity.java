/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.domain;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author campitos
 */
public class AbstractEntity implements JsonItem, Serializable {

    @Override
    public JsonObject toJSon() {
        JsonObjectBuilder builder=Json.createObjectBuilder();
        addJson(builder);
        return builder.build();
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

    }
    
}
