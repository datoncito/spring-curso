/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author campitos
 */

@Controller
@RequestMapping("/servicio")

public class ControladorBasico {
    
    @RequestMapping(value="/hola", method=RequestMethod.GET, headers={"Accept=text/html"})
    public @ResponseBody String hola(){
        
        return "Hola mundo";
    }
    
}
