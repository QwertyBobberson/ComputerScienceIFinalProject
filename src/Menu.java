import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Menu
{
    private static ArrayList<Member> members = new ArrayList<Member>();
    private static ArrayList<Book> books = new ArrayList<Book>();
    private static Scanner input = new Scanner(System.in);
    
    
    private static int choice;
    
    public static void ShowMenu()
    {
	Load();
	//Displays a list of the options
	System.out.println("1 - Members");
	System.out.println("2 - Books");
	System.out.println("3 - Checked Out Books");
	System.out.println("4 - Exit");
	
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
		ShowCheckedOutBooks();
		break;
	    case 4:
		Quit();
		break;
	    default:
		System.out.println("Please type the number that corresponds to the menu you wish to see.");
		return;
		
	}
	
	Save();
    }
    
    private static void ShowMembers()
    { 
	boolean found = false;
	do
	{
	    //List all members
	    for(int i = 0; i < members.size(); i++)
	    {
		if(members.get(i) != null)
		{
		    System.out.println(members.get(i).id + " - " + members.get(i).name);		
		}
	    }
	    
	    System.out.println("0 - New Member\n1 - Back");
	    input.nextLine();
	    String choice = input.next();
	    
	    if(choice.equals("0"))
	    {
		members.add(CreateNewMember());
		System.out.println("New Member Added, returning to menu.");
		return;
	    }
	    
	    if(choice.equals("1"))
	    {
		return;
	    }
	    
	    //Show the information of the selected member
	    for(int i = 0; i < members.size(); i++)
	    {
		if(members.get(i).id.equals(choice))
		{
		    ShowMember(members.get(i));
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
	    //List all the books
	    for(int i = 0; i < books.size(); i++)
	    {
		if(books.get(i) != null)
		{
		    System.out.println(books.get(i).ISBN + " - " + books.get(i).name);		
		}
	    }
	    
	    System.out.println("0 - Add New Book\n1 - Back");
	    input.nextLine();
	    String choice = input.next();
	    
	    if(choice.equals("0"))
	    {
		books.add(CreateNewBook());
		System.out.println("Book added to library. Returning to menu.");
		return;
	    }
	    
	    if(choice.equals("1"))
	    {
		return;
	    }
	    
	    for(int i = 0; i < books.size(); i++)
	    {
		if(books.get(i).ISBN.equals(choice))
		{
		    books.get(i).PrintInfo();;
		    found = true;
		}
	    }
	} while(!found);
	BookActionCompleted();
	
    }
    
    private static void ShowMember(Member member)
    {
	member.PrintInfo();
	//The options on a specific member's profile
	System.out.println("1 - Check Out");
	System.out.println("2 - Check In");
	System.out.println("3 - Back");
	
	
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
	    
	    
	    
	    System.out.println("Books Checked Out:");
	    member.PrintCheckedOut();
	    System.out.println("0: Exit");
	    System.out.println("Type the ISBN of the book to be checked in.");
	    
	    try
	    {
		choice = input.nextInt();
	    }
	    catch(Exception e)
	    {
		input.next();
		ShowCheckInMenu(member);
		return;
	    }
	    
	    for(int i = 0; i < books.size(); i++)
	    {
		if(books.get(i).ISBN.equals(Integer.toString(choice)))
		{
		    member.CheckIn(books.get(i));
		    selected = true;
		}
	    }
	    
	    
	    if(choice == 0)
	    {
		selected = true;
	    }
	    
	} while(!selected);
	
	UserActionCompleted(member);
	
	
    }
    
    private static void ShowCheckOutMenu(Member member)
    {     
	
	
	for(int i = 0; i < books.size(); i++)
	{
	    System.out.println(books.get(i).ISBN + ": " + books.get(i).name);
	}
	
	System.out.println("Type the ISBN of the book to check out.");
	
	try
	{	
	    
	    choice = input.nextInt();
	    for(int i = 0; i < books.size(); i++)
	    {
		if(String.format("%04d", choice).equals(books.get(i).ISBN))
		{
		    member.CheckOut(books.get(i));
		}
	    }
	}
	catch (Exception e)
	{
	    input.next();
	    ShowCheckOutMenu(member);
	    return;
	}
	
	UserActionCompleted(member);
	
    }

    private static void ShowCheckedOutBooks()
    {
	for(Book book : books)
	{
	    if(!book.in)
	    {
		System.out.println(book.ISBN + ": " + book.name);		
	    }
	}
	System.out.println("0: Exit");
	
	try
	{	
	    
	    choice = input.nextInt();
	    
	    if(choice == 0)
	    {
		return;
	    }
	    
	    for(int i = 0; i < books.size(); i++)
	    {
		if(String.format("%04d", choice).equals(books.get(i).ISBN))
		{
		    books.get(i).PrintInfo();
		}
	    }
	}
	catch (Exception e)
	{
	    input.next();
	    ShowCheckedOutBooks();
	    return;
	}
	
    }
    
    public static void UserActionCompleted(Member member)
    {    
	System.out.println("1 - Back To Menu\n2 - Back to Member Profile");
	
	
	
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
	System.out.println("1 - Back To Menu\n2 - Back to Book List");
	
	
	
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
    
    private static Member CreateNewMember()
    {
	System.out.print("First Name: ");
	String firstName = input.next();
	
	System.out.print("Last Name: ");
	String lastName = input.next();
	
	return new Member(firstName + " " + lastName);
    }
    
    private static Book CreateNewBook()
    {
	System.out.print("Name of Book: ");
	input.nextLine();
	String name = input.nextLine();
	return new Book(name);
    }
    
    private static void Quit()
    {
	Save();
	input.close();
	System.exit(0);
    }
    
    private static void Save()
    {
	try
	{   
	    PrintWriter b = new PrintWriter("Books.txt");
	    b.close();
	    
	    FileOutputStream booksFile = new FileOutputStream("Books.txt");
	    
	    ObjectOutputStream booksSave = new ObjectOutputStream(booksFile);
	    
	    booksSave.writeObject(books);
	    
	    
	    booksSave.close();
	    booksFile.close();
	    
	}
	catch (IOException e)
	{
	    System.out.println("Failed to save books.");
	}
	
	try
	{
	    PrintWriter m = new PrintWriter("Members.txt");
	    m.close();
	    
	    FileOutputStream membersFile = new FileOutputStream("Members.txt");
	    
	    ObjectOutputStream membersSave = new ObjectOutputStream(membersFile);
	    
	    membersSave.writeObject(members);
	    
	    
	    membersSave.close();
	    membersFile.close();
	    
	}
	catch (IOException e)
	{
	    System.out.println("Failed to save members.");
	}
	
	
    }
    
    private static void Load()
    {
	try
	{   
	    FileInputStream booksFile = new FileInputStream(new File("Books.txt"));
	    
	    ObjectInputStream booksSave = new ObjectInputStream(booksFile);
	    
	    try
	    {
		books = (ArrayList) booksSave.readObject();
	    }
	    catch (ClassNotFoundException e)
	    {
		e.printStackTrace();
	    }
		
	    
	    booksSave.close();
	    booksFile.close();
	    
	}
	catch (IOException e)
	{
	    System.out.println("Failed to load books.");
	}
	
	try
	{
	    FileInputStream membersFile = new FileInputStream(new File("Members.txt"));
	    
	    ObjectInputStream membersSave = new ObjectInputStream(membersFile);
	    
	    try
	    {
		members = (ArrayList) membersSave.readObject();
	    }
	    catch (ClassNotFoundException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
		
	    
	    membersSave.close();
	    membersFile.close();
	    
	}
	catch (IOException e)
	{
	    System.out.println("Failed to save members.");
	}
    }	    
}

