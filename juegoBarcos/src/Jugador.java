import java.util.Scanner;

public class Jugador {


    Scanner scanner = new Scanner(System.in);
    private String nombre;
    private Tablero propio;
    private Tablero enemigo;
    private Tetramino[] barcos = new Tetramino[5];
    private int tetraminosVivos;

    public Jugador(String nombre, int tetraminosVivos) {
        this.nombre = nombre;
        this.enemigo = new Tablero();
        this.propio = new Tablero();
        this.tetraminosVivos = tetraminosVivos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tablero getPropio() {
        return propio;
    }

    public void setPropio(Tablero propio) {
        this.propio = propio;
    }

    public Tablero getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Tablero enemigo) {
        this.enemigo = enemigo;
    }

    public int getTetraminosVivos() {
        return tetraminosVivos;
    }

    public void setTetraminosVivos(int tetraminosVivos) {
        this.tetraminosVivos = tetraminosVivos;
    }

    public Tetramino[] getBarcos() {
        return barcos;
    }

    /**
     * limpia el terminal para que se entienda mejor
     */
    public void limpiartablero(){
            for (int i=0; i < 30; i++)
            {
                System.out.println();
            }
    }

    public void setBarcos(Tetramino[] barcos) {
        this.barcos = barcos;
    }

    /**
     * crea los datos del ataque para que no se salgan del tablero y los introduce en un array
     * @return coordenadas de ataque
     */
    public int[] creardatosataque() {
        int[] coordenadas = new int[2];

        do {
            System.out.println("dime la fila");
            coordenadas[1] = scanner.nextInt();

            if (coordenadas[1] < 0 || coordenadas[1] > 9) {
                System.err.println("posicion invalida");
            }

        } while (coordenadas[1] < 0 || coordenadas[1] > 9);

        do {
            System.out.println("dime la columna");
            coordenadas[0] = scanner.nextInt();

            if (coordenadas[0] < 0 || coordenadas[0] > 9) {
                System.err.println("posicion invalida");
            }

        } while (coordenadas[0] < 0 || coordenadas[0] > 9);

        return coordenadas;
    }

    /**
     * introduce las coordenadas de ataque y cambia los estados de las casillas ademas de cambiar la vida de los barcos atacados
     * en caso de que no las coordenadas sean de una casilla ya atacada te pide que ataques de nuevo
     * @param coordenadas
     * @param IA
     */
    public void ataque(int[] coordenadas, Jugador IA) {
        int columna = coordenadas[0];
        int fila = coordenadas[1];

        switch (IA.getPropio().getCasilla(fila, columna).getEstado()) {
            case 0:
                System.out.println("Agua");
                getEnemigo().getCasilla(fila, columna).setEstado(4);
                break;
            case 1:
                System.out.println("Enemigo tocado");
                getEnemigo().getCasilla(fila, columna).setEstado(2);
                IA.getPropio().getCasilla(fila, columna).setEstado(2);
                break;
            case 2:
                System.out.println("Posicion ya atacada");
                System.out.println("ataca de nuevo");
                ataque(creardatosataque(), IA);
                break;
        }

        enemigo.imprimirTableroEnemigo();
    }

    /**
     * revisa un array de int y mira si lo valores ya han sido repetidos
     * @param tipos
     * @param pos
     * @return true si ya existe el valor, false si no existe
     */
    private boolean existeValorRepetido(int[] tipos, int pos) {
        for (int i = 0; i < pos; i++) {
            if (tipos[pos] == tipos[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * crea los datos para introducir los barcos
     * @return las coordenadas para introducir los barcos
     */
    public int[] crearbarcosDatos() {
        boolean confirmacion = false;
        int[] tipos = new int[5];

        for (int a = 0; a < tipos.length; a++) {
            System.out.println("dime el tipo del barco " + (a + 1) + " del 1 al 7");

            //bucle para verificar que los valores son validos
            do {
                tipos[a] = scanner.nextInt();
                if (tipos[a] < 1 || tipos[a] > 7) {
                    System.err.println("valor de barco invalido");
                } else if (tipos[a] > 0 || tipos[a] < 8) {
                    confirmacion = true;
                }
            } while (confirmacion = false);


            while (existeValorRepetido(tipos, a)) { // Bucle while para verificar si el valor generado ya existe en el array

                //bucle para verificar que los valores son validos
                do {
                    System.err.println("valor repetido");
                    tipos[a] = scanner.nextInt();
                    if (tipos[a] < 1 || tipos[a] > 7) {
                        System.err.println("valor de barco invalido");
                    } else if (tipos[a] > 0 || tipos[a] < 8) {
                        confirmacion = true;
                    }
                } while (confirmacion = false);
            }
        }

        return tipos;
    }

    /**
     * crea los barcos con los tipos ya dados
     * @param tipos
     */
    public void crearBarcos(int[] tipos) {
        for (int i = 0; i < getBarcos().length; i++) {
            int tipo = tipos[i];
            Tetramino barco = new Tetramino(4, tipo, 1);
            barcos[i] = barco;
        }
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirLinea(int fila, int columna, Tetramino barco) {

        if (fila > 9 || columna > 6 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna; i < columna + 4; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila, columna + 3).setEstado(1);
        //alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 3), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean válidos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirCubo(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 8 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);
        //alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirTe(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                propio.imprimirTablero();
                System.out.println();
                System.err.println("posicion ya ocupada");
                System.out.println();
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna + 1).getEstado() != 0) {
            propio.imprimirTablero();
            System.out.println();
            System.err.println("posicion ya ocupada");
            System.out.println();
            return false;
        }


        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirEle(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            System.out.println();
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna).getEstado() != 0) {
            System.err.println("posicion ya ocupada");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        //Alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 2);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirJota(int fila, int columna, Tetramino barco) {

        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna + 2).getEstado() != 0) {
            System.err.println("posicion ya ocupada");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 2).setEstado(1);
        //Alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 2), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirZeta(int fila, int columna, Tetramino barco) {

        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        for (int i = columna + 1; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 2).setEstado(1);

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 2), 3);

        return true;
    }

    /**
     * introduce los valores a las casillas y se asegura que todos los valores sean validos para este tipo de barco
     * @param fila
     * @param columna
     * @param barco
     * @return si todo esta correcto true, sino false
     */
    public boolean introducirEse(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            System.err.println("posicion no posible");
            System.out.println();
            propio.imprimirTablero();
            System.out.println();
            return false;
        }

        for (int i = columna + 1; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                System.err.println("posicion ya ocupada");
                System.out.println();
                propio.imprimirTablero();
                System.out.println();
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);
        //Alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 3);

        return true;
    }

    /**
     * recibe un barco y dependiendo del tipo le aplica los valores correctos dentro del tablero y del barco,
     * en caso de haber algun problema repite el proceso de nuevo
     * @param barco
     */
    public void introducirBarco(Tetramino barco) {
        boolean confirmacion = false;

        do {
            System.out.println("dime la fila");
            int fila = scanner.nextInt();
            System.out.println("dime la columna");
            int columna = scanner.nextInt();

            switch (barco.getTipo()) {
                case 1: //LINEA
                    confirmacion = introducirLinea(fila, columna, barco);
                    break;
                case 2: //CUBO
                    confirmacion = introducirCubo(fila, columna, barco);
                    break;
                case 3: //LETRA TE
                    confirmacion = introducirTe(fila, columna, barco);
                    break;
                case 4: //LETRA ELE
                    confirmacion = introducirEle(fila, columna, barco);
                    break;
                case 5: //LETRA JOTA
                    confirmacion = introducirJota(fila, columna, barco);
                    break;
                case 6: //LETRA ZETA
                    confirmacion = introducirZeta(fila, columna, barco);
                    break;
                case 7: //LETRA ESE
                    confirmacion = introducirEse(fila, columna, barco);
                    break;
            }
        } while (!confirmacion); //Si ha habido algun problema realiza todo de nuevo

        scanner.nextLine();
    }


    /* metodos especificos del bot */

    /**
     * le da valores aleartorios de los tipos de barco para crear los barcos del bot
     * @return valores del los tipos de barco
     */
    public int[] crearDatosBarcosBot() {
        int[] tipos = new int[5];

        for (int i = 0; i < tipos.length; i++) {
            tipos[i] = (int) (Math.random() * 7 + 1);

            // Bucle while para verificar si el valor generado ya existe en el array
            while (existeValorRepetido(tipos, i)) {
                tipos[i] = (int) (Math.random() * 7 + 1);
            }
        }
        return tipos;
    }

    /**
     * crea los barcos para el bot
     * @param tiposbot
     */
    public void crearBot(int[] tiposbot)  {
        for (int i = 0; i < getBarcos().length; i++) {
            int tipo = tiposbot[i];
            Tetramino barco = new Tetramino(4, tipo, 1);
            barcos[i] = barco;
        }
    }


    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirLineabot(int fila, int columna, Tetramino barco) {

        if (fila > 9 || columna > 6 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 4; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila, columna + 3).setEstado(1);
        //alias


        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 3), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirCubobot(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 8 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);
        //alias

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirTebot(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna + 1).getEstado() != 0) {
            return false;
        }


        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);


        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirElebot(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna).getEstado() != 0) {
            return false;
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 2);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirJotabot(int fila, int columna, Tetramino barco) {

        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        if (getPropio().getCasilla(fila + 1, columna + 2).getEstado() != 0) {
            return false;
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 2).setEstado(1);

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 2), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirZetabot(int fila, int columna, Tetramino barco) {

        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        for (int i = columna + 1; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 1).setEstado(1);
        getPropio().getCasilla(fila + 1, columna + 2).setEstado(1);
        //Alias
        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 1), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna + 2), 3);

        return true;
    }

    /**
     * lo mismo que el caso del humano pero sin avisos
     * @param fila
     * @param columna
     * @param barco
     * @return
     */
    public boolean introducirEsebot(int fila, int columna, Tetramino barco) {
        if (fila > 8 || columna > 7 || fila < 0 || columna < 0) {
            return false;
        }

        for (int i = columna + 1; i < columna + 3; i++) {
            if (getPropio().getCasilla(fila, i).getEstado() != 0) {
                return false;
            }
        }

        for (int i = columna; i < columna + 2; i++) {
            if (getPropio().getCasilla(fila + 1, i).getEstado() != 0) {
                return false;
            }
        }

        getPropio().getCasilla(fila, columna).setEstado(1);
        getPropio().getCasilla(fila, columna + 1).setEstado(1);
        getPropio().getCasilla(fila, columna + 2).setEstado(1);
        getPropio().getCasilla(fila + 1, columna).setEstado(1);

        barco.setCasilla(getPropio().getCasilla(fila, columna), 0);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 1), 1);
        barco.setCasilla(getPropio().getCasilla(fila, columna + 2), 2);
        barco.setCasilla(getPropio().getCasilla(fila + 1, columna), 3);

        return true;
    }

    /**
     * introduce los barcos en el tablero asegurandose que los barcos entran en el array,
     * funciona igual que el metodo para el humano
     * @param barco
     */
    public void introducirBarcoBot(Tetramino barco) {
        boolean confirmacion = false;

        do {
            int fila = (int) (Math.random() * 10);
            int columna = (int) (Math.random() * 10);

            if (fila == 10) {
                fila = 9;
            }

            if (columna == 10) {
                columna = 9;
            }

            switch (barco.getTipo()) {
                case 1:
                    confirmacion = introducirLineabot(fila, columna, barco);
                    break;
                case 2:
                    confirmacion = introducirCubobot(fila, columna, barco);
                    break;
                case 3:
                    confirmacion = introducirTebot(fila, columna, barco);
                    break;
                case 4:
                    confirmacion = introducirElebot(fila, columna, barco);
                    break;
                case 5:
                    confirmacion = introducirJotabot(fila, columna, barco);
                    break;
                case 6:
                    confirmacion = introducirZetabot(fila, columna, barco);
                    break;
                case 7:
                    confirmacion = introducirEsebot(fila, columna, barco);
                    break;
            }
        } while (!confirmacion);
    }

    /**
     * crea los datos de ataque de manera aleatoria para que sean validos
     * @return datos de ataque del bot
     */
    public int[] datosataquebot() {
        int[] datos = new int[2];
        do {
            datos[0] = (int) (Math.random() * 10);
        } while (datos[0] < 0 || datos[0] > 9);

        do {
            datos[1] = (int) (Math.random() * 10);
        } while (datos[1] < 0 || datos[1] > 9);

        return datos;
    }

    /**
     * funciona igual que el humano solo que sin avisos en caso de error
     * @param coordenadas
     * @param player
     */
    public void ataquebot(int[] coordenadas, Jugador player) {
        int columna = coordenadas[0];
        int fila = coordenadas[1];


        switch (player.getPropio().getCasilla(fila, columna).getEstado()) {
            case 0:
                getEnemigo().getCasilla(fila, columna).setEstado(4);
                player.getPropio().getCasilla(fila, columna).setEstado(4);
                break;
            case 1:
                getEnemigo().getCasilla(fila, columna).setEstado(2);
                player.getPropio().getCasilla(fila, columna).setEstado(2);
                break;
            case 2:
                ataquebot(datosataquebot(), player);
                break;
        }
    }
}