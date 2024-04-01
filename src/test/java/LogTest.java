import com.maxpri.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    private static double eps = 0.0001;
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
        Assertions.assertEquals(expected, log.log(10, value, eps), eps);
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
        Assertions.assertEquals(expected, log.log(10, value, eps), eps*10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/Log2In.csv")
    void testLog2(double value, double expected) {
        Log log = new Log();
        Assertions.assertEquals(expected, log.log(2, value, eps), eps*10);
    }
}
