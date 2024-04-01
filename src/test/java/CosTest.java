import com.maxpri.Cos;
import com.maxpri.Sin;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author max_pri
 */
public class CosTest {
    private static double eps = 0.0001;
    private static Sin mockedSin;
    private static Reader sinReader;
    private Cos cos;


    @BeforeAll
    public static void setUpSinMock() throws IOException {
        mockedSin = Mockito.mock(Sin.class);

        sinReader = new FileReader("src/main/resources/CsvFiles/Inputs/SinIn.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(sinReader);
        for (CSVRecord record : records) {
            Mockito.when(mockedSin.sin(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
        }
    }

    @BeforeEach
    public void setUpCos() {
        cos = new Cos(mockedSin);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CosIn.csv")
    void testCosWithMock(double value, double expected) {
        Assertions.assertEquals(expected, cos.cos(value, eps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CosIn.csv")
    void testCos(double value, double expected) {
        Cos cos = new Cos();
        Assertions.assertEquals(expected, cos.cos(value, eps), eps*10);
    }

    @Test
    void testCsvWrite() throws IOException {
        Cos cos = new Cos();
        cos.write(-2*Math.PI, Math.PI * 2, Math.PI/4, eps, "src/main/resources/CsvFiles/Outputs/CosOut.csv", true);

        Reader cosReader = new FileReader("src/main/resources/CsvFiles/Outputs/CosOut.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cosReader);

        for (CSVRecord record : records) {
            Assertions.assertEquals(Double.parseDouble(record.get(1)), cos.cos(Double.parseDouble(record.get(0)), eps), eps*10);
        }
    }
}
