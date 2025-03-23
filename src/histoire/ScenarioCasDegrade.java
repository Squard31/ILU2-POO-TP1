package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {

	public static void main(String[] args) {

		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("Astérix", 10);

		etal.libererEtal();

		try {
			etal.acheterProduit(1, acheteur);
		} catch (IllegalStateException e) {
			e.printStackTrace(System.err);
		} catch (Exception e) {
			System.err.println(false);
		}

		etal.occuperEtal(acheteur, "chausettes", 1);

		System.err.println("".equals(etal.acheterProduit(1, null)));

		try {
			etal.acheterProduit(0, acheteur);
		} catch (IllegalStateException e) {
			e.printStackTrace(System.err);
		} catch (Exception e) {
			System.err.println(false);
		}

		Village village = new Village("nouveau village", 1, 1);
		village.ajouterHabitant(acheteur);

		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			System.err.println(true);
		}
	}
}
