import com.maxpri.Csc;
import com.maxpri.Ln;
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
public class LnTest {
    private static double eps = 0.00001;
    private Ln ln;

    @BeforeEach
    public void setUpLn() {
        this.ln = new Ln();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/LnIn.csv")
    void testLn(double value, double expected) {
        Assertions.assertEquals(expected, ln.ln(value, eps), 0.1);
    }

    @Test
    void testCsvWrite() throws IOException {
        Ln ln = new Ln();
        ln.write(-2*Math.PI, Math.PI * 2, Math.PI/4, eps, "src/main/resources/CsvFiles/Outputs/LnOut.csv", true);

        Reader lnReader = new FileReader("src/main/resources/CsvFiles/Outputs/LnOut.csv");

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(lnReader);

        for (CSVRecord record : records) {
            Assertions.assertEquals(Double.parseDouble(record.get(1)), ln.ln(Double.parseDouble(record.get(0)), eps), 0.001);
        }
    }
}
