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
public class Csc {
    private final Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double csc(double x, double eps) {
        double sinVal = sin.sin(x, eps);
        if (Double.isNaN(sinVal) || sinVal == 0) return Double.NaN;
        return 1 / sinVal;
    }

    private void writeResultToCSV(double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + csc(x, eps));
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
