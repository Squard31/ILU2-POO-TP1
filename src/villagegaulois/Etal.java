package villagegaulois;

import personnages.Gaulois;
import histoire.EtalNonOccupe;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	
	public String libererEtal() throws EtalNonOccupe 
	{
	    if (etalOccupe == true) 
	    {
	        throw new EtalNonOccupe("Impossible de libérer l'étal : il n'est pas occupé.");
	    }

	    etalOccupe = false;
	    StringBuilder chaine = new StringBuilder(
	            "Le vendeur " + vendeur.getNom() + " quitte son étal, ");
	    int produitVendu = quantiteDebutMarche - quantite;
	    if (produitVendu > 0) 
	    {
	        chaine.append(
	                "il a vendu " + produitVendu + " parmi " + produit + ".\n");
	    } 
	    else 
	    {
	        chaine.append("il n'a malheureusement rien vendu.\n");
	    }
	    return chaine.toString();
	}


	
	public String afficherEtal() 
	{
		if (etalOccupe) 
		{
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	
	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) 
	{
	    
	    if (acheteur == null) 
	    {
	        System.err.println("Erreur : L'acheteur ne doit pas être null.");
	        return "";  
	    }

	    
	    if (!etalOccupe) 
	    {
	        System.err.println("Erreur : L'étal n'est pas occupé.");
	        return "";  
	    }

	    
	    if (quantiteAcheter < 1) 
	    {
	        System.err.println("Erreur : La quantité doit être positive.");
	        return "";  
	    }

	    StringBuilder phrase = new StringBuilder();
	    
	    phrase.append(acheteur.getNom()).append(" veut acheter ").append(quantiteAcheter)
	            .append(" ").append(produit).append(" à ").append(vendeur.getNom());

	    
	    if (quantite == 0) 
	    {
	    	phrase.append(", malheureusement il n'y en a plus !");
	        quantiteAcheter = 0;
	    }

	    
	    if (quantiteAcheter > quantite) 
	    {
	    	phrase.append(", comme il n'y en a plus que ").append(quantite)
	                .append(", ").append(acheteur.getNom()).append(" vide l'étal de ")
	                .append(vendeur.getNom()).append(".\n");
	        
	        quantiteAcheter = quantite;
	        quantite = 0;
	    }

	    
	    if (quantite != 0) 
	    {
	        quantite -= quantiteAcheter;
	        
	        phrase.append(". ").append(acheteur.getNom())
	                .append(", est ravi de tout trouver sur l'étal de ")
	                .append(vendeur.getNom()).append("\n");
	    }
	    return phrase.toString();
	}


     
	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
