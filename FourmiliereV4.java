/** Fourmiliere V4
  * date : le 03/11/2016
  * @author: Louis Boursier <louisboursier@hotmail.fr>, Erwan Leboucher <erwanleboucher@gmail.com>, Antoine Alain <antoine76bis@gmail.com>
  * @group: 52
  * Il s'agit d'un jeu qui consiste a faire traverser une fourmi d'un labyrinthe
  */

public class FourmiliereV4 {
    /*----------------------------------------------------------------*/
    /* Constantes accessibles par toutes les methodes de cette classe */
    /*----------------------------------------------------------------*/
    private static final int MUR = -1;
    private static final int ENTREE = -2;
    private static final int SORTIE_1 = -3;
    private static final int SORTIE_2 = -4;
    private static final int DIM = 15;

    public static void main(String[] a) {
        /*------------------*/
        /*    VARIABLES     */
        /*------------------*/
        int[][] terrain;
        int ligF = 0, colF = 0, mouvement, nbTentative = 0;
		
        /*------------------*/
        /*  INSTRUCTIONS    */
        /*------------------*/

        // Initialisation du Terrain
        terrain = FourmiliereV4.initTerrain();

        // Recherche de la colonne de l'entree du parcours, l'entree sera toujours sur la ligne 0
        for (int i = 0; i < DIM; i++)
            if (terrain[0][i] == ENTREE) colF = i;


        do {
            // Affichage du Terrain
            System.out.println(FourmiliereV4.enChaine(terrain, colF, ligF));

            // Deplacement de la fourmi par le joueur
            mouvement = deplaceFourmi(terrain, colF, ligF);
            nbTentative++;

            // Negatif : decrementation
            // Positif : incrementation
            if (mouvement % 2 != 0) // Nombre impair : ordonnee
                ligF = (mouvement < 0) ? ligF - 1 : ligF + 1;

            else // Nombre pair : abscisse
                colF = (mouvement < 0) ? colF - 1 : colF + 1;
				
        } while (!sortieTrouvee(terrain, colF, ligF));

        // Affichage du Terrain avec la fourmi sur une des sorties
        System.out.println(FourmiliereV4.enChaine(terrain, colF, ligF));
        System.out.println("Sortie trouvee en " + nbTentative + " coups!");

    }


    private static int[][] initTerrain() {
        int[][] tab = new int[DIM][DIM];
        int[][] bloc = new int[][] { {-1,-1,-1,-1,-1,-1,-1,-2 },
                                     {-1, 0,-1, 0,-1, 0, 0, 0 },
                                     {-1, 0, 0, 0, 0, 0, 0,-1 },
                                     {-1, 0,-1, 0,-1, 0, 0,-1 },
                                     {-1, 0, 0, 0,-1, 0, 0, 0 },
                                     {-1, 0, 0, 0,-1, 0, 0,-1 },
                                     {-1,-1,-1, 0,-1,-1,-1,-1 },
                                     {-1, 0, 0, 0, 0, 0, 0, 0 },
                                     {-1, 0, 0,-1,-1,-1,-1,-1 },
                                     {-1, 0,-1, 0, 0, 0, 0,-1 },
                                     {-1, 0, 0, 0,-1, 0, 0, 0 },
                                     {-1, 0,-1, 0,-1, 0,-1,-1 },
                                     {-1, 0, 0, 0,-1, 0, 0,-1 },
                                     {-1, 0, 0, 0, 0, 0, 0,-1 },
                                     {-1,-1,-1,-3,-1,-1,-1,-1 }  };

        // recopie du tableau bloc dans la partie gauche du tableau tab
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM / 2 + 1; j++) {
                tab[i][j] = bloc[i][j];
            }
        }

        // construction de la partie droite du terrain selon la symetrie verticale
        for (int i = 0; i < DIM; i++) {
            for (int j = DIM / 2; j >= 0; j--) {
                tab[i][DIM - 1 - j] = bloc[i][j];
            }
        }

        return tab;
    }

    private static String enChaine(int[][] tab, int fourmiPosX, int fourmiPosY) {
        String sRet = "";

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (fourmiPosX == j && fourmiPosY == i) {
                    // c'est l'emplacement de la fourmi, on la dessine
                    sRet += ".";
                } else {
                    switch (tab[i][j]) {
                        case 0:
                            sRet += " ";
                            break;
                        case MUR:
                            sRet += "X";
                            break;
                        case ENTREE:
                            sRet += " ";
                            break;
                        case SORTIE_1:
                            sRet += " ";
                            break;
                        case SORTIE_2:
                            sRet += " ";
                            break;
                    }
                }

            }
            sRet += "\n";
        }

        return sRet;
    }

    private static boolean sortieTrouvee(int[][] tab, int fourmiPosX, int fourmiPosY) {
        return (tab[fourmiPosY][fourmiPosX] == SORTIE_1 ||
            tab[fourmiPosY][fourmiPosX] == SORTIE_2);
    }


    private static int deplaceFourmi(int[][] tab, int colF, int ligF) {

        int nb;

        do {

            nb = (int)((Math.random() * 4));

            //Nombre impair pour les ordonnees et pair pour les abscisses
            //Nombre positif pour une incrementation et negatif pour une decrementation

            switch (nb) {
                case 0:
                    if (ligF - 1 >= 0)
                        if (tab[ligF - 1][colF] != MUR) return -1;
                    break;
                case 1:
                    if (ligF + 1 < DIM)
                        if (tab[ligF + 1][colF] != MUR) return +1;
                    break;
                case 2:
                    if (colF + 1 < DIM)
                        if (tab[ligF][colF + 1] != MUR) return +2;
                    break;
                case 3:
                    if (colF - 1 >= 0)
                        if (tab[ligF][colF - 1] != MUR) return -2;
                    break;
                    ///Un default n'est pas utile ici
            }

        } while (true);


    }

}