package com.smtono.fun.doge.Lexer.Token;

public class Token {
    // TOKEN ATTRIBUTES
    private final TokenType type;
    private String value;

    // CONSTRUCTORS
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    public Token(TokenType type) {
        this.type = type;
        this.value = "";
    }

    // ACCESSORS/MUTATORS
    public TokenType getType() { return type; }
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    
    // HELPER METHODS
    /** Returns if a token type is an ArithmeticOperation or not */
    public boolean isArithmeticOperation() {
        ArithmeticOperation[] operations = ArithmeticOperation.values();

        for (ArithmeticOperation operation : operations) {
            if (operation.getKeyword().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public boolean isNumber() {
        return type == TokenType.INTEGER || type == TokenType.FLOAT;
    }

    @Override
    public String toString() { return value; }
}
