Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=FIRST -Darg1=SECOND -Darg2=THIRD
	 -Darg3=FOURTH -Darg4=FIFTH

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: 10/02/2017 ]

-----------------------------------------------------------------------

I have used Binary Search tree for this project.
BST allow fast lookup, addition and removal of items, and can be used 
to implement either dynamic sets of items, or lookup tables that allow 
finding an item by its key i.e. buId in our case.
Insertion : O(log n)
Deletion : O(height) i.e O(log n)
Traverasal : O(n)
Search : O(height) i.e O(log n)

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).
