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
	
	static int itemCounter = 1;

    static ArrayList < ArrayList < String > > CSVarray = new ArrayList<>();
    
    static JTextField itemIdInput = null;
    static JTextField quantityInput = null;
    static JTextField itemDetailsOutput = null;
    static JTextField subtotalOutput = null;
    
    static JLabel line1 = null;
    static JLabel line2 = null;
    static JLabel line3 = null;
    static JLabel line4 = null;
    
    static JButton searchButton = new JButton( "Search for item " + itemCounter );
    static JButton viewCartButton = new JButton( "View Cart" );
    static JButton emptyCart = new JButton( "Empty Cart" );
    static JButton addItemButton = new JButton( "Add Item " + itemCounter + " to Cart" );
    static JButton checkoutButton = new JButton( "Checkout" );
    static JButton exitButton = new JButton( "Exit (close app)" );
    
//----------------------------------------------------------------------------------------
	public static void main(String[] args)
	{
		openAndReadFile();

		startGUI();
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
	    topThirdLabels();

	    itemIdInput = new JTextField();
	    quantityInput = new JTextField();
	    itemDetailsOutput = new JTextField();
	    subtotalOutput = new JTextField();
	    
	    itemDetailsOutput.setEditable(false);
	    subtotalOutput.setEditable(false);

	    //top third spacing
	    int topThirdVerticalSpacing = topThirdHeight / 4;
	    startPage.add(line1).setBounds(10, 10, 200, 30);
	    startPage.add(itemIdInput).setBounds(220, 10, 300, 30);

	    startPage.add(line2).setBounds(10, 10 + topThirdVerticalSpacing, 200, 30);
	    startPage.add(quantityInput).setBounds(220, 10 + topThirdVerticalSpacing, 300, 30);

	    startPage.add(line3).setBounds(10, 10 + 2 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(itemDetailsOutput).setBounds(220, 10 + 2 * topThirdVerticalSpacing, 300, 30);

	    startPage.add(line4).setBounds(10, 10 + 3 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(subtotalOutput).setBounds(220, 10 + 3 * topThirdVerticalSpacing, 300, 30);

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

	    //bottom third section - moved to global vars
	    
	    //bottom third spacing
	    int buttonVerticalSpacing = topThirdHeight / 4;
	    startPage.add(searchButton).setBounds(10, bottomThirdStart + 10, 200, 30);
	    startPage.add(viewCartButton).setBounds(10, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(emptyCart).setBounds(10, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    startPage.add(addItemButton).setBounds(220, bottomThirdStart + 10, 200, 30);
	    startPage.add(checkoutButton).setBounds(220, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(exitButton).setBounds(220, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    //ActionListeners for buttons
	    searchButton.addActionListener( e -> searchCSV() );
	    
	    addItemButton.addActionListener(e -> addToCartButtonPush() );
	    
	    viewCartButton.addActionListener(e -> System.out.println("View Cart button clicked"));
	    emptyCart.addActionListener(e -> System.out.println("Empty Cart button clicked"));
	    
	    checkoutButton.addActionListener(e -> System.out.println("Checkout button clicked"));
	    exitButton.addActionListener(e -> System.exit(0));

	    startPage.setVisible(true);
	    ButtonsOnLaunch();
	}
//----------------------------------------------------------------------------------------
	private static void addToCartButtonPush()
	{
		
	}
//----------------------------------------------------------------------------------------
	private static void topThirdLabels()
	{
		line1 = new JLabel("Enter item ID for item " + itemCounter + ":");
	    line2 = new JLabel("Enter quantity for item " + itemCounter + ":");
	    line3 = new JLabel("Details for item " + itemCounter + ":");
	    line4 = new JLabel("Current sub-total for " + (itemCounter - 1) + " items:");
	}
//----------------------------------------------------------------------------------------
	private static void searchCSV()
	{		
		for( ArrayList < String > s : CSVarray )
		{			
			if( s.get(0).equals( itemIdInput.getText().trim() ) )//exists
			{
				if( quantityInput.getText().isBlank() )//has a quantity
				{
					JOptionPane.showMessageDialog(null, "Please Enter a Quantity", "Search Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if( Integer.parseInt( s.get(3) ) < Integer.parseInt( quantityInput.getText().trim() ) && s.get(2).toLowerCase().equals( "true" ) )//has enough quantity
				{
					JOptionPane.showMessageDialog(null, "Quantity Not Available", "Not Available", JOptionPane.ERROR_MESSAGE);
				}
				
				else if( s.get(2).toLowerCase().equals( "true" ) )//in stock
				{
	                updateDetailsLine( s, quantityInput.getText() );
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "Item Out of Stock", "Not Available", JOptionPane.ERROR_MESSAGE);
				}
				
				ButtonsAfterSearch();
				
				return;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Item ID Not Found", "Not Available", JOptionPane.ERROR_MESSAGE);
	}
//----------------------------------------------------------------------------------------
	private static void updateDetailsLine( ArrayList < String > s, String quant )
	{
        String id = s.get(0);
        String priceNotFormated = s.get(4);
        
        String descriptionForDisplay = s.get(1).substring(3, s.get(1).length() - 2);

        int discountValueAsPercent = discountValue( Integer.parseInt( quant ) );
        
        double SubForItemQuant = calculateItemSub( Double.parseDouble( s.get(4) ), Integer.parseInt( quant), discountValueAsPercent );
        
        itemDetailsOutput.setText( id + " " + descriptionForDisplay + " $" + priceNotFormated + " " + quant + " " + discountValueAsPercent + "% $" + SubForItemQuant );
	}
//-----------------------------------------------------------------------------------------
	private static double calculateItemSub( double pricePer, int quant, int disc )
	{
		if( disc > 0 )
		{
			double multVal = 1.0 - disc / 100.0;
			return quant * pricePer * multVal;
		}
		
		else
		{
			return quant * pricePer;
		}
	}
//----------------------------------------------------------------------------------------
	private static int discountValue( int quant )
	{
		if( quant > 14 )
		{
			return 20;
		}
		
		else if( quant > 9 )
		{
			return 15;
		}
		
		else if( quant > 4 )
		{
			return 10;
		}
		
		else
		{
			return 0;
		}
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
	            parseTo2dArray( line, scanLoop );
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
	static void parseTo2dArray( String scan, int index )
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
	private static void ButtonsOnLaunch()
	{
	    searchButton.setEnabled( true );
	    viewCartButton.setEnabled( false );
	    emptyCart.setEnabled( true );
	    addItemButton.setEnabled( false );
	    checkoutButton.setEnabled( false );
	    exitButton.setEnabled( true );
	}
//----------------------------------------------------------------------------------------
	private static void ButtonsAfterSearch()
	{
	    searchButton.setEnabled( false );
	    viewCartButton.setEnabled( false );
	    emptyCart.setEnabled( true );
	    addItemButton.setEnabled( true );
	    checkoutButton.setEnabled( false );
	    exitButton.setEnabled( true );
	}
//----------------------------------------------------------------------------------------
	private static void ButtonTempVis2()
	{
	    searchButton.setEnabled( true );
	    viewCartButton.setEnabled( false );
	    emptyCart.setEnabled( true );
	    addItemButton.setEnabled( false );
	    checkoutButton.setEnabled( false );
	    exitButton.setEnabled( true );
	}
//----------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------