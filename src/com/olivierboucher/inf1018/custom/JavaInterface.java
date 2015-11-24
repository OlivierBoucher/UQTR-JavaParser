package com.olivierboucher.inf1018.custom;

import java.util.ArrayList;
import java.util.List;

public class JavaInterface implements IHasAttributes, IHasMethods, IHasInterfaces {
    private String name;
    private List<JavaInterface> interfaces;
    private List<JavaAttribute> attributes;
    private List<JavaMethod> methods;
    private int modifiers;
    
    public JavaInterface(String name) {
	this.name = name;
	this.attributes = new ArrayList<>();
	this.methods = new ArrayList<>();
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
    @Override
    public void addMethod(JavaMethod m) {
	this.methods.add(m);
    }
    @Override
    public void addAttribute(JavaAttribute a) {
	this.attributes.add(a);
    }

    @Override
    public void addInterface(JavaInterface i) {
	this.interfaces.add(i);
    }
}
