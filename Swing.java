/*Swing.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program implements the GUI of game called precision
 * using swings, Its a two player game, where a each player has
 * to get the sum from given set of  numbers as close as possible 
 * to system generated number (randomly generated) 
 * 
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * this class contains the implementation of GUI for game
 * precision.
 * 
 */

public class Swing {
	
	static JFrame frame =null;  //Jframe variable
	//private JFrame frame;
	private JLabel label = null;
	private JLabel label2 = null;
	private JLabel status;
	private JButton restart;
	Random rand=new Random();
	private int numClicks = 0;
	int no=0;
	int no2=0;
	int player=1;
	int rand_num,distance1,distance2;

	/**
	 * this method creates Jlabel components for
	 * displaying current players turn
	 * 
	 * @return 	pane;
	 */
	
	public Component createHeader(){
		JPanel pane = new JPanel();  // object of JPanel
		pane.setBorder(BorderFactory.createBevelBorder(8));
		final JLabel head=new JLabel("Player 1 plays");
		pane.add(head);  
		return pane;
	}
	
	/**
	 * this method implements gridbaglayout for jpanel
	 * and include texts in jlabel it displays
	 * labels and current totals.
	 * 
	 * @return	pane
	 */
	
	public Component createLabel() {

		JPanel pane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		//implementing GridBagConstraints
		GridBagConstraints c = new GridBagConstraints(); 
		pane.setLayout(gridbag); // setting layout
		c.fill = GridBagConstraints.HORIZONTAL;

		final JLabel random = new JLabel("Random number"); //text
		random.setFont(new Font("Courier New", Font.ITALIC, 36));
		c.weightx =1; // for resizing
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(random,c);
		pane.add(random);
		// generatign random number label
		final JLabel randomnum = new JLabel();
		randomnum.setFont(new Font("Courier New", Font.ITALIC, 36));
		c.weightx =1;
		rand_num=rand.nextInt(10)-5;
		randomnum.setText(new Integer(rand_num).toString());
		c.ipadx=500;
		c.gridx = 4;
		c.gridy = 0;
		gridbag.setConstraints(randomnum,c);
		pane.add(randomnum);
		// text for player1
		final JLabel text1 = new JLabel("Player 1");
		// setting up attributes
		text1.setFont(new Font("Courier New", Font.BOLD, 36));
		c.weightx =1;
		c.insets = new Insets(100,0,0,0);  	//top padding
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(text1,c);
		pane.add(text1);
		//label to display sum for player 1
		final JLabel label = new JLabel("0");
		label.setFont(new Font("Courier New", Font.BOLD, 36));
		c.weightx =1;
		c.gridx = 4;
		c.gridy = 0;
		gridbag.setConstraints(label,c);
		pane.add(label);
		final JLabel text2 = new JLabel("Player 2");
		text2.setFont(new Font("Courier New", Font.BOLD, 36));
		c.weightx =1;
		c.insets = new Insets(200,0,0,0);  	//top padding
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(text2,c);
		pane.add(text2);
		final JLabel label2 = new JLabel("0");
		label2.setFont(new Font("Courier New", Font.BOLD, 36));
		c.weightx =1;
		c.gridx = 4;
		c.gridy = 0;
		gridbag.setConstraints(label2,c);
		pane.add(label2);
		label.setText("0");
		this.label = label; //setting the label
		this.label2=label2;
		return pane;
	}

	/**
	 * this method creates buttons for individual numbers
	 * 
	 * @return 	pane
	 */
	
	public Component createButtons() {
		JPanel pane = new JPanel();
		JPanel innerpane=new JPanel(new GridBagLayout());
		GridBagConstraints g=new GridBagConstraints();
		g.weightx=0;
		//border for inner panel
		innerpane.setBorder(BorderFactory.createMatteBorder(
                10, 10, 10, 10, Color.black));
		pane.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		// 1 row and 11 coloums
		pane.setLayout(new GridLayout(1, 11));
		for ( int index = -5; index <=5; index ++ )	{
			// creating  buttons
			final JButton button2 = 
				new JButton(new Integer(index).toString());
			button2.setPreferredSize(new Dimension(100, 100));
			button2.addActionListener( 
			   new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					action(e); //perform action on click 
					button2.setEnabled(false);
				}
			}
			);
			innerpane.add(button2); // adding to jpanel
			pane.add(innerpane);
		}
		return pane;
	}
	
	/**
	 * this method increments the sum and gives results
	 * it also computes the distance from the 
	 * target each player has at a each turn 
	 * 
	 * @param 	e  	input from click
	 */

	public void action(ActionEvent e){
		numClicks++;  
		if(numClicks%2==1){  // condition for click
			player=1;
			no=no+Integer.parseInt(e.getActionCommand());
			status.setText("Player 2's turn");
		}
		else{
			player=2;
			no2=no2+Integer.parseInt(e.getActionCommand());
			status.setText("Player 1's turn");
		}
		
		sum(no,player);  //calling sum

		if(numClicks==11){  // condition for completion of game
			restart.setEnabled(true);
			distance1=Math.abs(rand_num-no);
			distance2=Math.abs(rand_num-no2);
			// deciding who wins
			if(distance1>distance2)
				status.setText("Player 2 wins");
			else if(distance2>distance1)
				status.setText("Player 1 wins");
			else
				status.setText("Its a Draw");
		}
	}

	/**
	 * this method sets the text against each player
	 * 
	 * @param	num	number to be added to sum
	 * @param	player	player number
	 */
	
	public void sum(int num,int player){
		if(player==1)
			label.setText(new Integer(no).toString()+
					" (d= "+Math.abs(rand_num-no)+")");
		else
			label2.setText(new Integer(no2).toString()+
					" (d= "+Math.abs(rand_num-no2)+")");
	}

	/**
 	* this method displays the text 
 	* 
 	* @return	pane
 	*/

	public Component createStatus(){
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createBevelBorder(8));
		final JLabel head=new JLabel("Player 1's turn");
		head.setFont(new Font("Courier New", Font.ITALIC, 36));
		status=head;
		pane.add(head);
		return pane;
	}

	/**
	 *  this method is for new game and help buttons
	 *  it restarts a new game
	*
	 * @return 	pane
	 */
	
	public Component newGame(){
		JPanel pane = new JPanel();
		restart=new JButton("Click for new game");
		restart.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e){
					stop();
					start(); // starting fresh game
				}
			}
			);
		pane.add(restart);
		JButton how=new JButton("How to play");
		how.setToolTipText("Select numbers such that your sum is "
				+ "close to the random number." 
				+ "d=distance from the number");
		pane.add(how);
		return pane;
	}

	/**
	 * this method calls all the components requiered
	 * 
	 * @return 	pane
	 */
	
	public Component createComponents() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS) );
		pane.add(createLabel());
		pane.add(createButtons());
		pane.add(createStatus());
		pane.add(newGame());
		return pane;
	}

	/**
	 * this method starts the game when it is invoked
	 * 
	 */
	
	public void start() {
		String lookAndFeel;
		lookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel( lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame=new JFrame("Precision");
		Swing app = new Swing(); // new swing object
		this.frame=frame;
		Component contents = app.createComponents();
		frame.getContentPane().add(contents);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * this method is to close current games window
	 */
	
	public void stop(){
		frame.setVisible(false);
		frame.dispose();
	}
}
