# Algorithms-Extracredit
A work by: Tyler Watson

For this extra credit assignment, I decided to implement a divide and conquer algorithm which find a closest pair of points. To help demonstrate the importance and effectiveness of divide and conquer algorithms, I decided to create two implementations which can solve the closest pair of points problem: a brute force method and another using an efficient algorithm implementation. 

**Description**

For this project, I decided to use java to implement these two solutions to the closest pair of points problem. This problem is where you try and find the two points in a large data set of points that are the closests to each other. For instance, this problem can be applicable if you have a map with a hundreds of points which we will consider to be the location of a person within a city. In this situation, if you would want to find the two people in the city that are the closest distance to eachother then this is the type of problem you will end up needing to solve.

For implementing a solution to this problem, I took two approaches: a brute force approach and an efficient polytime approach (by utilizing my understanding of algorithms). The reason for picking these two approaches was to allow for good comparisons when it came to runtime and show the massive impact your algorithm can have when it comes to the runtime of a specific solution. For an analysis on the results and a breakdown of my implementation, make sure to checkout the final section in this readme titled 'Reflection'.

Video Demonstration: https://youtu.be/Vpg_nVb019s

**Requirements**

You will need to be able to run a java jar program which utilizes is built with OpenJDK 17.0.1, so it is important to make sure your system is compatible with this version of java. 

**User Manual**

Below I have included two methods for running the program and they both will contain the same outputs.

**Method 1:** Running the program via a Jar file

Step one: Clone the repository and navigate to the project directory
![image](https://user-images.githubusercontent.com/49106114/157729600-1a703cd1-c9fc-4f15-9506-ca4b0a1a63ad.png)

Step two: Run the command "java -jar Algorithms-Extracredit.jar"
![image](https://user-images.githubusercontent.com/49106114/157729687-f7f93ca9-b99e-4411-8f50-543ccf26dd75.png)

Step three: You will be prompted to input a text file which contains the points. For ease of running the program, a text file called "input.txt" has already been provided for you to run.
![image](https://user-images.githubusercontent.com/49106114/157729725-f787d689-9bbe-4d3b-92c5-6279d8c235f6.png)

Step four: Thats all! Now you should be able to see the statistics for the runtime of each implementation. In the screenshot below, I have included an example output of the program when run on my machine.
![image](https://user-images.githubusercontent.com/49106114/157733440-e42e77fa-58b7-445f-bd6a-949cdbd71409.png)

**Method 2:** Compile and run 

Step one: Clone the repository and first navigate to the project directory. Then navigate into the src folder where it contains Main.java. 
![image](https://user-images.githubusercontent.com/49106114/157730163-97692f77-0acf-4709-bb37-9505cecf3c12.png)

Step two: Run the command "javac Main.java" and then "java Main"
![image](https://user-images.githubusercontent.com/49106114/157730340-f545327b-1d31-4176-ae8b-fe6023c764fb.png)

Step three: You will be prompted to input a text file which contains the points. For ease of running the program, a text file called "input.txt" has already been provided for you to run.
![image](https://user-images.githubusercontent.com/49106114/157730440-89f48053-51fb-47d4-a845-6e15fec92ad7.png)

Step four: Thats all! Now you should be able to see the statistics for the runtime of each implementation. In the screenshot below, I have included an example output of the program when run on my machine.
![image](https://user-images.githubusercontent.com/49106114/157733196-10eed7f2-4846-4867-bb89-c8fb0091530e.png)

**Reflection**

One key concept this program is tasked to demonstrate is the importance of recognizing algorithms that are exponential and the importance of finding solutions that exist in polytime if possible. For instance, in this program we can observe two implementations of the closest pair of points problem. The first implementation is a brute force implementation which compares every possible combination by comparing every point to every other point. In practice, this implementation is highly inefficent because the time it would take to run this implementation would increase exponentially as the input (or set of points increases).

For the second implementation, I implemented the closest pair of points algorithm from the divide and conquer lecture we covered in class. This algorithm run's in O(nlogn) time complexity because the most expensive cost when it comes to time complexity, is the call to the MergeSort in the EfficientClosestPair method. As a result, this method runs in polytime and from the video posted in the description section we can observe that the efficient implementation of the closest pair problem ran approximately 400% faster when compared to the brute force alternative.

When it came to implementing the efficient solution, there were multiple pieces that came together in the code to make this implementation possible. First, I went and implemented a mergesort method which utilizes an algorithm technique called divide and conquer. As a result, I was able to presort the points by x value before passing them into the efficient implementation method. Next, in the EfficientClosestPair method, I first divided the points in half (based off the midpoint of the list of points). Then I would make a recursive call on the right and left halfs to calculate the closest point in each half. After that call, I used the min value to get a list of points which were within the min value of the line of separation and I removed the points that were outside this region. Next, I went from the bottom point up and compared the nearest 11 points within the region and checked if there was a new min. After this process I compared all three min values (left half, right half and middle region min) to return the pairing with the smallest distance. As a result, I was able to implement this algorithm in O(nlogn) time since the mergesort call was the most time expensive part of this implenetation.

In conclusion, I believe that the statistics gathered from comparing these two implementations display the critical importance of implementing solutions that can run efficiently rather then a "lazy" quick solution which tend to become exponential when it comes to time complexity. As we saw, with an input set of one hundred point, there was already a 400% difference in the run time between the brute force and efficient solution. Since the brute force solution is exponential and the efficient solution runs in O(nlogn), it is important to realize that as the input size increases so will the difference in runtime between the two methods.
