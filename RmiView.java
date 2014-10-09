/*RmiView.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program implements the GUI for game called precision
 * using swings, Its a two player game, where a each player has
 * to get the sum from given set of  numbers as close as possible 
 * to system generated number (randomly generated). 
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

/**
 * this class is view class of MVC and it has all the tools
 * to bulid the gui for the game
 */

public class RmiView {
	RmiModel model;
	static JFrame frame =null;  //Jframe variable
	private JLabel label = null;
	private JLabel label2 = null;
	private JLabel randomnum=null;
	private JLabel status;
	private JLabel state;
	private JButton button2[]=new JButton[11];
	private JDialog dialog=new JDialog();

	/**
	 * this is the constructor 
	 * @param  modle  initialies the object
	 */
	
	RmiView(RmiModel model){
		this.model=model;
		start();
	}

	/**
	 * this method starts the intial structure of gui
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
		this.frame=frame;
		Component contents = this.createComponents();
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
	 * this method shutdown the gui
	 */
	
	public void stop(){
		frame.setVisible(false);
		frame.dispose();
	}
	
	/**
	 * this method creates Jlabel components for
	 * displaying current players turn
	 * 
	 * @return 	pane;
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
		this.randomnum=randomnum;
		randomnum.setFont(new Font("Courier New", Font.ITALIC, 36));
		c.weightx =1;
		//String ran=model.getrandomnum();
		randomNumSet(model.getrandomnum());
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
			button2[index+5] = 
				new JButton(new Integer(index).toString());
			button2[index+5].
				setPreferredSize(new Dimension(100,100));
			innerpane.add(button2[index+5]); // adding to jpanel
			pane.add(innerpane);
		}
		return pane;
	}

	/**
	 * this method sets the status of the game
	 * @return pane
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

	public Component howToPlay(){
		JPanel pane = new JPanel();
		JButton how=new JButton("How to play");
		how.setToolTipText("Select numbers such that your sum is "
				+ "close to the random number." 
				+ "d=distance from the number");
		pane.add(how);
		return pane;
	}
	 

	/**
	 * this method sets the other methods in working
	 * @retun pane
	 */
	
	public Component createComponents() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS) );
		pane.add(createLabel());
		pane.add(createButtons());
		pane.add(createStatus());
		pane.add(howToPlay());
		createDialog();
		return pane;
	}

	/**
	 * this method creates dialog box after each players turn
	 */
	
	public void createDialog(){
		state=new JLabel();
		state.setFont(new Font("Courier New", Font.ITALIC, 20));
		dialog.add(state);
		dialog.setMinimumSize(new Dimension(500,75));
		dialog.setTitle("Wait");
		dialog.setLocationRelativeTo(null);
		dialog.pack();
	}

	/**
	 * this method sets the dialog box text
	 * @param  text  text to be set
	 */
	
	public void setDialog(String text){
		state.setText(text);
	}

	/**
	 * this method makes the dialog box visible
	 * @param  visible
	 */
	
	public void dialogvisible(boolean visible){
		dialog.setVisible(visible);
	}

	/**
	 * this method responds when an action is recived
	 */
	
	void buttonListener(ActionListener bListener,int index){
		button2[index].addActionListener(bListener);
	}

	/**
	 * this method disable the button after pressed
	 * @param  index   button at that index to be disable
	 */
	
	void disableButton(int index){
		button2[index].setEnabled(false);
	}

	/**
	 * this method sets the randoum num as text of frame
	 * @param  randonNum  to be displayed
	 */
	void randomNumSet(String randomNum){
		randomnum.setText(randomNum);
	}

	/**
	 * this method sets the text on the label
	 * @param text to be displayed and set
	 */
	void labelSet(String text){
		label.setText(text);
	}

	/**
	 * this method sets the label_2's text
	 * @param  text  to be displayed and set
	 */
	void label_2Set(String text){
		label2.setText(text);
	}

	/**
	 * this method sets the status
	 * @param  text  text to be set
	 */
	void statusSet(String text){
		status.setText(text);
	}

	/**
	 * this method returns the random number
	 * @return randomnum 
	 */
	String getRandomNum(){
		return randomnum.getText();
	}
}
