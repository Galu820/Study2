package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NothingToUndo {

        System.out.println("******************");
        System.out.println("Hello and welcome!");

        Account acc = new Account("Иван Иванов");

        Class accClass = acc.getClass();
        System.out.println(Arrays.toString(accClass.getDeclaredFields()));

        Fraction fr = new Fraction(3,2);
        fr.setNum(4);
        System.out.println(fr.doubleValue());
        Fractionable num = new Utils(fr);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит
        num.doubleValue();// sout молчит
        num.setNum(5);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит
    }
}