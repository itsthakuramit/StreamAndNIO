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

		
		/*List<File> files =Files.list(Paths.get("C:\\Users\\Cyclopes\\Documents\\hclws\\Streams"))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".java"))
                .map(Path::toFile)
                .collect(Collectors.toList());
		Paths.get("C:\\Users\\Cyclopes\\Documents\\hclws\\Streams")
		files.forEach(System.out::println);
	*/
		Path configFilePath = FileSystems.getDefault().getPath("C:\\User\\Cyclopes\\Documents\\hclws\\Streams");
		List<Path> fileWithName = Files.walk(configFilePath)
		                .filter(s -> s.toString().endsWith(".java"))
		                .map(Path::getFileName)
		                .sorted()
		                .collect(Collectors.toList());

		for (Path name : fileWithName) {
		    System.out.println(name);
		}
	}

	public static void main(String[] args) throws IOException {
	
		System.out.println("\nA Map with key as Tablet name and value as expiry date of tablets which are Expired :");
		NIO2Exercise.getExpiredTablets("TabletDetails.txt", "GlaxoSmithKline");
		
		System.out.println("\nList all the files ending with .java in the current project's src folder and its subfolders :");
		NIO2Exercise.getAllJavaFileNames();

	}

}
