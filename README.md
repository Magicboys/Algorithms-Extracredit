# Algorithms-Extracredit
A work by: Tyler Watson

For this extra credit assignment, I decided to implement a divide and conquer algorithm which find a closest pair of points. To help demonstrate the importance and effectiveness of divide and conquer algorithms, I decided to create two implementations which can solve the closest pair of points problem: a brute force method and another using an efficient algorithm implementation. 

**Description**
For this project, I decided to use java to implement these two solutions to the closest pair of points problem. This problem is where you try and find the two points in a large data set of points that are the closests to each other. For instance, this problem can be applicable if you have a map with a hundreds of points which we will consider to be the location of a person within a city. In this situation, if you would want to find the two people in the city that are the closest distance to eachother then this is the type of problem you will end up needing to solve.

For implementing a solution to this problem, I took two approaches: a brute force approach and an efficient polytime approach (by utilizing my understanding of algorithms). The reason for picking these two approaches was to allow for good comparisons when it came to runtime and show the massive impact your algorithm can have when it comes to the runtime of a specific solution. For an analysis on the results and a breakdown of my implementation, make sure to checkout the final section in this readme titled 'Reflection'.

**Requirements**
You will need to be able to run a java program which utilizes java 1.8 if you choose to run the jar file. If you wish to compile and run by command line, then any current java version should work.

**User Manual**
Below I have included two methods for running the program and they both will contain the same outputs.

Method 1: Running the program via a Jar file

Step one: Clone the repository and navigate to the project directory
![image](https://user-images.githubusercontent.com/49106114/157729600-1a703cd1-c9fc-4f15-9506-ca4b0a1a63ad.png)

Step two: Run the command "java -jar Algorithms-Extracredit.jar"
![image](https://user-images.githubusercontent.com/49106114/157729687-f7f93ca9-b99e-4411-8f50-543ccf26dd75.png)

Step three: You will be prompted to input a text file which contains the points. For ease of running the program, a text file called "input.txt" has already been provided for you to run.
![image](https://user-images.githubusercontent.com/49106114/157729725-f787d689-9bbe-4d3b-92c5-6279d8c235f6.png)

Step four: Thats all! Now you should be able to see the statistics for the runtime of each implementation. In the screenshot below, I have included an example output of the program when run on my machine.
![image](https://user-images.githubusercontent.com/49106114/157733440-e42e77fa-58b7-445f-bd6a-949cdbd71409.png)

Method 2: Compile and run 
Step one: Clone the repository and first navigate to the project directory. Then navigate into the src folder where it contains Main.java. 
![image](https://user-images.githubusercontent.com/49106114/157730163-97692f77-0acf-4709-bb37-9505cecf3c12.png)

Step two: Run the command "javac Main.java" and then "java Main"
![image](https://user-images.githubusercontent.com/49106114/157730340-f545327b-1d31-4176-ae8b-fe6023c764fb.png)

Step three: You will be prompted to input a text file which contains the points. For ease of running the program, a text file called "input.txt" has already been provided for you to run.
![image](https://user-images.githubusercontent.com/49106114/157730440-89f48053-51fb-47d4-a845-6e15fec92ad7.png)

Step four: Thats all! Now you should be able to see the statistics for the runtime of each implementation. In the screenshot below, I have included an example output of the program when run on my machine.
![image](https://user-images.githubusercontent.com/49106114/157733196-10eed7f2-4846-4867-bb89-c8fb0091530e.png)

**Reflection**
Write the reflection about getting the small groups in the minimum number of iterations, etc.
