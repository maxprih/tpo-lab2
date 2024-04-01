import com.maxpri.Ln;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author max_pri
 */
public class LnTest {
    private static double eps = 0.0001;
    private Ln ln;

    @BeforeEach
    public void setUpLn() {
        this.ln = new Ln();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/LnIn.csv")
    void testLn(double value, double expected) {
        Assertions.assertEquals(expected, ln.ln(value, eps), eps*100);
    }

}
