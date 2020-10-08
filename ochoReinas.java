package modelo;

import java.util.ArrayList;

public class ochoReinas {
////////////////////////////////////////////*METODOS DE INICIALIZACION*///////////////////////////////////// 
    public ochoReinas(){
    	int tamanio = 8;
        if (tamanio < 4) throw new NullPointerException();
        this.numTablero = tamanio;
        inicializarVariables();
    }
    private void inicializarVariables(){
        this.lineax = new boolean[numTablero];
        this.lineay = new boolean[numTablero];
        this.solucion = new int[numTablero];
        for (int i = 0; i<numTablero;i++){
            this.lineax [i] = true;
            this.lineay [i] = true;
            this.solucion [i] = -1;
        }
        this.diagonalI = new boolean[2*numTablero-1];
        this.diagonalS = new boolean[2*numTablero-1];
        for (int i = 0; i<2*numTablero-1;i++){
            this.diagonalI[i] = true;
            this.diagonalS[i] = true;
        }
        existeSolucion = false;
    }
////////////////////////////////////////////////*METODOS IMPLEMENTADOS*///////////////////////////////////// 
    private void buscarSolucion(int fila){
        int colum = 0;
        while (colum < numTablero && !existeSolucion){
            if (lineax[fila] && lineay[colum] && diagonalI[colum-fila+numTablero-1] && diagonalS[colum+fila]){
 
                solucion[fila] = colum;
                lineax[fila] = false;
                lineay[colum] = false;
                diagonalI[colum-fila+numTablero-1] = false;
                diagonalS[colum+fila] = false;
 
                if (fila == numTablero-1 && solucionNueva(this.solucion)){
                	existeSolucion = true;
                }else{
                    if (fila+1 < numTablero ){
                        buscarSolucion(fila+1); 
                    }
                    if (!existeSolucion){                  
                        solucion[fila] = -1;
                        lineax[fila] = true;
                        lineay[colum] = true;
                        diagonalI[colum-fila+numTablero-1] = true;
                        diagonalS[colum+fila] = true;
                    }
                }
            }
            colum++;
        }
    }
    public void buscarSoluciones(){
        boolean aux = true;
        while(aux){
            buscarSolucion(0);
            if (solucionNueva(solucion)){
                aux = true;
                agregarSolucion();
            } else{
                aux = false;
            }
            inicializarVariables();
        }
    }
    public void buscarUnaSolucion(){
        buscarSolucion(0);
        agregarSolucion();
    }
    private void agregarSolucion(){
        soluciones.add(this.solucion);  
    }
    private boolean solucionNueva(int[] nuevaSolucion){
        if (nuevaSolucion[0] == -1) return false;
        boolean esNueva = true;
        int i = 0;
        while (i < soluciones.size() && esNueva){ 
            int[] unaSol = (int[]) soluciones.get(i);
            esNueva = !sonParecidas(unaSol,nuevaSolucion);
            i++;
        }
        return esNueva;
    }
    private  boolean sonParecidas (int[] x, int[] y){
        int i = 0;
        int j = 0;
        boolean aux = true;        
        while ((i < x.length) && (j < y.length)){
            if(x[i] != y[j]){
                return false;
            }
            i++;
            j++;            
        }
        return aux;
    }
    public ArrayList<int[]> getSoluciones(){
        return this.soluciones;
    }
//////////////////////////////////////////////*VARIABLES A UTILIZAR*////////////////////////////////////////////////////  
    private ArrayList<int[]> soluciones = new ArrayList<int[]>();
    private int numTablero; 
    private int[] solucion;
    private boolean[] lineax;
    private boolean[] lineay;
    private boolean[] diagonalS;
    private boolean[] diagonalI;
    private boolean existeSolucion;   
}
