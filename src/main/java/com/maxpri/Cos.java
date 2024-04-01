package com.maxpri;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cos {

    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        this.sin = new Sin();
    }

    public double cos(double x, double eps) {
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) {
            return Double.NaN;
        }
        double newX = x % (2 * Math.PI);
        if (x < 0) {
            newX += 2 * Math.PI;
        }
        double sinX = Math.pow(sin.sin(x, eps), 2);
        if (newX > Math.PI / 2 && newX < (Math.PI + Math.PI / 2)) {
            return -Math.sqrt(Math.max(0, 1 - sinX));
        } else {
            return Math.sqrt(Math.max(0, 1 - sinX));
        }
    }

    private void writeResultToCSV(double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + cos(x, eps));
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
