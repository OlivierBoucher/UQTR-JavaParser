package com.olivierboucher.inf1018.custom;

import java.util.ArrayList;
import java.util.List;

public class JavaMethod implements IHasAttributes {
    private String name;
    private String returnType;
    private int modifiers;
    private List<JavaAttribute> parameters;
    private List<JavaAttribute> localVariables;
    
    public JavaMethod() {
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
    }
    
    public JavaMethod(String name) {
	this.name = name;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
    }
    
    public JavaMethod(String name, int modifiers){
	this.name = name;
	this.modifiers = modifiers;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
    }
    
    public JavaMethod(String name, int modifiers, String returnType){
	this.name = name;
	this.modifiers = modifiers;
	this.returnType = returnType;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
    }
    
    @Override
    public void addAttribute(JavaAttribute a) {
	this.localVariables.add(a);
    }
    
    public void addParameters(List<JavaAttribute> l){
	this.parameters.addAll(l);
    }
    
    public void addParameter(JavaAttribute a) {
	this.parameters.add(a);
    }
    
    public void setName(String name) {
        this.name = name;
    }


    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }


    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
}
