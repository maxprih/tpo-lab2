import com.maxpri.Sec;
import com.maxpri.Sin;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author max_pri
 */
public class SinTest {
    private static double eps = 0.00001;
    private Sin sin;

    @BeforeEach
    public void setUpSin() {
        this.sin = new Sin();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SinIn.csv")
    void testSin(double value, double expected) {
        Assertions.assertEquals(expected, sin.sin(value, eps), eps);
    }

    @Test
    void testCsvWrite() throws IOException {
        Sin sin = new Sin();
        sin.write(-2*Math.PI, Math.PI * 2, Math.PI/4, eps, "src/main/resources/CsvFiles/Outputs/SinOut.csv", true);

        Reader sinReader = new FileReader("src/main/resources/CsvFiles/Outputs/SinOut.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(sinReader);

        for (CSVRecord record : records) {
            Assertions.assertEquals(Double.parseDouble(record.get(1)), sin.sin(Double.parseDouble(record.get(0)), eps), 0.001);
        }
    }
}
