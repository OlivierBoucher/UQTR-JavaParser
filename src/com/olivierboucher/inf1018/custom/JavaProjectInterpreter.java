package com.olivierboucher.inf1018.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class JavaProjectInterpreter {
    private JavaObjectManager objectManager;
    public JavaProjectInterpreter(JavaObjectManager objectManager){
	this.objectManager = objectManager;
    }

    public void outputProjectStatisticsToConsole(){
	StringBuilder sbModifiers = new StringBuilder();
	StringBuilder sbVisibility = new StringBuilder();
	StringBuilder sbCoupling = new StringBuilder();
	StringBuilder sbCalls = new StringBuilder();
	StringBuilder sbInheritance = new StringBuilder();
	JavaModifierSet modifiersHelper = new JavaModifierSet();

	int fileCount = objectManager.size();
	for(int i = 0; i < fileCount; i++){
	    JavaFile file = (JavaFile) objectManager.get(i);

	    for(JavaClass currentClass : file.getClasses()){
		int publicAttributesCount = 0;
		int protectedAttributesCount = 0;
		int privateAttributesCount = 0;
		int primitiveAttributesCount = 0;
		int objectAttributesCount = 0;

		Map<String, Integer> coupling = new HashMap<String, Integer>();
		sbModifiers.append(String.format("\nAttribute distribution for class: %s\n", currentClass.getName()));
		sbVisibility.append(String.format("\nPermanent visibility for class: %s\n", currentClass.getName()));
		sbCoupling.append(String.format("\nCoupling for class: %s\n", currentClass.getName()));
		sbCalls.append(String.format("\nCalls graph for class: %s\n", currentClass.getName()));
		sbInheritance.append(String.format("\nClass: %s\n\tInherits from: %s\n\tAssociations:\n", currentClass.getName(), currentClass.hasSuperClass() ? currentClass.getSuperClassName() : "None"));

		List<String> listedAssociations = new ArrayList<>();
		List<String> listedVisibility = new ArrayList<>();
		for(JavaAttribute attr : currentClass.getAttributes()){

		    if(modifiersHelper.isPublic(attr.getModifiers())){
			publicAttributesCount++;
		    }
		    else if(modifiersHelper.isProtected(attr.getModifiers())){
			protectedAttributesCount++;
		    }
		    else if(modifiersHelper.isPrivate(attr.getModifiers())){
			privateAttributesCount++;
		    }

		    if(attr.isPrimitive()){
			primitiveAttributesCount++;
		    }
		    else {
			objectAttributesCount++;
			
			//NOTE(Olivier): We don't want self references either in the schemes or in the visibility list
			//		 Same goes for duplicates
			
			if(!listedVisibility.contains(attr.getType()) && !currentClass.getName().equalsIgnoreCase(attr.getType())){
			    listedVisibility.add(attr.getType());
			    sbVisibility.append(String.format("\t%s\n", attr.getType()));
			}
			if(!listedAssociations.contains(attr.getType()) && !currentClass.getName().equalsIgnoreCase(attr.getType())){
			    listedAssociations.add(attr.getType());
			    sbInheritance.append(String.format("\t\t%s\n", attr.getType()));
			}
			
		    }

		}
		//NOTE(Olivier): Question 1 & 2 are solved here.
		double total = privateAttributesCount + publicAttributesCount + protectedAttributesCount;
		if(total > 0) {
		    sbModifiers.append(String.format("\tPublic: %.2f%s\n", ((double) publicAttributesCount/total * 100), "%"));
		    sbModifiers.append(String.format("\tProtected: %.2f%s\n", ((double) protectedAttributesCount/total * 100), "%"));
		    sbModifiers.append(String.format("\tPrivate: %.2f%s\n", ((double) privateAttributesCount/total * 100), "%"));

		    total = primitiveAttributesCount + objectAttributesCount;

		    sbModifiers.append(String.format("\tSimple type: %.2f%s\n", ((double) primitiveAttributesCount/total * 100), "%"));
		    sbModifiers.append(String.format("\tReference type: %.2f%s\n", ((double) objectAttributesCount/total * 100), "%"));
		}
		else {
		    sbModifiers.append("\tNo Attributes\n");
		}
		//NOTE(Olivier): Method analysis will help us solve the rest of the questions
		for(JavaMethod method : currentClass.getMethods()){
		    sbCalls.append(String.format("\t%s:\n", method.getName()));
		    for(JavaMethodCall methodCall : method.getMethodCalls()){

			//NOTE(Olivier): Check if the calls origin is from a local variable,
			//		 Otherwise it checks for class attributes
			//System.out.println(String.format("- %s - DEBUG IN METHOD: %s RAWCALL %s",currentClass.getName(), method.getName(), methodCall.getRawCall()));
			JavaAttribute attr = method.getLocalVariables()
				.stream()
				.filter(v -> {
				    //System.out.println(String.format("- %s - DEBUG LOCAL V: %s ?= %s",currentClass.getName(), v.getName(), methodCall.getObj()));
				    return v.getName().equalsIgnoreCase(methodCall.getObj());
				})
				.findAny()
				.orElseGet(() -> {
				    return currentClass.getAttributes()
					    .stream()
					    .filter(a -> {
						//System.out.println(String.format("- %s - DEBUG CLASS ATTR: %s ?= %s",currentClass.getName(), a.getName(), methodCall.getObj()));
						return a.getName().equalsIgnoreCase(methodCall.getObj());
						})
					    .findAny()
					    .orElse(null);
				});
			JavaClass parent = currentClass.getParent();
			while(attr == null && parent != null){
			    attr = parent.getAttributes()
				    .stream()
				    .filter(a -> a.getName().equalsIgnoreCase(methodCall.getObj()))
				    .findAny()
				    .orElse(null);
			    parent = parent.getParent();
			}
			JavaClass otherClass = getClassByName(attr != null ? attr.getType() : methodCall.getObj());

			for(int callPos = 1; callPos < methodCall.getLenght(); callPos++){
			    if(otherClass == null) break;
			    
			    String call = methodCall.getCall(callPos);
			    if(call.contains("()")){
				sbCalls.append(String.format("\t\t%s.%s\n", otherClass.getName(), call));
		
				int couplingCount = coupling.getOrDefault(otherClass.getName(), 0);
				coupling.put(otherClass.getName(), couplingCount + 1);

				//NOTE(Olivier): Get return class of the function
				List<JavaMethod> methodList = otherClass.getMethods();
				otherClass = null;
				for(JavaMethod nextMethod : methodList){
				    if(nextMethod.getName().equalsIgnoreCase(call.replace("()", ""))){
					otherClass = getClassByName(nextMethod.getReturnType());
					break;
				    }
				}
			    }
			    else {
				List<JavaAttribute> attributeList = otherClass.getAttributes();
				otherClass = null;
				for(JavaAttribute nextAttribute : attributeList){
				    if(nextAttribute.getName() == call){
					otherClass = this.getClassByName(nextAttribute.getType());
				    }
				}
			    }
			}
		    }
		}
		if(!coupling.entrySet().isEmpty()){
        		for(Entry<String, Integer> entry: coupling.entrySet()){
        			sbCoupling.append(String.format("\t%s: %d\n", entry.getKey(), entry.getValue()));
        		}
		}
		else {
		    sbCoupling.append("\tNo direct reference.\n");
		}
	    }
	}
	
    	System.out.println("==========================================");
	System.out.println("=============== Modifiers ================");
	System.out.println("==========================================");
	System.out.println(sbModifiers.toString());
	System.out.println("==========================================");
	System.out.println("=============== Visibility ===============");
	System.out.println("==========================================");
	System.out.println(sbVisibility.toString());
	System.out.println("==========================================");
	System.out.println("=============== Class Schemes ============");
	System.out.println("==========================================");
	System.out.println(sbInheritance);
	System.out.println("==========================================");
	System.out.println("=============== Coupling =================");
	System.out.println("==========================================");
	System.out.println(sbCoupling.toString());
	System.out.println("==========================================");
	System.out.println("=============== Call Hierarchy ===========");
	System.out.println("==========================================");
	System.out.println(sbCalls.toString());
    }

    private JavaClass getClassByName(String name){
	for(Object f : objectManager){
	    JavaFile file = (JavaFile)f;
	    Optional<JavaClass> maybeClass = file.getClasses()
		    .stream()
		    .filter(c -> c.getName().equals(name))
		    .findAny();
	    if(maybeClass.isPresent()){
		return maybeClass.get();
	    }
	};	
	return null;

    }
    
    public void debugTree(){
	System.out.println("==========================================");
	System.out.println("============== DEBUG =====================");
	System.out.println("==========================================");
	for(Object o : objectManager){
	    JavaFile f = (JavaFile)o;
	    for(JavaClass c : f.getClasses()){
		System.out.println(String.format("CLASS: %s", c.getName()));
		for(JavaAttribute a : c.getAttributes()){
		    System.out.println(String.format("\tATTR: %s : %s", a.getName(), a.getType()));
		}
		for(JavaMethod m : c.getMethods()){
		    System.out.println(String.format("\tMETHOD: %s -> %s", m.getName(), m.getReturnType()));
		    for(JavaMethodCall mc : m.getMethodCalls()){
			System.out.println(String.format("\t\tCALL: %s", mc.getRawCall()));
		    }
		}
	    }
	}
    }
}
