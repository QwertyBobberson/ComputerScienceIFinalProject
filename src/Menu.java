import java.util.Scanner;

public class Menu
{
    // private static Member[] members = new Member[4];
    // private static Book[] books = new Book[16];
    // private static Scanner input = new Scanner(System.in);
    
    //This sets the variables for testing, can be removed once saving is implemented
    private static Member[] members = {new Member("Ayssa Welk"), new Member("Qwerty Bobberson"), new Member("Gavin Brock"), new Member("Holly Meyer")};
    private static Book[] books = {new Book("Harry Potter"), new Book("Percy Jackson"), new Book("Magnus Chase"), new Book("Sword Art Online"), new Book("Boku No Hero Academia"), new Book("Legend of Zelda"), new Book("Doctor Who"), new Book("Dan Machi")};
    private static Scanner input = new Scanner(System.in);
    
    public static void ShowMenu()
    {
	//Displays a list of the options
	System.out.println("1 - Members");
	System.out.println("2 - Books");
	System.out.println("3 - Exit");
	
	int choice;	
	
	try
	{
	    choice = input.nextInt();
	}
	catch (Exception e)
	{
	    System.out.println("Please type the number that corresponds to your choice.");
	    input.next();
	    return;
	}
	
	//Logic for loading the selected menu
	switch(choice)
	{
	    case 1:
		ShowMembers();
		break;
	    case 2:
		ShowBooks();
		break;
	    case 3:
		Quit();
		break;
	    default:
		System.out.println("Please type the number that corresponds to the menu you wish to see.");
		return;
	}
	
	
    }
    
    private static void ShowMembers()
    { 
	boolean found = false;
	do
	{
	    System.out.println("\n\n\n");
	    //List all members
	    for(int i = 0; i < members.length; i++)
	    {
		if(members[i] != null)
		{
		    System.out.println(members[i].id + " - " + members[i].name);		
		}
	    }
	    
	    System.out.println("0 - Back");
	    input.nextLine();
	    String choice = input.next();
	    
	    if(choice.equals("0"))
	    {
		return;
	    }
	    
	    //Show the information of the selected member
	    for(int i = 0; i < members.length; i++)
	    {
		if(members[i].id.equals(choice))
		{
		    ShowMember(members[i]);
		    found = true;
		}
	    }
	}
	while(!found);
	
    }
    
    private static void ShowBooks()
    {	    
	boolean found = false;
	do
	{
	    System.out.println("\n\n\n");
	    //List all the books
	    for(int i = 0; i < books.length; i++)
	    {
		if(books[i] != null)
		{
		    System.out.println(books[i].ISBN + " - " + books[i].name);		
		}
	    }
	    
	    System.out.println("0 - Back");
	    input.nextLine();
	    String choice = input.next();
	    
	    for(int i = 0; i < books.length; i++)
	    {
		if(books[i].ISBN.equals(choice))
		{
		    System.out.println("\n\n\n");
		    books[i].PrintInfo();;
		    found = true;
		}
	    }
	} while(!found);
	BookActionCompleted();
	
    }
    
    private static void ShowMember(Member member)
    {
	System.out.println("\n\n\n");
	member.PrintInfo();
	//The options on a specific member's profile
	System.out.println("1 - Check Out");
	System.out.println("2 - Check In");
	System.out.println("3 - Back");
	int choice;
	
	try
	{	    
	    choice = input.nextInt();
	}
	catch(Exception e)
	{
	    input.next();
	    ShowMember(member);
	    return;
	}
	
	//Does the action the user asked for
	switch(choice) 
	{
	    case 1:
		ShowCheckOutMenu(member);
		break;
	    case 2:
		ShowCheckInMenu(member);
		break;
	    case 3:
		ShowMembers();
		break;
	    default:
		System.out.println("Please type the number that correspondes to the action you wish to take.");
		ShowMember(member);
		break;
	}
	
    }
    
    public static void ShowCheckInMenu(Member member)
    {
	
	boolean selected = false;
	
	do
	{
	    
	    System.out.println("\n\n\n");
	    int choice;
	    
	    System.out.println("Books Checked Out:");
	    member.PrintCheckedOut();;
	    System.out.println("Type the ISBN of the book to be checked in.");
	    choice = input.nextInt();
	    System.out.println("Choice: " + choice);
	    for(int i = 0; i < books.length; i++)
	    {
		if(books[i].ISBN.equals(Integer.toString(choice)))
		{
		    member.CheckIn(books[i]);
		    selected = true;
		}
	    }
	} while(!selected);
	
	UserActionCompleted(member);
	
	
    }
    private static void ShowCheckOutMenu(Member member)
    {     
	    System.out.println("\n\n\n");
	    int choice;
	    
	    for(int i = 0; i < books.length; i++)
	    {
		System.out.println((i + 1) + ": " + books[i].name);
	    }
	    
	    System.out.println("Please select a book to check out.");
	    
	    try
	    {	
		
		choice = input.nextInt();
		member.CheckOut(books[choice - 1]);
	    }
	    catch (Exception e)
	    {
		input.next();
		ShowCheckOutMenu(member);
		return;
	    }
	    
	UserActionCompleted(member);
	
    }
    
    
    public static void UserActionCompleted(Member member)
    {    
	System.out.println("\n\n\n");
	System.out.println("1 - Back To Menu\n2 - Back to Member Profile");
	
	int choice;
	
	try
	{
	    choice = input.nextInt();
	}
	catch(Exception e)
	{
	    input.next();
	    UserActionCompleted(member);
	    return;
	}
	
	switch(choice)
	{
	    case 1:
		return;
	    case 2:
		ShowMember(member);
		break;
	    default:
		System.out.println("Please type the number that correspondes to the action you wish to take.");
		UserActionCompleted(member);
		break;
	}
	
	
    }
    
    public static void BookActionCompleted()
    {
	System.out.println("\n\n\n");
	System.out.println("1 - Back To Menu\n2 - Back to Book List");
	
	int choice;
	
	try
	{
	    choice = input.nextInt();
	}
	catch(Exception e)
	{
	    input.next();
	    BookActionCompleted();
	    return;
	}
	
	switch(choice)
	{
	    case 1:
		return;
	    case 2:
		ShowBooks();
		break;
	    default:
		System.out.println("Please type the number that correspondes to the action you wish to take.");
		BookActionCompleted();
		break;
	}
	
    }
    
    private static void Quit()
    {
	input.close();
	System.exit(0);
    }
    
}

