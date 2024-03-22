package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NothingToUndo {

        //Account acc = new Account("Иван Иванов");

        //Class accClass = acc.getClass();
        //System.out.println(Arrays.toString(accClass.getDeclaredFields()));

        Fraction fr = new Fraction(3,2);
        System.out.println(fr.doubleValue());
        fr.setNum(4);
        System.out.println(fr.doubleValue());
        System.out.println(fr.doubleValue());

        Fractionable num = new UtilFractionAdapter(fr);
        //Utils<Fraction,String> numb = new Fraction(3,2);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит
        num.doubleValue();// sout молчит
        num.setNum(5);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит
    }
}