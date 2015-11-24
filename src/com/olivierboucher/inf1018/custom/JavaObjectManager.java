package com.olivierboucher.inf1018.custom;

import java.util.Stack;

public class JavaObjectManager extends Stack<Object> {
    
    public void addToCurrentObject(JavaAttribute a){
	if(peek() instanceof IHasAttributes){
	    IHasAttributes o = (IHasAttributes) peek();
	    o.addAttribute(a);
	}
	push(a);
    }
    
    public void addToCurrentObject(JavaClass c){
	if(peek() instanceof IHasClasses){
	    IHasClasses o = (IHasClasses) peek();
	    o.addClass(c);
	}
	push(c);
    }
    
    public void addToCurrentObject(JavaMethod m){
	if(peek() instanceof IHasMethods){
	    IHasMethods o = (IHasMethods) peek();
	    o.addMethod(m);
	}
	push(m);
    }
    
    public void addToCurrentObject(JavaInterface i){
	if(peek() instanceof IHasInterfaces){
	    IHasInterfaces o = (IHasInterfaces) peek();
	    o.addInterface(i);
	}
	push(i);
    }
}
