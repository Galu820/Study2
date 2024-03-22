package org.example;

public class UtilFractionAdapter implements Fractionable {
    private Fractionable baseFr;

    private UtilFractionAdapter() {
    }

    public UtilFractionAdapter(Fractionable baseFr) {
        this.baseFr = baseFr;
    }

    @Override
    public double doubleValue() {
    System.out.println("invoke double value");
    return (double) baseFr.doubleValue();
    }

    @Override
    public void setNum(int num) {

    }

    @Override
    public void setDenum(int denum) {

    }
}
