import com.maxpri.Cos;
import com.maxpri.Csc;
import com.maxpri.Sin;
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
public class CscTest {
    private static double eps = 0.0001;
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
    void testCsc(double value, double expected) {
        Assertions.assertEquals(expected, csc.csc(value, eps), eps);
    }
}
