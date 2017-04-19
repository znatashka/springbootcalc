package u26c4.brain;

import org.junit.Test;
import u26c4.brain.exception.BrainException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OperatorTest {

    @Test
    public void findBySymbolOk() throws Exception {
        // ACT
        Operator operator = Operator.findBySymbol("+");

        // ASSERT
        assertNotNull(operator);
        assertEquals(operator, Operator.PLUS);
    }

    @Test(expected = BrainException.class)
    public void findBySymbolExc() throws Exception {
        // ACT
        Operator.findBySymbol("&");
    }
}