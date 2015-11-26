package com.olivierboucher.inf1018.custom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class JavaFileExplorer {

    public static List<File> getAllJavaFiles(File dir, int depth){

	try {
	    if(dir.isDirectory()){
		return Files.find(dir.toPath(), depth, (p, bfa) -> bfa.isRegularFile())
			.filter(p -> {
			    try {
				String fileName = p.toString();
				String extension = "";
				if(fileName.lastIndexOf(".") > 0){
				    extension = fileName.substring(fileName.lastIndexOf(".")+1);
				}
				return extension.equalsIgnoreCase("java");
			    } catch (Exception e) {
				return false;
			    }
			})
			.map(p -> p.toFile())
			.collect(Collectors.toList());
	    }
	    return null;
	} catch (IOException e) {
	    return null;
	}
    }
}
