import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class OpenFile  {
	String txt;
	String bin;
	String nom;
	String nomCourt;
	char reponse;
	TreeSet<Tuple> tuples = new TreeSet<Tuple>();
	ArrayList<Character> alphabet = new ArrayList<Character>();
	ArrayList<Integer> frequence = new ArrayList<Integer>();
	ArrayList<Integer> newFrequence = new ArrayList<Integer>();
	ArrayList<Character> newAlphabet = new ArrayList<Character>();
	ArrayList<Integer> newFrequence2 = new ArrayList<Integer>();
	ArrayList<Character> newAlphabet2 = new ArrayList<Character>();
	HashMap<Character, String> dictionnaire = new HashMap<Character, String >();
	ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
	ArrayList<String> octet = new ArrayList<String>();
	
	public OpenFile(String fichier) {
		this.txt=this.open("fichiers Test\\" + fichier);
		this.nom = fichier;
		this.nomCourt = fichier.substring(0 ,fichier.length() - 4);
	}
	
	public String getTxt() {
		return txt;
	}

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public ArrayList<Integer> getFrequence() {
		return frequence;
	}

	public ArrayList<Character> getNewAlphabet() {
		return newAlphabet;
	}

	public ArrayList<Integer> getNewFrequence() {
		return newFrequence;
	}

	public String open(String adresse) {
		String texte = "";
		try{
			InputStream ips=new FileInputStream(adresse); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;		   
			while ((ligne=br.readLine())!=null){
				texte += ligne;
			}
			br.close(); 
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return texte;		  
	}
	
	public void frequenceCaractere() {
		for (int i = 0; i<this.txt.length(); i++){
			if (this.alphabet.contains(this.txt.charAt(i)) == false)				{
			 	Character str = this.txt.charAt(i);
			 	this.alphabet.add(str);
			 	this.newAlphabet.add(str);
			 	this.newAlphabet2.add(str);
			 	this.frequence.add(1);
			 	this.newFrequence.add(1);
			 	this.newFrequence2.add(1);
			}else{
			 	int rang = alphabet.indexOf(this.txt.charAt(i));
			 	this.frequence.set(rang, this.frequence.get(rang)+1);
			 	this.newFrequence.set(rang, this.newFrequence.get(rang)+1);
			 	this.newFrequence2.set(rang, this.newFrequence2.get(rang)+1);
			}		 						 
		}
	}

	
	public int  minFreque() {
		int res = this.frequence.get(0);
		for(int j = 0; j<this.frequence.size(); j++){
		   if(this.frequence.get(j) < res)
		      res=this.frequence.get(j);
		   }
		return res;
	}
	

	
	public ArrayList<Character> ordAlph(ArrayList<Character> alphOrd){
		if (this.alphabet.isEmpty()) {
			 return alphOrd;
		}
		int min_freq = minFreque();
		for(int i = 0; i<this.frequence.size(); i++) {
			if(this.frequence.get(i) == min_freq) {
				alphOrd.add(this.alphabet.get(i));
				this.frequence.remove(i);
				this.alphabet.remove(i);				
			}
		}
		return ordAlph(alphOrd);
	}
	
	public ArrayList<Integer> ordFreq(){
		Collections.sort(this.newFrequence);
		return this.newFrequence;
	}
		
	public void creationTreeSet() {
		for(int f =0; f<this.newFrequence2.size(); f++) {
			Tuple t =new Tuple(this.newFrequence2.get(f),this.newAlphabet2.get(f));
			this.tuples.add(t);
		}
	}
	
	public ArrayList<Noeud> creationNoeud() {
		@SuppressWarnings("unchecked")
		TreeSet<Tuple> tuples2 = (TreeSet<Tuple>) this.tuples.clone();
		while(tuples2.size() > 0) {
			int f = tuples2.first().frequence;
			char c = tuples2.first().caractere;
			tuples2.remove(tuples2.first());
			this.listeNoeuds.add(new Noeud(f,c,null,null,""));
		}
		return this.listeNoeuds;
	}
	
	
	public Noeud creationArbre() {
		@SuppressWarnings("unchecked")
		ArrayList<Noeud> listeNoeuds2 = (ArrayList<Noeud>) listeNoeuds.clone();
		while(listeNoeuds2.size() > 1) {
			Noeud noeudG = listeNoeuds2.get(0);
			Noeud noeudD = listeNoeuds2.get(1);
			listeNoeuds2.add(new Noeud(noeudG.frequence+noeudD.frequence,'€',noeudG,noeudD,""));
			listeNoeuds2.remove(0);
			listeNoeuds2.remove(0);
			this.triNoeuds(listeNoeuds2);
		}
		return listeNoeuds2.get(0);
	}
	
	public void triNoeuds(ArrayList<Noeud> list) {
		for (int i= 1; i<list.size(); i++) {
			int freq_now = list.get(i).getFrequence();
			char caract_now = list.get(i).getCarac();
			Noeud fd_now = list.get(i).getFilsD();
			Noeud fg_now = list.get(i).getFilsG();
			String codeB_now = list.get(i).getCodeB();			
			int j=i;
			while(j>0 && list.get(j-1).getFrequence()>freq_now) {
				list.get(j).setFrequence(list.get(j-1).getFrequence());
				list.get(j).setCarac(list.get(j-1).getCarac());
				list.get(j).setFilsD(list.get(j-1).getFilsD());
				list.get(j).setFilsG(list.get(j-1).getFilsG());
				list.get(j).setCodeB(list.get(j-1).getCodeB());
				j=j-1;						
			}
			list.get(j).setFrequence(freq_now);
			list.get(j).setCarac(caract_now);
			list.get(j).setFilsD(fd_now);
			list.get(j).setFilsG(fg_now);
			list.get(j).setCodeB(codeB_now);
		}
	}
	
	public void parcoursEnProfondeur(Noeud node, String codeB){		
		if (node.estFeuille()) 
			this.dictionnaire.put(node.getCarac(),codeB);
		
		if (node.getFilsG() != null) 
			if (node.getCodeB() =="") 
				this.parcoursEnProfondeur(node.getFilsG(), codeB + "0");
		
		if (node.getFilsD()!= null) 
			if(node.getCodeB()=="") 
				this.parcoursEnProfondeur(node.getFilsD(), codeB + "1");	
	}
	
	public void conversion() {
		for (int i = 0; i < this.txt.length(); i++) {
				this.bin = this.bin + this.dictionnaire.get(this.txt.charAt(i));
		}		
	}
	
	public void ecritureFichierDecimal(String fichierInit) {
		this.bin = this.bin.substring(4);
		while(this.bin.length()%8 != 0) {
			this.bin = this.bin + "0";
		}
		for (int c = 0; c < this.bin.length() - 8; c+=8) {
			this.octet.add(this.bin.substring(c, c + 8));
		}
		try {
			File ff = new File("fichiers Test\\" + this.nomCourt + "_comp.bin");
			ff.createNewFile();
			FileWriter ffw = new FileWriter(ff);		
			for (String o : this.octet) {
				int i = Integer.parseInt(o,2);
				ffw.write((byte)i);
			}
			ffw.close();
		}catch (Exception e) {
			System.out.println("Impossible de créer le fichier resultat!");
		}		
	}
	
	public void ecritureFichierFrequence(String fichierInit) {
		@SuppressWarnings("unchecked")
		TreeSet<Tuple> tuples3 = (TreeSet<Tuple>) this.tuples.clone();
		OpenFile init = new OpenFile(fichierInit);
		String nom = fichierInit.substring(0 ,fichierInit.length() - 4);
		try {
			File ff = new File("fichiers Test\\" + nom + "_freq.txt");
			ff.createNewFile();
			FileWriter ffw = new FileWriter(ff);
			int longueur = init.txt.length();
			ffw.write(Integer.toString(longueur));
			ffw.write("\n");			
			while (tuples3.size() > 0) {
				ffw.write(tuples3.first().caractere + " : " + tuples3.first().frequence);
				ffw.write("\n");
				tuples3.remove(tuples3.first());
			}
			ffw.close();
		}catch (Exception e) {
			System.out.println("Impossible de créer le fichier fréquence!");
		}
	}
	
	public double taux(String fichierInit, String fichierRes) {
		OpenFile init = new OpenFile(fichierInit);
		OpenFile res = new OpenFile(fichierRes);
		double volumeInit = init.txt.length();
		double volumeRes = res.txt.length();
		return (1 - volumeRes/volumeInit)*100;
	}
	
	public double moyenneNombreBits() {
		int somme = 0;
		for (char c : this.dictionnaire.keySet()) {
			  int longueur = this.dictionnaire.get(c).length();
			  somme += longueur;
		}
		return somme/this.dictionnaire.size();
	}
	
	public void attendre() throws InterruptedException {
		for (int i = 0; i<3; i++) {
			Thread.sleep(1000);
			System.out.print(".");
		}
	}
	
	public void affichageTauxEtNbBits() throws InterruptedException {		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Voulez vous afficher le taux de compression et le nombre moyen de bit de stockage? (O/N)");
			reponse = sc.nextLine().charAt(0);
		}while (reponse != 'O' && reponse != 'N');
		if (reponse == 'O') {
			System.out.print("Calcul du taux de compression en cours");
			attendre();
			System.out.println("");
			System.out.println("Le taux de compression est de:" + this.taux(this.nom, this.nomCourt + "_comp.bin") + "%");
			System.out.println("");
			System.out.print("Calcul du nombre moyen de bit de stockage");
			attendre();
			System.out.println("");
			System.out.println("Le nombre moyen de bit de stockage est:" + this.moyenneNombreBits());
			System.out.println("");
			Thread.sleep(2000);
		}
		System.out.println("Votre fichier compressé '" + this.nomCourt + "_comp.bin' et votre fichier de fréquence '" + this.nomCourt + "_freq.txt' sont disponibles dans le repertoire fichier Test");
		System.out.println("");
		
	}
	
	public void traitement() throws InterruptedException {		
		ArrayList<Character> alphOrd = new ArrayList<Character>();		
		System.out.print("Le texte est en cours de traitement");
		attendre();
		this.frequenceCaractere();
		this.ordFreq();
		alphOrd = this.ordAlph(alphOrd);
		this.creationTreeSet();
		this.creationNoeud();
		this.parcoursEnProfondeur(this.creationArbre(), "");
		this.conversion();
		this.ecritureFichierDecimal(this.nom);
		this.ecritureFichierFrequence(this.nom);
		System.out.println("");
		System.out.println("Le texte a été compressé avec succès !");			
	}
	
	
}	