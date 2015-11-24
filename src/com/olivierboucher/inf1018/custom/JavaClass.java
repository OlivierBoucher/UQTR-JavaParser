package com.olivierboucher.inf1018.custom;

import java.util.ArrayList;
import java.util.List;

public class JavaClass implements IHasClasses, IHasAttributes, IHasInterfaces, IHasMethods {
    private JavaClass parent;
    private JavaFile file;
    private List<JavaClass> classes;
    private List<JavaInterface> interfaces;
    private List<JavaAttribute> attributes;
    private List<JavaMethod> methods;
    private String name;
    private String superClassName;
    private int modifiers;
    
    public JavaClass(String name){
	this.name = name;
	this.classes = new ArrayList<>();
	this.interfaces = new ArrayList<>();
	this.attributes = new ArrayList<>();
	this.methods = new ArrayList<>();
    }
    
    public Boolean hasSuperClass(){
	return this.parent != null;
    }
    
    public void setParent(JavaClass parent) {
        this.parent = parent;
    }
    public void setFile(JavaFile file) {
        this.file = file;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
    @Override
    public void addMethod(JavaMethod m) {
	this.methods.add(m);
    }
    @Override
    public void addInterface(JavaInterface i) {
	this.interfaces.add(i);
    }
    @Override
    public void addAttribute(JavaAttribute a) {
	this.attributes.add(a);
    }
    @Override
    public void addClass(JavaClass c) {
	this.classes.add(c);
    }
}
