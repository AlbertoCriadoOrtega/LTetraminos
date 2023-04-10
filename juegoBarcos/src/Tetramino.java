public class Tetramino {

    private int vida;
    private int tipo;
    private int posicion;
    private Casilla[] casillas;

    public Tetramino(int vida, int tipo, int posicion) {
        this.vida = vida;
        this.tipo = tipo;
        this.posicion = posicion;
        this.casillas = new Casilla[4];
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Casilla[] getCasillas() {
        return casillas;
    }

    /**
     *
     * establece en la casilla en una posicion del array que le das
     * @param casilla
     * @param posicion
     */
    public void setCasilla(Casilla casilla, int posicion) {
        casillas[posicion] = casilla;
    }


    /**
     * evalua las casillas dentro de un barco cuando se la ha disparado
     */
    public void tocado() {

        setVida(4);

        for (int i = 0; i < getCasillas().length; i++) {
            if (getCasillas()[i].getEstado() == 2) { //si el estado es tocado le resta la vida en uno
                vida --;
            }
        }

        if (getVida() <= 0) {
            System.out.println("!HUNDIDO!");
        }
    }

    /**
     * revisa las casillas y si la casilla que le has dado es parte de las casillas que tiene el barco
     * @param casilla
     */
    public void detectarCasilla(Casilla casilla) {
        for (int i = 0; i < casillas.length; i++) {
            if (casillas[i].equals(casilla)){
                tocado();
            }
        }
    }

}
