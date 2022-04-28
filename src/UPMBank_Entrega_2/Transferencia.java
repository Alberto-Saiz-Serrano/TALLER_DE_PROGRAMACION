package UPMBank_Entrega_2;

public class Transferencia {
    private String ibanEmisor;
    private double importe;
    private String ibanReceptor;

    public Transferencia(String ibanEmisor, double importe, String ibanReceptor) {
        this.ibanEmisor = ibanEmisor;
        this.importe = importe;
        this.ibanReceptor = ibanReceptor;
    }

    public String getIbanReceptor() {
        return ibanReceptor;
    }
    public double getImporte() {
        return importe;
    }

    public void imprimirTransferenciaEmitida(){
        System.out.println("[" + ibanEmisor + "] envía " + importe + " € a [" + ibanReceptor + "].");
    }
    public void imprimirTransferenciaRecibida(){
        System.out.println("[" + ibanReceptor + "] recibe " + importe + " € de [" + ibanEmisor + "].");
    }
}
