package u26c4.brain.analyzer;

import u26c4.brain.Operator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static u26c4.brain.Operator.*;

/**
 * Реализация алгоритма `Обратная польская запись`
 */
class RPN {

    List<?> calculate(String expression) {
        Stack<Operator> operators = new Stack<>();
        Stack<Object> out = new Stack<>();

        Arrays.asList(expression.split(SPACE)).forEach(item -> {
            if (Utils.isNumber(item)) {
                out.push(new BigDecimal(item));
            } else {
                Operator nextOp = findBySymbol(item);
                if (nextOp == CLOSING_BRACKET) {
                    while (isNotEmpty(operators)) {
                        Operator operator = operators.pop();
                        if (operator == OPENING_BRACKET) {
                            break;
                        } else {
                            out.push(operator);
                        }
                    }
                } else {
                    if (isNotEmpty(operators) && nextOp != OPENING_BRACKET) {
                        if (nextOp.getPriority() <= operators.peek().getPriority()) {
                            out.push(operators.pop());
                        }
                    }
                    operators.push(nextOp);
                }
            }
        });

        while (isNotEmpty(operators)) {
            out.push(operators.pop());
        }

        return out;
    }
}
