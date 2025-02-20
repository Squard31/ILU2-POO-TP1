package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("Astérix", 8);
		etal.occuperEtal(new Gaulois("Obélix", 12), "menhir", 10);

		String phrase = "";
		int quantiteAcheter = 5;

		if (acheteur == null) {
			System.out.println("Erreur : L'acheteur ne doit pas être null.");
		}

		else if (quantiteAcheter < 1) {
			System.err.println("Erreur : La quantité doit être positive.");
		}

		else if (!etal.isEtalOccupe()) {
			System.err.println("Erreur : L'étal n'est pas occupé.");
		}

		else {
			phrase = etal.acheterProduit(quantiteAcheter, acheteur);
		}

		System.out.println("Résultat de l'achat : " + phrase);
		System.out.println("Fin du test");
	}
}
