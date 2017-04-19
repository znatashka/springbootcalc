package u26c4.brain.analyzer;

import org.apache.commons.lang3.StringUtils;

class Utils {

    private Utils() {}

    static String formatExpression(String expression) {
        StringBuilder formattedExpression = new StringBuilder();
        for (Character c : expression.toCharArray()) {
            if (isNumeric(c)) {
                formattedExpression.append(StringUtils.wrap(String.valueOf(c), StringUtils.SPACE));
            } else {
                formattedExpression.append(c);
            }
        }
        return StringUtils.normalizeSpace(formattedExpression.toString());
    }

    static boolean isNumeric(String s) {
        for (Character c : s.toCharArray()) {
            if (isNumeric(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(Character c) {
        return Character.isSpaceChar(c) || (!Character.isDigit(c) && c != '.');
    }
}
