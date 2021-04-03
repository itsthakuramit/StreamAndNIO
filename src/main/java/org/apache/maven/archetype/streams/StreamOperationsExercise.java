package org.apache.maven.archetype.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamOperationsExercise {
	
	static List<Player> playerList= new ArrayList<Player>();
	static Player p1= new Player("Virat Kohli", 36, 11000, 110, new Country("IND", "India"));
	static Player p2= new Player("Chris Gayle", 25, 6000, 200, new Country("WINDIES", "West Indies"));
	static Player p3= new Player("AB Devilliers", 24, 4000, 150, new Country("AUS", "Australia"));
	static Player p4= new Player("Shikhar Dhawan", 35, 6000, 120, new Country("IND", "India"));
	static Player p5= new Player("Pollard", 20, 2100, 99, new Country("WINDIES", "West Indies"));

	
	static {
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		playerList.add(p5);
	}
	
	public static void displayPlayers() {		
		playerList.stream()
		.forEach( player -> System.out.println(player.playerName));
	}
	
	
	public static void displayPlayersForCountry(String country) {
		playerList.stream()
		.filter(player -> (player.highestScore>100 && player.country.countryName.equals(country)))
		.forEach(player -> System.out.println(player.playerName));
	}
	
	
	public static void getPlayerNames() {	
		List<String> playerNameList = playerList.stream()
		.filter( player -> player.runs>5000)
		.sorted(Comparator.comparing(Player::getPlayerName).reversed())
		.map(player-> player.playerName)
		.collect(Collectors.toList());
		System.out.println(playerNameList);
	}
	
	
	public static void getAverageRunsByCountry(String country) {
		double averageRuns=playerList.stream()
		.filter(player -> player.country.countryName.equals(country))
		.collect(Collectors.averagingDouble(Player :: getRuns));
		System.out.println(averageRuns);
	}
	
	
	public static void getPlayerNamesSorted() {
		Comparator<Player> reverseNameComparator =(pl1, pl2)
															-> pl1.getCountry().getCountryName().compareTo(pl2.getCountry().getCountryName());
		  List<String> finalList = playerList.stream()
				  .sorted(reverseNameComparator.thenComparing(Player::getMatchesPlayed))
				  .map(player-> player.playerName)
				  .collect(Collectors.toList());
		  finalList.forEach(System.out::println);
	}
	
	
	public static void getMaxRunsPlayer() {
		int maxRuns = (int) playerList.stream()
	            .max(Comparator.comparing(Player :: getRuns))
	            .get()
	            .getRuns();
		List<String> listPlayerNames = playerList.stream()
	            .filter(player -> player.getRuns() == maxRuns)
	            .map(player-> player.playerName)
	            .collect(Collectors.toList());	
		System.out.println(listPlayerNames);
	}
	
	
	public static void getPlayerCountry() {
		Map<String,String> playerMap= new HashMap<>();
		List<Player> list=playerList.stream()
		.filter( player -> player.getMatchesPlayed()>20)
		.collect(Collectors.toList());
		
		for(Player p: list)	{
			playerMap.put(p.getPlayerName(), p.getCountry().countryName);
		}
		
		playerMap.forEach((k,v) -> System.out.println(k+"->"+v));
		
	}
	
	
	public static void findPlayer(String name, String country) {
		playerList.stream()
		.filter(player -> (player.playerName.equals(name) && player.country.countryName.equals(country)))
		.forEach(System.out::println);
	}
	
	
	public static void checkHighScorerByCountry(String country) {
		boolean bools=playerList.stream()
		.filter(player -> (player.country.countryName.equals(country) && player.runs>1000)) != null;
		System.out.println(bools);
	}

	public static void main(String[] args) {
		
		System.out.println("\nName of the Players :");
		StreamOperationsExercise.displayPlayers();
		
		System.out.println("\nName of the Players belonging to a particular country with highest score > 100 :");
		StreamOperationsExercise.displayPlayersForCountry("West Indies");
		
		System.out.println("\nName of the players whose runs > 5000 in decreased order of their names :");
		StreamOperationsExercise.getPlayerNames();
		
		System.out.println("\nThe average runs scored by players from a particular Country :");
		StreamOperationsExercise.getAverageRunsByCountry("West Indies");
		
		System.out.println("\nThe player who has scored maximum runs :");
		StreamOperationsExercise.getMaxRunsPlayer();

		System.out.println("\nThe player for a given name and country :");
		StreamOperationsExercise.findPlayer("Virat Kohli", "India");
		
		System.out.println("\nThe player in the given country who has scored more than 10000 runs (Returns true/false) :");
		StreamOperationsExercise.checkHighScorerByCountry("India");
		
		System.out.println("\nA map with the PlayerName and CountryName of all players who have played more than 20 matches :");
		StreamOperationsExercise.getPlayerCountry();
		
		System.out.println("\nA list with names of Players sorted as per country and then by matchesPlayed(descending) :");
		StreamOperationsExercise.getPlayerNamesSorted();
	}

}
