package JunitTest;

import java.io.*;
import java.util.*;

public class Board{

    protected ArrayList<Location> places = new ArrayList<Location>();

    public Board() {
	places.add(new Other("Go", 1500));
	places.add(new Property("Central", 800, 90));
	places.add(new Property("Wan Chai", 700, 65));
	places.add(new Other("Income Tax", 0));
	places.add(new Property("Stanley", 600, 60));
	places.add(new Other("Just Visiting", 0));
	places.add(new Property("Shek O", 400, 10));
	places.add(new Property("Mong Kok", 500, 40));
	places.add(new Chance("chance"));
	places.add(new Property("Tsing Yi", 400, 15));
	places.add(new Other("Free Parking", 0));
	places.add(new Property("Shatin", 700, 75));
	places.add(new Chance("chance"));
	places.add(new Property("Tuen Mun", 400, 20));
	places.add(new Property("Tai Po", 500, 25));
	places.add(new Other("Jail", 0));
	places.add(new Property("Sai Kung", 400, 10));
	places.add(new Property("Yuen Long", 400, 25));
	places.add(new Property("Tai O", 600, 25));
	
    }

    
    public String toString(int d) {
	String s = "";
	s +=  places.get(d);
	return s;
    }

   


}