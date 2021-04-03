package org.apache.maven.archetype.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIO2Exercise {
	
	public static void getExpiredTablets(String filename, String manufacturer) throws IOException {		
		Path path=Paths.get(filename);
		LocalDate todayDate=LocalDate.now();
		List<String> tabletList=Files.readAllLines(path);
		Map<String,String> tabletMap= new HashMap<>();
		
		for (String s : tabletList) {
			String[] details=s.split(",");
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate parseDate=LocalDate.parse(details[3],dtf);			
			if(details[1].equals(manufacturer)){
				if(parseDate.isBefore(todayDate)){
					tabletMap.put(details[0], details[3]);
				}
			}
		}
		tabletMap.forEach((k,v) -> System.out.println(k+ " -> "+ v));
	}
	
	
	public static void getAllJavaFileNames() throws IOException{
		Path path = Paths.get("C:\\Users\\Cyclopes\\Documents\\hclws\\Streams\\src");
		List<Path> fileWithName = Files.walk(path)
						.filter(s -> s.toString().endsWith(".java"))
		                .map(Path::getFileName)
		                .sorted()
		                .collect(Collectors.toList());

		for (Path name : fileWithName) {
		    System.out.println(name);
		}
	}
	
	
	public static void checkFile(String filename, String pathAbsolute) throws IOException {
		Path path = Paths.get(pathAbsolute);
		boolean check=Files.walk(path)
				.filter(s -> s.toString().equals(filename))
				.map(Path::getFileName) != null;
		
		if(check)
			System.out.println("File Present...!!");
		}
        
	
	

	public static void main(String[] args) throws IOException {
	
		System.out.println("\nA Map with key as Tablet name and value as expiry date of tablets which are Expired :");
		NIO2Exercise.getExpiredTablets("TabletDetails.txt", "GlaxoSmithKline");
		
		System.out.println("\nList all the files ending with .java in the current project's src folder and its subfolders :");
		NIO2Exercise.getAllJavaFileNames();

		System.out.println("\nFile Present at an absolute path :");
		NIO2Exercise.checkFile(null,"C:\\Users\\Cyclopes\\Documents\\hclws\\Streams");
	}
	
	
	

}
