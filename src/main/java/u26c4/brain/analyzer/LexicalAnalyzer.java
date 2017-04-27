package u26c4.brain.analyzer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import u26c4.brain.Operator;
import u26c4.builders.BrainExceptionBuilder;
import u26c4.models.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
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
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (Utils.isNumeric(token)) {
                createNumericNode(root, token);
            } else {
                Operator operator = Operator.findBySymbol(token);
                switch (operator) {
                    case OPENING_BRACKET:
                        createSubTree(exceptionBuilder, root, tokenizer, new ArrayDeque<>());
                        break;
                    case PLUS:
                    case MINUS:
                    case DIVIDE:
                    case MULTIPLY:
                    case DEGREE:
                        if (StringUtils.isBlank(root.getToken())) {
                            root.setToken(token);
                        } else {
                            root = createOperationNode(root, operator);
                        }
                        break;
                    case CLOSING_BRACKET:
                        break;
                }
            }
        }
        return findRealRoot(root);
    }

    private static void createSubTree(BrainExceptionBuilder exceptionBuilder, Tree root, StringTokenizer tokenizer, Deque<String> subExpr) {
        if (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            while (tokenizer.hasMoreTokens() && !nextToken.equals(Operator.CLOSING_BRACKET.getSymbol())) {
                subExpr.push(nextToken);
                nextToken = tokenizer.nextToken();
            }

            StringBuilder expr = new StringBuilder();
            int i = 1;
            int size = subExpr.size();
            while (i <= size) {
                String str = subExpr.removeLast();
                if (str.equals(Operator.OPENING_BRACKET.getSymbol())) {
                    subExpr.push(str);
                } else {
                    expr.append(str);
                }
                i++;
            }

            Tree tree = buildParseTree(Utils.formatExpression(expr.toString()), exceptionBuilder);
            tree.setParent(root);

            if (tree.getRight() == null) {
                root.getRight().setParent(tree);
                tree.setRight(root.getRight());
                root.setRight(tree);
            } else {
                if (root.getLeft() != null) {
                    root.setRight(tree);
                } else {
                    root.setLeft(tree);
                }
            }

            if (CollectionUtils.isNotEmpty(subExpr)) {
                subExpr.pop();
                createSubTree(exceptionBuilder, root, tokenizer, subExpr);
            }
        }
    }

    private static Tree createOperationNode(Tree root, Operator operator) {
        Tree op = new Tree(operator.getSymbol());
        if (operator.getPriority() > Operator.findBySymbol(root.getToken()).getPriority()) { // FIXME: 25.04.2017 поправить порядок слева направо
            op.setLeft(root.getRight());
            root.setRight(op);
            op.setParent(root);
        } else {
            op.setLeft(root);
            root.setParent(op);
        }
        return op;
    }

    private static void createNumericNode(Tree root, String token) {
        Tree num = new Tree(token);
        if (root.getLeft() == null) {
            root.setLeft(num);
            root.getLeft().setParent(root);
        } else if (root.getRight() == null) {
            root.setRight(num);
            root.getRight().setParent(root);
        }
    }

    private static Tree findRealRoot(Tree root) {
        if (root.getParent() == null) {
            return root;
        }
        return findRealRoot(root.getParent());
    }
}
