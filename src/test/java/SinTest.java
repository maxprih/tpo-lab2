import com.maxpri.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author max_pri
 */
public class SinTest {
    private static double eps = 0.0001;
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
}
