package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("Astérix", 10);
		Gaulois vendeur = new Gaulois ("Obelix", 15);
		
		
		// "LibérerÉtal"
		try {
			etal.libererEtal();
		}catch(IllegalStateException e){
			e.printStackTrace(System.err);
			
		}
		
		// "AcheterProduit"
		try {
			etal.acheterProduit(5, acheteur);
		}catch(IllegalStateException e){
			e.printStackTrace(System.err);
		}
		
		try {
			etal.occuperEtal(vendeur, "cailloux", 10);
			etal.acheterProduit(-2, acheteur);
		}catch (IllegalArgumentException e) {
			e.printStackTrace(System.err);
		}
		
		try {
			etal.acheterProduit(5,null);
		}catch(NullPointerException e) {
			e.printStackTrace(System.err);
		}
		
	}
}
