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

    private void writeResultToCSV(double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + tan(x, eps));
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
