
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Initialisation {
	File rep = new File("fichiers Test");
    String liste[] = rep.list();
    String fichier;
    ArrayList<String> fichiersComp = new ArrayList<String>();
    String reponse = "O";
    boolean compt = false;
    
	public void init() {
		System.out.println("COMPRESSION DE FICHIERS PAR METHODE DE HUFFMAN");
		System.out.println("                  ---------                  ");
		System.out.println("Auteur: NEPAUL Roshan IDU3");
		System.out.println("");		
	}
	public void fin() {
		System.out.println("N'oubliez pas, tous vos fichiers résultats se trouvent dans le repertoire fichiers Test");
		System.out.println("A bientôt !");
		System.out.println("              Fin du programme                ");
	}
	
	public void afficherRepertoire() { 
		System.out.println("Quel fichier souhaitez vous tester ?");
        if (liste != null) {         
            for (int i = 0; i < liste.length; i++) {
            	if (liste[i].substring(liste[i].length() - 4, liste[i].length()).equals(".txt") && !(liste[i].substring(liste[i].length() - 9, liste[i].length()).equals("_freq.txt")))
            		System.out.println("  - " + liste[i]);            	
            }
        } else {
            System.err.println("Nom de repertoire invalide !");
        }
	}
	
	public String choix() {
		@SuppressWarnings("resource")
		Scanner choix = new Scanner(System.in);
		do {
			compt=false;
			afficherRepertoire();				
			fichier = choix.nextLine();
			for (String f : fichiersComp) {
				if (f.equals(fichier)) {
					compt = true;
					do{
						System.out.println("Le fichier" + " a déja été compressé! Voulez vraiment réitérer l'opération ? (O/N)");
						reponse = choix.nextLine();
					}while(!(reponse.equals("O")) && !(reponse.equals("N")));
				}
			}
		}while(compt && !(reponse.equals("0")));
		if (reponse.equals("O")){
			boolean existe = false;
			while (existe == false) {
				for (String s : liste) {
					if (s.equals(fichier)) {
						existe=true;
						fichiersComp.add(s);
					}
				}
				if (existe == false) {
					System.out.println("Nom de fichier incorrect, veuillez saisir un nom de fichier valide.");
					fichier = choix.nextLine();			
				}
			}
		}else {
			
		}
		return fichier;		
	}
	
	public boolean continuer() { 
		@SuppressWarnings("resource")
		Scanner choix = new Scanner(System.in);
		do{
			System.out.println("Voulez vous compresser un fichier à nouveau (0/N)");
			reponse = choix.nextLine();
		}while(!(reponse.equals("O")) && !(reponse.equals("N")));				
	
	return reponse.equals("O");
	}	
	
}


