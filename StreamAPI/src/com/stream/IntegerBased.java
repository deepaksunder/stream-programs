package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	  
	//Multiply alternative numbers in a array
	  int[] ipArr = {4,5,1,7,2,9,2};
	  int mulAns = IntStream.range(0, ipArr.length).filter( x -> x%2 == 0).map( i -> ipArr[i]).reduce(1, (a,b)->a*b);
	  System.out.println("The product of alternative number is "+mulAns);
	    
	  //multiply 1 and last element, 2nd & 2nd last element, etc numbers in a arrya {4,5,1,7,2,9}; op 4*9, 5*2, 1*7 
	  int[] nums = {4,5,1,7,2,9};
	  IntStream.range(0, nums.length/2).map(indx -> nums[indx] * nums[nums.length-indx-1]).forEach(System.out::println);
	  
	  //move all the zeros to beging of the array zarr={5,0,1,0,8,0} op={0,0,0,5,1,8}
	  int[] zarr={5,0,1,0,8,0};
	  //approach1
	  List<Integer> tlist = Arrays.stream(zarr).boxed().collect(Collectors.toList());
	  List<Integer> zeroList = tlist.stream().filter(x -> x == 0 ).toList();
	  List<Integer> nonZeroList = tlist.stream().filter(x -> x != 0 ).toList();
	  List<Integer> finalList = new ArrayList<>();
	  finalList.addAll(zeroList);
	  finalList.addAll(nonZeroList);
	  System.out.println("Approach 1 " +finalList);
	  //approach2
	  Map<Boolean, List<Integer>> mgMap =  tlist.stream().collect(Collectors.partitioningBy( x -> x != 0));
	  System.out.println(mgMap);
	  System.out.println("Approach 2 "+mgMap.values());
	  List<Integer>mgList =  mgMap.values().stream().flatMap( x -> x.stream()).collect(Collectors.toList());
	  System.out.println("Approach 2 "+mgList);
	  
	  //given an array of integer return true if it contains distinct values
	  int[] disArr={5,0,1,0,8,0};
	  List<Integer> disList = Arrays.stream(disArr).boxed().toList();
	  boolean isDistinct = disList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().stream().noneMatch(x->x>1);
	  System.out.println("Is the list distinct "+isDistinct);
	  
	  //find the sum of all the integer in a array arr = {1,2,3,4,5} op = 15
	  int[] arr22 = {1,2,3,4,5};
	int sunVal = Arrays.stream(arr22).boxed().map(Integer::intValue).reduce(0,(a,b)->a+b);
	// we can also use
	int sun1Val = Arrays.stream(arr22).boxed().mapToInt(Integer::intValue).sum();
	System.out.println("Approach1 value :"+sunVal+" Approach2 value :"+sun1Val);
	
	//convert a list of integer to list of square
	List<Integer> squareList =  Arrays.stream(arr22).boxed().map( x -> x*x ).toList();
	System.out.println("The Square array list is "+squareList);
	
	//find & print the distinct odd number
	List<Integer> arrList = Arrays.asList(1,2,3,4,5,5,6,7,7,8,9,9,10,11);
	List<Integer> oddList = arrList.stream().distinct().filter(x -> x%2 != 0).toList();
	Set<Integer> oddSet = arrList.stream().filter(x -> x%2 != 0).collect(Collectors.toSet());
	System.out.println("The Unique odd list is "+oddList);
	System.out.println("The Unique odd set is "+oddSet);
	
	//find the union of 2 list of integers
	List<Integer> u1 = Arrays.asList(1,2,3,4,5);
	List<Integer> u2 = Arrays.asList(5,6,7,8,9,10);
	List<Integer> uOut = Stream.concat(u1.stream(), u2.stream()).toList();
	System.out.println("The Union of 2 list is "+uOut);
	
	
	//find the kth smallest element from a list of integer
	List<Integer> list27= Arrays.asList(7,1,6,2,1,3,4,5);
	int k = 3;//op-2
	int ans27 = list27.stream().sorted().skip(k-1).findFirst().get();
	System.out.println("The "+k+" smallest number in the list is "+ans27);
	
	//calculate the average of all the number
	List<Integer> list31 = Arrays.asList(1,2,3,4,5);
	double averageAns = list31.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
	System.out.println("The average of the number list is "+averageAns);
	
	//find the intersection of 2 list
	List<Integer> int1 = Arrays.asList(1,2,3,4,5);
	List<Integer> int2 = Arrays.asList(3,5,6,7);
	List<Integer> intRes1 = int1.stream().filter(x -> int2.contains(x)).toList();
	List<Integer> intRes2 = int1.stream().filter(int2::contains).toList();
	System.out.println("The intersection of 2 list is approach1 "+intRes1);
	System.out.println("The intersection of 2 list is approach2 "+intRes2);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
