package JunitTest;
import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class game {


    private InputStreamReader isr;
    private BufferedReader br;
   
    public static int roll;
    public static int dice1;
    public static int dice2;
    public static ArrayList<Player> players;
    public static boolean show = true;
    public static boolean showowner = true;
    public static boolean showwhere = true;
    public static int round = 1;
	public static int tax;
	public static int jailroll = 0;
	public int temp = 1;
	public int temp1 = 1;
	public int highest_money = 0;
	public boolean jail=true;

    public game() {
	players = new ArrayList<Player>();
	isr = new InputStreamReader(System.in);
	br = new BufferedReader(isr);


	roll = 0;

    }
   
  


    public static int Roll(Player s) {
        dice1 = (int) (Math.random() * 4) + 1;
	dice2 = (int) (Math.random() * 4) + 1;
	if (dice1 == dice2){
	    s.doubles=true;
	}
	if (s.inJail || dice1 != dice2) 
	    s.doubles = false;

       	roll = dice1 + dice2;
	return roll;
    }

    public static int Roll() {
	return dice1 + dice2;
    }

    public int playerlose() {
	for (int i=0;i<players.size();i++) {
	    if (players.get(i).money > 0) 
	    	if(temp == 1)
	    		temp = -1;
	    	else
	    		temp1 = -1;
    }
	return temp1;
    }
    
    public static boolean checkdouble() {
    	if(dice1 == dice2) 
    	return true;
    	else 
    		return false;
    }
    

  

    public Location getPlace(Player w, Board q) {
	return q.places.get(w.location);
    }


    public void payMoney(int i, Board z) {
	Player a = players.get(i);
   
	if (a.enoughMoney) {
	    //paying rent
	    if (getPlace(a, z).isPlace() && getPlace(a, z).getOwner() != null && getPlace(a, z).getOwner() != a) {
		a.payRent((Place)getPlace(a, z));
		}
	    //Chance/Other cards
	    else if(z.places.get(a.location).isOther()) {
		a.pay_or_get_getMoney(getPlace(a, z));

	    }
	}
	else {
	    notEnough(a);
	}
	
    }
  


  public void notEnough(Player p) {
	if (!(p.enoughMoney)) {
	    System.out.println("You don't have enough money!");
	}
    }

   
    public void afterRoll(int i, Board x) {
	Player r = players.get(i); 
	    r.move(roll, i, players);
	    payMoney(i, x);
	    show=true;
	    showowner=true;
	    if(showwhere==true) {
	    String s = "You are in : " + r.getPlace(x) ;
	    System.out.println(s);
	    }
	    showwhere=true;
	    //if this is an unbought place
	  
	    if (getPlace(r, x).isPlace() && getPlace(r, x).getOwner() == null){
	    	String temp="";
	    	temp = "Your Money: " + r.money + '\n';
	    	System.out.println(temp);
		System.out.println(menuAfterProp());
		System.out.println("Please input : ");
		if (read().equals("1")) {
			System.out.println();
		    r.buyplace((Place)getPlace(r, x));
		    }
		else {
		    System.out.println("\nYou did not buy " + getPlace(r, x) + "\n");
			
		}
	    }
	    
	}
    
    public void jail(int i,Board x) {
    	Player r = players.get(i);
    	if (jail == false) {
    		this.Roll(r);
    		System.out.println("\nYou rolled : " + roll);
    		if (checkdouble()==true) {
    			System.out.println("\nYou rolled a double");
    		}
    		afterRoll(i,x);

    	}else {
    		this.Roll(r);
    		if (r.doubles == false) {
    			System.out.println("\nYou rolled : " + roll);
    			System.out.println("\nYou did not roll a double");
    			jailroll +=1;
    			if (jailroll>=3) {
    				System.out.println("But you have rolled three time which are not a double therefore you must pay money to get out the jail");
    				jailroll = 0;
    				afterRoll(i,x);
    			}else {
    				System.out.println("You are still in jail");
    			}
    		}else {
    			jailroll = 0;
    			System.out.println("You have gotten out of Jail");
    			afterRoll(i,x);
    		}
    	}
    }

  
	
    


    public String menuBefore() {
	String s = "\nWhat would you like to do?" + "\n\n1. Roll Dice" + '\n' +  "2. See Your Properties" +  "\n\nPlease Input: ";
	return s;
    }

    public String menuAfterProp() {
	String s = "What would you like to do?";
	s += "\n1. Buy \n2. Do not buy";
	return s;
    }
    
    public String jailmenu() {
    	String s = "\nWhat would you like to do?" + "\n\n1. Roll double for get out" + '\n' +  "2. paymoney to get out" +  "\n\nPlease Input: ";
    	return s;
        }

   
    
    
    
    public String read() {
	String d = "";
	try {
	    d = br.readLine();
	}
	catch (IOException x) { }
	return d;
    }
    
    public int readint() {
    	int number = 0;
    	Scanner scan = new Scanner(System.in);
    	number = scan.nextInt();
    	    return number;
        }

   
    public void newGame( Board c) {
	String s = "";
System.out.print("test");
	while (playerlose() == -1 ) {
		if(round <= 100) {
	    for (int i=0;i<players.size();i++) {	
	    	 System.out.println("----------------------------------------------------------");
		Player a = players.get(i);
		for (int t = 0; t < players.size(); t++) {
		    Player f = players.get(t);
		    show = false;
		    System.out.println("\nPlayer " + (t + 1) + "'s Status: \n" +  "Your Location: " +f.getPlace(c));
		    show = true;
		    showowner= false;
		    System.out.print("Properties Owned:\n" + f.printProp() + "\n\n");
		}
	    System.out.println("----------------------------------------------------------");
	    System.out.println("Round "+ round );
	    if (a.inJail) {
	    	show = false;
			s = "Name: " + a.toString(c) + "Location: " +a.getPlace(c)+ jailmenu();
			System.out.println(s); 
			String g = read();
			if (g.equals("1")) {
				jail = true;
				jail(i, c);
			}else if (g.equals("2")) {
				jail = false;
				jail(i, c);
			}
			
	    }else {
	    	jail = false;
		show = false;
		s = "Name: " + a.toString(c) + "Location: " +a.getPlace(c)+ menuBefore();
		System.out.println(s); 
		String g = read(); 
		while (!(g.equals("1"))) {		    
		   
		    if (g.equals("2")) {
		    	show = false;
		    System.out.println("\nYour Properties : ");
			System.out.println(a.printProp());
			System.out.println("----------------------------------------------------------");
			s = "Name: " + a.toString(c) + "Location: " +a.getPlace(c) + menuBefore();
			System.out.println(s);
			g = read();	
		    }	    
		    else {
			System.out.println("Error, invalid input.");
			s = "Name: " + a.toString(c) + "Location: " +a.getPlace(c) + menuBefore();
			System.out.println(s);
			g = read();
		    }			
		}
		if (g.equals("1")) 
			jail(i, c);
	    }
	//	Intro wait = new Intro();
		//wait.pause(1500);
	    } 
	    round = round +1;
	}
		else {
			System.out.println("----------------------------------------------------------");
			System.out.println("End Game!!");
			Endgame(c);
			break;}
		}
	
    }
    
    public void Endgame(Board c) {
    	int[] moneyarray = new int [players.size()+1];
    	for(int i = 0; i<players.size();i++) {
    		Player P = players.get(i);
    		moneyarray[i] = P.getplayermoney(c);
    		Arrays.sort(moneyarray);
    		highest_money = moneyarray[players.size()];
    		 System.out.println("Player " + (i + 1) + "\t\tTotal Money: " +P.getplayermoney(c));
    	}
    	System.out.println("----------------------------------------------------------");
    	for(int j= 0;j<players.size();j++) {
    		Player player = players.get(j);
    		if(player.getplayermoney(c) == highest_money) {
    			 System.out.println("\nPlayer " + (j + 1) + " WIN!!" + "\t\tTotal Money: "+ highest_money);
    		}
    	}
    }
    
    public void startmenu() {
    	int input = readint();
    		if(input>=2 && input <=6) {
 
    		for(int i =1;i<=input;i++) {
    			players.add(new Player("Player " + i));
    		
    		}
    		}
    		else {
    			System.out.println("Please input a valid value!");
    			startmenu();
    		}
    		
    }
		    

	    
       public static void main(String[] args) {

	game m = new game();
	System.out.println("Welcome to Monopoly!\n\nPlease enter the number of player(2-6) :");
	m.startmenu();


	Board z = new Board();
     //  	Intro i = new Intro();
     //  	i.play(100);
       //	i.pause(1000);



	m.newGame(z);

	
    }









}