package com.smtono.fun.doge.Lexer;

import com.smtono.fun.doge.Lexer.Token.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    // LEXER ATTRIBUTES
    private final String text;
    private int currentPosition;
    private char currentCharacter;

    // CONSTRUCTOR
    public Lexer(String text) {
        this.text = text;
        currentPosition = -1;
        currentCharacter = 0; // Default null character
        advance();
    }

    // HELPER METHODS
    /** Goes to the next character in the text */
    private void advance() {
        currentPosition += 1;
        if (this.currentPosition < text.length()) {
            this.currentCharacter = text.charAt(currentPosition);
        }
        else {
            this.currentCharacter = 0; // Default null character
        }
    }

    List<Token> tokens = new ArrayList<>();

    public List<Token> makeTokens() {
        while (currentCharacter != 0) {
            switch (currentCharacter) {
                // checking whitespace characters
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    advance(); // skip whitespace characters
                    break;

                // checking digits
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    tokens.add(makeNumber());
                    break;

                // checking for characters which will make keywords
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    tokens.add(makeLexeme());
                    break;

                // checking for strings
                case '\"':
                    advance();
                    tokens.add(makeString());
                    break;

                // checking capital letters which won't be allowed
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    System.out.println("pls nao yellingg!!");
                    break;
                default:
                    advance();
                    return new ArrayList<>();
            }
        }

        return tokens;
    }

    // CONSTANT FOR PARSING NUMBERS
    String DIGITS = "0123456789";

    /** Generates a number from a given input of digits */
    public Token makeNumber() {
        StringBuilder number = new StringBuilder(); // check to see if possible to parse into int at end

        // While the current character is not null, and is also a digit
        while (currentCharacter != 0 && DIGITS.contains(Character.toString(currentCharacter))) {
            number.append(currentCharacter);
            advance();
        }
        return new Token(TokenType.INTEGER, number.toString());
    }

    // CONSTANTS FOR FINDING KEYWORDS
    List<String> keywords = Arrays.asList(
            "bekom", "wow" // variable instantiation
    );
    List<String> arithmeticOperations = Arrays.asList(
            "add", "sub", "timez", "divid"// arithmetic
    );

    /** Generates a keyword from a given input of characters */
    public Token makeLexeme() {
        StringBuilder lexeme = new StringBuilder(); // compare to keywords above at the end

        // While the current character is not null
        while (currentCharacter != 0) {
            switch (currentCharacter) {
                // checking capital letters which won't be allowed
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    advance();
                    System.out.println("pls nao yellingg!!");
                    break;

                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    lexeme.append(currentCharacter);
                    advance();
                    break;

                // White space will be the terminator character
                case ' ':
                case '\t':
                case '\r':
                case '\n': {
                    advance();
                    List<TokenType> tokenTypes = Arrays.asList(TokenType.values());
                    List<ArithmeticOperation> arithmeticOperationsTokens = Arrays.asList(ArithmeticOperation.values());

                    if (keywords.contains(lexeme.toString())) { // if the lexeme is a keyword, then it will be that keyword
                        for (int i = 0; i < keywords.size(); i++) { // iterating through all keywords in the list
                            if (keywords.get(i).equals(lexeme.toString())) { // if a keyword matches the lexeme then we get that token
                                for (int j = 0; j < tokenTypes.size(); j++) { // iterate through all the tokens
                                    if (keywords.get(i).equals(tokenTypes.get(j).getKeyword())) { // if the keyword equals the matching token, return that token
                                        return new Token(tokenTypes.get(j)); // return that token
                                    }
                                }
                            }
                        }
                    }
                    else if (arithmeticOperations.contains(lexeme.toString())) { // if the lexeme is a keyword, then it will be that keyword
                        for (int i = 0; i < arithmeticOperations.size(); i++) { // iterating through all keywords in the list
                            if (arithmeticOperations.get(i).equals(lexeme.toString())) { // if a keyword matches the lexeme then we get that token
                                for (int j = 0; j < arithmeticOperationsTokens.size(); j++) { // iterate through all the tokens
                                    if (arithmeticOperations.get(i).equals(arithmeticOperationsTokens.get(j).getKeyword())) { // if the keyword equals the matching token, return that token
                                        return new Token(TokenType.OPERATOR, arithmeticOperationsTokens.get(j).getKeyword()); // return that token
                                    }
                                }
                            }
                        }
                    }
                    else { // else, it must be an identifier. (variable name)
                        return new Token(TokenType.IDENTIFIER, lexeme.toString());
                    }
                }
                break;
                default:
                    advance();
                    break;
            }
        }
        return new Token(TokenType.IDENTIFIER, lexeme.toString());
    }

    public Token makeString() {
        StringBuilder string = new StringBuilder();

        // While the current character is not null
        while (currentCharacter != 0) {
            switch (currentCharacter) {
                // checking capital letters which won't be allowed
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    advance();
                    System.out.println("pls nao yellingg!!");
                    break;

                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case ' ':
                case '\t':
                case '\r':
                case '\n': {
                    string.append(currentCharacter);
                    advance();
                    break;
                }
                // Quotes will be the terminating character
                case '\"': {
                    advance();
                    return new Token(TokenType.STRING, string.toString());
                }
                default:
                    advance();
                    break;
            }
        }
        return new Token(TokenType.NONE);
    }

    /** Runs the tokenizer on the text input for debugging purposes */
    public static List<Token> run(String code) {
        Lexer lexer = new Lexer(code);

        List<Token> tokens = lexer.makeTokens();
        StringBuilder output = new StringBuilder();

        output.append("(");
        tokens.forEach(token -> {
            if (token.getValue() != null) {
                output.append(token).append(", ");
            }
            else {
                output.append(token.getType()).append(", ");
            }
        });
        output.append(")");
        return tokens;
    }
}
