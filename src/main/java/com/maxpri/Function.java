package com.maxpri;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Math.pow;

public class Function {
    Sec sec;
    Tan tan;
    Csc csc;
    Sin sin;
    Cos cos;
    Cot cot;
    Ln ln;
    Log log;

    public Function() {
        this.sec = new Sec();
        this.cos = new Cos();
        this.tan = new Tan();
        this.csc = new Csc();
        this.sin = new Sin();
        this.cot = new Cot();
        this.log = new Log();
        this.ln = new Ln();
    }

    public Function(Sec sec, Tan tan, Csc csc, Sin sin, Cos cos, Cot cot) {
        this.sec = sec;
        this.tan = tan;
        this.csc = csc;
        this.sin = sin;
        this.cos = cos;
        this.cot = cot;
    }

    public double solve(double x, double eps) {
        if (x <= 0) {
            return (((((((((((pow(((((pow((csc.csc(x, eps) + tan.tan(x, eps)), 2) - tan.tan(x, eps)) + tan.tan(x, eps)) / tan.tan(x, eps)) + sec.sec(x, eps)), 3) - pow(sin.sin(x, eps), 2)) + ((pow((cos.cos(x, eps) + sec.sec(x, eps)), 3) + cot.cot(x, eps)) * (cos.cos(x, eps) - sec.sec(x, eps)))) + (sin.sin(x, eps) * ((pow(cos.cos(x, eps), 2) / (sec.sec(x, eps) - tan.tan(x, eps))) - sin.sin(x, eps)))) * csc.csc(x, eps)) / cot.cot(x, eps)) + (cos.cos(x, eps) * cot.cot(x, eps))) - (tan.tan(x, eps) * (cot.cot(x, eps) / cos.cos(x, eps)))) / pow((pow((pow(sec.sec(x, eps), 3) + (sin.sin(x, eps) - (((csc.csc(x, eps) + sin.sin(x, eps)) / tan.tan(x, eps)) / cos.cos(x, eps)))), 2) + (((tan.tan(x, eps) / sec.sec(x, eps)) / csc.csc(x, eps)) / pow(((cot.cot(x, eps) + sin.sin(x, eps)) * tan.tan(x, eps)), 2))), 2)) - ((pow((tan.tan(x, eps) + pow(((cos.cos(x, eps) - csc.csc(x, eps)) - csc.csc(x, eps)), 3)), 2) * sin.sin(x, eps)) + pow((pow(((sin.sin(x, eps) / (pow((csc.csc(x, eps) * (pow(sin.sin(x, eps), 2) / cos.cos(x, eps))), 2) / (((tan.tan(x, eps) / sec.sec(x, eps)) - cot.cot(x, eps)) * (cot.cot(x, eps) / csc.csc(x, eps))))) * (cos.cos(x, eps) + ((csc.csc(x, eps) * (pow(csc.csc(x, eps), 3) / (cot.cot(x, eps) * pow(sec.sec(x, eps), 3)))) - sin.sin(x, eps)))), 3) - (sin.sin(x, eps) * (pow(pow(tan.tan(x, eps), 2), 3) - csc.csc(x, eps)))), 2))) + ((csc.csc(x, eps) - tan.tan(x, eps)) / (cos.cos(x, eps) * ((pow(cos.cos(x, eps), 3) - pow((cos.cos(x, eps) * (tan.tan(x, eps) - sin.sin(x, eps))), 3)) - (pow(tan.tan(x, eps), 2) / (pow(pow(((tan.tan(x, eps) / csc.csc(x, eps)) + csc.csc(x, eps)), 2), 2) * (csc.csc(x, eps) * (sec.sec(x, eps) + csc.csc(x, eps))))))))) / pow((((pow(pow(tan.tan(x, eps), 3), 3) + (cot.cot(x, eps) * (sin.sin(x, eps) + (pow(tan.tan(x, eps), 3) + cos.cos(x, eps))))) + ((((sin.sin(x, eps) + ((sin.sin(x, eps) * csc.csc(x, eps)) / (tan.tan(x, eps) * csc.csc(x, eps)))) * (tan.tan(x, eps) / csc.csc(x, eps))) / pow((((((cot.cot(x, eps) * (cos.cos(x, eps) - sec.sec(x, eps))) + pow((((cot.cot(x, eps) - cos.cos(x, eps)) - sin.sin(x, eps)) * cot.cot(x, eps)), 2)) * tan.tan(x, eps)) / pow(sec.sec(x, eps), 3)) + tan.tan(x, eps)), 2)) / cos.cos(x, eps))) + (((((((sin.sin(x, eps) - sec.sec(x, eps)) / tan.tan(x, eps)) * pow(cot.cot(x, eps), 3)) - pow(tan.tan(x, eps), 3)) / (sin.sin(x, eps) - (((((sec.sec(x, eps) / sin.sin(x, eps)) / cot.cot(x, eps)) - sec.sec(x, eps)) + cot.cot(x, eps)) - cos.cos(x, eps)))) - (cos.cos(x, eps) - tan.tan(x, eps))) - (sin.sin(x, eps) * (sin.sin(x, eps) / cos.cos(x, eps))))), 3));
        }
        return pow(pow(pow(log.log(10, x, eps), 3) - (ln.ln(x, eps) - log.log(2, x, eps)) - log.log(2, x, eps), 3), 2);
    }

    private void writeResultToCSV(double x, double eps, String filename, boolean override) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (override) {
            file.delete();
            file.createNewFile();
        } else if (!file.exists()) {
            file.createNewFile();
        }
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(x + "," + solve(x, eps));
        printWriter.close();
    }

    public void write(double from, double to, double step, double eps, String filename, boolean override) throws IOException {
        if (!override) {
            for (double curr = from; curr <= to; curr += step) {
                writeResultToCSV(curr, eps, filename, false);
            }
        } else {
            for (double curr = from; curr <= to; curr += step) {
                writeResultToCSV(curr, eps, filename, true);
            }
        }
    }
}
