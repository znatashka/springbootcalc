package u26c4.brain.analyzer;

import u26c4.brain.Operator;
import u26c4.builders.BrainExceptionBuilder;
import u26c4.models.Tree;

import java.util.StringTokenizer;

class LexicalAnalyzer {

    static Tree evaluate(String expression) {
        BrainExceptionBuilder exceptionBuilder = new BrainExceptionBuilder();

        Tree root = buildParseTree(expression);

        if (exceptionBuilder.hasErrors()) {
            throw exceptionBuilder
                    .buildHtml()
                    .build();
        }
        return root;
    }

    private static Tree buildParseTree(String expression) {
        Tree root = new Tree();
        Tree current = root;
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (Utils.isNumeric(token)) {
                current.setToken(token);
                current = current.getParent();
            } else {
                Operator operator = Operator.findBySymbol(token);
                switch (operator) {
                    case OPENING_BRACKET:
                        Tree openBr = new Tree();
                        openBr.setParent(current);
                        current.setLeft(openBr);
                        current = openBr;
                        break;
                    case PLUS:
                    case MINUS:
                    case DIVIDE:
                    case MULTIPLY:
                    case DEGREE:
                        Tree op = new Tree();
                        op.setParent(current);
                        current.setRight(op);
                        current.setToken(token);
                        current = op;
                        break;
                    case CLOSING_BRACKET:
                        current = current.getParent();
                        break;
                }
            }
        }
        return root;
    }
}
