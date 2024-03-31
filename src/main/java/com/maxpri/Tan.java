package com.maxpri;

/**
 * @author max_pri
 */
public class Tan {
    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }
    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double tan(double x, double eps) {
        double sinVal = sin.sin(x, eps);
        double cosVal = cos.cos(x, eps);
        if (Double.isNaN(sinVal)) return Double.NaN;
        if (Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return sinVal / cosVal;
    }
}
