package com.maxpri;

/**
 * @author max_pri
 */
public class Cot {
    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }
    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double cot(double x, double eps) {
        double sinVal = sin.sin(x, eps);
        double cosVal = cos.cos(x, eps);
        if (Double.isNaN(sinVal) || sinVal == 0) return Double.NaN;
        if (Double.isNaN(cosVal)) return Double.NaN;
        return cosVal / sinVal;
    }
}
