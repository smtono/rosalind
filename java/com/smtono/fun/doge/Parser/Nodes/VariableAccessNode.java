package com.smtono.fun.doge.Parser.Nodes;

import com.smtono.fun.doge.Lexer.Token.Token;

public class VariableAccessNode extends Node {
    // ATTRIBUTES
    private final Token token;

    // CONSTRUCTOR
    public VariableAccessNode(Token token) {
        super(NodeType.VARIABLE);
        this.token = token;
    }

    // ACCESSORS
    @Override
    public Token getToken() { return token; }

    @Override
    public String toString() {
        return token.toString();
    }
}