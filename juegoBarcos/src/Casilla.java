public class Casilla {

    private int estado;
    private int posicion;

    public Casilla( int estado, int posicion) {
        this.estado = estado;
        this.posicion = posicion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * imprime el estado de la casilla q mira
     */
    public void imprimirEstado() {
        switch (getEstado()) {
            case 0:
                System.out.println("AGUA");
                break;
            case 1:
                System.out.println("BARCO");
                break;
            case 2:
                System.out.println("TOCADO");
                break;
            case 3:
                System.out.println("HUNDIDO");
                break;
        }
    }
}

