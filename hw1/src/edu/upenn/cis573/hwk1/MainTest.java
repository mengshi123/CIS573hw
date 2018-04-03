package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

public class MainTest {

	@Test
	public void testencrypt() throws IOException {
		String s1 = "aaa";
		String s2 = Main.encrypt(s1);
		assertEquals(s2,"ddd");
		String s3 = "xxx";
		String s4 = Main.encrypt(s3);
		assertEquals(s4,"aaa");
		String s5 = s3.toUpperCase();
		String s6 = Main.encrypt(s5);
		assertEquals(s6,"aaa");
	}
	
	@Test
	public void testformModel(){
		String s1 = "aaaabbbccd";
		int[] a1 = new int[26];
		Main.formModel(s1, a1);
		assertEquals(a1[1],3);
		String s2 = "aaaaabbbbcccdde";
		Main.formModel(s2, a1);
		assertEquals(a1[1],7);
	}
	
	@Test
	public void testgetMaxChar(){
		int[] a1 = new int[26];
		for(int i = 0; i < a1.length; i++){
			a1[i] = i;
		}
		assertEquals(Main.getMaxChar(a1),'z');
	}
	
	@Test
	public void testdecrypt(){
		int[] a1 = new int[26];
		int[] a2 = new int[26];
		String s1 = "aaaabbbccd";
		for(int i = 0; i < a1.length; i++){
			a1[i] = i;
			a2[i] = 26 - i;
		}
		String s2 = Main.decrypt(s1, a1, a2);
		assertEquals(s2,"zzzzyyyxxw");
	}
	@Test
	public void testcompareAndPrint1(){
		String s1 = "aaaabbbccd";
		String s2 = "aaaabbbced";
		int a1 = (int)Main.compareAndPrint1(s1,s1,s2);
		assertEquals(a1,1);
	}
	

}
