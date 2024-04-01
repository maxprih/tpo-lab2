package com.maxpri;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sec {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public Sec() {
        this.cos = new Cos();
    }

    public double sec(double x, double eps) {
        double cosVal = cos.cos(x, eps);
        if (Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return 1 / cosVal;
    }

    private void writeResultToCSV(double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + sec(x, eps));
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
