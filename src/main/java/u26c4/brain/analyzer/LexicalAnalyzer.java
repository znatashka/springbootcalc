package u26c4.brain.analyzer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import u26c4.brain.Operator;
import u26c4.builders.BrainExceptionBuilder;
import u26c4.models.Tree;

import java.util.Stack;
import java.util.StringTokenizer;

class LexicalAnalyzer {

    static Tree evaluate(String expression) {
        BrainExceptionBuilder exceptionBuilder = new BrainExceptionBuilder();

        Tree root = buildParseTree(expression, exceptionBuilder);

        if (exceptionBuilder.hasErrors()) {
            throw exceptionBuilder
                    .buildHtml()
                    .build();
        }
        return root;
    }

    private static Tree buildParseTree(String expression, BrainExceptionBuilder exceptionBuilder) {
        Tree root = new Tree();
        Stack<String> lastTokenStack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            String lastToken = getLastToken(lastTokenStack);
            if (Utils.isNumeric(token)) {
                lastTokenStack.push(token);
                if (Utils.isNumeric(lastToken)) {
                    exceptionBuilder.buildError(String.format("Double numeric: %s %s", lastToken, token));
                    continue;
                }
                Tree num = new Tree(token);
                if (root.getLeft() == null) {
                    root.setLeft(num);
                    root.getLeft().setParent(root);
                } else if (root.getRight() == null) {
                    root.setRight(num);
                    root.getRight().setParent(root);
                }
                // если два числа через пробел, то ошибка
            } else {
                Operator operator = Operator.findBySymbol(token);
                switch (operator) {
                    case OPENING_BRACKET:
                        break;
                    case PLUS:
                    case MINUS:
                    case DIVIDE:
                    case MULTIPLY:
                    case DEGREE:
                        lastTokenStack.push(token);
                        if (Operator.isOperator(lastToken)) {
                            exceptionBuilder.buildError(String.format("Double operator: %s %s", lastToken, token));
                            continue;
                        }
                        if (StringUtils.isBlank(root.getToken())) {
                            root.setToken(token);
                        } else {
                            Tree op = new Tree(token);
                            if (operator.getPriority() > Operator.findBySymbol(root.getToken()).getPriority()) {
                                op.setLeft(root.getRight());
                                root.setRight(op);
                                op.setParent(root);
                            } else {
                                op.setLeft(root);
                                root.setParent(op);
                            }
                            root = op;
                        }
                        // если два оператора через пробел, то ошибка
                        break;
                    case CLOSING_BRACKET:
                        break;
                }
            }
        }
        return findRoot(root);
    }

    private static String getLastToken(Stack<String> lastTokenStack) {
        String lastToken = null;
        if (CollectionUtils.isNotEmpty(lastTokenStack)) {
            lastToken = lastTokenStack.pop();
        }
        return lastToken;
    }

    private static Tree findRoot(Tree root) {
        if (root.getParent() == null) {
            return root;
        }
        return findRoot(root.getParent());
    }
}
