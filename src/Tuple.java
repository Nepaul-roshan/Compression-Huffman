import java.util.ArrayList;
import java.util.TreeSet;

public class Tuple implements Comparable<Object>{
	
	char caractere;
	Integer frequence;
	TreeSet<Tuple> tuples = new TreeSet<Tuple>();
	ArrayList<Character> alphOrd = new ArrayList<Character>();
	ArrayList<Integer> freqOrd = new ArrayList<Integer>();
	
	public Tuple (Integer f, char c) {
		this.caractere = c;
		this.frequence=f;
	}
	
	
	public char getCaractere() {
		return caractere;
	}

	public Integer getFrequence() {
		return frequence;
	}


	@Override
	public int compareTo(Object o) {
		if( o instanceof Tuple) {
			Tuple t = (Tuple)o;
			
			if(this.frequence<t.frequence) {
				return -1;					
			}
			
			if(this.frequence>t.frequence) {
				return 1;
			}
			
			if(this.frequence.equals(t.frequence)) {
				if((int)this.caractere<(int)t.caractere){
					return -1;				
				}
				if ((int)this.caractere>(int)t.caractere) {
					return 1;
				}
			}
		}
		return 0;
	}	
	
	public String toString() {
		return this.caractere +" "+ this.frequence;
	}
	
}
	
