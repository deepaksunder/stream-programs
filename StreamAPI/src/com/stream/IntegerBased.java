package com.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntegerBased {
	
	public static void main(String[] args) {
		//Given a list of integer, divide into 2 half one with even & another with odd numbers
		int [] arr = {1,3,5,2,4,6,9,7,8};
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		
		Map<Boolean, List<Integer>> groupedArr = list.stream().collect(Collectors.groupingBy(x->x%2== 0, Collectors.toList()));
		System.out.println(groupedArr);
		//return as list<list<>>
	   List<List<Integer>> listOfInt = list.stream().collect(Collectors.groupingBy(x->x%2== 0, Collectors.toList()))
		.entrySet().stream().map(val->val.getValue()).collect(Collectors.toList());
	   System.out.println(listOfInt);
	   
	   /**Given a integer array, rearrange the elements from highest/lowest possible value
	    * arr = {1,2,3,4,5}
	    * op = 54321/12345
	    */
	   System.out.println("Ascending order");
	   Arrays.stream(arr).boxed().sorted().forEach(System.out::print);//ascending
	   System.out.println("");
	   System.out.println("Desending order");
	   Arrays.stream(arr).mapToObj(x->x).sorted(Collections.reverseOrder()).forEach(System.out::print);
	   System.out.println("");
	   
	   //Given an array, find the sum of unique elements
	   int[] arrs = {1,6,7,8,1,1,8,8,7};
	   int sumVal = Arrays.stream(arrs).distinct().sum();
	   System.out.println("The SUM of unique element in the array is "+sumVal);
	   
	   //find the product of 1st 2 elements in a array
	   int[] elm = {12,5,6,8,4};
	  int elmAns = Arrays.stream(elm).boxed()//without converting the stream to list we get unorder element
			  .limit(2).reduce(1,(a,b)->a*b); 
	  System.out.println("The product of 1st 2 element is "+elmAns);
	    
	}

}
