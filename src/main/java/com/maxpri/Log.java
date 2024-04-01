package com.maxpri;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Log {

    private final Ln ln;

    public Log(Ln ln) {
        this.ln = ln;
    }

    public Log() {
        this.ln = new Ln();
    }

    public double log(double a, double b, double esp) {
        return ln.ln(b, esp) / ln.ln(a, esp);
    }

    private void writeResultToCSV(double a, double x, double eps, File file) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + log(a, x, eps));
        printWriter.close();
    }

    public void write(double a, double from, double to, double step, double eps, String filename, boolean override) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (override) {
            file.delete();
            file.createNewFile();
        } else if (!file.exists()) {
            file.createNewFile();
        }
        for (double curr = from; curr <= to; curr += step) {
            writeResultToCSV(a, curr, eps, file);
        }
    }
}
