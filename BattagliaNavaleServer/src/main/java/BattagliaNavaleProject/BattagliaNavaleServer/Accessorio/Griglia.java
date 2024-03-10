package BattagliaNavaleProject.BattagliaNavaleServer.Accessorio;

public class Griglia {

	        // Definizione della matrice 
			final static int GRID_DIMENSION=10;
	        static Square[][] griglia = new Square[GRID_DIMENSION][GRID_DIMENSION];
	        Square square;
	        String name;
	        
	        //creazione della griglia vuota
	        
	        public Griglia(String n){
	        	this.name=n;
	        // Inizializzazione della matrice con valori nulli
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                griglia[i][j] = new Square(0,0,0, ""); // valori nulli 
	                
	        }
	        }
	        }
	        
	        public static void addSquare(Square s) {
	        	
					griglia[s.x][s.y]= s;
				
	        }
	    
	
}
