package JunitTest;
import java.util.Random;

public class Chance extends Location {

	 

    public Chance(String s) {
	name = s;
	Random r = new Random();
	int lower = -300;
	int upper = 200;
	rent = r.nextInt(upper-lower)+lower;
	rent=Math.round(rent/10);
	rent=rent*10;
    }
  
    public String toString() {
	String s = "";
	s += name + '\t';;
	if (rent >= 0)
	    s += "You gain: " + rent + '\n';
	else
	    s += "You lose: " + (-1 * rent) + '\n';

	return s;

    }

 


}
