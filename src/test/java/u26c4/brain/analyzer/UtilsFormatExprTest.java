package u26c4.brain.analyzer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilsFormatExprTest {

    @Parameter
    public String expr;
    @Parameter(1)
    public Integer length;

    @Parameters(name = "{index}: expression - {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"3+4*2/(1-5)^2", 13},
                {"(8+2*5)/(1+3*2-4)", 17},
                {"(15/3+11-3*5)/3.2*(5.6-10)", 19}
        });
    }

    @Test
    public void formatExpression() throws Exception {
        // ACT
        String formattedExpr = Utils.formatExpression(expr);

        // ASSERT
        assertEquals(Integer.valueOf(formattedExpr.split("\\s+").length), length);
    }
}