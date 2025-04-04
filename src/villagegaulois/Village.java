package villagegaulois;

import histoire.EtalNonOccupe;
import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	// METHODE "afficherVillageois"

	public String afficherVillageois() throws VillageSansChefException {
		StringBuilder phrase = new StringBuilder();
		if (chef == null) {
			throw new VillageSansChefException("Le village n'a pas de chef !");
		}
		if (nbVillageois < 1) {
			phrase.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			phrase.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				phrase.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return phrase.toString();
	}

/////////////////////// METHODE "installerVendeur"

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int numEtal = marche.trouverEtalLibre();
		StringBuilder phrase = new StringBuilder()
				.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		if (numEtal == -1) {
			phrase.append("Aucun étal libre.\n");
			return phrase.toString();
		}
		marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
		phrase.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (numEtal + 1) + ".\n");
		return phrase.toString();
	}

////// METHODE "rechercherVendeursProduit"

	public String rechercherVendeursProduit(String produit) {
		Etal[] etals = marche.trouverEtals(produit);
		switch (etals.length) {
		case 0:
			return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n";
		case 1:
			return "Seul le vendeur " + etals[0].getVendeur().getNom() + " propose des " + produit + " au marché.\n";
		default:
			StringBuilder nameList = new StringBuilder().append("Les vendeur qui proposent des " + produit + " sont :\n");
			for (Etal etal : etals) {
				nameList.append("- " + etal.getVendeur().getNom() + "\n");
			}
			return nameList.toString();
		}
	}

	// METHODE "rechercherEtal"

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	// METHODE "partirVendeur"

	public String partirVendeur(Gaulois vendeur) throws EtalNonOccupe {
		return rechercherEtal(vendeur).libererEtal();
	}

	// METHODE "afficherMarche"

	public String afficherMarche() {
		StringBuilder text = new StringBuilder()
				.append("Le marché du village " + this.getNom() + " possède plusieurs étals :\n");
		text.append(marche.afficherMarche());
		return(text.toString());
	}
	
/////////////////////////////////////////// CLASSE INTERNE "MARCHE"	
	
	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				this.etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtal = 0;
			for (Etal etal : etals) {
				if (etal.contientProduit(produit)) {
					nbEtal++;
				}
			}
			Etal[] produitEtals = new Etal[nbEtal];
			for (int i = 0, j = 0; i < produitEtals.length; i++) {
				Etal etal = etals[i];
				if (etal.contientProduit(produit)) {
					produitEtals[j] = etal;
					j++;
				}
			}
			return produitEtals;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (Etal etal : etals) {
				if (etal.getVendeur().equals(gaulois)) {
					return etal;
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder text = new StringBuilder();
			int etalTotal = etals.length;
			int etalUtil = 0;
			for (Etal etal : etals) {
				if (etal.isEtalOccupe()) {
					etalUtil++;
					text.append(etal.afficherEtal());
				}
			}
			text.append("Il reste " + (etalTotal - etalUtil) + " non utilisés dans le marché");
			return text.toString();
		}
	}

}