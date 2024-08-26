
/* Name: Andrew Fugate
 Course: CNT 4714 – Fall 2024
 Assignment title: Project 1 – An Event-driven Enterprise Simulation
 Date: Sunday September 8, 2024
*/
//--------------------------------------------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
//--------------------------------------------------------------------------------------
public class project1 {

	public static void main(String[] args)
	{
		//openAndReadFile();
		
		startGUI();
		
		//testingSwing();  
	}

	private static void startGUI()
	{
		JFrame startPage = new JFrame();
		startPage.setLayout(null); //disable layout
        startPage.setSize(362, 200); //frame size (x, y)
        startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        
        
        
        
        
		JButton searchButton = new JButton("Search for item #");
		JButton viewCartButton = new JButton("View Cart");
		JButton emptyCart = new JButton("Empty Cart");
		JButton addItemButton = new JButton("Add Item # to Cart");
		JButton checkoutButton = new JButton("Checkout");
		JButton exitButton = new JButton("Exit (close app)");

		startPage.add(searchButton).setBounds(1, 0, 150, 30);
		startPage.add(viewCartButton).setBounds(1, 50, 150, 30);
		startPage.add(emptyCart).setBounds(1, 100, 150, 30);
		startPage.add(addItemButton).setBounds(200, 0, 150, 30);
		startPage.add(checkoutButton).setBounds(200, 50, 150, 30);
		startPage.add(exitButton).setBounds(200, 100, 150, 30);
		
		startPage.setVisible(true);
	}

	private static void testingSwing()
	{
		JFrame f=new JFrame();//creating instance of JFrame  
        
		JButton b=new JButton("click");//creating instance of JButton  
		b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
		          
		f.add(b);//adding button in JFrame  
		          
		f.setSize(400,500);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible	
	}

	private static void openAndReadFile()
	{
		try
		{
	        File inv = new File( "inventory.csv" );
	        Scanner scanner = new Scanner( inv );
	
	        while( scanner.hasNextLine() )
	        {
	            String line = scanner.nextLine();
	            System.out.println( line );
	        }
	
	        scanner.close();
        }
		
		catch( FileNotFoundException e )
		{
            System.out.println( "File not found: " + e.getMessage() );
        }
	}
}
//--------------------------------------------------------------------------------------