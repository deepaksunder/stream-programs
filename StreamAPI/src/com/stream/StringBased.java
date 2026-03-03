package com.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee{
	private String name;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Employee(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
}
public class StringBased {
	
	public static void main(String[] args) {
		//Given a sentence find the word that has highest length
		String sentence = "I am learning java streams API java";
		String ans = Arrays.stream(sentence.split(" ")).max(Comparator.comparing(String::length)).get();
		System.out.println("The longest word is "+ans);
		
		//remove duplicate charater from the string & return in same order
		String s= "dabcadefg";
		s.chars().distinct().mapToObj(x->(char)x).forEach(System.out::print);
		System.out.println("");
		//Find the word that has second or nth highest length
		String secHigh = Arrays.stream(sentence.split(" ")).sorted(Comparator.comparing(String::length).reversed()).skip(1).findFirst().get();
		System.out.println("The 2nd Highest word is "+secHigh);
		
		//find the occurance of each word in sentence
		Map<String, Long> mapOfWord = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(mapOfWord);
	/*	for(String key : mapOfWord.keySet()) {
			System.out.println(key +" : "+mapOfWord.get(key));
		}*/
		
		/** Given a sentence , find the specified no of vowels
		 * if no of vowels in a word is 2
		 * stream has 2 vowels e & a 
		 */
		Arrays.stream(sentence.split(" ")).filter(x -> x.replaceAll("[^aeiouAEIOU]", "").length() == 2).forEach(System.out::println);
		/**- x.replaceAll("[^aeiouAEIOU]", "") removes all characters that are not vowels (both uppercase and lowercase).
		- Example: "Hello" → "eo".
		"learning -> eai since length is 3 this word is skipped
		*/

		//Given a word find the occurance of each character, function.identity can be replace by x->x
		String word = "Mississipi";
		Map<String, Long> charLength = Arrays.stream(word.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(charLength);
		
		//find the first non repeating character in a string
		String msg = "hello word look";
		int index = msg.indexOf('l');
		int lastIndex = msg.lastIndexOf('l');
		String fChar = Arrays.stream(msg.split("")).filter(c ->msg.indexOf(c) == msg.lastIndexOf(c)).findFirst().get();
		System.out.println("The first non repeating character is "+fChar +"  The first & last index of H is "+index +" , "+lastIndex);
		//approach 2
		Map<Character, Long> charMap = msg.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(charMap);
		//now get the first character
		char resultChar = charMap.entrySet().stream().filter( vl -> vl.getValue() == 1).map(x -> x.getKey()).findFirst().get();
		System.out.println("First non repeatring character is "+resultChar);
		
		//find the first repeating character
		char repChar = msg.chars().mapToObj(x -> (char)x).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
		.entrySet().stream().filter(vl -> vl.getValue() > 1).map( m -> m.getKey()).findFirst().get();
		System.out.println("The First repeating charater is "+repChar);
		
		//Given an array of integer group them up in the ranges in which they belong, range value is 10
		//so 0={2,3}, 10={10,14}
		//to get the index we use 2/10*10 = 0, 10/10*10 = 1
		
		int[] arrGrp = {2,3,10,14,20,24,30,34,40,44,50,55};
		
		Map<Object, List<Integer>> mapGrp =  Arrays.stream(arrGrp).boxed().collect(Collectors.groupingBy( x -> x/10*10, Collectors.toList()));//if we use this output is not ordered to maintain the order we need to convert to list
		Map<Integer, List<Integer>> mapGrp1 =  Arrays.stream(arrGrp).boxed().collect(Collectors.toList()).stream().collect(Collectors.groupingBy( x -> x/10*10, LinkedHashMap::new, Collectors.toList()));//i
		System.out.println("The grouped elements are unordered "+mapGrp );
		System.out.println("The grouped elements are ordered "+mapGrp1 );
		
		//Given a list of string , create a list that only contains integer
		String[] strArr = {"abc","123","456","xyz"};
		List<Integer> onlyInt = Arrays.stream(strArr).filter( x -> x.matches("[1-9]+")).map(Integer::valueOf).toList();
		System.out.println("The only Integer list is "+onlyInt);
		
		
		//Group the anagram from the list of string
		//[[pat,tap],[pan,nap],[Team,meat],[tree]]
		
		String [] ang = {"pat","tap","pan","nap","Team","tree","meat"};
		List<String> lst = Arrays.asList(ang);
		Map<Object, List<String>> mapwrd = lst.stream().collect(Collectors.groupingBy(x -> Arrays.stream(x.toLowerCase().split("")).sorted().toList()));
		System.out.println(mapwrd);
		Collection<List<String>> mapwrd1 = lst.stream().collect(Collectors.groupingBy(x -> Arrays.stream(x.toLowerCase().split("")).sorted().toList())).values();
		System.out.println(mapwrd1);
		
		//Group the string based on the middle character  {"ewe","jji","jhj","kwk","aha"} op=[w=["ewe","kwk"],h=["jhj","aha"],j=["jji"]]
		
		String[] gArr = {"ewe","jji","jhj","kwk","aha"};
		//Arrays.asList(gArr).stream can also be replaced by Stream.of(gArr)
		Map<String, List<String>> grpArr = Stream.of(gArr).collect(Collectors.groupingBy(x-> x.toString().substring(1, 2)));
		System.out.println(grpArr);
		
		//sort a list of strings in alphabetic order
		List<String> wordList = Arrays.asList("Zudio","Puma","Addidas","MAC","H&M");
		System.out.println("Sort the list of words in ascending order");
		wordList.stream().sorted().forEach(System.out::println);
		
		//Remove a non numeric character from the list of String
		List<String> list28 = Arrays.asList("a1b2b3", "d4e5f6","g7h8i9","1a2b3c","654abc" );
		Pattern pattern = Pattern.compile("[^0-9]");
		List<String> res28 = list28.stream().map(x -> pattern.matcher(x).replaceAll("")).toList();
		System.out.println("After removing non numeric character "+res28);
		
		//Find & print only the string containing digit 
		List<String> list29 = Arrays.asList("12345","12abc","xyz","456");
		List<String> resu29 =  list29.stream().filter(x -> x.matches("[0-9]+")).toList();
		System.out.println("THe list with only digit is "+resu29);
		
		//convert a list of string to upper case
		List<String> list30 = Arrays.asList("barking dog", "Never bits");
		List<String> resu30 = list30.stream().map(String::toUpperCase).toList();
		System.out.println("Converted list of uppercase string is "+resu30);
		
		//From the list of employee object with name & email, find the list of domain like gmail.com, yahoo.com etc
		Employee e1 = new Employee("sharvin", "sharvin@gmail.com");
		Employee e2 = new Employee("murugan", "shakthi.murugan@yahoo.com");
		Employee e3 = new Employee("selvum", "selvum.@cool@yahoo.com");
		List<Employee> empList = Arrays.asList(e1,e2,e3);
		
		Map<String, Long> domainCount =  empList.stream().map( x -> x.getEmail().
				substring(x.getEmail().lastIndexOf('@'))).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		//if dont use lastIndexOf then the output will be {@cool@yahoo.com=1, @gmail.com=1, @yahoo.com=1}
		System.out.println("The domain count of the employees are "+domainCount);
		
	}
	

}
