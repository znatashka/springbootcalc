package u26c4.brain;

import lombok.Getter;
import u26c4.builders.BrainExceptionBuilder;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public enum Operator implements DoubleBinaryOperator {

    OPENING_BRACKET("(", 0) {
        @Override
        public double applyAsDouble(double left, double right) {
            return 0;
        }
    },
    CLOSING_BRACKET(")", 0) {
        @Override
        public double applyAsDouble(double left, double right) {
            return 0;
        }
    },
    PLUS("+", 1) {
        @Override
        public double applyAsDouble(double left, double right) {
            return left + right;
        }
    },
    MINUS("-", 1) {
        @Override
        public double applyAsDouble(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY("*", 2) {
        @Override
        public double applyAsDouble(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/", 2) {
        @Override
        public double applyAsDouble(double left, double right) {
            if (right == 0.0) {
                throw new BrainExceptionBuilder()
                        .buildError("Division by zero")
                        .buildHtml()
                        .build();
            }
            return left / right;
        }
    },
    DEGREE("^", 3) {
        @Override
        public double applyAsDouble(double left, double right) {
            if (right < 0.0) {
                throw new BrainExceptionBuilder()
                        .buildError("Negative exponent")
                        .buildHtml()
                        .build();
            }
            return Math.pow(left, right);
        }
    };

    @Getter
    private int priority;
    @Getter
    private String symbol;

    Operator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator findBySymbol(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(operator -> operator.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() ->
                        new BrainExceptionBuilder()
                                .buildError("Unknown symbol: `" + symbol + "`")
                                .buildHtml()
                                .build());
    }

    public static boolean isOperator(String symbol) {
        return Arrays.stream(Operator.values())
                .anyMatch(operator -> operator.symbol.equals(symbol));
    }

    @Override
    public String toString() {
        return symbol;
    }
}