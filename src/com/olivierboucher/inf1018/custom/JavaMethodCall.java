package com.olivierboucher.inf1018.custom;

public class JavaMethodCall {
    private String rawCall;
    private String[] splittedCall;
    
    public JavaMethodCall(String rawCall){
	this.rawCall = rawCall;
	this.splittedCall = rawCall.split("[.]");
    }

    public String getRawCall() {
        return rawCall;
    }
    
    public String getObj(){
	if(splittedCall.length > 0){
	    return splittedCall[0];
	}
	return "";
    }

    public String getCall(int index) {
	if(index < splittedCall.length){
	    return splittedCall[index];
	}
	return "";
    }
    
    public int getLenght(){
	return splittedCall.length;
    }
}
