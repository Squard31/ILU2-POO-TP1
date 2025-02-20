package villagegaulois;

import histoire.EtalNonOccupe;
import personnages.Chef;
import personnages.Gaulois;

public class Village 
{
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtals)
	{
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
	
	public String afficherVillageois() throws VillageSansChefException 
	{
		if (chef == null)
		{
			throw new VillageSansChefException("Le village doit avoir un chef");
		}
		
		StringBuilder chaine = new StringBuilder();
		
		if (nbVillageois < 1) 
		{
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} 
		else 
		{
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			
			for (int i = 0; i < nbVillageois; i++) 
			{
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
////////////////////////////////////// CLASSE INTERNE "Marche" ///////////////////////////////////
	
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
		
		private void utiliserEtal (int indiceEtal, Gaulois vendeur, String produit, int nbProduit)
		{
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		
		// METHDOE "trouverEtalLibre"
		
		private int trouverEtalLibre()
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
		
		private Etal[] trouverEtals(String produit)
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
		
		private Etal trouverVendeur (Gaulois gaulois)
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
		
		private String afficheMarche()
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
				System.out.println("Il reste" + vide + "etals non utilises dans le marche.\n");
			}
			return null;
		}
		
	}
	
	
	// METHODE "installerVendeur"
	
	public String installerVendeur (Gaulois vendeur, String produit, int nbProduit)
	{
		StringBuilder phrase = new StringBuilder();
		int etalLibre = marche.trouverEtalLibre();
		
		if (etalLibre != -1)
		{
			marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
			
			phrase.append(vendeur.getNom()).append(" cherche un endroit pour vendre ")
            .append(nbProduit).append(produit).append(".\n");
			
			phrase.append(" Le vendeur ").append(vendeur.getNom()).append(" vend des ")
			.append(produit).append("à l'Etal n° ").append(etalLibre + 1).append(" \n ");
		}
		else
		{
			phrase.append(" Aucun Etal libre \n ");
		}
		return phrase.toString();
	}
	
	
////// METHODE "rechercherVendeursProduit"
	
	public String rechercherVendeursProduit(String produit)
	{
		StringBuilder phrase = new StringBuilder();
		Etal[] possedeProduit = marche.trouverEtals(produit);
		int nbVendeurs = 0;
		
		for (int i = 0 ; i < possedeProduit.length ; i++)
		{
			Etal etal = possedeProduit[i];
			
			if(etal != null && etal.isEtalOccupe())
			{
				if (nbVendeurs == 0)
				{
					phrase.append(" Les vendeurs qui proposent des ").append(produit)
					.append(" sont : \n ");
				}
				phrase.append(etal.getVendeur().getNom());
				nbVendeurs ++;
			}
		}
		
		if (nbVendeurs == 0)
		{
			phrase.append(" Aucun Vendeur ne vend des ").append(produit);
		}
		return phrase.toString();
	}
	
	
	// METHODE "rechercherEtal"
	
	public Etal rechercherEtal (Gaulois vendeur)
	{
		return marche.trouverVendeur(vendeur);
	}
	
	
	// METHODE "partirVendeur"
	
	public String partirVendeur(Gaulois vendeur) throws EtalNonOccupe
	{
		StringBuilder phrase = new StringBuilder();
		Etal etal = marche.trouverVendeur(vendeur);
		
		if (etal != null)
		{
			String libre = etal.libererEtal();
			
			phrase.append(" Le vendeur ").append
			(vendeur.getNom()).append(" quitte son etal, ").append(libre);
		}
		else
		{
			phrase.append(" Le vendeur ").append(vendeur.getNom()).append(" n'a aucun etal \n ");
		}
		return phrase.toString();
	}
	
	
	// METHODE "afficherMarche"
	
	public String afficherMarche() 
	{
	    StringBuilder phrase = new StringBuilder();
	    phrase.append(" Le marché du village ").append(nom).append(" \n possède plusieurs étals : \n ");
	    int etalsLibres = 0;

	   
	    for (int i = 0; i < marche.etals.length; i++) 
	    {
	        Etal etal = marche.etals[i];

	        if (etal.isEtalOccupe()) 
	        {
	            phrase.append(etal.afficherEtal());
	        } 
	        else 
	        {
	            etalsLibres++;
	        }
	    }

	    if (etalsLibres > 0) 
	    {
	        phrase.append(" Il reste ").append(etalsLibres).append(" étals non utilisés dans le marché.\n ");
	    }

	    return phrase.toString();
	}

	
	
}