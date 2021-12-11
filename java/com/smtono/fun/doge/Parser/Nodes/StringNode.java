package com.smtono.fun.doge.Parser.Nodes;

import com.smtono.fun.doge.Lexer.Token.Token;

public class StringNode extends Node {
    // STRING NODE ATTRIBUTES
    private final Token token;

    // CONSTRUCTOR
    public StringNode(Token token) {
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
