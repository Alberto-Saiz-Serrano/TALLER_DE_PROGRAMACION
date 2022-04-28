package UPMBank_Entrega_2;

public class ListaMovimientos {
    private int numMovimientos;
    private final int MAX_MOVIMIENTOS = 50;
    private Movimiento [] movimientos;

    public ListaMovimientos() {
        this.movimientos = new Movimiento[MAX_MOVIMIENTOS];
        numMovimientos = 0;
    }

    public int getNumMovimientos() {
        return numMovimientos;
    }
    public int getMAX_MOVIMIENTOS() {
        return MAX_MOVIMIENTOS;
    }

    public void addMovimiento(Movimiento movimiento) {
        this.movimientos[numMovimientos] = movimiento;
        numMovimientos++;
    }

    public void imprimirMovimientos(){
        for(int x = 0; x < numMovimientos; x++){
            System.out.print("\t");
            movimientos[x].imprimir();
        }
    }
}