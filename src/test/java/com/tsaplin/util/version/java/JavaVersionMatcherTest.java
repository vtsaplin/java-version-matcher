package com.tsaplin.util.version.java;

import org.junit.Assert;
import org.junit.Test;

import com.tsaplin.util.version.java.JavaVersionMatcher;

public class JavaVersionMatcherTest {

	private static final String JAVA_VERSION = "1.8.4_05";
	
	@Test
	public void shouldMatchVersions() {
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1.8"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1.8.4"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1.08.004"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "1.8.4_05"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "1.8.4-05"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1.8"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "< 1.9.1"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, ">= 1.8"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "<> 1.8"));
		Assert.assertFalse(JavaVersionMatcher.match(JAVA_VERSION, "<> 1.8.4_05"));
		Assert.assertFalse(JavaVersionMatcher.match(JAVA_VERSION, "<= 1.8"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "!1.7"));
		Assert.assertFalse(JavaVersionMatcher.match(JAVA_VERSION, "< 1.7 & > 1.9"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "> 1.7 & < 1.9"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "< 1.7 | > 1.8"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "!(< 1.7 & > 1.9) & (> 1.7 & < 1.9)"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, "!(< 1.7 | > 1.9) & (> 1.7 & < 1.9)"));
		Assert.assertTrue(JavaVersionMatcher.match(JAVA_VERSION, ">= 1.8 & < 1.9 & !(1.8.4_01 | 1.8.4_06)"));
		Assert.assertFalse(JavaVersionMatcher.match(JAVA_VERSION, ">= 1.8 & < 1.9 & !(1.8.4_05 | 1.8.4_06)"));
	}
	
}
