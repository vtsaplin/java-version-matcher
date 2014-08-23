Java Version Matcher
====================

A helper class that you can use to match Java versions.

It uses simple expression language rules:
* A version, when it is specified without a prefix, is matched exactly (i.e. 1.7.0).
* If a version is prefixed with comparison operator (<, >, <=, >=, <>) it will be exaluated to *true* or *false*.
* Boolean operators (&, |, !) and parentheses can be used to combine several expressions together.

Examples of matcher expressions:
```java
JavaVersionMatcher.match(version, ">= 1.8");
JavaVersionMatcher.match(version, ">= 1.8 & < 1.9");
JavaVersionMatcher.match(version, ">= 1.8 & < 1.9 & !(1.8.4_05 | 1.8.4-06)");
```
