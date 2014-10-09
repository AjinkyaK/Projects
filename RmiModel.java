/*RmiModel.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program implements the logic for game called precision
 * using swings, Its a two player game, where a each player has
 * to get the sum from given set of  numbers as close as possible 
 * to system generated number (randomly generated). 
 * 
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.util.Random;

/**
 * this class contains the working and logic for game
 * precision. This class is a model of MVC
 * 
 */

public class RmiModel {
	private String label = null;
	private String label2 = null;
	private String status;
	private String randomnum;
	//private JButton restart;
	Random rand=new Random();
	private int numClicks;
	private int no;
	private int no2;
	private int player;
	private int distance1,distance2;
	int rand_num;

	/**
	 * this is constructor for class
	 */
	
	RmiModel(){
		setRandom();  //sets the random number
		numClicks=0;
		no=0;
		no2=0;
		player=1;
	}

	/**
	 * this method generates the random number.
	 * 
	 */

	public void setRandom(){
		rand_num=rand.nextInt(10)-5;
		randomnum=(new Integer(rand_num).toString());
	}

	/**
	 * this method keeps the track of the players turn
	 * @param  bnum  
	 */
	
	public void action(int bnum){
		numClicks++;    // counts the number of clicks
		if(numClicks%2==1){  // condition for click
			player=1; // setting the player
			no=no+bnum;
			status=("Player 2's turn");
		}
		else{
			player=2;
			no2=no2+bnum;
			status=("Player 1's turn");
		}

		sum(no,player);  //calling sum

		if(numClicks==11){  // condition for completion of game
			distance1=Math.abs(rand_num-no);
			distance2=Math.abs(rand_num-no2);
			// deciding who wins
			if(distance1>distance2)
				status=("Player 2 wins");
			else if(distance2>distance1)
				status=("Player 1 wins");
			else
				status=("Its a Draw");
		}
	}

	/**
	 * this method adds the score of each player
	 * after evey turn.
	 * @param  num       score
 	 * @param  player    players
	 */
	
	public void sum(int num,int player){
		if(player==1)
			label=(new Integer(no).toString()+
					" (d= "+Math.abs(rand_num-no)+")");
		else
			label2=(new Integer(no2).toString()+
					" (d= "+Math.abs(rand_num-no2)+")");
	}

	/**
	 * this method returns the label
	 * @return  label
	 */
	
	public String getlabel(){
		return label;
	}

	/**
	 * this method returns the label_2
	 * @return  label2
	 */
	
	public String getlabel_2(){
		return label2;
	}

	/**
	 * this method returns the status
	 * @return  status
	 */
	
	public String getstatus(){
		return status;
	}

	/**
	 * this method returns random number
	 */
	
	public String getrandomnum(){
		return randomnum;
	}
}
