package com.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		
		
	}
	

}
