package com.smtono.fun.doge.Interpreter;

import java.util.HashMap;

public class VariableDictionary {
    private HashMap<String, String> local;
    private HashMap<String, String> global;

    public VariableDictionary() {
        local = new HashMap<>();
        global = new HashMap<>();
    }

    /** Gets the value of the given variable name */
    public String getValue(String variableName) {
        // TODO: make VariableAccessNode toString return it's value (or change it to the get method in that class)
        String value;

       value = local.get(variableName);

       // if value is still not a value, then check the global variables
       if(value.equals("")) {
           value = global.get(variableName);
       }

       return value;
    }
    
    public void addVariable(String variableName, String value) {
        local.put(variableName, value);
    }

    /** Sets up a new variable with the given name and value */
    public void setValue(String variableName, String value) {
        local.replace(variableName, local.get(variableName), value); // replaces old value with new value
    }

    public void removeVariable(String variableName) {
        local.remove(variableName);
    }

}
