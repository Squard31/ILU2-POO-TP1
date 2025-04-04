package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("Obélix", 100);
		Village village = new Village("Village Gaulois", 10, 5);
		etal.occuperEtal(acheteur, "fleurs", 10);
		etal.acheterProduit(5, null);
	}
}
