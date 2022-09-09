package com.smtono.fun.doge.Parser.Nodes;

import com.smtono.fun.doge.Interpreter.Values.GenericNumber;
import com.smtono.fun.doge.Lexer.Token.ArithmeticOperation;
import com.smtono.fun.doge.Lexer.Token.Token;
import com.smtono.fun.doge.Lexer.Token.TokenType;

public class ExpressionNode extends Node {
    // ATTRIBUTES
    private final Node left;
    private final Token operation;
    private final Node right;

    // CONSTRUCTOR
    public ExpressionNode() {
        this.left = new Node();
        this.operation = new Token(TokenType.NONE);
        this.right = new Node();
    }

    public ExpressionNode(Node left, Token operation, Node right) {
        super(operation);
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    // ACCESSORS
    public Node getLeft() { return left; }
    public Token getOperation() { return operation; }
    public Node getRight() { return right; }

    // HELPER METHODS
    public GenericNumber evaluateExpression() {
        GenericNumber left = new GenericNumber(Integer.parseInt(String.valueOf(getLeft().toString())));
        int right = Integer.parseInt(String.valueOf(getRight().toString()));
        
        switch (ArithmeticOperation.getArithmeticOperation(operation.getValue())) {
            case MULTIPLY:
                return left.times(right);
            case DIVIDE:
                return left.dividedBy(right);
        }
        return new GenericNumber();
    }

    // TODO: fix to show whole operation (left and right nodes)
    @Override
    public String toString() { return '(' + left.toString() + ' ' + operation.toString() + ' ' + right.toString() + ')'; }
}
