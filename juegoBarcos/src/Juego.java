/**
 * @author alberto y Martin
 * @version 2023/03/20
 */
public class Juego {

    public static void main(String[] args) throws InterruptedException {

        // crea al bot con los barcos y sus posiciones correspondientes
        BOT bot = new BOT("IA", 5);
        bot.crearBot(bot.crearDatosBarcosBot());
        for (int i = 0; i < bot.getBarcos().length; i++) {
            bot.introducirBarcoBot(bot.getBarcos()[i]);
        }

        // crea al humano con los barcos y sus posiciones correspondientes
        Jugador uno = new Jugador("Martin", 5);
        uno.crearBarcos(uno.crearbarcosDatos());
        for (int i = 0; i < uno.getBarcos().length; i++) {
            uno.introducirBarco(uno.getBarcos()[i]);
            uno.getPropio().imprimirTablero();
        }

        boolean juego = true;

        //bucle del juego
        do {

            System.out.println();
            System.out.println("MOMENTO DE ATACAR");
            System.out.println();

            //ataca el humano primero
            uno.getEnemigo().imprimirTableroEnemigo();
            uno.ataque(uno.creardatosataque(), bot);


            //evalua si ya ha ganado
            if (bot.getPropio().terminar()){
                System.out.println("************************************************************************************ GANASTE TÚ ********************************************************************************************************************************************************");
                juego = false;
                return;
            }

            //guarda los datos de las coordenadas de ataque
            int[] datosDeAtaque = bot.datosataquebot();

            //el bot ataca
            bot.ataquebot(datosDeAtaque,uno);
            System.out.println();

            //evalua si ha ganado el bot
            if (uno.getPropio().terminar()){
                System.out.println("*********************************************************************************** GANÓ LA MAQUINA ********************************************************************************************************************************************************");
                juego = false;
                return;
            }

            uno.getPropio().imprimirTablero();

            Thread.sleep(2500);

        }while (juego);
    }
}
