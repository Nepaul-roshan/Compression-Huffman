
public class Main {

	public static void main(String[] args) throws InterruptedException{
		Initialisation init = new Initialisation();
		boolean continuer = true;
		init.init();
		while(continuer) {
			OpenFile texte = new OpenFile(init.choix());
			texte.traitement();
			texte.affichageTauxEtNbBits();
			continuer = init.continuer();
		}
		init.fin();
	}
}