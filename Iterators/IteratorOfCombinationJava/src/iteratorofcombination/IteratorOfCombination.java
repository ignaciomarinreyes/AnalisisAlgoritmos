package iteratorofcombination;

class IteratorOfCombination {

    private int[] combinacion;
    private int valormaximo;
    private int valorminimo;

    public IteratorOfCombination(int N) {
        this.combinacion = new int[N];
        this.valormaximo = 1;
        this.valorminimo = 0;
    }

    public boolean ultimaCombinacion() {
        for (int i = 0; i < combinacion.length; i++) {
            if (combinacion[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] siguienteCombinacion() {
        for (int i = 0; i < combinacion.length; i++) {
            if (combinacion[i] == valormaximo) {
                combinacion[i] = valorminimo;
            } else {
                combinacion[i] = combinacion[i] + 1;
                return combinacion;
            }
        }
        return null;
    }
}
