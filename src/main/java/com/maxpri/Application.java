package com.maxpri;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

import static java.lang.Math.pow;

/**
 * @author max_pri
 */

public class Application {
    public static void main(String[] args) throws IOException {
//        Cos cos = new Cos();
//        Ln ln = new Ln();
//        0.030379
//        0.02
//        0
//        0.05
//        0.13302
//        Function function = new Function();
//        double x = 500;
//        System.out.println(function.solve(x, 0.000001));
//        System.out.println(pow(pow(pow(Math.log(x) / Math.log(10), 3) - (Math.log(x) - Math.log(x) / Math.log(2)) - Math.log(x) / Math.log(2), 3), 2));
        Log log = new Log();
        System.out.println(log.log(2, 30.0, 0.00001));
//        generateTestCSV("src/main/resources/CsvFiles/Inputs/FuncIn.csv", 20);
//        generateTestCSV("src/main/resources/CsvFiles/Outputs/TestOut.csv", 123);
//        cos.write(-Math.PI*8, Math.PI * 8, Math.PI/16, 0.001, "src/main/resources/CsvFiles/Outputs/CosOut.csv", true);
//        cos.write(-2*Math.PI, Math.PI * 2, Math.PI/4, 0.001, "src/main/resources/CsvFiles/Outputs/CosOut.csv", true);
    }

    private static void generateTestCSV(String filename, int numLines) throws IOException {
        Reader reader1 = new FileReader("src/main/resources/CsvFiles/Inputs/FunctionIn.csv");
        Reader reader2 = new FileReader("src/main/resources/CsvFiles/Inputs/Function2In.csv");

//        Function function = new Function();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader1);
        Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.parse(reader2);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                if (x <= 0) {
                    double expected = (((((((((((pow(((((pow(((1 / Math.sin(x)) + Math.tan(x)), 2) - Math.tan(x)) + Math.tan(x)) / Math.tan(x)) + (1 / Math.cos(x))), 3) - pow(Math.sin(x), 2)) + ((pow((Math.cos(x) + (1 / Math.cos(x))), 3) + (1 / Math.tan(x))) * (Math.cos(x) - (1 / Math.cos(x))))) + (Math.sin(x) * ((pow(Math.cos(x), 2) / ((1 / Math.cos(x)) - Math.tan(x))) - Math.sin(x)))) * (1 / Math.sin(x))) / (1 / Math.tan(x))) + (Math.cos(x) * (1 / Math.tan(x)))) - (Math.tan(x) * ((1 / Math.tan(x)) / Math.cos(x)))) / pow((pow((pow((1 / Math.cos(x)), 3) + (Math.sin(x) - ((((1 / Math.sin(x)) + Math.sin(x)) / Math.tan(x)) / Math.cos(x)))), 2) + (((Math.tan(x) / (1 / Math.cos(x))) / (1 / Math.sin(x))) / pow((((1 / Math.tan(x)) + Math.sin(x)) * Math.tan(x)), 2))), 2)) - ((pow((Math.tan(x) + pow(((Math.cos(x) - (1 / Math.sin(x))) - (1 / Math.sin(x))), 3)), 2) * Math.sin(x)) + pow((pow(((Math.sin(x) / (pow(((1 / Math.sin(x)) * (pow(Math.sin(x), 2) / Math.cos(x))), 2) / (((Math.tan(x) / (1 / Math.cos(x))) - (1 / Math.tan(x))) * ((1 / Math.tan(x)) / (1 / Math.sin(x)))))) * (Math.cos(x) + (((1 / Math.sin(x)) * (pow((1 / Math.sin(x)), 3) / ((1 / Math.tan(x)) * pow((1 / Math.cos(x)), 3)))) - Math.sin(x)))), 3) - (Math.sin(x) * (pow(pow(Math.tan(x), 2), 3) - (1 / Math.sin(x))))), 2))) + (((1 / Math.sin(x)) - Math.tan(x)) / (Math.cos(x) * ((pow(Math.cos(x), 3) - pow((Math.cos(x) * (Math.tan(x) - Math.sin(x))), 3)) - (pow(Math.tan(x), 2) / (pow(pow(((Math.tan(x) / (1 / Math.sin(x))) + (1 / Math.sin(x))), 2), 2) * ((1 / Math.sin(x)) * ((1 / Math.cos(x)) + (1 / Math.sin(x)))))))))) / pow((((pow(pow(Math.tan(x), 3), 3) + ((1 / Math.tan(x)) * (Math.sin(x) + (pow(Math.tan(x), 3) + Math.cos(x))))) + ((((Math.sin(x) + ((Math.sin(x) * (1 / Math.sin(x))) / (Math.tan(x) * (1 / Math.sin(x))))) * (Math.tan(x) / (1 / Math.sin(x)))) / pow(((((((1 / Math.tan(x)) * (Math.cos(x) - (1 / Math.cos(x)))) + pow(((((1 / Math.tan(x)) - Math.cos(x)) - Math.sin(x)) * (1 / Math.tan(x))), 2)) * Math.tan(x)) / pow((1 / Math.cos(x)), 3)) + Math.tan(x)), 2)) / Math.cos(x))) + (((((((Math.sin(x) - (1 / Math.cos(x))) / Math.tan(x)) * pow((1 / Math.tan(x)), 3)) - pow(Math.tan(x), 3)) / (Math.sin(x) - ((((((1 / Math.cos(x)) / Math.sin(x)) / (1 / Math.tan(x))) - (1 / Math.cos(x))) + (1 / Math.tan(x))) - Math.cos(x)))) - (Math.cos(x) - Math.tan(x))) - (Math.sin(x) * (Math.sin(x) / Math.cos(x))))), 3));
                    writer.println(x + "," + expected);
                } else {
                    double expected = pow(pow(pow(Math.log(x) / Math.log(10), 3) - (Math.log(x) - Math.log(x) / Math.log(2)) - Math.log(x) / Math.log(2), 3), 2);
                    writer.println(x + "," + expected);
                }
            }

            for (CSVRecord record : records2) {
                double x = Double.parseDouble(record.get(0));
                if (x <= 0) {
                    double expected = (((((((((((pow(((((pow(((1 / Math.sin(x)) + Math.tan(x)), 2) - Math.tan(x)) + Math.tan(x)) / Math.tan(x)) + (1 / Math.cos(x))), 3) - pow(Math.sin(x), 2)) + ((pow((Math.cos(x) + (1 / Math.cos(x))), 3) + (1 / Math.tan(x))) * (Math.cos(x) - (1 / Math.cos(x))))) + (Math.sin(x) * ((pow(Math.cos(x), 2) / ((1 / Math.cos(x)) - Math.tan(x))) - Math.sin(x)))) * (1 / Math.sin(x))) / (1 / Math.tan(x))) + (Math.cos(x) * (1 / Math.tan(x)))) - (Math.tan(x) * ((1 / Math.tan(x)) / Math.cos(x)))) / pow((pow((pow((1 / Math.cos(x)), 3) + (Math.sin(x) - ((((1 / Math.sin(x)) + Math.sin(x)) / Math.tan(x)) / Math.cos(x)))), 2) + (((Math.tan(x) / (1 / Math.cos(x))) / (1 / Math.sin(x))) / pow((((1 / Math.tan(x)) + Math.sin(x)) * Math.tan(x)), 2))), 2)) - ((pow((Math.tan(x) + pow(((Math.cos(x) - (1 / Math.sin(x))) - (1 / Math.sin(x))), 3)), 2) * Math.sin(x)) + pow((pow(((Math.sin(x) / (pow(((1 / Math.sin(x)) * (pow(Math.sin(x), 2) / Math.cos(x))), 2) / (((Math.tan(x) / (1 / Math.cos(x))) - (1 / Math.tan(x))) * ((1 / Math.tan(x)) / (1 / Math.sin(x)))))) * (Math.cos(x) + (((1 / Math.sin(x)) * (pow((1 / Math.sin(x)), 3) / ((1 / Math.tan(x)) * pow((1 / Math.cos(x)), 3)))) - Math.sin(x)))), 3) - (Math.sin(x) * (pow(pow(Math.tan(x), 2), 3) - (1 / Math.sin(x))))), 2))) + (((1 / Math.sin(x)) - Math.tan(x)) / (Math.cos(x) * ((pow(Math.cos(x), 3) - pow((Math.cos(x) * (Math.tan(x) - Math.sin(x))), 3)) - (pow(Math.tan(x), 2) / (pow(pow(((Math.tan(x) / (1 / Math.sin(x))) + (1 / Math.sin(x))), 2), 2) * ((1 / Math.sin(x)) * ((1 / Math.cos(x)) + (1 / Math.sin(x)))))))))) / pow((((pow(pow(Math.tan(x), 3), 3) + ((1 / Math.tan(x)) * (Math.sin(x) + (pow(Math.tan(x), 3) + Math.cos(x))))) + ((((Math.sin(x) + ((Math.sin(x) * (1 / Math.sin(x))) / (Math.tan(x) * (1 / Math.sin(x))))) * (Math.tan(x) / (1 / Math.sin(x)))) / pow(((((((1 / Math.tan(x)) * (Math.cos(x) - (1 / Math.cos(x)))) + pow(((((1 / Math.tan(x)) - Math.cos(x)) - Math.sin(x)) * (1 / Math.tan(x))), 2)) * Math.tan(x)) / pow((1 / Math.cos(x)), 3)) + Math.tan(x)), 2)) / Math.cos(x))) + (((((((Math.sin(x) - (1 / Math.cos(x))) / Math.tan(x)) * pow((1 / Math.tan(x)), 3)) - pow(Math.tan(x), 3)) / (Math.sin(x) - ((((((1 / Math.cos(x)) / Math.sin(x)) / (1 / Math.tan(x))) - (1 / Math.cos(x))) + (1 / Math.tan(x))) - Math.cos(x)))) - (Math.cos(x) - Math.tan(x))) - (Math.sin(x) * (Math.sin(x) / Math.cos(x))))), 3));
                    writer.println(x + "," + expected);
                } else {
                    double expected = pow(pow(pow(Math.log(x) / Math.log(10), 3) - (Math.log(x) - Math.log(x) / Math.log(2)) - Math.log(x) / Math.log(2), 3), 2);
                    writer.println(x + "," + expected);
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
