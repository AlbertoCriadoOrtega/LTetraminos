public class BOT extends Jugador{
    public BOT(String nombre, int tetraminosVivos) {
        super(nombre, tetraminosVivos);
    }

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
