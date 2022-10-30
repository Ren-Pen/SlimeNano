package com.slimenano;

import com.slimenano.anntation.SlimeNano;
import com.slimenano.spring.SlimeNanoSpringApplication;


@SlimeNano
public class SlimeNanoApplication {

    public static void main(String[] args) {
        SlimeNanoSpringApplication.start(SlimeNanoApplication.class);
    }

}
