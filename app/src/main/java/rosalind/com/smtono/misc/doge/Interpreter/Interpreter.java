package com.smtono.fun.doge.Interpreter;

import com.smtono.fun.doge.Interpreter.Values.GenericNumber;
import com.smtono.fun.doge.Interpreter.Values.GenericString;
import com.smtono.fun.doge.Lexer.Token.TokenType;
import com.smtono.fun.doge.Parser.Nodes.*;
import com.smtono.fun.doge.Parser.Parser;

public class Interpreter {
    public static VariableDictionary variables = new VariableDictionary(); // this might be bad practice oh well .-.

    // VISIT METHODS
    /** "Visits" each node in the expression and evaluates as necessary with the corresponding method call */
    public static String visit(Node node) {
        try {
            switch (node.getNodeType()) {
                case NUMBER:
                    return visitNumberNode((NumberNode) node).toString();
                case ARITHMETIC_OPERATION:
                    if (node instanceof ArithmeticOperationNode) {
                        return visitArithmeticOperationNode((ArithmeticOperationNode) node).toString();
                    }
                    else if (node instanceof ExpressionNode) {
                        return visitExpressionNode((ExpressionNode) node).toString();
                    }
                    break;
                case STRING:
                    if (node instanceof StringConcatenationNode) {
                        return visitStringConcatenationNode((StringConcatenationNode) node).toString();
                    }
                    else if (node instanceof StringNode) {
                        return visitStringNode((StringNode) node).toString();
                    }
                    break;
                case VARIABLE:
                    visitVariableAssignmentNode((VariableAssignmentNode) node);
                    break;
                case IDENTIFIER:
                    visitVariableAccessNode(node);
                case NONE:
                default:
                    noVisit();
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
        return "";
    }

    public static void noVisit() {
        // TODO: implement no visit error
        System.out.println("Error: No visit");
    }

    public static GenericNumber visitArithmeticOperationNode(ArithmeticOperationNode node) {
        if (node.getLeft().getNodeType().equals(NodeType.VARIABLE)) {
            String variableValue = visitVariableAccessNode(node.getLeft());
            node.getLeft().getToken().setValue(variableValue);
        }
        if (node.getRight().getNodeType().equals(NodeType.VARIABLE)) {
            String variableValue = visitVariableAccessNode(node.getRight());
            node.getRight().getToken().setValue(variableValue);
        }
        return node.evaluateExpression();
    }

    public static GenericNumber visitExpressionNode(ExpressionNode node) {
        if (node.getLeft().getNodeType().equals(NodeType.VARIABLE)) {
            String variableValue = visitVariableAccessNode(node.getLeft());
            node.getLeft().getToken().setValue(variableValue);
        }
        if (node.getRight().getNodeType().equals(NodeType.VARIABLE)) {
            String variableValue = visitVariableAccessNode(node.getRight());
            node.getRight().getToken().setValue(variableValue);
        }
        return node.evaluateExpression();
    }

    public static GenericString visitStringConcatenationNode(StringConcatenationNode node) {
        return node.concatenateStrings();
    }

    public static GenericNumber visitNumberNode(NumberNode node) {
        return new GenericNumber(Integer.parseInt(String.valueOf(node.getToken().getValue())));
    }

    public static GenericString visitStringNode(StringNode node) {
        return new GenericString(node.getToken().getValue());
    }

    /** Returns the value of the variable assignment node associated with the variable name passed */
    public static void visitVariableAssignmentNode(VariableAssignmentNode node) {
        String variableName = node.getToken().getValue();
        String value = "";

        if (node.getValue().getToken().getType().equals(TokenType.INTEGER)) {
            value = node.getValue().evaluateExpression().toString();
        }
        else if (node.getValue().getNodeType().equals(NodeType.ARITHMETIC_OPERATION)) {
            value = node.getValue().evaluateExpression().toString();
        }
        else if (node.getValue().getNodeType().equals(NodeType.STRING)) {
            value = node.evaluateString().toString();
        }

        variables.addVariable(variableName, value); // add new variable to dictionary
    }

    /** Returns the value of the variable given */
    public static String visitVariableAccessNode(Node node) {
        String value = variables.getValue(node.getToken().getValue());

        if (value.equals("")) { // if value was not found print error
            System.out.println("Variable does not exist");
            return "";
        }
        else {
            return value;
        }
    }

    // TODO: simplify, put run method in one place
    public static String run(String code) {
        Node node = Parser.run(code);
        return visit(node);
    }
}