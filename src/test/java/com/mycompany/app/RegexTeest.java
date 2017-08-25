package com.mycompany.app;

import com.mycompany.app.presentacion.controlador.Utils;



import org.junit.Test;

public class RegexTeest {

    @Test
    public void regexTest(){
        org.junit.Assert.assertEquals(true,Utils.matchesRegex("","(\\d{10})"));
    }
}
