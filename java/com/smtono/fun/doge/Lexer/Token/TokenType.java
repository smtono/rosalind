package com.smtono.fun.doge.Lexer.Token;

// TODO: Split into more specific token classes (and make generic interface)
// TOKEN TYPES
public enum TokenType {
    VARIABLE("wow"),
    VARIABLE_INSTANTIATION("bekom"),

    IDENTIFIER(""),
    COMMENT_START("quite!!"),
    COMMENT_END("!!"),

    OPERATOR(""),

    INTEGER(""),
    FLOAT(""),
    EQUAL("iz"),

    STRING(""),

    UNEXPECTED(""),
    NONE("");


    private final String keyword;
    TokenType(String keyword) { this.keyword = keyword; }
    public String getKeyword() { return keyword; }
}