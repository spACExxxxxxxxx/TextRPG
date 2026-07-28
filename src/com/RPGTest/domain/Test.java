package com.RPGTest.domain;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static String getCodes(){
        String[] testCodes= new String[5];
        Random r = new Random();
        for(int i = 0; i < 5; i++){
            testCodes[i] = String.valueOf((char)('A' + r.nextInt(26)));
        }

        return String.join("", testCodes);
    }
}
