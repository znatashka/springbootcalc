package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RPNTest {

    @Parameter
    public String expr;
    @Parameter(1)
    public String rpn;

    @Parameters(name = "{index}: expression - {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"( 1 + 2 ) * 4 + 3", "1 2 + 4 * 3 +"},
                {"3 + 4", "3 4 +"},
                {"3 + 4 * 2 / ( 1 - 5 ) ^ 2", "3 4 2 * 1 5 - 2 ^ / +"}
        });
    }

    @Test
    public void calculate() throws Exception {
        // ACT
        List<?> result = new RPN().calculate(expr);

        // ASSERT
        assertEquals(StringUtils.join(result, " "), rpn);
    }
}