package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;
import u26c4.brain.Brain;
import u26c4.brain.analyzer.exception.RNPException;
import u26c4.builders.ResultBuilder;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static u26c4.brain.analyzer.Utils.formatExpression;

@SuppressWarnings("unchecked")
public class LexicalAnalyzer extends Brain<String> {

    @Override
    public ResultBuilder analyze(ResultBuilder resultBuilder, String expression) {
        log.info("### LexerAnalyzer is working");

        if (StringUtils.isBlank(expression)) {
            return resultBuilder.buildError("Empty Expression");
        } else {
            String formattedExpression = formatExpression(expression);
            resultBuilder.buildFormattedExpression(formattedExpression);

            try {
                List<String> stack = new RPN().calculate(formattedExpression);

                resultBuilder.buildRPN(StringUtils.join(stack, SPACE));
                return next.analyze(resultBuilder, stack);
            } catch (RNPException e) {
                log.error(e.getMessage(), e);
                return resultBuilder.buildError(e.getMessage());
            }
        }
    }
}
