//----------------------------------------------------------------------------------------
/* Name: Andrew Fugate
 Course: CNT 4714 – Fall 2024
 Assignment title: Project 1 – An Event-driven Enterprise Simulation
 Date: Sunday September 8, 2024
*/
//----------------------------------------------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//----------------------------------------------------------------------------------------
public class project1{
	
	static int itemCounter = 1;
    static int cartItemCount = 0;

    static ArrayList < ArrayList < String > > CSVarray = new ArrayList<>();
    static ArrayList < String > TopThirdDetailsOfCartItemsForPopUp = new ArrayList<>();
    
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
    
    static JTextField cartItem1 = new JTextField();
    static JTextField cartItem2 = new JTextField();
    static JTextField cartItem3 = new JTextField();
    static JTextField cartItem4 = new JTextField();
    static JTextField cartItem5 = new JTextField();
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
	    int frameWidth = 850;
	    int topThirdHeight = frameHeight / 3;
	    int middleThirdStart = topThirdHeight;
	    int bottomThirdStart = 2 * frameHeight / 3;

	    int CartItemWidth = 800;
	    
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

	    // Top third spacing
	    int topThirdVerticalSpacing = topThirdHeight / 4;
	    startPage.add(line1).setBounds(10, 10, 200, 30);
	    startPage.add(itemIdInput).setBounds(220, 10, 300, 30);

	    startPage.add(line2).setBounds(10, 10 + topThirdVerticalSpacing, 200, 30);
	    startPage.add(quantityInput).setBounds(220, 10 + topThirdVerticalSpacing, 300, 30);

	    startPage.add(line3).setBounds(10, 10 + 2 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(itemDetailsOutput).setBounds(220, 10 + 2 * topThirdVerticalSpacing, 300, 30);

	    startPage.add(line4).setBounds(10, 10 + 3 * topThirdVerticalSpacing, 200, 30);
	    startPage.add(subtotalOutput).setBounds(220, 10 + 3 * topThirdVerticalSpacing, 300, 30);

	    // Middle third section
	    JLabel cartTitle = new JLabel("Shopping Cart");
	    
	    cartItem1.setEditable(false);
	    cartItem2.setEditable(false);
	    cartItem3.setEditable(false);
	    cartItem4.setEditable(false);
	    cartItem5.setEditable(false);

	 // Middle third spacing
	    int middleThirdVerticalSpacing = topThirdHeight / 7;
	    startPage.add(cartTitle).setBounds(10, middleThirdStart + 10, 200, 30);
	    startPage.add(cartItem1).setBounds(10, middleThirdStart + 10 + middleThirdVerticalSpacing, CartItemWidth, 30);
	    startPage.add(cartItem2).setBounds(10, middleThirdStart + 10 + 2 * middleThirdVerticalSpacing, CartItemWidth, 30);
	    startPage.add(cartItem3).setBounds(10, middleThirdStart + 10 + 3 * middleThirdVerticalSpacing, CartItemWidth, 30);
	    startPage.add(cartItem4).setBounds(10, middleThirdStart + 10 + 4 * middleThirdVerticalSpacing, CartItemWidth, 30);
	    startPage.add(cartItem5).setBounds(10, middleThirdStart + 10 + 5 * middleThirdVerticalSpacing, CartItemWidth, 30);


	    // Bottom third section
	    int buttonVerticalSpacing = topThirdHeight / 4;
	    startPage.add(searchButton).setBounds(10, bottomThirdStart + 10, 200, 30);
	    startPage.add(viewCartButton).setBounds(10, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(emptyCart).setBounds(10, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    startPage.add(addItemButton).setBounds(220, bottomThirdStart + 10, 200, 30);
	    startPage.add(checkoutButton).setBounds(220, bottomThirdStart + 10 + buttonVerticalSpacing, 200, 30);
	    startPage.add(exitButton).setBounds(220, bottomThirdStart + 10 + 2 * buttonVerticalSpacing, 200, 30);

	    // ActionListeners for buttons
	    searchButton.addActionListener( e -> searchCSV() );
	    
	    addItemButton.addActionListener( e -> addToCartButtonPush() );
	    
	    emptyCart.addActionListener( e -> resetApp( startPage ) );
	    
	    viewCartButton.addActionListener( e -> viewCartButton() );

	    checkoutButton.addActionListener( e -> checkoutButtonClick() );
	    
	    exitButton.addActionListener( e -> System.exit(0) );

	    startPage.setVisible(true);
	    
	    ButtonsOnLaunch();
	}
//----------------------------------------------------------------------------------------	
	private static void checkoutButtonClick()
	{
		ButtonsAfterCheckoutClick();
		
	    SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" , Locale.getDefault() );
	    
	    String currentDateTime = sdf.format( new Date() );

	    StringBuilder checkoutSummary = new StringBuilder();

	    checkoutSummary.append( "Date and Time: " ).append( currentDateTime ).append("\n\n");

	    checkoutSummary.append( "Number of line items: " ).append( cartItemCount ).append("\n\n");

	    checkoutSummary.append( "Item# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n" );

	    if( !cartItem1.getText().isEmpty() )
	    {
	    	checkoutSummary.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(0) ).append("\n");
	    }
	    
	    if( !cartItem2.getText().isEmpty() )
	    {
	    	checkoutSummary.append("2. ").append( TopThirdDetailsOfCartItemsForPopUp.get(1) ).append("\n");
	    }
	    
	    if( !cartItem3.getText().isEmpty() )
	    {
	    	checkoutSummary.append("3. ").append( TopThirdDetailsOfCartItemsForPopUp.get(2) ).append("\n");
	    }
	    
	    if( !cartItem4.getText().isEmpty() )
	    {
	    	checkoutSummary.append("4. ").append( TopThirdDetailsOfCartItemsForPopUp.get(3) ).append("\n");
	    }
	    
	    if( !cartItem5.getText().isEmpty() )
	    {
	    	checkoutSummary.append("5. ").append( TopThirdDetailsOfCartItemsForPopUp.get(4) ).append("\n");
	    }

	    double subtotal = calculateOrderSubtotal();
	    
	    checkoutSummary.append( "\nOrder subtotal: $" ).append( String.format( "%.2f", subtotal ) ).append("\n\n");

	    double taxRate = 0.06; //6%
	    
	    double taxAmount = subtotal * taxRate;
	    
	    checkoutSummary.append( "Tax rate: 6%\n" );
	    checkoutSummary.append( "Tax amount: $" ).append( String.format( "%.2f", taxAmount ) ).append("\n");
	    
	    checkoutSummary.append( "ORDER TOTAL: $").append( String.format( "%.2f", subtotal + taxAmount ) ).append( "\n\nThanks for shopping at Nile Dot Com!");
	    
	    OutputCSV( checkoutSummary );
	    
	    //System.out.println( checkoutSummary.toString() );
	    
	    JOptionPane.showMessageDialog(null, checkoutSummary.toString(), "Checkout Summary", JOptionPane.INFORMATION_MESSAGE);
	}
//----------------------------------------------------------------------------------------
	private static void OutputCSV( StringBuilder disp )
	{
		String Decon = disp.toString();
		
		String TransID = Decon.substring( 15, 34 );
		TransID = TransID.replace( "/", "" ).replace( ":", "" ).replace( " ", "" );
		
		int i = 0;
		
		for( String p : TopThirdDetailsOfCartItemsForPopUp )
		{
			String temp = TopThirdDetailsOfCartItemsForPopUp.get( i ).replace(" ", ", ").replace( "%", ".0" );
			String tempBTQ = temp.substring( 0, temp.indexOf( "\"" ) );
			
			String tempITQ = temp.substring( temp.indexOf( "\"" ), temp.indexOf( "$" ) );
			tempITQ = tempITQ.replace( ", " , " " );
			
			String tempATQ = temp.substring( temp.indexOf( "$" ) , temp.length() /*- 1*/ );
			
			String splitATQ = tempATQ;
			String ATD = null;
			
			for( int j = 0; j < 2; j++ )
			{
				splitATQ = splitATQ.substring( splitATQ.indexOf( "," ) + 1 );
			}
			
			splitATQ = splitATQ.trim();
			ATD = splitATQ;
			
			splitATQ = splitATQ.substring( 0 , splitATQ.indexOf( "," ) );
			ATD = ATD.substring( ATD.indexOf( "," ) + 2 );
			
			int ci = tempATQ.indexOf( "," );
			ci = tempATQ.indexOf( "," , ci + 1 );
			
			tempATQ = tempATQ.substring( 0 , ci + 1 );
			
			//System.out.println( TransID + ", " + tempBTQ + tempITQ.trim() + ", " + tempATQ + " " + String.format( "%.2f" , Double.parseDouble( splitATQ ) / 100 ) + ", " + ATD );
			
			String StoreInCSV = TransID + ", " + tempBTQ + tempITQ + tempATQ + " " + String.format( "%.2f" , Double.parseDouble( splitATQ ) / 100 ) + " " + ATD ;
			
			fileWrite( StoreInCSV );
			
			i++;
		}
		
		fileWrite( "EndOfCurrentTransactionAddTwoLinesForSpacing" );
	}
//----------------------------------------------------------------------------------------
	private static void fileWrite( String store ) 
	{
		if( store == "EndOfCurrentTransactionAddTwoLinesForSpacing" )
		{
			try( FileWriter writer = new FileWriter( "transactions.csv", true ) )
			{
		        writer.write( "\n\n" );
	        }
			
			catch( IOException e )
			{
	            e.printStackTrace();
	        }
		}
		
		else
		{
			try( FileWriter writer = new FileWriter( "transactions.csv", true ) )
			{
		        writer.write( store + "\n" );
	        }
			
			catch( IOException e )
			{
	            e.printStackTrace();
	        }
		}
	}
//----------------------------------------------------------------------------------------	
	private static double calculateOrderSubtotal()
	{
	    double subtotal = 0.0;

	    if( !TopThirdDetailsOfCartItemsForPopUp.isEmpty() )
	    {
	    	int index = 0;
	    	
	    	for( String s : TopThirdDetailsOfCartItemsForPopUp )
	    	{
	    		subtotal = subtotal + Double.parseDouble( TopThirdDetailsOfCartItemsForPopUp.get( index ).substring( TopThirdDetailsOfCartItemsForPopUp.get(index).length() - 4 ) );
	    		
	    		index++;
	    	}
	    }
	    
	    return Double.parseDouble( String.format( "%.2f" , subtotal ) );
	}
//----------------------------------------------------------------------------------------
	private static void viewCartButton()
	{
	    StringBuilder cartDetails = new StringBuilder();
	    
	    if( !cartItem1.getText().isEmpty() )
	    {
	        cartDetails.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(0) ).append("\n");
	    }
	    
	    if( !cartItem2.getText().isEmpty() )
	    {
	    	cartDetails.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(1) ).append("\n");
	    }
	    
	    if( !cartItem3.getText().isEmpty() )
	    {
	    	cartDetails.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(2) ).append("\n");
	    }
	    
	    if( !cartItem4.getText().isEmpty() )
	    {
	    	cartDetails.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(3) ).append("\n");
	    }
	    
	    if( !cartItem5.getText().isEmpty() )
	    {
	    	cartDetails.append("1. ").append( TopThirdDetailsOfCartItemsForPopUp.get(4) ).append("\n");
	    }

	    JOptionPane.showMessageDialog(null, cartDetails.toString(), "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
	}
//----------------------------------------------------------------------------------------	
	private static void addToCartButtonPush()
	{
		String itemDetails = itemDetailsOutput.getText();
		
		TopThirdDetailsOfCartItemsForPopUp.add( itemDetails );

		if( itemDetails.isBlank() )
		{
		    JOptionPane.showMessageDialog(null, "No item details to add", "Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		String[] parts = itemDetails.split(" "); // Split the string by spaces

		if( parts.length < 6 )
		{
		    JOptionPane.showMessageDialog(null, "Item details format is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		String sku = parts[0];
		String description = itemDetails.substring(itemDetails.indexOf('"'), itemDetails.lastIndexOf('"') + 1);

		// Adjust the order to match the correct labels
		String price = parts[parts.length - 4];    // Price Ea.
		String quantity = parts[parts.length - 3]; // Qty
		String discount = parts[parts.length - 2]; // Discount
		String total = parts[parts.length - 1];    // Total

		cartItemCount++;

		itemDetails = "Item " + cartItemCount + " - SKU: " + sku + ", Desc: " + description + ", Price Ea. " + price + ", Qty: " + quantity + ", Total: " + total;

	    switch( cartItemCount )
	    {
	        case 1: cartItem1.setText(itemDetails); break;
	        case 2: cartItem2.setText(itemDetails); break;
	        case 3: cartItem3.setText(itemDetails); break;
	        case 4: cartItem4.setText(itemDetails); break;
	        case 5: cartItem5.setText(itemDetails); break;
	        default:
	            JOptionPane.showMessageDialog(null, "Cart is full", "Error", JOptionPane.ERROR_MESSAGE);
	            break;
	    }

	    // Clear inputs and details
	    itemIdInput.setText("");
	    quantityInput.setText("");
	    itemDetailsOutput.setText("");
	    subtotalOutput.setText("");

	    // Update item counter
	    itemCounter++;
	    
	    searchButton.setText("Search for item " + itemCounter );
	    addItemButton.setText("Add Item " + itemCounter + " to Cart");
	    
	    if( itemCounter > 5 )
	    {
	    	cartFullButtons();
	    }
	    
	    else
	    {
	    	itemIdInput.setVisible(true);
	    	quantityInput.setVisible(true);
	    	ButtonsOnLaunch();
	    }
	    
	    line1.setText("Enter item ID for item " + itemCounter + ":");
	    line2.setText("Enter quantity for item " + itemCounter + ":");
	    line3.setText("Details for item " + itemCounter + ":");
	    line4.setText("Current sub-total for " + (itemCounter - 1) + " items:");
	    
	    subtotalOutput.setText( "$" + String.format( "%.2f", calculateOrderSubtotal() ) );
	    
	}
//----------------------------------------------------------------------------------------
	private static void cartFullButtons()
	{
		searchButton.setText("Search for item " + 5 );
	    addItemButton.setText("Add Item " + 5 + " to Cart");
	    
		searchButton.setEnabled( false );
	    viewCartButton.setEnabled( true );
	    emptyCart.setEnabled( true );
	    addItemButton.setEnabled( false );
	    checkoutButton.setEnabled( true );
	    exitButton.setEnabled( true );
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
				if( quantityInput.getText().isBlank() )//!has a quantity
				{
					JOptionPane.showMessageDialog(null, "Please Enter a Quantity", "Search Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if( Integer.parseInt( s.get(3) ) < Integer.parseInt( quantityInput.getText().trim() ) && s.get(2).toLowerCase().equals( "true" ) )//has enough quantity
				{
					JOptionPane.showMessageDialog(null, "Quantity Not Available", "Not Available", JOptionPane.ERROR_MESSAGE);
				}
				
				else if( s.get(2).toLowerCase().equals( "true" ) )//in stock
				{
					ButtonsAfterSearch();
	                updateDetailsLine( s, quantityInput.getText() );
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "Item Out of Stock", "Not Available", JOptionPane.ERROR_MESSAGE);
				}
				
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
			return Double.parseDouble( String.format( "%.2f" , quant * pricePer * multVal ) );
		}
		
		else
		{
			return Double.parseDouble( String.format( "%.2f" , quant * pricePer ) );
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
	    
	    if( itemCounter > 1 )
	    {
	    	viewCartButton.setEnabled( true );
	    	checkoutButton.setEnabled( true );
	    }
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
	    
	    itemIdInput.setVisible(false);
    	quantityInput.setVisible(false);
	}
//----------------------------------------------------------------------------------------	
	private static void ButtonsAfterCheckoutClick()
	{
		searchButton.setEnabled( false );
	    viewCartButton.setEnabled( false );
	    emptyCart.setEnabled( true );
	    addItemButton.setEnabled( false );
	    checkoutButton.setEnabled( false );
	    exitButton.setEnabled( true );
	    
	    itemIdInput.setEditable(false);
	    quantityInput.setEditable(false);
	}
//----------------------------------------------------------------------------------------
	private static void resetApp( JFrame frame )
	{
		frame.dispose();
	    
	    itemCounter = 1;
	    cartItemCount = 0;

	    initializeComponents();

	    searchButton.setText("Search for item " + itemCounter);
	    addItemButton.setText("Add Item " + itemCounter + " to Cart");
	    
	    topThirdLabels();
	    
	    itemIdInput.setText("");
	    quantityInput.setText("");
	    itemDetailsOutput.setText("");
	    subtotalOutput.setText("");
	    
	    cartItem1.setText("");
	    cartItem2.setText("");
	    cartItem3.setText("");
	    cartItem4.setText("");
	    cartItem5.setText("");
	    
	    startGUI();

	    frame.revalidate();
	    frame.repaint();
	}
//----------------------------------------------------------------------------------------
	private static void initializeComponents()
	{
	    itemIdInput = new JTextField();
	    quantityInput = new JTextField();
	    itemDetailsOutput = new JTextField();
	    subtotalOutput = new JTextField();

	    itemDetailsOutput.setEditable(false);
	    subtotalOutput.setEditable(false);

	    cartItem1 = new JTextField();
	    cartItem2 = new JTextField();
	    cartItem3 = new JTextField();
	    cartItem4 = new JTextField();
	    cartItem5 = new JTextField();

	    cartItem1.setEditable(false);
	    cartItem2.setEditable(false);
	    cartItem3.setEditable(false);
	    cartItem4.setEditable(false);
	    cartItem5.setEditable(false);

	    searchButton = new JButton("Search for item " + itemCounter);
	    addItemButton = new JButton("Add Item " + itemCounter + " to Cart");
	    viewCartButton = new JButton("View Cart");
	    emptyCart = new JButton("Empty Cart");
	    checkoutButton = new JButton("Checkout");
	    exitButton = new JButton("Exit (close app)");
	}
//----------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------