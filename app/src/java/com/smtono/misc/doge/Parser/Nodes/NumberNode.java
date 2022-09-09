package com.smtono.fun.doge.Parser.Nodes;

import com.smtono.fun.doge.Lexer.Token.Token;

/**
 *
 */
public class NumberNode extends Node {
    // NUMBER NODE ATTRIBUTES
    private final Token token;

    // CONSTRUCTOR
    public NumberNode(Token token) {
        super(token);
        this.token = token;
    }

    // ACCESSORS
    public Token getToken() { return token; }

    @Override
    public String toString() {
        return token.toString();
    }
}
