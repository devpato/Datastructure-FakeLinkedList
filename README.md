# DS-FakeLInkedList
An array-based linked list is similar to our Node-based linked list. However, instead of allocating a single Node at a time, we allocate an array of Nodes at a time and the Nodes contain the data and a Next pointer to the next piece of data (or -1 for null). The array contains a bunch of null nodes at the beginning and these represent the set of free nodes. Free nodes are all linked together just like a linked list, but you wouldn’t want to look at the data. When space is needed, we must check if we have a free node, if we do, put that in our list and fix the free list. When we remove a node, give it back to the free list. We are essentially managing two lists.
1)	In this assignment you will be creating an array-based linked list. Not just in any old array, but within an object that represents memory. [85%]
a.	Review the API for the FakeArray, FakePointer and Memory (see cs3410_hw03doc.docx)
b.	Using the given API, generate a LinkedListArray class that can handle the given LinkedListTester class.
c.	You only need the constructors used by LinkedListTester.
d.	Upon construction, a Memory object will be given. You must save a reference to that object. 
e.	The copy constructor will copy the reference to the Memory object but must request its own FakeArray  in order to store the data from the source.
i.	The result of the copy will be the data in contiguous order.
f.	insert returns false only if the free pointer is -1.
g.	insertFront returns false only if the free pointer is -1.
h.	remove removes only the first occurrence of the requested data.
i.	find finds only the first occurrence of the requested data.
j.	dataAt treats first as index 0, and increments by one for each Node traveled to.
k.	getCapacity returns the maximum capacity of the container, as far as it knows.
l.	getSize returns the number of objects our container can properly access.
m.	rawOutput should display information about first, free, the starting address of the array as well as outputting all of the locations within the array, their values and corresponding next values.
2)	One, unfortunate problem with an array-based linked list is that when it runs out of room, it is full. However, we can attach the full array-based linked list to a nice new set of Nodes. [15%]
a.	Create a method in LinkedListArray that can acquire an additional FakeArray when the list is full in order to increase capacity.
i.	The capacity of that additional FakeArray should be double the current capacity.
b.	Remember to increase capacity, of course.
c.	The LinkedListArray must maintain a list of all of FakeArray objects that it owns (you can add an array of FakeArray objects and increase that in size each time).
d.	When the new FakeArray is allocated, free will point to it.
e.	Add a deallocate method that empties the LinkedListArray. All of the FakeArray objects are returned to Memory (using deallocate), we remove them from our tracking, and set ourselves to empty.


To save paper, you can copy and paste all the code into one file (be careful of autocorrections).


 
The Memory, FakeArray, FakePointer and Node classes allow the user to allocate a chunk of memory and derive dynamically allocated arrays of Strings and Next pointers in an addressable memory space.

Class Node:
A Node object contains a String of data and a next pointer (FakePointer) that contains the memory value of the next object in memory (when set properly).
	Default constructor: creates a Node with default, useless values.
	Copy constructor: creates Node from source node
	Node(String): creates a Node using the given String, its next pointer is invalid.
	String getData(): Returns the String
	FakePointer getNext(): Returns the next pointer.
	String setData(String): Sets the Node’s String and returns it.
	FakePointer setNext(FakePointer): Sets the Node’s FakePointer and returns it.
	String toString(): Generates a String containing the data and the value of the FakePointer, separated by a space.

Class FakePointer:
A FakePointer contains an integer value representing the location of a piece of data contained in the Memory object.
	Default constructor: Creates FakePointer with value 0 (null).
	Copy Constructor: creates FakePointer from a source FakePointer
	FakePointer(int): Creates a FakePointer using int as the starting value.
	int set(int): Sets FakePointer to the specified value and returns it.
	FakePointer set(FakePointer): Sets FakePointer to the value from the specified FakePointer and returns itself.
	int get(): Returns the value of the FakePointer
	int add(int): Adds a value (can be negative) to the FakePointer.
	String toString(): Generates a string containing the value of the FakePointer.

Class FakeArray:
A FakeArray simulates dynamically allocated memory that can be accessed by index or pointer. This memory space comes from a Memory object.
	Default constructor: Not safe for userland use. Acquire your FakeArray objects from Memory.
	int length(): Returns the length of the FakeArray
	Node getAt(int): Returns the Node at the specified index.
	Node getAt(FakePointer): Returns the Node at the specified FakePointer
	String setAt(int, String): Sets the String contained within the Node at the specified index to the specified String and returns that String.
	FakePointer setAt(int, FakePointer): Sets the FakePointer contained within the Node at the specified index to the specified FakePointer and returns that FakePointer.
	Node setAt(int, Node): Sets the Node contained at the specified index to the specified Node and returns that Node.
	String setAt(FakePointer, String): Sets the String contained within the Node at the specified FakePointer to the specified String and returns that String.
	FakePointer setAt(FakePointer, FakePointer): Sets the FakePointer contained within the Node at the specified FakePointer to the specified FakePointer and returns that FakePointer.
	Node setAt(FakePointer, Node): Sets the Node contained at the specified FakePointer to the specified Node and returns that Node.
	FakePointer addressOf(): Returns a FakePointer containing the location ofthe FakeArray.

Class Memory:
This class represents a chunk of managed memory and generates FakeArray objects for the user.
	Default Constructor: Creates Memory Object with debug and resizeAllowed set to false.
	boolean toggleDebug(): Inverts the debug flag and returns it. The debug flag adds additional output to the actions of the Memory object for debugging purposes.
	boolean toggleResizeAllowed(): Inverts the resizeAllowed flag and returns it. This flag determines if the Memory object will resize itself when it can not fulfill allocation requests.
	FakeArray allocate(int): Returns a FakeArray of the desired size. Returns null upon failure.
	boolean deallocate(FakeArray): Tells the Memory object that the specified FakeArray will no longer be used to free up its allocation of space within the Memory object. Returns false if not found. This does not conform to “double free” errors.
	Node getAt(FakePointer, int): Returns the Node at the specified FakePointer + int as an offset.
	Node getAt(FakePointer): Returns the Node at the specified FakePointer.
	FakePointer addressOf(int): Converts a 0-indexed int to an equivalent FakePointer.
	FakePointer addressOf(FakeArray): Returns a FakePointer that refers to the specified FakeArray.
	String toString(): Textual display of all Memory allocations.
