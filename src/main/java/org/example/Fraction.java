package org.example;

import java.lang.reflect.Proxy;

public class Fraction implements Fractionable {
    public Object getProxy() {
        Class cls = this.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{Loadable.class, Fractionable.class},
                new LoadableInvHandler(this));
    }
    private int num;
    private int denum;
    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }
    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }
    @Override
    @Cachable
    public double doubleValue() {
        System.out.println("invoke double value");
        return (double) num/denum;
    }
}

