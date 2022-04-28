package UPMBank_Entrega_2;

public class ListaCuentas {
    public int numCuentas;
    private final int MAX_CUENTAS;
    private Cuenta [] cuentas;

    public ListaCuentas(int max) {
        MAX_CUENTAS = max;
        this.cuentas = new Cuenta[MAX_CUENTAS];
        numCuentas = 0;
    }

    public int getNumCuentas() {
        return numCuentas;
    }
    public int getMAX_CUENTAS() {
        return MAX_CUENTAS;
    }

    public Cuenta getCuenta(int posicion) {
        return cuentas[posicion];
    }

    public Cuenta getCuenta(String iban){
        Cuenta resultado = null;
        for(int x = 0; x < numCuentas; x++){
            if(cuentas[x].getIban().equals(iban)){
                resultado = cuentas[x];
            }
        }
        return resultado;
    }

    public void addCuentas(Cuenta cuenta) {
        this.cuentas[numCuentas] = cuenta;
        numCuentas++;
    }

    public void imprimirIbanesCuentas(){ //Imprime los ibanes de todas las cuentas
        for(int x = 0; x < numCuentas; x++){
            System.out.print((x + 1) + ":");
            cuentas[x].imprimirIban();
        }
    }

    public void imprimirTodoCuentas(){ //Imprime lo que posee la cuenta.
        for(int x = 0; x < numCuentas; x++){
            System.out.println("\n\t~~Cuenta " + (x + 1) + "~~ ");
            cuentas[x].imprimirCuenta();
        }
    }
}