import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.checkFeetEquality(1.0,1.0));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkFeetEquality(1.0,2.0));
    }

    @Test
    public void testInchEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.checkInchEquality(1.0,1.0));
    }

    @Test
    public void testInchEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkInchEquality(1.0,2.0));
    }
}