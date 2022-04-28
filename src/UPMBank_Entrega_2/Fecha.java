package UPMBank_Entrega_2;

public class Fecha {
    private int dia;
    private int mes;
    private int year;

    public Fecha(int dia, int mes, int year) { //constructor para meter la variable a la privada
        this.dia=dia;
        this.mes=mes;
        this.year =year;
    }

    public void imprimir() {
        System.out.printf("%02d/%02d/%04d\n", dia, mes, year);
    }

    //Métodos estáticos
    public static boolean esBisiesto(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static boolean comprobarFecha(int dia, int mes, int year) {
        boolean fechaCorrecta = (year >= 1920 && year <= 2003 && mes >= 1 && mes <=12 && dia >= 1 && dia <= 31);
        if (fechaCorrecta){
            if (mes == 2)
                fechaCorrecta = dia <= 28 || (dia <= 29 && esBisiesto(year));
            else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
                fechaCorrecta = dia <= 30;
        }
        return fechaCorrecta;
    }
}