package com.managemed.managemedapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class Dev {
    //How to inject the Computer object?
    //1. field injection
    
    // @Autowired
    Computer comp;
    
    //2. setter injection
    
    @Autowired
    @Qualifier("laptop")
    // to specify which bean to inject when multiple beans of the same type are present
    public void setComp(Computer comp) {
        this.comp = comp;
    }

    //constructor injection: doesnt require @Autowired annotation
    // public Dev(Computer comp) {
    //     this.comp = comp;
    // }
    public void build() {
        System.out.println("Dev Building!");
        comp.compile();

    }
    
}
