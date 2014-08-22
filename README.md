Java Version Matcher
====================

A helper class that you can use to compare Java versions.

Examples of matcher expressions:
```java
JavaVersionMatcher.matchJavaVersion(">= 1.8");
JavaVersionMatcher.matchJavaVersion(">= 1.8 & < 1.9");
JavaVersionMatcher.matchJavaVersion(">= 1.8 & < 1.9 & !(1.8.4_05 | 1.8.4-06)");
```
