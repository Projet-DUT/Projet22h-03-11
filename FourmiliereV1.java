public class FourmiliereV1 {
    /*----------------------------------------------------------------*/
    /* Constantes accessibles par toutes les méthodes de cette classe */
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

        /*------------------*/
        /*  INSTRUCTIONS    */
        /*------------------*/

        // Initialisation du Terrain
        terrain = FourmiliereV1.initTerrain();

        // Affichage du Terrain
        System.out.println(FourmiliereV1.enChaine(terrain));

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

    private static String enChaine(int[][] tab) {
        String sRet = "";

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
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
            sRet += "\n";
        }

        return sRet;

    }
}