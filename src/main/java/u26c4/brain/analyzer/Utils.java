package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

class Utils {

    static String formatExpression(String expression) {
        StringBuilder formattedExpression = new StringBuilder();
        for (Character c : expression.toCharArray()) {
            if (!Character.isSpaceChar(c)) {
                if (!Character.isDigit(c) && c != '.') {
                    formattedExpression.append(StringUtils.wrap(String.valueOf(c), StringUtils.SPACE));
                } else {
                    formattedExpression.append(c);
                }
            }
        }
        return StringUtils.normalizeSpace(formattedExpression.toString());
    }

    static boolean isNumber(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
