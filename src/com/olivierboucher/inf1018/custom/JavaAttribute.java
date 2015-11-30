package com.olivierboucher.inf1018.custom;

public class JavaAttribute {
    private String name;
    private String type;
    private int modifiers;
    
    public JavaAttribute(){}
    
    public JavaAttribute(String name, String type, int modifiers) {
	this.name = name;
	this.type = type;
	this.modifiers = modifiers;
    }
    
    public JavaAttribute(String name){
	this.name = name;
    }
    
    public boolean isPrimitive(){
	return (type.equals("boolean") || type.equals("char") || type.equals("byte") || type.equals("short") || type.equals("int") || type.equals("long") || type.equals("double") || type.equals("float"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
}
