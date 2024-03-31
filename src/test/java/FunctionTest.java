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

    private static double functionEps = 0.1;
    private double eps = 0.1;

    private static Sec secMock;
    private static Cos cosMock;
    private static Sin sinMock;
    private static Csc cscMock;
    private static Tan tanMock;
    private static Cot cotMock;

    private static Reader secIn;
    private static Reader cosIn;
    private static Reader sinIn;
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
    }
}