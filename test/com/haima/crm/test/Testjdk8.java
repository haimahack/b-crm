package com.haima.crm.test;

import java.util.Arrays;


public class Testjdk8 {
	public static void main(String[] args) {
		Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
	}
}
