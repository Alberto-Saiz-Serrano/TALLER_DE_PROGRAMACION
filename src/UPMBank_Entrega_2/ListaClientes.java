package UPMBank_Entrega_2;

public class ListaClientes {
    private int numClientes;
    private final int MAX_CLIENTES;
    private Cliente [] clientes;

    public ListaClientes(int max) {
        MAX_CLIENTES = max;
        this.clientes = new Cliente[MAX_CLIENTES];
        numClientes = 0;
    }

    public Cliente getCliente(String dni) {
        Cliente resultado = null;
        for(int x = 0; x < numClientes; x++){
            if(clientes[x].getDni().equals(dni)){
                resultado = clientes[x];
            }
        }
        return resultado;
    }
    public Cliente getCliente(int posicion){
        return  clientes[posicion];
    }
    public int getNumClientes() {
        return numClientes;
    }
    public int getMAX_CLIENTES() {
        return MAX_CLIENTES;
    }

    public void addCliente(Cliente cliente){
        this.clientes[numClientes] = cliente;
        numClientes++;
    }

    public void imprimirClientes(){
        for(int x = 0; x < numClientes; x++){
            System.out.println("\n\t/Cliente " + (x + 1) +"/");
            clientes[x].imprimirTodoClientes();
        }
    }
}