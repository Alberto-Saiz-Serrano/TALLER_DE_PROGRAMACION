package UPMBank_Entrega_2;

public class Prestamo {
    private float capital;
    private int numeroYears;
    private int numeroMeses;
    private float interesAnual;
    private float interesMensual;
    private float cuota;

    public Prestamo(float capital, int numeroYears, float interesAnual) {
        this.capital = capital;
        this.numeroYears = numeroYears;
        this.numeroMeses = numeroYears * 12;
        this.interesAnual = interesAnual;
        this.interesMensual = (interesAnual / 100 ) / 12;
        this.cuota = (float) (capital * interesMensual * ((Math.pow(1 + interesMensual, numeroMeses)) / (Math.pow(1 + interesMensual, numeroMeses) - 1)));
    }

    public float getCapital() {
        return capital;
    }
    public int getNumeroYears() {
        return numeroYears;
    }
    public int getNumeroMeses() {
        return numeroMeses;
    }
    public float getInteresAnual() {
        return interesAnual;
    }
    public float getInteresMensual() {
        return interesMensual;
    }
    public float getCuota() {
        return cuota;
    }

    public void imprimir(){
        System.out.println("Préstamo de [" + capital + "€] durante " + numeroYears + "/" + numeroMeses + " años/meses con un interés anual del [" + interesAnual + " %].");
    }
}