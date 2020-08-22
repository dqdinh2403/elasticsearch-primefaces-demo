package com.testing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SummingNumberWithJavaStream {
	
	public static int customAdd(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
		
		//1 Stream.reduce()
		Integer sum1 = integers.stream().reduce(0, (a, b) -> a + b);
		System.out.println("Sum1 = " + sum1);

		Integer sum2 = integers.stream().reduce(0, Integer::sum);
		System.out.println("Sum2 = " + sum2);
		
		Integer sum3 = integers.stream().reduce(0, SummingNumberWithJavaStream::customAdd);
		System.out.println("Sum3 = " + sum3);
		
		
		//2 Stream.collect()
		Integer sum4 = integers.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Sum4 = " + sum4);
		
		
		//3 IntStream.sum()
		Integer sum5 = integers.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum5 = " + sum5);
	}

}
