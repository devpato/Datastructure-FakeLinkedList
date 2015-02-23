public class FakePointer
{
  private int value;
  
  public FakePointer()
  {
    this.value = 0;
  }
  
  public FakePointer(int paramInt)
  {
    this.value = paramInt;
  }
  
  public FakePointer(FakePointer paramFakePointer)
  {
    this.value = paramFakePointer.value;
  }
  
  public int add(int paramInt)
  {
    return this.value += paramInt;
  }
  
  public FakePointer set(FakePointer paramFakePointer)
  {
    this.value = paramFakePointer.value;
    return this;
  }
  
  public int set(int paramInt)
  {
    return this.value = paramInt;
  }
  
  public int get()
  {
    return this.value;
  }
  
  public String toString()
  {
    return "" + this.value;
  }
}

