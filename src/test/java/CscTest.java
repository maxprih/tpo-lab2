import com.maxpri.Cos;
import com.maxpri.Csc;
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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author max_pri
 */
public class CscTest {
    private static double eps = 0.0000001;
    private static Sin mockedSin;
    private static Reader sinReader;
    private Csc csc;


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
    public void setUpCsc() {
        csc = new Csc(mockedSin);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CscIn.csv")
    void testCscWithMock(double value, double expected) {
        Assertions.assertEquals(expected, csc.csc(value, eps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CscIn.csv")
    void testCsc(double value, double expected) {
        Csc csc = new Csc();
        Assertions.assertEquals(expected, csc.csc(value, eps), 0.1);
    }

    @Test
    void testCsvWrite() throws IOException {
        Csc csc = new Csc();
        csc.write(-2*Math.PI, Math.PI * 2, Math.PI/4, eps, "src/main/resources/CsvFiles/Outputs/CscOut.csv", true);

        Reader cscReader = new FileReader("src/main/resources/CsvFiles/Outputs/CscOut.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cscReader);

        for (CSVRecord record : records) {
            Assertions.assertEquals(Double.parseDouble(record.get(1)), csc.csc(Double.parseDouble(record.get(0)), eps), 0.001);
        }
    }
}
