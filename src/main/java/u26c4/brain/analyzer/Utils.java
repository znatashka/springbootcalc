package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;

import static java.util.Arrays.stream;
import static u26c4.brain.analyzer.Constants.brackets;
import static u26c4.brain.analyzer.Constants.operations;

class Utils {

    static String formatExpression(String expression) {
        StringBuilder formattedExpression = new StringBuilder();
        for (Character c : expression.toCharArray()) {
            if (!Character.isSpaceChar(c)) {
                if (stream(operations).anyMatch(c::equals) || stream(brackets).anyMatch(c::equals)) {
                    formattedExpression.append(StringUtils.wrap(String.valueOf(c), StringUtils.SPACE));
                } else {
                    formattedExpression.append(c);
                }
            }
        }
        return StringUtils.normalizeSpace(formattedExpression.toString());
    }
}
