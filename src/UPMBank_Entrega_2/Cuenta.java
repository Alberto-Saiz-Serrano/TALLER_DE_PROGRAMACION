package UPMBank_Entrega_2;

import java.lang.Math;
import java.lang.Integer;

public class Cuenta {
    public final static int CE = 9010;
    private int CS;
    private int DC;
    private long NC;
    private String iban;
    private double saldo;
    private TipoCuenta.Tipo tipocuenta;
    private ListaMovimientos lMovimientos;
    private ListaPrestamos lPrestamos;
    private ListaTransferencias lTransferenciasEmitidas;
    private ListaTransferencias lTransferenciasRecibidas;

    public Cuenta(int cs, int dc, long nc, String iban, TipoCuenta.Tipo tipocuenta) {
        this.CS = cs;
        this.DC = dc;
        this.NC = nc;
        this.iban = iban;
        this.saldo = 0;
        this.tipocuenta = tipocuenta;
        this.lMovimientos = new ListaMovimientos();
        this.lPrestamos = new ListaPrestamos();
        this.lTransferenciasEmitidas = new ListaTransferencias();
        this.lTransferenciasRecibidas = new ListaTransferencias();
    }

    public long getNC() {
        return NC;
    }
    public String getIban() {
        return iban;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public ListaMovimientos getlMovimientos() {
        return lMovimientos;
    }
    public ListaPrestamos getlPrestamos() {
        return lPrestamos;
    }
    public ListaTransferencias getlTransferenciasEmitidas() {
        return lTransferenciasEmitidas;
    }
    public ListaTransferencias getlTransferenciasRecibidas() {
        return lTransferenciasRecibidas;
    }

    public void imprimirIban(){
        System.out.printf(" IBAN ---> [ %s ] (%s) ---> %.2f€.\n", iban, tipocuenta, saldo);
    }

    public void imprimirCuenta(){
        System.out.println("Iban ---> [" + iban + "]");
        System.out.println("CE: [" + CE + "] - CS: [0" + CS + "] - DC: [" + DC + "] - NC: [" + NC + "]");
        System.out.println("Tipo de Cuenta --->  [" + tipocuenta + "]");
        System.out.println("Saldo ---> [" + saldo + "€]");
        System.out.println("· Movimientos: ");
        lMovimientos.imprimirMovimientos();
        System.out.println("· Préstamos: ");
        lPrestamos.imprimirPrestamos();
        System.out.println("· Transferencias emitidas: ");
        lTransferenciasEmitidas.imprimirTransferenciasEmitidas();
        System.out.println("· Transferencias recibidas: ");
        lTransferenciasRecibidas.imprimirTransferenciasRecibidas();
    }

    public double metodoTotalEnviado(Cuenta cuenta){
        double saldoTotal = 0;
        for(int x = 0; x < lTransferenciasEmitidas.getNumTransferencias(); x++){
            if(lTransferenciasEmitidas.getTransferencia(x).getIbanReceptor().equals(cuenta.getIban())){
                saldoTotal += lTransferenciasEmitidas.getTransferencia(x).getImporte();
            }
        }
        return saldoTotal;
    }

    //Métodos estáticos

    public static long FuncionNC(){
        long nc;
        int d1 = (int) Math.floor(Math.random() * 10);
        int d2 = (int) Math.floor(Math.random() * 10);
        int d3 = (int) Math.floor(Math.random() * 10);
        int d4 = (int) Math.floor(Math.random() * 10);
        int d5 = (int) Math.floor(Math.random() * 10);
        int d6 = (int) Math.floor(Math.random() * 10);
        int d7 = (int) Math.floor(Math.random() * 10);
        int d8 = (int) Math.floor(Math.random() * 10);
        int d9 = (int) Math.floor(Math.random() * 10);
        int d10 = (int) Math.floor(Math.random() * 10);

        String r1 = String.valueOf(d1);
        String r2 = String.valueOf(d2);
        String r3 = String.valueOf(d3);
        String r4 = String.valueOf(d4);
        String r5 = String.valueOf(d5);
        String r6 = String.valueOf(d6);
        String r7 = String.valueOf(d7);
        String r8 = String.valueOf(d8);
        String r9 = String.valueOf(d9);
        String r10 = String.valueOf(d10);

        String juntarnc = r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10;
        nc = Long.parseLong(juntarnc);
        return nc;
    }

    public static int generarDC(long nc, int cs) {
        String dcstring, c1, c2;
        int c1entero, c2entero;
        int numeroc1, numeroc2, dc;

        numeroc1 = 6 * (cs % 10) + 3 * (cs / 10 % 10) + 7 * (cs / 100 % 10) + 9 * (cs / 1000);
        numeroc1 += 10 * (CE % 10) + 5 * (CE / 10 % 10) + 8 * (CE / 100 % 10) + 4 * (CE / 1000);
        numeroc1 = 11 - (numeroc1 % 11);
        if (numeroc1 == 11) {
            c1entero = 0;
        } else if (numeroc1 == 10) {
            c1entero = 1;
        } else {
            c1entero = numeroc1;
        }

        numeroc2 = (int) ((nc / 1000000000) + 2 * (nc / 100000000 % 10) + 4 * (nc / 10000000 % 10) + 8 * (nc / 1000000 % 10) + 5 * (nc / 100000 % 10));
        numeroc2 += 10 * (nc / 10000 % 10) + 9 * (nc / 1000 % 10) + 7 * (nc / 100 % 10) + 3 * (nc / 10 % 10) + 6 * (nc % 10);
        numeroc2 = 11 - (numeroc2 % 11);
        if (numeroc2 == 11) {
            c2entero = 0;
        } else if (numeroc2 == 10) {
            c2entero = 1;
        } else {
            c2entero = numeroc1;
        }
        c1 = String.valueOf(c1entero);
        c2 = String.valueOf(c2entero);
        dcstring = c1 + c2;
        dc = Integer.parseInt(dcstring);
        return dc;
    }

    public static String SacarIban(int cs, int dc, long nc) {
        String iban;
        String ceString = String.valueOf(CE);
        String csString = String.valueOf(cs);
        String dcString = String.valueOf(dc);
        String ncString = String.valueOf(nc);
        iban = ceString + "0" + csString + dcString + ncString;
        return iban;
    }
}