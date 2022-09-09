package com.smtono.fun.doge.Interpreter.Values;

public class GenericString {
    private final String value;

    public GenericString() { this.value = ""; }
    public GenericString(String value) { this.value = value; }

    // STRING OPERATIONS
    public GenericString concatenate(GenericString other) { return new GenericString(value + other); }

    @Override
    public String toString() { return value; }
}
