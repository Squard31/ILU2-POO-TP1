package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	// CLASSE INTERNE "Marche"
	
	private static class Marche 
	{
		private Etal[] etals;
		private int nbEtals = 0;
		
		
		// CONSTRUCTEUR "Marche"
		
		private Marche (int nbEtals)
		{
			super();
			this.nbEtals = nbEtals;
			etals = new Etal[nbEtals];
			
			for (int i = 0; i < nbEtals; i++)
			{
				etals[i] = new Etal();
			}
		}
		
		
		// METHODE "UtiliserEtal"
		
		public void utiliserEtal (int indiceEtal, Gaulois vendeur, String produit, int nbProduit)
		{
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		
		// METHDOE "trouverEtalLibre"
		
		public int trouverEtalLibre()
		{
			for (int i = 0; i < nbEtals ; i++)
			{
				if (etals[i].isEtalOccupe() == false)
				{
					return i;
				}
			}
			
			return -1;
		}
		
		
		// METHODE "trouverEtals"
		
		public Etal[] trouverEtals(String produit)
		{
			Etal[] tabEtals = new Etal[nbEtals];
			int n = 0;
			
			for (int i = 0 ; i < nbEtals ; i++)
			{
				if (etals[i].contientProduit(produit) == true)
				{
					tabEtals[n] = etals[i];
					n = n+1;
				}
			}
			return tabEtals;	
		}
		
		
		// METHODE "trouverVendeur"
		
		public Etal trouverVendeur (Gaulois gaulois)
		{
			for (int i = 0 ; i < nbEtals ; i++)
			{
				if (etals[i].getVendeur() == gaulois)
				{
					return etals[i];
				}
			}
			return null;
		}
		
		
		// METHODE "afficherMarche"
		
		public String afficheMarche()
		{
			int vide = 0;
			for (int i = 0 ; i < nbEtals ; i++)
			{
				if (etals[i].isEtalOccupe() == true)
				{
					etals[i].afficherEtal();
				}
				else
				{
					vide = vide + 1;
				}
			}
			if (vide != 0)
			{
				System.out.println("Il reste" + vide + "étals non utilisés dans le marché.\n");
			}
			return null;
		}
		
		
	}
	
}