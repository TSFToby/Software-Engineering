package JunitTest;

import java.io.*;
import java.util.*;


/*
Go
Free Parking
Jail
Income Tax
Luxury Tax

 */

public class Other extends Location {

 

    public Other(String s, int rents) {
	name = s;
	rent = rents;

    }

    public String toString() {
    	String s = "";
    	s +=  name + '\t';
    	String strgain = "";
    	if(game.show == true) {
    	if (rent > 0)
    	    strgain += "You gain: " + rent  ;
    	else
    		strgain += "You lose: " + (rent * -1) ;
    	}
    	return String.format("%-15s",s)+strgain;

        }
    
    public void setrent(int r) {
    	rent = r;
    	
    }




}