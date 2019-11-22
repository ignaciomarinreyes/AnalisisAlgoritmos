package iteratorofcombination;

public class Main {

    public static void main(String[] args) {
        int[] combinacion;
        IteratorOfCombination it = new IteratorOfCombination(args.length);
        while (!it.ultimaCombinacion()) {
            combinacion = it.siguienteCombinacion();
            for (int i = 0; i < combinacion.length; i++) {
                if (combinacion[i] == 1) {
                    System.out.print(args[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
