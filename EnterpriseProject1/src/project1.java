//----------------------------------------------------------------------------------------
/* Name: Andrew Fugate
 Course: CNT 4714 – Fall 2024
 Assignment title: Project 1 – An Event-driven Enterprise Simulation
 Date: Sunday September 8, 2024
*/
//----------------------------------------------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
//----------------------------------------------------------------------------------------
public class project1{

    static ArrayList < ArrayList < String > > CSVarray = new ArrayList<>();
//----------------------------------------------------------------------------------------
	public static void main(String[] args)
	{
		openAndReadFile();
		
		arrayView();

		startGUI();
	}
//----------------------------------------------------------------------------------------
	private static void arrayView()
	{
		for( int i = 0; i < CSVarray.size(); i++ )
		{
		    ArrayList<String> row = CSVarray.get(i);
		    
		    System.out.println( "This is row " + i + " of the ArrayList and it contains:" );
		    
		    for( int j = 0; j < row.size(); j++ )
		    {
		        System.out.println( "Column " + (j + 1) + ": " + row.get(j) );
		    }
		    
		    System.out.println(); // Print a blank line for better readability between rows
		}
	}
//----------------------------------------------------------------------------------------
	private static void startGUI()
	{
	    JFrame startPage = new JFrame();

	    int frameHeight = 600;
	    int frameWidth = 600;
	    int topThirdHeight = frameHeight / 3;
	    int middleThirdStart = topThirdHeight;
	    int bottomThirdStart = 2 * frameHeight / 3;

	    startPage.setLayout(null); // Disable layout
	    startPage.setSize(frameWidth, frameHeight); // Frame size (width x, height y)
	    startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Top third section
	    JLabel line1 = new JLabel("Enter item ID for item #:");
	    JLabel line2 = new JLabel("Enter quantity for item #:");
	    JLabel line3 = new JLabel("Details for item #:");
	    JLabel line4 = new JLabel("Current sub-total for x items:");

	    JTextField itemIdInput = new JTextField();
	    JTextField quantityInput = new JTextField();
	    JTextField itemDetailsOutput = new JTextField();
	    JTextField subtotalOutput = new JTextField();
	    
	    itemDetailsOutput.setEditable(false);
	    subtotalOutput.setEditable(false);

	    //top third spacing
	    int topThirdVerticalSpacing = topThirdHeight / 4;
	    startPage.add(line1).setBounds(10, 10, 200, 30);
	    startPage.add(itemIdInput).setBounds(220, 10, 150, 30);

	    startPage.add(line2).setBounds(10, 10 + topThirdVerticalSpacing, 200, 30);
	    startPage.add(quantityInput).setBounds(220, 10 + topThirdVerticalSpacing, 150, 30);

	    startPage.add(line3).setBounds(10, 10 + 2 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(itemDetailsOutput).setBounds(220, 10 + 2 * topThirdVerticalSpacing, 150, 30);

	    startPage.add(line4).setBounds(10, 10 + 3 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(subtotalOutput).setBounds(220, 10 + 3 * topThirdVerticalSpacing, 150, 30);

	    //middle third section
	    JLabel cartTitle = new JLabel("Shopping Cart");
	    JTextField cartItem1 = new JTextField();
	    JTextField cartItem2 = new JTextField();
	    JTextField cartItem3 = new JTextField();
	    JTextField cartItem4 = new JTextField();
	    JTextField cartItem5 = new JTextField();

	    cartItem1.setEditable(false);
	    cartItem2.setEditable(false);
	    cartItem3.setEditable(false);
	    cartItem4.setEditable(false);
	    cartItem5.setEditable(false);

	    //middle third spacing
	    int middleThirdVerticalSpacing = topThirdHeight / 7;
	    startPage.add(cartTitle).setBounds(10, middleThirdStart + 10, 200, 30);
	    startPage.add(cartItem1).setBounds(10, middleThirdStart + 10 + middleThirdVerticalSpacing, 300, 30);
	    startPage.add(cartItem2).setBounds(10, middleThirdStart + 10 + 2 * middleThirdVerticalSpacing, 300, 30);
	    startPage.add(cartItem3).setBounds(10, middleThirdStart + 10 + 3 * middleThirdVerticalSpacing, 300, 30);
	    startPage.add(cartItem4).setBounds(10, middleThirdStart + 10 + 4 * middleThirdVerticalSpacing, 300, 30);
	    startPage.add(cartItem5).setBounds(10, middleThirdStart + 10 + 5 * middleThirdVerticalSpacing, 300, 30);

	    //bottom third section
	    JButton searchButton = new JButton("Search for item #");
	    JButton viewCartButton = new JButton("View Cart");
	    JButton emptyCart = new JButton("Empty Cart");
	    JButton addItemButton = new JButton("Add Item # to Cart");
	    JButton checkoutButton = new JButton("Checkout");
	    JButton exitButton = new JButton("Exit (close app)");

	    //bottom third spacing
	    int buttonVerticalSpacing = topThirdHeight / 4;
	    startPage.add(searchButton).setBounds(10, bottomThirdStart + 10, 200, 30);
	    startPage.add(viewCartButton).setBounds(10, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(emptyCart).setBounds(10, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    startPage.add(addItemButton).setBounds(220, bottomThirdStart + 10, 200, 30);
	    startPage.add(checkoutButton).setBounds(220, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(exitButton).setBounds(220, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    //ActionListeners for buttons
	    searchButton.addActionListener(e -> System.out.println("Search button clicked"));
	    viewCartButton.addActionListener(e -> System.out.println("View Cart button clicked"));
	    emptyCart.addActionListener(e -> System.out.println("Empty Cart button clicked"));
	    addItemButton.addActionListener(e -> System.out.println("Add Item button clicked"));
	    checkoutButton.addActionListener(e -> System.out.println("Checkout button clicked"));
	    exitButton.addActionListener(e -> System.exit(0));

	    startPage.setVisible(true);
	}
//----------------------------------------------------------------------------------------

	private static void openAndReadFile()
	{
		try
		{
			int scanLoop = 0;
			
	        File inv = new File( "inventory.csv" );
	        Scanner scanner = new Scanner( inv );
	
	        while( scanner.hasNextLine() )
	        {
	            String line = scanner.nextLine();
	            //System.out.println( line );
	            parseToDoubleArray( line, scanLoop );
	            scanLoop++;
	        }
	
	        scanner.close();
        }
		
		catch( FileNotFoundException e )
		{
            System.out.println( "File not found: " + e.getMessage() );
        }
	}
//----------------------------------------------------------------------------------------
	static void parseToDoubleArray( String scan, int index )
	{
		String[] passed = scan.split(","); // Split the line by commas
	    ArrayList < String > row = new ArrayList<>();

	    for( String delinatedPart : passed )
	    {
	        row.add( delinatedPart.trim() );
	    }
	    
        CSVarray.add( row ); // Add new row if index is out of bounds
	}
//----------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------