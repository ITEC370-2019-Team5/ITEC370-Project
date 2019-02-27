# Team 05 Style Guide

<brief description of your team's opinion or philosophy regarding Style Guides>

<Something descriptive, uses Camelcase style, and is less that 15 chars.>

See https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#code for markdown tips

## Naming conventions
Camelcase *somethingLikeThis*
<brief statement describing your team's naming conventions>

<Something descriptive, uses Camelcase style, and is less than 15 characters long.>

### Examples
* ClassInterface
* SomeClass
* EXCEPTION_*CLASS*_*METHOD*
* (int mile, string street)
* void SomeMethod
* (int mile, string street)
* int numMeters
* SOME_CONSTANT_IN_AN_INSTANCE_OF_AN_OBJECT
* CONSTAT_IN_A_CLASS

## Commenting style for public and private members of a class or interface:

<brief statement of your team's commenting style>

<Something descriptive, uses Camelcase, and is less than 15 chars. UNLESS it is a final/constant variable. Then we will describe the name in full and seperate by '_'.>

### Examples
//Some comment on what this function does.
public void addOne{}

```java
//Some comment on what this class is for.
public class ClassName {
   int x = 0; //A comment about the use of this var.
   addOne(x);
}
```

* fields - Same as parameters.
* constructors - Default constructor at top, followed by any overloaded constructors.
* methods - Grouped by usage, mathmatical methods are all near each other, and so on.
* coding style (brackets, horizontal, and vertical spacing) for:
  * if statements
  	if()
	{
		//Something
	}
	else
	{
		//Something
	}
  * switch statement
  	switch(x)
	{
		case 1: Something
			break;
		case 2: Something
			break;
	}
  * while loops
  	while()
	{
		//Something
	}
  * for loops
  	for(int x = 0; x < y; x++)
	{
		//Something
	}
  * enhanced for loops
  	for(int x : array)
	{
		//Something
	}
