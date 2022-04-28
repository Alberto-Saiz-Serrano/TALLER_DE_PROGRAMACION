package UPMBank_Entrega_2;

public class ListaPrestamos {
    private int numPrestamos;
    private final int MAX_PRESTAMOS = 50;
    private Prestamo [] prestamos;

    public ListaPrestamos(){
        prestamos = new Prestamo[MAX_PRESTAMOS];
        numPrestamos = 0;
    }

    public int getNumPrestamos() {
        return numPrestamos;
    }
    public int getMAX_PRESTAMOS() {
        return MAX_PRESTAMOS;
    }

    public Prestamo getPrestamo(double capital, int numeroYears, double interesAnual){
        Prestamo prestamo = null;
        for(int x = 0; x < numPrestamos; x++){
            if(prestamos[x].getCapital() == capital && prestamos[x].getNumeroYears() == numeroYears && prestamos[x].getInteresAnual() == interesAnual){
                prestamo = prestamos[x];
            }

        }
        return prestamo;
    }

    public void addPrestamo(Prestamo prestamo) {
        this.prestamos[numPrestamos] = prestamo;
        numPrestamos++;
    }

    public void imprimirPrestamos(){
        for(int x = 0; x < numPrestamos; x++){
            System.out.print("\t");
            prestamos[x].imprimir();
        }
    }
}