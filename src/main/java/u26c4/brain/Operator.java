package u26c4.brain;

import lombok.Getter;
import u26c4.brain.exception.BrainException;

import java.util.Arrays;

/**
 * Created by Nataliia_Zolotovitck on 14.04.2017.
 */
public enum Operator {
    OPENING_BRACKET("(", 0),
    CLOSING_BRACKET(")", 0),
    PLUS("+", 1),
    MINUS("-", 1),
    MULTIPLY("*", 2),
    DIVIDE("/", 2),
    DEGREE("^", 3);

    @Getter
    private int priority;
    @Getter
    private String symbol;

    Operator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator findBySymbol(String symbol) {
        return Arrays.stream(Operator.values()).
                filter(operator -> operator.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new BrainException("Unknown symbol: `" + symbol + "`"));
    }

    @Override
    public String toString() {
        return symbol;
    }
}