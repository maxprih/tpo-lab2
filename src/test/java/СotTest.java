import com.maxpri.Cos;
import com.maxpri.Cot;
import com.maxpri.Sin;
import com.maxpri.Tan;
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
public class СotTest {
    private static double eps = 0.000000001;
    private static Sin mockedSin;
    private static Cos mockedCos;
    private static Reader sinReader;
    private static Reader cosReader;
    private Cot cot;


    @BeforeAll
    public static void setUpSinMock() throws IOException {
        mockedSin = Mockito.mock(Sin.class);

        sinReader = new FileReader("src/main/resources/CsvFiles/Inputs/SinIn.csv");

        Iterable<CSVRecord> sinRecords = CSVFormat.DEFAULT.parse(sinReader);
        for (CSVRecord record : sinRecords) {
            Mockito.when(mockedSin.sin(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
        }

        mockedCos = Mockito.mock(Cos.class);

        cosReader = new FileReader("src/main/resources/CsvFiles/Inputs/CosIn.csv");

        Iterable<CSVRecord> cosRecords = CSVFormat.DEFAULT.parse(cosReader);
        for (CSVRecord record : cosRecords) {
            Mockito.when(mockedCos.cos(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
        }
    }

    @BeforeEach
    public void setUpCot() {
        cot = new Cot(mockedSin, mockedCos);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CotIn.csv")
    void testCotWithMocks(double value, double expected) {
        Assertions.assertEquals(expected, cot.cot(value, eps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CotIn.csv")
    void testCot(double value, double expected) {
        Cot cot = new Cot();
        Assertions.assertEquals(expected, cot.cot(value, eps), 0.1);
    }
}
