package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;
import u26c4.brain.Brain;
import u26c4.brain.exception.BrainException;
import u26c4.builders.ResultBuilder;
import u26c4.models.Tree;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static u26c4.brain.analyzer.Utils.formatExpression;

@SuppressWarnings("unchecked")
public class Analyzer extends Brain<String> {

    @Override
    public ResultBuilder analyze(ResultBuilder resultBuilder, String expression) {
        log.info("### Analyzer is working");

        if (StringUtils.isBlank(expression)) {
            return resultBuilder.buildError("Empty Expression");
        } else {
            String formattedExpression = formatExpression(expression);
            resultBuilder.buildFormattedExpression(formattedExpression);

            try {
                Tree parseTree = LexicalAnalyzer.evaluate(formattedExpression);
                resultBuilder.buildParseTree(parseTree);

                List<?> stack = new RPN().calculate(formattedExpression);
                resultBuilder.buildRPN(StringUtils.join(stack, SPACE));

                return next.analyze(resultBuilder, stack);
            } catch (BrainException e) {
                log.error("### Analysis errors", e);
                return resultBuilder.buildError(e.getHtml());
            }
        }
    }
}
