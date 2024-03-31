import com.maxpri.Cos;
import com.maxpri.Csc;
import com.maxpri.Sec;
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
public class SecTest {
    private static double eps = 0.0001;
    private static Cos mockedCos;
    private static Reader cosReader;
    private Sec sec;


    @BeforeAll
    public static void setUpCosMock() throws IOException {
        mockedCos = Mockito.mock(Cos.class);

        cosReader = new FileReader("src/main/resources/CsvFiles/Inputs/CosIn.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cosReader);
        for (CSVRecord record : records) {
            Mockito.when(mockedCos.cos(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
        }
    }

    @BeforeEach
    public void setUpSec() {
        sec = new Sec(mockedCos);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SecIn.csv")
    void testSec(double value, double expected) {
        Assertions.assertEquals(expected, sec.sec(value, eps), eps);
    }
}