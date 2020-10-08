package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Principal {
 
    /**
     * Autor Marcos Roque
     */
    public static void main(String[] args) {
    	String cx[] = {"","A","B","C","D","E","F","G","H"};
        ochoReinas reinas= new ochoReinas();
        reinas.buscarSoluciones();
        ArrayList<?> soluciones = reinas.getSoluciones();
        Random aleatorio = new Random();
        int random = aleatorio.nextInt(soluciones.size());
        for (int i = 0; i < 1;i++){
        	//Como hay 92 soluciones, genere un numero random para que no siempre sea la misma
            int[] cy  = (int[]) soluciones.get(random);
            System.out.println("Juego No." + (random+1) + ":");
            for (int j = 0; j < cy.length;j++){
                System.out.print("Reina "+(j+1)+":(" + cx[j+1] + "," + (cy[j]+1) + ")"+"\n");
            }
        }
        System.exit(0);
    }
}
