package com.maxpri;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private void writeResultToCSV(double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + cot(x, eps));
        printWriter.close();
    }

    public void write(double from, double to, double step, double eps, String filename, boolean override) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (override) {
            file.delete();
            file.createNewFile();
        } else if (!file.exists()) {
            file.createNewFile();
        }
        for (double curr = from; curr <= to; curr += step) {
            writeResultToCSV(curr, eps, file);
        }
    }
}
