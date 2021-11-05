package JunitTest;
import java.io.*;
import java.util.*;

public class Property extends Place{

    protected ArrayList<Integer> rents;

    public Property(String names, int costs, int rent1) {

	name = names;
	cost = costs;
	
    owner = null;
    
     
	rents = new ArrayList<Integer>();
	rents.add(rent1);


    }

    public boolean isProperty() {
	return true;
    }


    public String toString() {
    	int R =rents.get(0);
	String s = "";

	s += name + '\t';
	if (game.show == true && game.showowner==true) {
	s += "Owner: " + owner + '\t';
	if(!(owner==null)) {
		s += '\t'+ "Rents: " + R + '\t';
	}else
	s += "Cost: " + cost + '\t';
	}
	else if (game.show == true) {
		s += "Cost: " + cost + '\t';
	}

	return s;


    }

}