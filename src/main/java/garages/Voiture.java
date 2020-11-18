package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
        if (this.estDansUnGarage()) {
            throw new Exception("La voiture est déjà dans un garage");
        }

        Stationnement s = new Stationnement(this, g);
        myStationnements.add(s);
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        // TODO: Implémenter cette méthode
        if (this.estDansUnGarage() == false) {
            throw new Exception("La voiture n'est pas dans un garage");
        } else {
            // faire sortir la voiture du garage
            // trouver le dernier stationnement et le terminer
            myStationnements.get(myStationnements.size() - 1).terminer();
        }

    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        // TODO: Implémenter cette méthode
        Set<Garage> resultat = new HashSet<>(); // Ensemble vide
        // Calculer le résultat :
        myStationnements.forEach((s) -> {
            resultat.add(s.getGarage());
        });
        // Pour chaque stationnement s de la voiture ajouter le garage s au résultat
        return resultat;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        // TODO: Implémenter cette méthode
        // Vrai si le dernier stationnement est en cours

        // Faux si la liste des stationnements est vide
        if (myStationnements.isEmpty()) {
            return false;
        }
        // Sinon trouver le dernier stationnement
        Stationnement s = myStationnements.get(myStationnements.size() - 1);
        //Renvoyer Vrai si le dernier stationnement est en cours et Faux si non
        return s.estEnCours();
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        // TODO: Implémenter cette méthode
        for (Stationnement s : myStationnements) {
            if (s.estEnCours() == true) {
                System.out.println( "Garage "+s.getGarage().getName() + " : \n "
                        + "Stationnement{ entree=" + s.getEntree() + ", en cours" + " } \n");
            } else {
                System.out.println( "Garage "+s.getGarage().getName() + " : \n "
                        + "Stationnement{ entree=" + s.getEntree() + ", sortie=" + s.getFin() + " } \n");
            }
        }
    }

}
