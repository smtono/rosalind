package com.smtono.fun.doge.Parser;

import com.smtono.fun.doge.Interpreter.Interpreter;
import com.smtono.fun.doge.Lexer.Lexer;
import com.smtono.fun.doge.Lexer.Token.ArithmeticOperation;
import com.smtono.fun.doge.Lexer.Token.Token;
import com.smtono.fun.doge.Lexer.Token.TokenType;
import com.smtono.fun.doge.Parser.Nodes.*;

import java.util.List;

/**
 *
 */
public class Parser {
    // ATTRIBUTES
    private final List<Token> tokens;
    private int currentPosition;
    private Token currentToken;

    // CONSTRUCTOR
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        currentPosition = -1;
        currentToken = null;
        advance();
    }

    // HELPER METHODS
    /** Goes to the next token in the list of tokens */
    private void advance() {
        currentPosition += 1;

        if (currentPosition < tokens.size()) {
            currentToken = tokens.get(currentPosition);
        }
    }

    /** Parses each token to different terms/expressions to create an AST (abstract syntax tree) */
    private Node parse() {
        if (tokens.size() == 1) { // if there is just 1 token skip to getAtom
            return getAtom();
        }
        else {
            return getExpression();
        }
    }

    /** Returns node type for given token */
    private Node getAtom() {
        Token token = currentToken;

        switch (token.getType()) {
            case INTEGER:
            case FLOAT:
                advance();
                return new NumberNode(token);
            case STRING:
                advance();
                return new StringNode(token);
            case IDENTIFIER:
                advance();
                return new VariableAccessNode(token);
            case NONE:
            case UNEXPECTED:
                return new Node();
        }
        return new Node();
    }

    private Node getTerm() {

        Node leftAtom = getAtom();

        // check if it is a string, if it is we know we need to concatenate
        if (leftAtom instanceof StringNode) {
            advance();
            if (currentPosition > tokens.size()) { // if we get to the end of the list we don't need to make an expression
                return leftAtom;
            }
            try {
                StringNode rightAtom = (StringNode) getAtom();
               return new StringConcatenationNode((StringNode) leftAtom, rightAtom);
            }
            catch (Exception e) {
                System.out.println("Invalid syntax");
            }
        }

        ArithmeticOperation operation = ArithmeticOperation.getArithmeticOperation(currentToken.getValue());

        // check if the current token is still an add or subtract token
        if (operation.equals(ArithmeticOperation.ADD) || operation.equals(ArithmeticOperation.SUBTRACT)) {
            NumberNode left = new NumberNode(new Token(TokenType.NONE));
            NumberNode right = new NumberNode(new Token(TokenType.NONE));

            Token operatorToken = currentToken;
            advance();

            // get the factor to add/subtract by
            Node rightAtom = getAtom();

            // check type of node for factors
            // left atom
            if (leftAtom instanceof VariableAccessNode) {
                left = new NumberNode(new Token(TokenType.INTEGER, Interpreter.variables.getValue(leftAtom.getToken().getValue())));
            }
            else if (leftAtom instanceof NumberNode) {
                left = (NumberNode) leftAtom;
            }

            // right atom
            if (rightAtom instanceof VariableAccessNode) {
                right = new NumberNode(new Token(TokenType.INTEGER, Interpreter.variables.getValue(rightAtom.getToken().getValue())));
            }
            else if (rightAtom instanceof NumberNode) {
                right = (NumberNode) rightAtom;
            }

            // create a new term
            try {
                return new ArithmeticOperationNode(left, operatorToken, right);
            }
            catch (Exception e) {
                System.out.println("Token does not exist");
            }
        }
        return leftAtom; // If the term did not return, then just return the left atom
    }

    private Node getExpression() {
        // Check for variable instantiation
        if (currentToken.getType().equals(TokenType.VARIABLE)) {
            advance();
            if(!currentToken.getType().equals(TokenType.IDENTIFIER)) {
                return new Node(new Token(TokenType.NONE)); // failure
            }
            else {
                Token varName = currentToken;
                advance();
                if(!currentToken.getType().equals(TokenType.VARIABLE_INSTANTIATION)) {
                    return new Node(new Token(TokenType.NONE)); // failure
                }
                else {
                    advance();
                    try {
                        Node expression = getExpression();
                        return new VariableAssignmentNode(varName, expression);
                    }
                    catch (Exception e) {
                        return new Node(new Token(TokenType.NONE)); // failure
                    }
                }
            }
        }

        if (currentToken.getType().equals(TokenType.STRING)) {
          return getTerm();
        }

        // Check for arithmetic expression
        Node leftTerm = getTerm();
        ArithmeticOperation operation = ArithmeticOperation.getArithmeticOperation(currentToken.getValue());

        // check if the current token is still a multiply or divide token
        if (operation.equals(ArithmeticOperation.MULTIPLY) || operation.equals(ArithmeticOperation.DIVIDE)) {
            Token operatorToken = currentToken;
            advance();

            // get the factor to multiply/divide by
            NumberNode rightFactor = (NumberNode) getAtom();
            // create a new expression
            return new ExpressionNode(leftTerm, operatorToken, rightFactor);
        }

        // if the if was entered, it means there was more to read, and an expression was made
        // if it didn't, then we can just return the term we already made
        return leftTerm;
    }

    // TODO: simplify, put run method in one place
    public static Node run(String code) {
        // Generate tokens
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.makeTokens();

        // Generate AST
        Parser parser = new Parser(tokens);
        //System.out.println(parser.parse());
        return parser.parse();
    }
}
