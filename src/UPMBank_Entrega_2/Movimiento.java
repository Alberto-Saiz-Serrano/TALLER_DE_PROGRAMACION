package UPMBank_Entrega_2;

public class Movimiento {
    private double importe;
    private TipoMovimiento movimiento;

    public Movimiento(TipoMovimiento movimiento, double importe) {
        this.movimiento = movimiento;
        this.importe = importe;
    }

    enum TipoMovimiento {
        Deposito, Extraccion
    }

    public void imprimir(){
        System.out.println("Se ha hecho un/una " + movimiento + " de [" + importe + "€].");
    }
}
