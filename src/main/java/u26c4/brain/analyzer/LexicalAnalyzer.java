package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;
import u26c4.brain.Brain;
import u26c4.builders.ResultBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static u26c4.brain.analyzer.Utils.formatExpression;

@SuppressWarnings("unchecked")
public class LexicalAnalyzer extends Brain<String> {

    @Override
    public ResultBuilder analyze(ResultBuilder resultBuilder, String expression) {
        log.info("### LexerAnalyzer is working");

        if (StringUtils.isBlank(expression)) {
            resultBuilder.buildError("Empty Expression");
            return resultBuilder;
        } else {
            String formattedExpression = formatExpression(expression);
            resultBuilder.buildFormattedExpression(formattedExpression);
            return next.analyze(
                    resultBuilder,
                    Stream
                            .of(formattedExpression.split(StringUtils.SPACE))
                            .collect(Collectors.toList())
            );
        }
    }
}
