package u26c4.builders;

import u26c4.models.CalcResult;
import u26c4.models.Tree;

public class ResultBuilder {
    private CalcResult calcResult;

    public ResultBuilder() {
        calcResult = new CalcResult();
    }

    public ResultBuilder buildError(String msg) {
        calcResult.setError(msg);
        return this;
    }

    public ResultBuilder buildFormattedExpression(String formattedExpression) {
        calcResult.setFormattedExpression(formattedExpression);
        return this;
    }

    public ResultBuilder buildRPN(String rpn) {
        calcResult.setRpn(rpn);
        return this;
    }

    public ResultBuilder buildCalculation(double calculation) {
        calcResult.setCalculation(calculation);
        return this;
    }

    public ResultBuilder buildParseTree(Tree parseTree) {
        calcResult.setParseTree(parseTree);
        return this;
    }

    public CalcResult build() {
        return calcResult;
    }
}
