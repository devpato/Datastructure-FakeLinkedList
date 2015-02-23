import java.util.Scanner;

public class LinkedListTester
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		LinkedListArray myList = null;

		Memory env = new Memory();
//		env.toggleDebug();

		System.err.printf("Enter number of tests: ");
		int numTests = input.nextInt();

		System.err.printf("Begin entering %d tests\n", numTests);
		for(int i=0;i<numTests;i++)
		{
			String theTest = input.next().toLowerCase();
			System.err.printf("Received %d: <%s>\n", i, theTest);
			if(theTest.equals("initialize"))
			{
				myList = new LinkedListArray(env);
//				myList.toggleDebug();
			}
			else if(theTest.equals("initializesize"))
			{
				myList = new LinkedListArray(input.nextInt(), env);
			}
			else if(theTest.equals("copy"))
			{
				LinkedListArray copyList = new LinkedListArray(myList);
				for(int j=0;j<myList.getSize();j++)
				{
					if(myList.dataAt(j).equals(copyList.dataAt(j)) == false)
					{
						System.out.printf("Mismatch in copied list\n");
					}
				}
				System.out.printf("%s\n",copyList.rawOutput());
				copyList = null;
			}
			else if(theTest.equals("insert"))
			{
				if(myList.insert(input.next()) == false)
				{
					System.out.printf("No room!\n");
				}
			}
			else if(theTest.equals("insertfront"))
			{
				if(myList.insertFront(input.next()) == false)
				{
					System.out.printf("No room\n");
				}
			}
			else if(theTest.equals("remove"))
			{
				if(myList.remove(input.next()) == false)
				{
					System.out.printf("Not found\n");
				}
			}
			else if(theTest.equals("find"))
			{
				String toFind = input.next();
				int offset = input.nextInt();
				if(myList.find(toFind, offset) == -1)
				{
					System.out.printf("Not found\n");
				}
			}
			else if(theTest.equals("output"))
			{
				System.out.printf("%s\n", myList);
			}
			else if(theTest.equals("size"))
			{
				System.out.printf("Size: %d\n", myList.getSize());
			}
			else if(theTest.equals("capacity"))
			{
				System.out.printf("Capacity: %d\n", myList.getCapacity());
			}
			else if(theTest.equals("dataat"))
			{
				int index = input.nextInt();
				System.out.printf("At %d: %s\n", index, myList.dataAt(index));
			}
			else if(theTest.equals("rawoutput"))
			{
				System.out.printf("%s\n", myList.rawOutput());
			}
			else
			{
				System.out.printf("Invalid test scenario.\n");
			}
			System.err.printf("Finished test %d.\n", i);
		}

	}
}