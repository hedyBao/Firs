package com.tomas.hellodemo.Test;

import com.google.gson.Gson;

public class A {
   private int name=123;
   private String pass="234";
   private double legth=2.34;

    public static void main(String[] args) {
        A a = new A();
        System.out.println(new Gson().toJson(a));
    }
}
