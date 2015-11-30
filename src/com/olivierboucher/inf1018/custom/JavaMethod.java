package com.olivierboucher.inf1018.custom;

import java.util.ArrayList;
import java.util.List;

public class JavaMethod implements IHasAttributes, IHasMethodCalls {
    private String name;
    private String returnType;
    private int modifiers;
    private List<JavaAttribute> parameters;
    private List<JavaAttribute> localVariables;
    private List<JavaMethodCall> methodCalls;
    
    public JavaMethod() {
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
	this.methodCalls = new ArrayList<>();
    }
    
    public JavaMethod(String name) {
	this.name = name;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
	this.methodCalls = new ArrayList<>();
    }
    
    public JavaMethod(String name, int modifiers){
	this.name = name;
	this.modifiers = modifiers;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
	this.methodCalls = new ArrayList<>();
    }
    
    public JavaMethod(String name, int modifiers, String returnType){
	this.name = name;
	this.modifiers = modifiers;
	this.returnType = returnType;
	this.parameters = new ArrayList<>();
	this.localVariables = new ArrayList<>();
	this.methodCalls = new ArrayList<>();
    }
    
    @Override
    public void addAttribute(JavaAttribute a) {
	this.localVariables.add(a);
    }
    
    @Override
    public void addMethodCall(JavaMethodCall m) {
	this.methodCalls.add(m);
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

    public List<JavaAttribute> getParameters() {
        return parameters;
    }

    public void setParameters(List<JavaAttribute> parameters) {
        this.parameters = parameters;
    }

    public List<JavaAttribute> getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(List<JavaAttribute> localVariables) {
        this.localVariables = localVariables;
    }

    public List<JavaMethodCall> getMethodCalls() {
        return methodCalls;
    }

    public void setMethodCalls(List<JavaMethodCall> methodCalls) {
        this.methodCalls = methodCalls;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public int getModifiers() {
        return modifiers;
    }
}
