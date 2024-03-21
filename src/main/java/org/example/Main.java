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
        Fractionable num;
        num = Utils.Cache(fr);
    }
}