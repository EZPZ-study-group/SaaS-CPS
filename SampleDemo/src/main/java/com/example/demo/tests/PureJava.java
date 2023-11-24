package com.example.demo.tests;

import java.util.Arrays;

public class PureJava {
    private String data;

    public PureJava() {
    }

    public PureJava(String data) {
        this.data = data;
    }

    public int add(int... params){
        return Arrays.stream(params).sum();
    }
}
