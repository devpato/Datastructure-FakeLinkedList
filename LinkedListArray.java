import java.util.*;
public class LinkedListArray
{
   private int size;
   private FakeArray adata;
   private FakeArray [] subMem;
   private Memory env;
   private FakePointer first;
   private FakePointer free;
   private int capacity;
   public LinkedListArray()
   {
      this(10, null);
   }
   public LinkedListArray(int capacity)
   {
      this(capacity, null);
   }
   public LinkedListArray(Memory memory)
   {
	   this(10,memory);
   }
   public LinkedListArray(int capacity,Memory memory)
   {
      this.capacity = capacity;
      this.env = memory;
      subMem  = new FakeArray[1];
      size = 0;
      adata = env.allocate(capacity);
      first = new FakePointer(-1);
      free = adata.addressOf();
      for(int i = 0; i < this.capacity; i++)
      {
          adata.getAt(i).setNext(new FakePointer((free.get()+ i)+1));
      }
	   adata.getAt(capacity - 1).setNext(new FakePointer(-1));
	   subMem[0] = adata;
   }
   private boolean  mySubMem()
   {
      	   FakeArray bdata = env.allocate(capacity*2);
	   free = bdata.addressOf();
	   if(bdata == null){
                return false;
           }

	   for(int i = 0; i < this.capacity*2 ; i++)
      {
          bdata.getAt(i).setNext(new FakePointer((free.get()+ i)+1));
      }
      bdata.getAt(capacity*2 - 1).setNext(new FakePointer(-1));
      this.capacity = (capacity * 2)+ capacity;
	   FakeArray[] temp = new FakeArray[subMem.length +1];
	   for(int i = 0;i<subMem.length;i++){
		   temp[i] = subMem[i];
	   }
	   temp[subMem.length] = bdata;
	   subMem = temp;
	   return true;
   }
   public boolean deallocate()
   {
      for(int i = 0; i < subMem.length; i++)
      {
		   env.deallocate(subMem[i]);
      }
   	first = new FakePointer(-1);
   	free = new FakePointer(-1);
   	this.capacity = 0;
   	this.size = 0;
   	subMem = new FakeArray[0];
	return true;
   }
   public LinkedListArray(LinkedListArray copyLinkarray )
   {
   	this(copyLinkarray.getCapacity(),copyLinkarray.env);
   	for(int i = 0;i<=copyLinkarray.getSize();i++)
      {
   		insert(copyLinkarray.dataAt(i));
   	}
   }
   public boolean insert(String data)
   {
      if(free.get() == -1)
      {
        if( mySubMem() == false){
		return false;
	}
      }
		if(free.get() == -1)
      {
        return false;
      }
		FakePointer fakeLastPoint =  new FakePointer(first);
		if(fakeLastPoint.get() == -1)
      {
			FakePointer fakeInsertlast = fakeLastPoint;
         size++;
         adata.getAt(free).setData(data);
			first = free;
         free = adata.getAt(free).getNext();
			adata.getAt(first).setNext(new FakePointer(-1));
			return true;
      }
		while(fakeLastPoint.get() != -1 )
      {
			if(adata.getAt(fakeLastPoint).getNext().get()  == -1)
         {
				FakePointer fakeInsertlast = fakeLastPoint;
            size++;
            adata.getAt(free).setData(data);
            FakePointer temp  =adata.getAt(free).getNext();
				adata.getAt(fakeLastPoint).setNext(free);
				adata.getAt(free).setNext(new FakePointer(-1));
				free = temp;
				return true;
	      }
        	   fakeLastPoint = adata.getAt(fakeLastPoint).getNext();
		} 

		return true;
   }
   public boolean insertFront(String data){
		if(free.get() == -1)
      {
        if( mySubMem() == false){
                return false;
        }
      }
		size++;
		adata.getAt(free).setData(data);
		FakePointer insertFakefront = adata.getAt(free).getNext();
		adata.getAt(free).setNext(first);
		first  = free;
		free = insertFakefront;
		return true;
   }
   public boolean remove(String data)
   {
   	FakePointer fakeprevious  = new FakePointer(-1);
   	FakePointer fakecurrent = first;
   	while(fakecurrent.get() != -1){
   		if(adata.getAt(fakecurrent).getData().equals(data))
         {
   			if(fakeprevious.get() == -1)
            {
   				first = adata.getAt(fakecurrent).getNext();
   
   			}
   			else{
   				FakePointer temp = adata.getAt(fakecurrent).getNext();
   				adata.getAt(fakeprevious).setNext(temp);
   			}
   			FakePointer temp = free;
   			free  = fakecurrent;
   			adata.getAt(fakecurrent).setNext(temp);
   			size--;
   			return true;
		}
	 	fakeprevious = fakecurrent;
	 	fakecurrent = adata.getAt(fakecurrent).getNext();

  	}
	   return false;
   }
   public int find(String toFind,int index)
   {
   	int counter = 0;
   	FakePointer fakefind = first;
   	while(fakefind.get() != -1)
      {
   		if(adata.getAt(fakefind).getData().equals(toFind))
         {
   		 if(index >= counter)
          {
   			return counter;
   		 }
   		}
   		counter++;
   		fakefind = adata.getAt(fakefind).getNext();
   	}
   	return -1;
   }
  public String dataAt(int index)
  {
   FakePointer fakeDataAt = first;
	while(fakeDataAt.get() != -1){
		if(index == 0)
      {
			return adata.getAt(fakeDataAt).getData();
		}
		fakeDataAt =  adata.getAt(fakeDataAt).getNext();
      index--;
	}
	return null;
 }
   public int getCapacity()
   {
      return capacity;
   }
   public int getSize()
   {
      return size;
   }
	public String toString()//Output
   {
   	String result = "[*";
   	FakePointer fakeoutput = first;
   	while(fakeoutput.get() != -1)
      {
   		result += adata.getAt(fakeoutput).getData() + " ";
   		fakeoutput =  adata.getAt(fakeoutput).getNext();
   	}
   	 return result + "*]";
	}
  public String rawOutput()
  {
	String result = "";
	result += "First fake pointer =  " + first + "\n" ;
	result += "Free fake pointer =  " + free + "\n";
	FakePointer raw = adata.addressOf();
	for(int j = 0;j< subMem.length;j++){
		for(int i = 0 ;i < subMem[j].length(); i++)
      {
		result +=  "The address is =  " + (subMem[j].addressOf().get()+i) +subMem[j].getAt(i).getData()+ " " + "next : " +subMem[j].getAt(i).getNext().get() +" "+ "\n";
		}
	}
	return result;
 }
}

