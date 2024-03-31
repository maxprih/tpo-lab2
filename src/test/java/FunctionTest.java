import com.maxpri.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class FunctionTest {

    static double functionEps = 0.1;
    double eps = 0.1;

    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    private static Csc cscMock;
    private static Tan tanMock;
    private static Cot cotMock;

    static Reader secIn;
    static Reader cosIn;
    static Reader sinIn;
    private static Reader cscIn;
    private static Reader tanIn;
    private static Reader cotIn;


    @BeforeAll
    static void init() {
        secMock = Mockito.mock(Sec.class);
        cosMock = Mockito.mock(Cos.class);
        sinMock = Mockito.mock(Sin.class);
        cscMock = Mockito.mock(Csc.class);
        tanMock = Mockito.mock(Tan.class);
        cotMock = Mockito.mock(Cot.class);
        try {
            secIn = new FileReader("src/main/resources/CsvFiles/Inputs/SecIn.csv");
            cosIn = new FileReader("src/main/resources/CsvFiles/Inputs/CosIn.csv");
            sinIn = new FileReader("src/main/resources/CsvFiles/Inputs/SinIn.csv");
            cscIn = new FileReader("src/main/resources/CsvFiles/Inputs/CscIn.csv");
            tanIn = new FileReader("src/main/resources/CsvFiles/Inputs/TanIn.csv");
            cotIn = new FileReader("src/main/resources/CsvFiles/Inputs/CotIn.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.sec(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.cos(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.sin(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.csc(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.tan(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cotIn);
            for (CSVRecord record : records) {
                Mockito.when(cotMock.cot(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ignored) {
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/FunctionIn.csv")
    void testSystemWithMocks(double value, double expected) {
        Function function = new Function(secMock, tanMock, cscMock, sinMock, cosMock, cotMock);
        Assertions.assertEquals(expected, function.solve(value, functionEps), eps);
/*
        try {
            Assertions.assertEquals(expected, function.writeResultToCSV(value, functionEps,
                    new FileWriter("C:\\Users\\egorm\\IdeaProjects\\TpoLab2\\src\\main\\resources\\CsvFiles\\Outputs\\SystemOut.csv", true)), eps);
        } catch (IOException e) {
            System.err.println("Да как ты это делаешь ");
        }
*/

    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithSec(double value, double expected) {
//        Function function = new Function(new Sec(cosMock), lnMock, logMock);
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithCos(double value, double expected) {
//        Function function = new Function(new Sec(new Cos(sinMock)), lnMock, logMock);
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithSin(double value, double expected) {
//        Function function = new Function(new Sec(new Cos(new Sin())), lnMock, logMock);
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithLog(double value, double expected) {
//        Function function = new Function(secMock, lnMock, new Log(lnMock));
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithLn(double value, double expected) {
//        Function function = new Function(secMock, new Ln(), new Log());
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 20);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
//    void testWithSinAndLn(double value, double expected) {
//        Function function = new Function();
//        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 20);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/Inputs/CosIn.csv")
//    void testCos(double value, double expected) {
//        Cos cos = new Cos();
//        Assertions.assertEquals(expected, cos.cos(value, functionEps), eps * 20);
//    }
}