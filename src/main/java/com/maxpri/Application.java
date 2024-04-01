package com.maxpri;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Math.pow;

/**
 * @author max_pri
 */

public class Application {
    public static void main(String[] args) throws IOException {
        Cos cos = new Cos();
        cos.write(-2*Math.PI, Math.PI * 2, Math.PI/4, 0.001, "src/main/resources/CsvFiles/Outputs/CosOut.csv", true);
    }

    private static void generateTestCSV(String filename, int numLines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            double range = 8 * Math.PI;
            double step = range / numLines;
            for (double x = -range/2; x < range/2; x+=step) {
                if (x <= 0) {
                    double expected = (((((((((((pow(((((pow(((1 / Math.sin(x)) + Math.tan(x)), 2) - Math.tan(x)) + Math.tan(x)) / Math.tan(x)) + (1 / Math.cos(x))), 3) - pow(Math.sin(x), 2)) + ((pow((Math.cos(x) + (1 / Math.cos(x))), 3) + (1 / Math.tan(x))) * (Math.cos(x) - (1 / Math.cos(x))))) + (Math.sin(x) * ((pow(Math.cos(x), 2) / ((1 / Math.cos(x)) - Math.tan(x))) - Math.sin(x)))) * (1 / Math.sin(x))) / (1 / Math.tan(x))) + (Math.cos(x) * (1 / Math.tan(x)))) - (Math.tan(x) * ((1 / Math.tan(x)) / Math.cos(x)))) / pow((pow((pow((1 / Math.cos(x)), 3) + (Math.sin(x) - ((((1 / Math.sin(x)) + Math.sin(x)) / Math.tan(x)) / Math.cos(x)))), 2) + (((Math.tan(x) / (1 / Math.cos(x))) / (1 / Math.sin(x))) / pow((((1 / Math.tan(x)) + Math.sin(x)) * Math.tan(x)), 2))), 2)) - ((pow((Math.tan(x) + pow(((Math.cos(x) - (1 / Math.sin(x))) - (1 / Math.sin(x))), 3)), 2) * Math.sin(x)) + pow((pow(((Math.sin(x) / (pow(((1 / Math.sin(x)) * (pow(Math.sin(x), 2) / Math.cos(x))), 2) / (((Math.tan(x) / (1 / Math.cos(x))) - (1 / Math.tan(x))) * ((1 / Math.tan(x)) / (1 / Math.sin(x)))))) * (Math.cos(x) + (((1 / Math.sin(x)) * (pow((1 / Math.sin(x)), 3) / ((1 / Math.tan(x)) * pow((1 / Math.cos(x)), 3)))) - Math.sin(x)))), 3) - (Math.sin(x) * (pow(pow(Math.tan(x), 2), 3) - (1 / Math.sin(x))))), 2))) + (((1 / Math.sin(x)) - Math.tan(x)) / (Math.cos(x) * ((pow(Math.cos(x), 3) - pow((Math.cos(x) * (Math.tan(x) - Math.sin(x))), 3)) - (pow(Math.tan(x), 2) / (pow(pow(((Math.tan(x) / (1 / Math.sin(x))) + (1 / Math.sin(x))), 2), 2) * ((1 / Math.sin(x)) * ((1 / Math.cos(x)) + (1 / Math.sin(x)))))))))) / pow((((pow(pow(Math.tan(x), 3), 3) + ((1 / Math.tan(x)) * (Math.sin(x) + (pow(Math.tan(x), 3) + Math.cos(x))))) + ((((Math.sin(x) + ((Math.sin(x) * (1 / Math.sin(x))) / (Math.tan(x) * (1 / Math.sin(x))))) * (Math.tan(x) / (1 / Math.sin(x)))) / pow(((((((1 / Math.tan(x)) * (Math.cos(x) - (1 / Math.cos(x)))) + pow(((((1 / Math.tan(x)) - Math.cos(x)) - Math.sin(x)) * (1 / Math.tan(x))), 2)) * Math.tan(x)) / pow((1 / Math.cos(x)), 3)) + Math.tan(x)), 2)) / Math.cos(x))) + (((((((Math.sin(x) - (1 / Math.cos(x))) / Math.tan(x)) * pow((1 / Math.tan(x)), 3)) - pow(Math.tan(x), 3)) / (Math.sin(x) - ((((((1 / Math.cos(x)) / Math.sin(x)) / (1 / Math.tan(x))) - (1 / Math.cos(x))) + (1 / Math.tan(x))) - Math.cos(x)))) - (Math.cos(x) - Math.tan(x))) - (Math.sin(x) * (Math.sin(x) / Math.cos(x))))), 3));
                    writer.println(x + "," + expected);
                } else {
                    double expected = pow(pow(pow(Math.log(x) / Math.log(10), 3) - (Math.log(x) - Math.log(x) / Math.log(2)) - Math.log(x) / Math.log(2), 3), 2);
                    writer.println(x + "," + expected);
                }
            }
            System.out.println("CSV file generated successfully: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
