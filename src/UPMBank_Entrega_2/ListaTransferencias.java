package UPMBank_Entrega_2;

public class ListaTransferencias {
    private int numTransferencias;
    private final int MAX_TRANSFERENCIAS = 50;
    private Transferencia [] transferencias;

    public ListaTransferencias(){
        this.transferencias = new Transferencia[MAX_TRANSFERENCIAS];
        numTransferencias = 0;
    }

    public int getNumTransferencias() {
        return numTransferencias;
    }
    public int getMAX_TRANSFERENCIAS() {
        return MAX_TRANSFERENCIAS;
    }

    public Transferencia getTransferencia(int posicion){
        return transferencias[posicion];
    }

    public void addTransferencia(Transferencia transferencia){
        this.transferencias[numTransferencias] = transferencia;
        numTransferencias++;
    }

    public void imprimirTransferenciasEmitidas(){
        for(int x = 0; x < numTransferencias; x++){
            System.out.print("\t");
            transferencias[x].imprimirTransferenciaEmitida();
        }
    }
    public void imprimirTransferenciasRecibidas(){
        for(int x = 0; x < numTransferencias; x++){
            System.out.print("\t");
            transferencias[x].imprimirTransferenciaRecibida();
        }
    }
}