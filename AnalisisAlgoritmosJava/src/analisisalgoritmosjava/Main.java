package analisisalgoritmosjava;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    new Ayuda();
                    break;
                case "-f":
                    new LeerFichero();
                    break;
                case "-dt":
                    new MostrarTiempo();
                    break;
                case "-di":
                    new MostrarEntrada();
                    break;
                case "-do":
                    new MostrarSalida();
                    break;
                default:
                    System.out.println("El formato correcto es: java -jar Algoritmos.jar [option] [fichero]");
                    break;
            }
        }

    }

}
