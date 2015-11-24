package com.olivierboucher.inf1018.custom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFile implements IHasClasses, IHasInterfaces {
    private File handle;
    private List<JavaClass> classes;
    private List<JavaInterface> interfaces;
    private List<JavaImport> imports;
    private String pkg;
    
    public JavaFile(File file){
	this.handle = file;
	this.pkg = "";
	this.classes = new ArrayList<>();
	this.interfaces = new ArrayList<>();
	this.imports = new ArrayList<>();
    }
    
    public File getFileHandle(){
	return this.handle;
    }
    
    public List<JavaClass> getClasses(){
	return this.classes;
    }
    
    public List<JavaInterface> getInterfaces(){
	return this.interfaces;
    }
    
    public List<JavaImport> getImports(){
	return this.imports;
    }
    
    public String getPackage(){
	return this.pkg;
    }
    
    public void addImport(JavaImport i){
	this.imports.add(i);
    }
    
    public void addInterface(JavaInterface i){
	this.interfaces.add(i);
    }
    
    public void addClass(JavaClass c){
	this.classes.add(c);
    }
    
    public void setPackage(String pkg){
	this.pkg = pkg;
    }
    
}
