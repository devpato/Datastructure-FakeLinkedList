public class Node
{
  private String data;
  private FakePointer next;
  
  public Node()
  {
    this.data = null;
    this.next = new FakePointer(-1);
  }
  
  public Node(Node paramNode)
  {
    this.data = paramNode.data;
    this.next = paramNode.next;
  }
  
  public Node(String paramString)
  {
    this.data = paramString;
  }
  
  public String toString()
  {
    return this.data + " " + this.next;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public FakePointer getNext()
  {
    return this.next;
  }
  
  public String setData(String paramString)
  {
    return this.data = paramString;
  }
  
  public FakePointer setNext(FakePointer paramFakePointer)
  {
    return this.next = paramFakePointer;
  }
}

