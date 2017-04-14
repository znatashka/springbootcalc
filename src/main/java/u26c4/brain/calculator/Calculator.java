package u26c4.brain.calculator;

import org.apache.commons.collections.CollectionUtils;
import u26c4.brain.Brain;
import u26c4.brain.Operator;
import u26c4.builders.ResultBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class Calculator extends Brain<List<?>> {

    @Override
    public ResultBuilder analyze(ResultBuilder resultBuilder, List<?> expression) {
        if (CollectionUtils.isNotEmpty(expression)) {
            log.info("### Calculator is working");
            Stack<Double> numbers = new Stack<>();
            expression.forEach(item -> {
                if (item instanceof BigDecimal) {
                    numbers.push(((BigDecimal) item).doubleValue());
                } else if (item instanceof Operator) {
                    Operator operator = (Operator) item;
                    Double right = numbers.pop();
                    Double left = numbers.pop();
                    numbers.push(operator.applyAsDouble(left, right));
                }
            });
            resultBuilder.buildCalculation(numbers.pop());
        }
        return resultBuilder;
    }
}
