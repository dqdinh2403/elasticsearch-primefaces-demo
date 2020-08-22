package com.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBiFunction {
	
	public static boolean firstIsGreaterThanSecond(Double a, Float b) {
		return a > b;
	}
	
	public static <T, U, R> List<R> listCombiner(List<T> list1, List<U> list2,
			BiFunction<T, U, R> combiner){
		List<R> result = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			result.add(combiner.apply(list1.get(i), list2.get(i)));
		}
		return result;
	}
	
	public static String combineStringByUsingDash(String a, String b) {
		if(a.isEmpty()) {
			return b;
		}
		return a + "-" + b;
	}

	public static void main(String[] args) {
		//BiFunction functional interface: binary functions
		
		// Single-Parameter Functions
		List<String> mapped = Stream.of("hello", "world")
								.map(word -> word + "!")
								.collect(Collectors.toList());
		System.out.println(mapped.toString());
		
		// Two-Parameter Operations
		// Using lambda
		String result = Stream.of("hello", "world")
							.reduce("", (a,b) -> a + "-" + b);
		System.out.println(result);		
		
		String result2 = Stream.of("hello", "world")
				.reduce("", (a,b) -> combineStringByUsingDash(a, b));
		System.out.println(result2);	

		//Bi-Function
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		List<String> result3 = listCombiner(list1, list2, (a,b) -> a + b);
		System.out.println(result3);
		
		List<Double> list3 = Arrays.asList(1.0d, 2.1d, 3.3d);
		List<Float> list4 = Arrays.asList(0.1f, 0.2f, 4f);
		List<Boolean> result4 = listCombiner(list3, list4, (a, b) -> a > b);
		System.out.println(result4);
		
		//can use a method reference in a Bi-Function (this::firstIsGreaterThanSecond)
		
		//andThen method
		
	}

}
