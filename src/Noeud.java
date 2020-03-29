
public class Noeud {	
	public int frequence;
	private char carac ='¤';
	private Noeud filsD = null;
	private Noeud filsG = null;
	private String codeB = "";
	

	public Noeud(int frequence, char carac, Noeud filsG, Noeud filsD, String codeB) {
		super();
		this.frequence = frequence;
		this.carac = carac;
		this.filsD = filsD;
		this.filsG = filsG;
		this.codeB = codeB;
	}

	public String toString() {
		return "Noeud [Caractère=" + carac + ", Frequence=" + frequence + ", Fils gauche=" + filsG + ", Fils droit=" + filsD + ", Code binaire=" + codeB + "]";
	}

	public int getFrequence() {
		return frequence;
	}

	public char getCarac() {
		return carac;
	}

	public Noeud getFilsD() {
		return filsD;
	}

	public Noeud getFilsG() {
		return filsG;
	}

	public String getCodeB() {
		return codeB;
	}
	
	
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public void setCarac(char carac) {
		this.carac = carac;
	}

	public void setFilsD(Noeud filsD) {
		this.filsD = filsD;
	}

	public void setFilsG(Noeud filsG) {
		this.filsG = filsG;
	}

	public void setCodeB(String codeB) {
		this.codeB = codeB;
	}

	public boolean estFeuille() {
		return(this.filsG==null && this.filsD==null);
	}
}