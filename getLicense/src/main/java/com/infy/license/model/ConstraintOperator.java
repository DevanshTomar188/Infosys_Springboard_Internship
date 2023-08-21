package com.infy.license.model;
public enum ConstraintOperator {

    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<="),
    NEW_OPERATOR("<");

    private final String symbol;

    ConstraintOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static ConstraintOperator fromSymbol(String symbol) {
        for (ConstraintOperator operator : values()) {
            if (operator.getSymbol().equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Invalid symbol: " + symbol);
    }
}
