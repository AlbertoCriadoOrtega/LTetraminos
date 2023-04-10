public class Tablero {

    private Casilla[][] casillas = new Casilla[10][10];

    /**
     * constructor del tablero, crea las casillas correspondientes
     */
    public Tablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                Casilla casilla = new Casilla( 0, i+j);
                casillas[i][j] = casilla;
            }
        }
    }

    /**
     * Coge una casilla de la posicion en el tablero que le pidas
     * @return la casilla de la posicion a la que se pide
     * */
    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public void setCasilla(Casilla[][] casilla) {
        this.casillas = casilla;
    }

    /**
     * enseña el tablero donde tienes tus propios barcos
     */
    public void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                switch (getCasilla(i, j).getEstado()) {
                    case 0:
                        System.out.print(" ▒ "); //agua
                        break;
                    case 1:
                        System.out.print(" S "); //barco
                        break;
                    case 2:
                        System.out.print(" X "); //tocado
                        break;
                    case 4:
                        System.out.print(" * "); //agua pero que ya ha sido disparada
                        break;
                }

            }
            System.out.println("");
        }
    }

    /**
     * enseña el tablero donde tienes los barcos enemigos
     */
    public void imprimirTableroEnemigo(){
        System.out.println("");
        System.out.println("tablero enemigo".toUpperCase());
        System.out.println("");

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                switch (getCasilla(i, j).getEstado()) {
                    case 0:
                        System.out.print(" - "); //agua
                        break;
                    case 2:
                        System.out.print(" X "); //tocado
                        break;
                    case 4:
                        System.out.print(" W "); //agua pero que ya ha sido disparada
                        break;
                }

            }
            System.out.println("");
        }
    }

    /**
     * metodo que determina si la partida a terminado contando las casillas ya tocadas
     * @return
     */
    public boolean terminar(){
        int fin = 0;

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                switch (getCasilla(i, j).getEstado()) {
                    case 2:
                        fin++; //cada vez que detecta un tocado le suma uno
                        break;
                }
            }
        }

        if (fin == 16){ //si son (numero de barcos * puntos de vida de cada barco) termina
            return true;
        }

        return false;
    }
}
