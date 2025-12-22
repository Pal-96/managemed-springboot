package com.managemed.managemedapp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Component annotation to mark this class as a Spring bean
@Component

// To give preference to this implementation when multiple beans of the same type are present
// @Primary
public class Desktop implements Computer {
    @Override
    public void compile() {
        System.out.println("Compiling code on Desktop!");
    }
}
