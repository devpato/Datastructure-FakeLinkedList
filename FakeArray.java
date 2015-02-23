public class FakeArray
{
  public final int length;
  private final Memory memSpace;
  
  public FakeArray(Memory paramMemory, int paramInt)
  {
    assert ((paramMemory != null) && (paramInt >= 0)) : "Failed construction of FakeArray";
    this.memSpace = paramMemory;
    this.length = paramInt;
  }
  
  public int length()
  {
    return this.length;
  }
  
  public Node getAt(int paramInt)
  {
    return this.memSpace.getAt(this, paramInt);
  }
  
  public Node getAt(FakePointer paramFakePointer)
  {
    return this.memSpace.getAt(paramFakePointer);
  }
  
  public String setAt(int paramInt, String paramString)
  {
    return this.memSpace.setAt(this, paramInt, paramString);
  }
  
  public FakePointer setAt(int paramInt, FakePointer paramFakePointer)
  {
    return this.memSpace.setAt(this, paramInt, paramFakePointer);
  }
  
  public Node setAt(int paramInt, Node paramNode)
  {
    return this.memSpace.setAt(this, paramInt, paramNode);
  }
  
  public String setAt(FakePointer paramFakePointer, String paramString)
  {
    return this.memSpace.setAt(this, paramFakePointer, paramString);
  }
  
  public FakePointer setAt(FakePointer paramFakePointer1, FakePointer paramFakePointer2)
  {
    return this.memSpace.setAt(this, paramFakePointer1, paramFakePointer2);
  }
  
  public Node setAt(FakePointer paramFakePointer, Node paramNode)
  {
    return this.memSpace.setAt(this, paramFakePointer, paramNode);
  }
  
  public FakePointer addressOf()
  {
    return this.memSpace.addressOf(this);
  }
}

