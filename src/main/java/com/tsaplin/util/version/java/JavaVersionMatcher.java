package com.tsaplin.util.version.java;

import java.util.StringTokenizer;

public class JavaVersionMatcher {

	/**
	 * Checks if given Java version matches the expression.
	 * 
	 * Some examples: 
	 * 		>= 1.8
	 * 		>= 1.8 & < 1.9
	 * 		>= 1.8 & < 1.9 & !(1.8.4_05 | 1.8.4_06)
	 */
	public static boolean matchJavaVersion(String javaVersion, String exp) {
		return evaluateExpression(javaVersion, new StringTokenizer(exp.replaceAll("\\s+",""), "()|&!", true));
	}
	
	private static boolean evaluateExpression(String javaVersion, StringTokenizer toks) {
		boolean result = true;
		while(toks.hasMoreTokens()) {
			String tok = toks.nextToken();
			if ("(".equals(tok)) {
				result = evaluateExpression(javaVersion, toks);
			} else if (")".equals(tok)) {
				return result;
			} else if ("|".equals(tok)) {
				result |= evaluateExpression(javaVersion, toks);
			} else if ("&".equals(tok)) {
				result &= evaluateExpression(javaVersion, toks);
			} else if ("!".contains(tok)) {
				result = !evaluateExpression(javaVersion, toks);
			} else {
				result = evaluateVersion(javaVersion, tok);
			}
		}
		return result;	
	}
	
	private static boolean evaluateVersion(String javaVersion, String exp) {
		if (exp.startsWith("<>")) {
			return compareVersions(javaVersion, exp.substring(2)) != 0;
		} else if (exp.startsWith(">=")) {
			return compareVersions(javaVersion, exp.substring(2)) >= 0;
		} else if (exp.startsWith("<=")) {
			return compareVersions(javaVersion, exp.substring(2)) <= 0;
		} else if (exp.startsWith(">")) {
			return compareVersions(javaVersion, exp.substring(1)) > 0;
		} else if (exp.startsWith("<")) {
			return compareVersions(javaVersion, exp.substring(1)) < 0;
		} else {
			return compareVersions(javaVersion, exp) == 0;
		}
	}
	
	private static int compareVersions(String v1, String v2) {
		String [] v1Parts = v1.split("\\.|_|-"); 
		String [] v2Parts = v2.split("\\.|_|-");
		for (int i=0; i < v1Parts.length || i < v2Parts.length; i++) {
			Integer a1 = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
			Integer a2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;
			int r = Integer.compare(a1, a2);
			if (r != 0) {
				return r;
			}
		}
		return 0;
	}

}
