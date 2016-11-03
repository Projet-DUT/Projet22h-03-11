public class FourmiliereV5
{
    /*----------------------------------------------------------------*/
    /* Constantes accessibles par toutes les methodes de cette classe */
    /*----------------------------------------------------------------*/
    private static final int MUR      = -1;
    private static final int ENTREE   = -2;
    private static final int SORTIE_1 = -3;
    private static final int SORTIE_2 = -4;
    private static final int DIM      = 15;
    
    /*----------------------------------------------------------------*/
    /* Variable de classe identique pour chaques instances            */
    /*----------------------------------------------------------------*/
    static char orientation = 'S';

    public static void main(String[] a)
    {
	/*------------------*/
	/*    VARIABLES     */
	/*------------------*/
    int[][] terrain;
    int ligF=0,colF=0,mouvement,nbTentative=0;
	/*------------------*/
	/*  INSTRUCTIONS    */
	/*------------------*/

        // Initialisation du Terrain
        terrain = FourmiliereV5.initTerrain();

        // Recherche de l'entree du parcours
        for(int i=0 ; i<DIM  ; i++) {
            if (terrain[0][i] == ENTREE) colF = i;
        }

        do
        {
            // Affichage du Terrain
            System.out.println ( FourmiliereV5.enChaine ( terrain, colF, ligF ) );

            // Deplacement de la fourmi par le joueur
            mouvement=aMainDroite(terrain, colF, ligF);
            nbTentative++;


            if(mouvement%2!=0)
            {
                if(mouvement<0) 
                {
                	ligF--;
                	orientation='N';
                }
                else
                {
                	ligF++;
                	orientation='S';
                }
            }
            else
            {
                if(mouvement<0)
                {
                	colF--;
                	orientation='O';
                }
                else
                {
                	colF++;
                	orientation='E';
                }
            }
            

        }while (! sortieTrouvee(terrain , colF, ligF) );

        // Affichage du Terrain avec la fourmi sur une des sorties
        System.out.println ( FourmiliereV5.enChaine ( terrain, colF, ligF ) );
        System.out.println("Sortie trouvee en " + nbTentative + " coups !");

    }


    private static int[][] initTerrain()
    {
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
        for (int i=0 ; i<DIM ; i++)
        {
            for(int j=0 ; j<DIM/2+1 ; j++)
            {
                tab[i][j]=bloc[i][j];
            }
        }

        // construction de la partie droite du terrain selon la symetrie verticale
        for (int i=0 ; i<DIM ; i++)
        {
            for(int j=DIM/2 ; j>=0 ; j--)
            {
                tab[i][DIM-1-j]=bloc[i][j];
            }
        }

        return tab;
    }

    private static String enChaine(int[][] tab, int fourmiPosX, int fourmiPosY)
    {
        String sRet="";

        for (int i=0 ; i<DIM ; i++)
        {
            for(int j=0 ; j<DIM ; j++)
            {
                if(fourmiPosX==j && fourmiPosY==i)
                {
                    // c'est l'emplacement de la fourmi, on la dessine
                    sRet+= ".";
                }
                else
                {
                    switch(tab[i][j])
                    {
                        case 0        : sRet+= " "; break;
                        case MUR      : sRet+= "X"; break;
                        case ENTREE   : sRet+= " "; break;
                        case SORTIE_1 : sRet+= " "; break;
                        case SORTIE_2 : sRet+= " "; break;
                    }
                }

            }
            sRet+= "\n";
        }


        return sRet;

    }

    private static boolean sortieTrouvee(int[][] tab, int fourmiPosX, int fourmiPosY)
    {
        return (tab[fourmiPosY][fourmiPosX]==SORTIE_1 ||
                tab[fourmiPosY][fourmiPosX]==SORTIE_2);
    }


    private static int aMainDroite(int[][] tab, int  colF, int ligF)
    {
 	
 		switch(orientation)
 		{
 		
 		case 'N' : 
 			
 	    if(colF+1<DIM)
           if(tab[ligF][colF+1]!=MUR) 
        
           	return +2;
 	   
		    else if(ligF-1>=0)
			    if(tab[ligF-1][colF]!=MUR) 
	        
			   return -1;
	 		
		    else if(colF-1>=0)
		        if(tab[ligF][colF-1]!=MUR) 
	  
		        	return -2;
 	   
		       else  if(ligF+1<DIM)
	            if(tab[ligF+1][colF]!=MUR)

	            	return +1;
	        
	 	break;
	 	
	 	case 'S' : 
      
			 if(colF-1>=0)
			       if(tab[ligF][colF-1]!=MUR) 
		            
			    	   return -2;
			    
				    else if(ligF+1<DIM)
				    	 if(tab[ligF+1][colF]!=MUR) 
				          
				    		 return +1;
			 
				  else if(colF+1<DIM)
					            if(tab[ligF][colF+1]!=MUR) 
					          
					            	return +2;

			 
		 else if(ligF-1>=0)
            if(tab[ligF-1][colF]!=MUR) 
           
            	return -1;
	    



	 	break;
	 	
	 	case 'E' : 
	 		
	         if(ligF+1<DIM)
	            if(tab[ligF+1][colF]!=MUR) 
	            	return +1;
	         
	            else  if(colF+1<DIM)
	                if(tab[ligF][colF+1]!=MUR) 
	                	return +2;
	        
				       else if(ligF-1>=0)
				            if(tab[ligF-1][colF]!=MUR) 
				           
				            	return -1;
		        
		         else if(colF-1>=0)
				     if(tab[ligF][colF-1]!=MUR) 
				    	 return -2;



	 	break;
	 	
	 	case 'O' :	
	 		
			  if(ligF-1>=0)
				   if(tab[ligF-1][colF]!=MUR) 
					   return -1;
			  
				   else if(colF-1>=0)
			       if(tab[ligF][colF-1]!=MUR) 
			    	   return -2;
			  
			       else if(ligF+1<DIM)
				            if(tab[ligF+1][colF]!=MUR) 
				            	return +1;
	        
	
	         
	         else if(colF+1<DIM)
			       if(tab[ligF][colF+1]!=MUR) 
			    	   return +2;
		      

	 	break;
 		}
 

        
        return 0;
    }
}
