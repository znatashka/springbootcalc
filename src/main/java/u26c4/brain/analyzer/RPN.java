package u26c4.brain.analyzer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import u26c4.brain.analyzer.exception.RNPException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * Реализация алгоритма `Обратная польская запись`
 */
class RPN {

    private Map<String, Integer> priorities = new HashMap<String, Integer>() {{
        put("(", 0);
        put(")", 0);
        put("+", 1);
        put("-", 1);
        put("*", 2);
        put("/", 2);
        put("^", 3);
    }};

    List<String> calculate(String expression) throws RNPException {
        Stack<String> operators = new Stack<>();
        Stack<String> out = new Stack<>();

        Arrays.asList(expression.split(SPACE)).forEach(s -> {
            if (StringUtils.isNumeric(s)) {
                out.push(s);
            } else {
                if (s.equals(")")) {
                    while (CollectionUtils.isNotEmpty(operators)) {
                        String operator = operators.pop();
                        if (operator.equals("(")) {
                            break;
                        } else {
                            out.push(operator);
                        }
                    }
                } else {
                    if (CollectionUtils.isNotEmpty(operators) && !s.equals("(")) {
                        if (priorities.get(s) <= priorities.get(operators.peek())) {
                            out.push(operators.pop());
                        }
                    }
                    operators.push(s);
                }
            }
        });

        while (!operators.empty()) {
            out.push(operators.pop());
        }

        return out;
    }
}
