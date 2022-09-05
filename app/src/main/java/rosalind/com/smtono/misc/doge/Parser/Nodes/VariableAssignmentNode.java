package com.smtono.fun.doge.Parser.Nodes;

import com.smtono.fun.doge.Interpreter.Values.GenericNumber;
import com.smtono.fun.doge.Interpreter.Values.GenericString;
import com.smtono.fun.doge.Lexer.Token.Token;

public class VariableAssignmentNode extends Node {
    // ATTRIBUTES
    private final Token name;
    private final Node value;

    // CONSTRUCTOR
    public VariableAssignmentNode(Token name, Node value) {
        super(name);
        this.name = name;
        this.value = value;
    }

    // ACCESSORS
    public Token getName() { return name; }
    public Node getValue() { return value; }

    @Override
    public GenericNumber evaluateExpression() {
       return getValue().evaluateExpression();
    }

    public GenericString evaluateString() {
        return new GenericString(getValue().getToken().getValue());
    }

    @Override
    public String toString() { return value.toString(); }
}
