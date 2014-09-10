# Java-First-Year-Workshop

Solutions to exercises posed as part of the java workshop during the first year of my Computer Science degree. These exercises were given out over a ten week period with 1-2 weeks to complete each task.

## Ex8 - Falling blocks puzzle game
This exercise was to create a "falling block game" using swing, my implementation mimicked the classic game 'Tetris'. The game has randomized block generation, score multipliers, score & line count, a preview pane of the next block to arrive, pause option and scaling difficulty (over time).

[Download runnable JAR](https://github.com/Mattie432/Java-First-Year-Workshop/blob/master/dist/Ex8_FallingBlockPuzzleGame.jar?raw=true)

![alt text](https://raw.githubusercontent.com/Mattie432/Java-First-Year-Workshop/master/images/ex8.png?token=3811007__eyJzY29wZSI6IlJhd0Jsb2I6TWF0dGllNDMyL0phdmEtRmlyc3QtWWVhci1Xb3Jrc2hvcC9tYXN0ZXIvaW1hZ2VzL2V4OC5wbmciLCJleHBpcmVzIjoxNDEwOTE3NzYxfQ%3D%3D--085983715df0a1df629cd4b1628f0f09c10f45da "Tetris game image")


## Ex7 - Graphics
This exersise was to create a graphics program (like MS Paint) that could draw lines, rectangles and squares. Additional features include changing the colour of the lines, an undo feature and the ability to export drawings to JPG files.

[Download runnable JAR](https://github.com/Mattie432/Java-First-Year-Workshop/blob/master/dist/Ex7_Graphics.jar?raw=true)

![alt text](https://raw.githubusercontent.com/Mattie432/Java-First-Year-Workshop/master/images/ex7.png?token=3811007__eyJzY29wZSI6IlJhd0Jsb2I6TWF0dGllNDMyL0phdmEtRmlyc3QtWWVhci1Xb3Jrc2hvcC9tYXN0ZXIvaW1hZ2VzL2V4Ny5wbmciLCJleHBpcmVzIjoxNDEwOTE3ODA5fQ%3D%3D--dd45bcb6dc8f704e4576aa315112379f831b8269 "Drawing  program image")


## Ex6 - Containers
This exercise is about writing a program to merge together data files. The format used for the input and output consists of a row of column headings separated by commas, followed by any number of data lines that are also separated by commas, and using an empty string to represent a situation where there is no data available.

Here is an example:
```room,floor,use
LG04,LG,lab
UG04,UG,lab
130c,1,
217,2,tutorials
```

## Ex5 - Input/Output
This exercise is about writing a recursive search tool to find text strings in multiple files.

## Ex4 - Exceptions and Recursion
1. Write a checked exception class Ex4NotFoundException. This class is intended to represent situations where an object being searched for within an array was not found in that array.

2. This question is about writing a class to perform binary searches on a sorted array. (This will be explained in more detail in the relevant parts of the question). Call this class Ex4BinarySearch. It should contain only static methods and a private constructor. Do not use API methods, other than methods of Comparable, Exception, and possibly Object,

## Ex3 - Interfaces, Abstract Classes, and Inheritance
1. Write an interface Ex3IntSequence that describes classes that can provide a sequence of integers. It should have two method signatures, firstInt and nextInt, that describe methods that get the first integer in the sequence, and the integer immediately after the most recently returned integer, respectively. Neither should take arguments.

2. Write a class Ex3GeometricIntSequence that implements Ex3IntSequence. It should always return 1 as the first integer in the sequence, and should have a private field that records the integer it most recently returned. When the next integer in the sequence is requested, it should return twice the integer it most recently returned. (So for instance, asking for the first, next, next, next, first, next integers in that order should return 1, 2, 4, 8, 1, 2.)

3. One way to implement Ex3IntSequence is to have a private field that simply records an index into the sequence. We can use an abstract class in order to simplify implementing integer sequences this way. Write this abstract class, Ex3AbstractIntSequence, that implements Ex3IntSequence, and defines firstInt and nextInt in terms of a protected abstract method getIntByIndex and a private field that remembers the last returned index. The class should also have another private field, that specifies the index that firstInt should provide to getIntByIndex; this field should not have getters or setters, but should be set from an argument provided to the constructor. (So for instance, if the constructor of Ex3AbstractIntSequence is provided an argument of 2, then calling firstInt should return getIntByIndex(2), calling nextInt after that should return getIntByIndex(3), and so on.)

4. By extending your abstract class, write a class Ex3ArithmeticIntSequence that returns 1 when the first integer is requested, and 1 more than the last returned integer when the next integer is requested. Do not override the firstInt or nextInt methods (override only the getIntByIndex method). The class's constructor should take no arguments.

5. By extending your abstract class, write a class Ex3ArrayIntSequence. It should take an array as the only argument to its constructor; firstInt should return the first element of the array, and nextInt should return successive elements of the array (that is, if firstInt is called to return the first element, nextInt should return the second element if called immediately afterwards, then the third element if called immediately after that, and so on). For the purpose of this exercise, you don't need to handle the case where the array runs out of elements to return. As in question 4, do not override the firstInt or nextInt methods.

6. Write a concrete class Ex3Test whose purpose is to test the other classes. It should have two methods: a static method printFirstFiveElements that takes an Ex3IntSequence as its argument and prints the first five elements of it to System.out, and a main method that creates an Ex3GeometricIntSequence, an Ex3ArithmeticIntSequence, and an Ex3ArrayIntSequence, and uses the printFirstFiveElements method to print the first five elements of each of them.

## Ex2 - Classes, Objects, and Methods
1. This question asks you to write a class to represent a rectangle, Ex2Rectangle.java.

2. For the second part of the assessed exercise, you will write a file Ex2RectangleMethods.java, that uses the class you wrote in question 1. 

## Ex1 - Algorithms, Introductory Java, and Repetition
1. Define a subroutine for drawing a square using the turtle.
2. Define a subroutine for drawing a grid of squares
3. Using the subroutine you wrote in part (b), draw three grids of squares, in order to reproduce the following diagram


1. Input an integer from the input frame and assign it to an int variable called inches. This integer represents a measurement in inches.
2. Convert this number of inches into a format that calculates the number of miles, furlongs, chains, yards, feet, and leftover inches involved:
```
There are 12 inches in 1 foot.
There are 3 feet in 1 yard.
There are 22 yards in 1 chain.
There are 10 chains in 1 furlong.
There are 8 furlongs in 1 mile.
```
3. Write a message to System.out that shows the total number of inches, and then the equivalent in imperial units.
Use a format like this example: "72121 inches = 1 mile, 1 furlong, 1 chain, 1 yard, 1 foot, 1 inch."
Your output needs to vary according to the numbers involved: if one of the components would be 0, say "0 furlongs", just leave it out altogether. Likewise, if one of the components would be more than 1, say "5 yards", you need to use the plural rather than the singular (5 yard) is incorrect.
