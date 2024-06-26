import com.maxpri.*;
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
public class LogTest {
    private static double eps = 0.00001;
    private static Ln mockedLn;
    private static Reader lnReader;
    private Log log;

    @BeforeAll
    public static void setUpSinMock() throws IOException {
        mockedLn = Mockito.mock(Ln.class);

        lnReader = new FileReader("src/main/resources/CsvFiles/Inputs/LnIn.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(lnReader);
        for (CSVRecord record : records) {
            Mockito.when(mockedLn.ln(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
        }
    }

    @BeforeEach
    public void setUpLog() {
        log = new Log(mockedLn);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/Log10In.csv")
    void testLog10WithMocks(double value, double expected) {
        Assertions.assertEquals(expected, log.log(10, value, eps), 0.1);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/Log2In.csv")
    void testLog2withMocks(double value, double expected) {
        Assertions.assertEquals(expected, log.log(2, value, eps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/Log10In.csv")
    void testLog10(double value, double expected) {
        Log log = new Log();
        Assertions.assertEquals(expected, log.log(10, value, eps), 0.1);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/Log2In.csv")
    void testLog2(double value, double expected) {
        Log log = new Log();
        Assertions.assertEquals(expected, log.log(2, value, eps), 0.1);
    }

    @Test
    void testCsvWrite() throws IOException {
        Log log = new Log();
        log.write(10,-2*Math.PI, Math.PI * 2, Math.PI/4, eps, "src/main/resources/CsvFiles/Outputs/Log10Out.csv", true);

        Reader logReader = new FileReader("src/main/resources/CsvFiles/Outputs/Log10Out.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(logReader);

        for (CSVRecord record : records) {
            Assertions.assertEquals(Double.parseDouble(record.get(1)), log.log(10, Double.parseDouble(record.get(0)), eps), 0.001);
        }
    }
}
