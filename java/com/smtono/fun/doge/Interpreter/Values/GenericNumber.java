package com.smtono.fun.doge.Interpreter.Values;

public class GenericNumber {
    private final int value;

    public GenericNumber() {this.value = 0;}
    public GenericNumber(int value) {
        this.value = value;
    }

    // ARITHMETIC OPERATIONS
    public GenericNumber plus(int other) {
        return new GenericNumber(value + other);
    }

    public GenericNumber minus(int other) {
        return new GenericNumber(value - other);
    }

    public GenericNumber times(int other) {
        return new GenericNumber(value * other);
    }

    public GenericNumber dividedBy(int other) {
        return new GenericNumber(value / other);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
