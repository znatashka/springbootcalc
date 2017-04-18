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
public class UtilsIsNumberTest {

    @Parameter
    public String number;
    @Parameter(1)
    public Boolean isNum;

    @Parameters(name = "{index}: number - {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"32", true},
                {"(", false},
                {"10", true},
                {"+", false}
        });
    }

    @Test
    public void isNumber() throws Exception {
        // ACT
        boolean isNumRes = Utils.isNumber(number);

        // ASSERT
        assertEquals(isNumRes, isNum);
    }
}
