package JunitTest;

import java.io.*;
import java.util.*;

public class Player {

    protected int money;
    protected int location;
    protected String name;
    protected boolean doubles;

    protected boolean enoughMoney = true;
    protected boolean inJail = false;



    protected ArrayList<Place> properties;


    public Player(String s) {
	name = s;
    money = 1500;
	location = 0;
	properties = new ArrayList<Place>();
	doubles =true;



    }


	public String printProp() {
	String s = "";
	for (int i = 0; i < properties.size(); i++) {
	    s += (i + 1);
	    s +=  ": " +  properties.get(i);
	    s += '\n';
	}
	return s;
    }

    public void move(int r, int i, ArrayList<Player> p) {
	Player b = p.get( (i + 1) % p.size());	
	//not in jail and no doubles
	if (!(this.inJail)) {
	    if (b.inJail) {
		location += r;
	    }
	    else {
		location += r;
	    }
	    if (location > 18) { 
		location -= 19;
		this.money += 1500;
		System.out.print("\nYou had passed 'Go' and got $1500\n\n");
	    }  
	}
      
	//in jail
	else {
	    if (inJail = true) {
		money -= 150;
		inJail = false;
		System.out.println("You have gotten out of Jail.");
		location += r;
	
		if (location > 18) { 
			location -= 19;
			this.money += 1500;     
			System.out.print("\nYou had passed 'Go' and got $1500\n\n");
		}
	    }
	    
	    else {
		System.out.println("You are in Jail");
	    }
	}
    
	//you are just visiting
	if (location == 5 && !(inJail)) 
	    System.out.println("You are just visiting");

	//go to jail square
	if (location == 15)  {
	    location = 5;
	    inJail = true;
	    System.out.println("You are in Jail");
	  ;
	}
    }

    
    public void buyplace(Place p) {
	if (this.money >= p.getCost()) {
	    System.out.println("You bought: " + p.name + "\n");
	    money -= p.getCost();
	    p.setOwner(this);
	    properties.add(p);	    

	}
	else {
	    System.out.println("You don't have enough money to buy this!");
	}
    }

    public void pay_or_get_getMoney(Location p) {
    	
	if (p.rent < 0 && this.money <= p.rent * -1){
		System.out.println("You don't have enough money to do this!");
	    enoughMoney = false;

	}else if(location == 3) {
		int tax = (int)(money * 0.1);
		money= money-tax;
		game.showwhere=false;
		String s = "You are in : Income Tax \tYou lose : " + tax ;
		System.out.println(s);
	}
	else
	    money += p.rent;
    }
    
    public int getplayermoney(Board x) {
    	return money;
    }
    

    public String getPlace(Board x) {
	String s = "";
	s = x.toString(this.location);
	if(game.show==false)
	s += "\nTotal Money: " + money + '\n';
	return s;
    }
    
  

    public void payRent(Place p) {
	int rentNow = p.rent;
	if (p.getOwner() != null) {
			rentNow = ((Property)p).rents.get(0);
		 
	    if (this.money >= rentNow) {
		this.money -= rentNow;
		p.getOwner().money += rentNow;
	    }
	    else {
		System.out.println("You don't have enough money to pay rent!");
		enoughMoney = false;
	
	    }
	}
    }
   
    
   

    public String toString() {
	String s = "";
	s = name;
	return s;
    }
    

    public String toString(Board r) {
	String s = "";
	s = name +" " +"\t  ";

	return s;

    }

  



}




  /*
    public void whatColor(Property a) {
	if (!(a.isUtility()) && !(a.isRailroad())) {
	    if (a.mColor.equals("purple"))
		numPurple++;
	    else if (a.mColor.equals("light blue"))
		numLight++;
	    else if (a.mColor.equals("magenta"))
		numMagenta++;
	    else if (a.mColor.equals("orange"))
		numOrange++;
	    else if (a.mColor.equals("red"))
		numRed++;
	    else if (a.mColor.equals("yellow"))
		numYellow++;
	    else if (a.mColor.equals("green"))
		numGreen++;
	    else
		numBlue++;
	}
	    
    }
    */