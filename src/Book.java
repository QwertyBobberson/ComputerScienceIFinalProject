import java.time.LocalDateTime;
import java.util.Random;

public class Book
{
    public String name;
    public LocalDateTime dateOut;
    public LocalDateTime dateIn;
    public Boolean in = true;
    public String ISBN;
    
    public Book(String _name)
    {
	Random rand = new Random();
	name = _name;
	ISBN = String.format("%04d", rand.nextInt(9999));
    }
    
    public void PrintInfo()
    {
	System.out.println("ISBN: " + ISBN + "\n" + name + "\n" + "In: " + in);
	if(!in)
	{
	    System.out.println("Checked Out: " + dateOut + "\nExpected Back: " + dateIn);
	}
	System.out.println();
    }
    
    public boolean equals(Book other)
    {
	return this.name == other.name;
    }
}
