import java.time.LocalDateTime;
import java.util.Random;

public class Member
{
    public String id;
    public String name;
    public Book[] booksOut;
    public static int maxBooks = 4;
    
    public Member(String _name)
    {
	Random rand = new Random();
	name = _name;
	id = String.format("%04d", rand.nextInt(9999));
	booksOut = new Book[maxBooks];
    }
    
    public void PrintInfo()
    {
	System.out.println("ID: " + id + "\nName: " + name + "\nBooks Checked Out: ");
	PrintCheckedOut();
	System.out.println();
    }
    
    public void PrintCheckedOut()
    {
	for(int i = 0; i < booksOut.length; i++)
	{
	    if(booksOut[i] != null)
	    {
		System.out.println("\t" + booksOut[i].ISBN + ": " + booksOut[i].name);
	    }
	}
    }
    
    public void CheckOut(Book book)
    {
	if(book.in)
	{
	    for(int i = 0; i < booksOut.length; i++)
	    {
		if(booksOut[i] == null)
		{
		    booksOut[i] = book;
		    System.out.println(name + " has checked out \"" + book.name + "\"\n");
		    book.in = false;
		    book.dateOut = LocalDateTime.now();
		    book.dateIn = LocalDateTime.now().plusWeeks(2);
		    return;
		}
	    }
	    
	    System.out.println(name + " already has " + maxBooks + " books checked out.\n");
	    
	}
    }
    
    public void CheckIn(Book book)
    {
	for(int i = 0; i < booksOut.length; i++)
	{
	    if(booksOut[i] != null)
	    {
		if(booksOut[i].equals(book))
		{
		    booksOut[i] = null;
		    System.out.println(name + " has checked \"" + book.name + "\" in.\n");
		    book.in = true;
		    book.dateIn = null;
		    book.dateOut = null;
		    return;
		}		
	    }
	}
	
	System.out.println(name + " does not have \"" + book.name + "\" checked out.\n");
    }
    
}
