package com.smtono.fun.doge.Lexer.Token;

public enum ArithmeticOperation {
    ADD("add"),
    SUBTRACT("sub"),
    MULTIPLY("timez"),
    DIVIDE("divid"),
    NONE("");
    private final String keyword;

    ArithmeticOperation(String keyword) { this.keyword = keyword; }

    public String getKeyword() { return keyword; }

    /** Returns the ArithmeticOperation enum for a given string */
    public static ArithmeticOperation getArithmeticOperation(String operation) {
        ArithmeticOperation[] arithmeticOperations = ArithmeticOperation.values();
        ArithmeticOperation arithmeticOperation = NONE;
        for (ArithmeticOperation value : arithmeticOperations) {
            if (value.getKeyword().equals(operation)) {
                return value;
            }
        }
        return NONE;
    }
}