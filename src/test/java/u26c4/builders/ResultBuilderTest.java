package u26c4.builders;

import org.junit.Test;
import u26c4.models.CalcResult;

import static org.junit.Assert.assertEquals;

public class ResultBuilderTest {

    @Test
    public void buildError() throws Exception {
        // PREPARE
        String error = "Test Error Msg";

        // ACT
        CalcResult result = new ResultBuilder()
                .buildError(error)
                .build();

        // ASSERT
        assertEquals(result.getError(), error);
    }

    @Test
    public void buildFormattedExpression() throws Exception {
        // PREPARE
        String formattedExpr = "Test Formatted Expression";

        // ACT
        CalcResult result = new ResultBuilder()
                .buildFormattedExpression(formattedExpr)
                .build();

        // ASSERT
        assertEquals(result.getFormattedExpression(), formattedExpr);
    }

    @Test
    public void buildRPN() throws Exception {
        // PREPARE
        String rpn = "Test RPN Expression";

        // ACT
        CalcResult result = new ResultBuilder()
                .buildRPN(rpn)
                .build();

        // ASSERT
        assertEquals(result.getRpn(), rpn);
    }

    @Test
    public void buildCalculation() throws Exception {
        // PREPARE
        Double calculation = 45.67;

        // ACT
        CalcResult result = new ResultBuilder()
                .buildCalculation(calculation)
                .build();

        // ASSERT
        assertEquals(result.getCalculation(), calculation);
    }
}