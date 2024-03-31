package com.maxpri;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sin {
    private double getFactorial(long n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public double sin(double x, double eps) {
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) {
            return Double.NaN;
        }

        x = x % (2 * Math.PI);
        if (x < -Math.PI) {
            x += 2 * Math.PI;
        } else if (x > Math.PI) {
            x -= 2 * Math.PI;
        }

        double result = x;
        long n = 1;
        double curSum = x;

        while (Math.abs(curSum) >= eps) {
            curSum = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / getFactorial(2 * n + 1);
            result += curSum;
            n++;
        }
        return result;
    }

    public void writeResultToCSV(double x, double eps, String filename, boolean override) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (override) {
            file.delete();
            file.createNewFile();
        } else if (!file.exists()) {
            file.createNewFile();
        }
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + sin(x, eps));
        printWriter.close();
    }
}
