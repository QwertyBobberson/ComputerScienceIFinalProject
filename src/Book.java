
public class Book
{
    public String name;
    public String dateOut;
    public String dateIn;
    public Boolean in = true;
    
    public Book(String _name)
    {
	name = _name;
    }
    
    public void PrintInfo()
    {
	System.out.println(name + "\n" + "In: " + in);
	if(!in)
	{
	    System.out.println("Checked Out: " + dateOut + "\n Expected Back: " + dateIn);
	}
	System.out.println();
    }
    
    public boolean equals(Book other)
    {
	return this.name == other.name;
    }
}
