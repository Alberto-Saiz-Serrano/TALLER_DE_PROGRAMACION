//Alberto Saiz Serrano-IWSIM11-bs0128

package UPMBank_Entrega_1;

import java.util.Objects;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Math;

public class UPMBank1 {
    static String nombre = "", apellidos = "", dni = "", mes = "", correo = "", deposito = "", extraccion = "", iban="", c2;
    static String transaccion = "", ce = "9010", cs = "0201", tipocuenta = "";
    static int dia = 0, year = 0, numeroc2;
    static char letradni = 0;
    static float saldo = 0, capitalvivo = 0;

    public static void main(String[] arg) {
        MenuPrincipal();
    }
    public static void MenuPrincipal(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("""

                $$\\   $$\\ $$$$$$$\\  $$\\      $$\\       $$$$$$$\\   $$$$$$\\  $$\\   $$\\ $$\\   $$\\\s
                $$ |  $$ |$$  __$$\\ $$$\\    $$$ |      $$  __$$\\ $$  __$$\\ $$$\\  $$ |$$ | $$  |
                $$ |  $$ |$$ |  $$ |$$$$\\  $$$$ |      $$ |  $$ |$$ /  $$ |$$$$\\ $$ |$$ |$$  /\s
                $$ |  $$ |$$$$$$$  |$$\\$$\\$$ $$ |      $$$$$$$\\ |$$$$$$$$ |$$ $$\\$$ |$$$$$  / \s
                $$ |  $$ |$$  ____/ $$ \\$$$  $$ |      $$  __$$\\ $$  __$$ |$$ \\$$$$ |$$  $$<  \s
                $$ |  $$ |$$ |      $$ |\\$  /$$ |      $$ |  $$ |$$ |  $$ |$$ |\\$$$ |$$ |\\$$\\ \s
                \\$$$$$$  |$$ |      $$ | \\_/ $$ |      $$$$$$$  |$$ |  $$ |$$ | \\$$ |$$ | \\$$\\\s
                 \\______/ \\__|      \\__|     \\__|      \\_______/ \\__|  \\__|\\__|  \\__|\\__|  \\__|
                                                                                              \s
                """);
        System.out.println("~~Bienvendo a la UPMBank~~");
        int elegirquehacer;
        System.out.println("\n1. Darse de alta como nuevo cliente.");
        System.out.println("2. Iniciar sesión en una cuenta existente.");
        System.out.println("3. Crear una cuenta bancaria.");
        System.out.println("4. Realizar operaciones en su cuenta.");
        System.out.println("5. Mostrar las operaciones de la cuenta.");
        System.out.println("6. Mostrar los datos de la cuenta.");
        System.out.println("0. Salir de la UPMBank.");
        System.out.print("\nElija un número entre 0 y 6: ");
        elegirquehacer = teclado.nextInt();
        switch(elegirquehacer){
            case 1:
                NuevoCliente();
                break;
            case 2:
                System.out.println("Esta función esta en desarollo, sentimos las molestias.");
                MenuPrincipal();
                break;
            case 3:
                TipoCuenta();
                break;
            case 4:
                MenuSecundario();
                break;
            case 5:
                Operaciones();
                break;
            case 6:
                Datos();
                break;
            case 0:
                System.out.println("---------------------");
                System.out.println("~~SESION FINALIZADA~~");
                System.out.println("---------------------");
                break;
            default:
                System.out.println("El numero que ha introducido es incorrecto.");
                MenuPrincipal();
        }
    }

    public static void NuevoCliente() {
        //Crea una cuenta para un cliente nuevo
        Scanner scan = new Scanner(System.in);

        deposito = "";
        extraccion = "";
        transaccion = "";
        tipocuenta = "";
        System.out.println("Introduzca sus datos para darse de alta como cliente.");
        do {
            System.out.print("Nombre: ");
            nombre = scan.nextLine();
        }while(nombre.isBlank());
        do {
            System.out.print("Apellidos: ");
            apellidos = scan.nextLine();
        }while(apellidos.isBlank());
        System.out.println("Fecha de Nacimiento.");
        System.out.print("Dia: ");
        dia = scan.nextInt();
        System.out.print("Mes: ");
        mes = scan.next();
        Comprobarmes();
        Comprobarfecha();
        System.out.print("Año: ");
        year = scan.nextInt();
        Comprobaryear();
        System.out.println("Tu fecha de Nacimiento es: " + dia + "/" + mes + "/" + year);
        Comprobarcorreo();
        System.out.print("DNI(Números): ");
        dni = scan.next();
        Longituddni();
        System.out.print("Letra del DNI: ");
        letradni = scan.next().charAt(0);
        letradni = Character.toUpperCase(letradni); //Pone la letra en mayúsculas
        System.out.println("Tu DNI es: " + dni + letradni);
        iban = ce + cs + Funtiondc() + FuncionNC();
        System.out.println("\nBienvenido a la UPMBank " + nombre + " " + apellidos + "\n" +
                "-----------------------------\n" +
                "~~Gracias por darse de alta~~\n" +
                "-----------------------------\n");
        MenuPrincipal();
    }

    public static void Comprobarmes() {
        Scanner compmes = new Scanner(System.in);
        boolean valid = false;
        do {
            int comprobarmes;
            try {
                comprobarmes = Integer.parseInt(mes);
            } catch (Exception e) {
                comprobarmes = 14;
            }
            if (comprobarmes > 12 || comprobarmes < 1) {
                System.out.print("Por favor, introduzca un mes válido\n Mes: ");
                mes = compmes.next();
            } else {
                valid = true;
            }

        } while (!valid);
    }

    public static void Comprobarfecha() {
        Scanner compfecha = new Scanner(System.in);
        boolean comprobacionfecha = true;
        do{
            if (Objects.equals(mes, "1") || Objects.equals(mes, "01")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 31) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "2") || Objects.equals(mes, "02")) {
                while (dia < 1 || dia > 29) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 30) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "3") || Objects.equals(mes, "03")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "4") || Objects.equals(mes, "04")) {
                while (dia < 1 || dia > 30) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 31) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "5") || Objects.equals(mes, "05")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "6") || Objects.equals(mes, "06")) {
                while (dia < 1 || dia > 30) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 31) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "7") || Objects.equals(mes, "07")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "8") || Objects.equals(mes, "08")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "9") || Objects.equals(mes, "09")) {
                while (dia < 1 || dia > 30) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 31) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "10")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "11")) {
                while (dia < 1 || dia > 30) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 31) {
                    comprobacionfecha = false;
                }
            } else if (Objects.equals(mes, "12")) {
                while (dia < 1 || dia > 31) {
                    System.out.println("Introduce un día válido");
                    dia = compfecha.nextInt();
                }
                if (dia > 1 && dia < 32) {
                    comprobacionfecha = false;
                }
            }
        }while (comprobacionfecha);

    }

    public static void Comprobaryear() {
        Scanner compyear = new Scanner(System.in);
        while (year < 1920 || year > 2021) {
            System.out.println("Intoduzca un año válido: ");
            year = compyear.nextInt();
        }
    }

    public static void Comprobarcorreo() {
        Scanner corr = new Scanner(System.in);
        boolean correoCorrecto = false;
        do{
            System.out.print("Correo UPM: ");
            correo = corr.next();
            if (correo.startsWith("@")) {
                System.out.println("Su correo no es válido.");
            } else if (correo.endsWith("@alumnos.upm.es") || correo.endsWith("@upm.es")) {
                System.out.println("Su correo es válido.");
                correoCorrecto = true;
            } else {
                System.out.println("Su correo no es válido.");
            }
        }while (!correoCorrecto);
    }

    public static void Longituddni() {
        Scanner longdni = new Scanner(System.in);
        while (dni.length() != 8) {
            System.out.print("!SU DNI NO ES VÁLIDO!\nIntroduzca los 8 números: ");
            dni = longdni.next();
        }
    }
    public static void TipoCuenta() {
        Scanner menu = new Scanner(System.in);
        int tipocuentaelegida;
        boolean cuentacorrecta = false;
        if(nombre.equals("")){
            System.out.println("Debe darse de alta primero.");
        }
        else{
            System.out.println("Vamos a crear una cuenta bancaria.");
            System.out.println("Estos son los tipos de cuentas que poseemos: ");
            System.out.println("\n1-Corriente");
            System.out.println("2-Ahorro");
            System.out.println("3-Remunerada");
            do{
                System.out.print("\nIntroduce el número de la cuenta que deseas: ");
                tipocuentaelegida = menu.nextInt();
                switch (tipocuentaelegida) {
                    case 1:
                        tipocuenta = "Corriente";
                        System.out.println("Ha elegido la Cuenta " + tipocuenta + ".");
                        System.out.println("Su número de cuenta es " + iban);
                        cuentacorrecta = true;
                        break;
                    case 2:
                        tipocuenta = "Ahorro";
                        System.out.println("Ha elegido la Cuenta " + tipocuenta + " .");
                        System.out.println("Su número de cuenta es " + iban);
                        cuentacorrecta = true;
                        break;
                    case 3:
                        tipocuenta = "Remunerada";
                        System.out.println("Ha elegido la Cuenta " + tipocuenta + ".");
                        System.out.println("Su número de cuenta es " + iban);
                        cuentacorrecta = true;
                        break;
                    default:
                        System.out.println("El número es incorrecto.");
                        break;
                }
            }while (!cuentacorrecta);
        }
        MenuPrincipal();
    }

    public static void MenuSecundario() {
        Scanner oper = new Scanner(System.in);
        int operation;
        if(nombre.equals("")){
            System.out.println("Debe darse de alta primero");
            MenuPrincipal();
        }
        else{
            System.out.println("\nBienvenido a su cuenta, estos son los tipos de operaciones que puede realizar: ");
            System.out.println("\n1-Deposito.");
            System.out.println("2-Extracción.");
            System.out.println("3-Transferencia.");
            System.out.println("4-Préstamos Hipotecarios.");
            System.out.println("0-Volver al Menu Principal.");
            System.out.print("\nElija un número entre 0 y 4: ");
            operation = oper.nextInt();
            switch(operation) {
                case 1:
                    MenuDepositos();
                    break;
                case 2:
                    MenuExtracciones();
                    break;
                case 3:
                    MenuTransferencias();
                    break;
                case 4:
                    MenuPrestamos();
                    break;
                case 0:
                    MenuPrincipal();
                    break;
                default:
                    System.out.println("El numero que ha ingresado no es válido.");
                    MenuSecundario();
            }
        }
    }
    public static long FuncionNC(){
        long NC;

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

        String juntarnc = r1 + r2 + r2 + r4 + r5 + r6 + r7 + r8 + r9 + r10;
        NC = Long.parseLong(juntarnc);
        numeroc2 = 11 - ((d1 + 2 * d2 + 4 * d3 + 8 * d4 + 5 * d5 + 10 * d6 + 9 * d7 + 7 * d8 + 3 * d9 + 6 * d10) % 11);
        return NC;
    }

    public static String Funtiondc() {
        String dc, c1;
        int a1 = 9, a2 = 0, a3 = 1, a4 = 0;
        int b1 = 0, b2 = 2, b3 = 0, b4 = 1;
        int c1entero = 0;
        int numeroc1;
        numeroc1 = 11 - ((6 * b4 + 3 * b3 + 7 * b2 + 9 * b1 + 10 * a4 + 5 * a3 + 8 * a2 + 4 * a1) % 11);//
        if (numeroc1 < 10) {
            c1entero = numeroc1;
        }
        else if (numeroc1 == 11) {
            c1entero = 0;
        }
        else if (numeroc1 == 10) {
            c1entero = 1;
        }
        c1 = String.valueOf(c1entero);
        c2 = String.valueOf(numeroc2);
        dc = c1 + c2;
        return dc;
    }




    public static void MenuDepositos() {
        Scanner dep = new Scanner(System.in);
        String confirmardeposito;
        if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }
        else {
            System.out.println("Bienvenido al apartado de Depósitos.");
            System.out.print("¿Que cantidad de dinero desea depositar?: ");
            float depositar = dep.nextFloat();
            System.out.print("Inserte CONFIRMAR para aceptar el depósito: ");
            confirmardeposito = dep.next();
            while (!Objects.equals(confirmardeposito, "CONFIRMAR")) {
                System.out.println("No lo ha escrito correctamente.");
                System.out.print("Si desea continuar inserte CONFIRMAR, si desea cancelarlo escriba CANCELAR: ");
                confirmardeposito = dep.next();
            }
            if (confirmardeposito.equals("CONFIRMAR")) {
                saldo += depositar;
                System.out.println("El saldo de su cuenta es de: " + saldo);
                deposito = "Se ha depositado [" + depositar + " €] y quedan en la cuenta [" + saldo + " €]\n";
            } else if (confirmardeposito.equals("CANCELAR")) {
                System.out.println("La operación ha sido cancelada.");
            }
            MenuSecundario();
        }
    }

    public static void MenuExtracciones() {
        Scanner ext = new Scanner(System.in);
        String confirmarextraccion;
        float extraer;
        if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }
        else {
            System.out.println("Bienvenido al apartado de Extracciones.");
            System.out.print("¿Que cantidad de dinero desea extraer?: ");
            extraer = ext.nextFloat();
            System.out.print("Inserte CONFIRMAR para aceptar la extracción: ");
            confirmarextraccion = ext.next();
            while (!Objects.equals(confirmarextraccion, "CONFIRMAR") && !Objects.equals(confirmarextraccion, "CANCELAR")) {
                System.out.println("No lo ha escrito correctamente.");
                System.out.print("Si desea continuar inserte CONFIRMAR, si desea cancelarlo escriba CANCELAR: ");
                confirmarextraccion = ext.next();
            }
            if (confirmarextraccion.equals("CONFIRMAR")) {
                if (extraer > saldo) {
                    System.out.println("No tiene suficiente saldo para realizar la operación.");
                } else {
                    saldo -= extraer;
                    System.out.println("El saldo de su cuenta es de: " + saldo);
                    extraccion = "Se ha extraido [" + extraer + " €] y quedan en la cuenta [" + saldo + " €]\n";
                }
            } else if (confirmarextraccion.equals("CANCELAR")) {
                System.out.println("La operación ha sido cancelada.");
            }
            MenuSecundario();
        }
    }

    public static void MenuTransferencias() {
        Scanner tran = new Scanner(System.in);
        String transferenciaIban;
        int cetransf, cstransf,dctransf;
        float transferir;
        long nctransf;
        String confirmartransferencia,juntarnumerotransferencia="";
        if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }
        else {

            System.out.println("Bienvenido al apartado de Transferencias.");
            System.out.print("¿Que cantidad de dinero desea transferir?: ");
            transferir = tran.nextFloat();
            if (transferir > saldo) {
                System.out.println("No tiene suficiente saldo para realizar la operación.");
            } else {
                do {
                    System.out.print("Introduzca el CE(4 dígitos) de tu universidad: ");
                    cetransf = tran.nextInt();
                }while(cetransf < 0 || cetransf > 9999);
                String cetransferencia = String.valueOf(cetransf);
                do {
                    System.out.print("Introduzca el CS(4 dígitos) de tu campus: ");
                    cstransf = tran.nextInt();
                }while(cstransf < 0 || cstransf > 9999);
                String cstransferencia = String.valueOf(cstransf);
                do {
                    System.out.print("Introduzca el DC(2 dígitos): ");
                    dctransf = tran.nextInt();
                }while(dctransf < 0 || dctransf > 99);
                String dctransferencia = String.valueOf(dctransf);
                do {
                    System.out.print("Introduzca el CE(10 dígitos) de tu universidad: ");
                    nctransf = tran.nextLong();
                }while(nctransf < 0 || nctransf > 9999999999L);
                String nctransferencia = String.valueOf(nctransf);

                transferenciaIban = cetransferencia + cstransferencia + dctransferencia + nctransferencia;


                System.out.print("Inserte CONFIRMAR para enviar " + transferir + " a la cuenta [" + transferenciaIban + "]: ");
                confirmartransferencia = tran.next();
                while (!Objects.equals(confirmartransferencia, "CONFIRMAR") && !Objects.equals(confirmartransferencia, "CANCELAR")) {
                    System.out.println("No lo ha escrito correctamente.");
                    System.out.print("Si desea continuar inserte CONFIRMAR, si desea cancelarlo escriba CANCELAR: ");
                    confirmartransferencia = tran.next();
                }
                if (confirmartransferencia.equals("CONFIRMAR")) {
                    saldo -= transferir;
                    System.out.println("La Transferencia se ha realizado correctamentea la cuenta " + transferenciaIban);
                    transaccion = "Se ha transferido [" + transferir + " €] a la cuenta [" + transferenciaIban + "] y quedan en la cuenta [" + saldo + " €]\n";
                    System.out.println("Su saldo actual es de: " + saldo);

                } else if (confirmartransferencia.equals("CANCELAR")) {
                    System.out.println("La operación ha sido cancelada.");
                }
            }
            MenuSecundario();
        }
    }

    public static void MenuPrestamos() {
        float interesanual = (float) 0.03, interesmensual = interesanual / 12, cuota, interesabonado=0, capitalamortizado=0, capital,cuotainicial=0;
        Scanner menupres = new Scanner(System.in);
        int numeromeses, numeroyear;
        if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }
        else {
            System.out.println("Bienvenido al apartado de Préstamos.");
            System.out.println("¿De que cantidad quiere que sea el préstamo?");
            capital = menupres.nextFloat();
            capitalvivo = capital;
            System.out.println("¿En cuántos años desea devolver el préstamo?");
            numeroyear = menupres.nextInt();
            numeromeses = numeroyear * 12;
            cuota = (float) (capital * interesmensual * ((Math.pow(1 + interesmensual, numeromeses)) / (Math.pow(1 + interesmensual, numeromeses) - 1)));
            System.out.println("Tu cuota mensual es de: " + cuota + " €\n");
            int contador = 0;
            System.out.println("\t\t  PAGO MENSUAL\t\t  INTERESES\t\t  AMORTIZADO\t\t  CAPITAL VIVO");
            System.out.printf("%4d. %16.2f %16.2f %16.2f %16.2f\n", contador, cuotainicial, interesabonado, capitalamortizado, capitalvivo);
            contador++;
            for (int i = 1; i <= numeromeses; i++) {
                interesabonado = capitalvivo * interesmensual;
                capitalamortizado = cuota - interesabonado;
                capitalvivo -= capitalamortizado;
                System.out.printf("%4d. %16.2f %16.2f %16.2f %16.2f\n", contador, cuota, interesabonado, capitalamortizado, capitalvivo);
                contador++;
            }
            MenuSecundario();
        }
    }

    public static void Datos() {
        if(nombre.equals("")){
            System.out.println("Debe darse de alta primero");
            MenuPrincipal();
        }
        if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }
        else{
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellidos: " + apellidos);
            System.out.println("Fecha de Nacimiento: " + dia + "/" + mes + "/" + year);
            System.out.println("Correo UPM: " + correo);
            System.out.println("DNI: " + dni + letradni);
            System.out.println("Tipo de cuenta: " + tipocuenta);
            System.out.println("Número de Cuenta: " + iban);
            System.out.println("Saldo de la Cuenta: " + saldo + "€");
            MenuPrincipal();
        }
    }

    public static void Operaciones(){
        if(nombre.equals("")){
            System.out.println("Debe darse de alta primero");
            MenuPrincipal();
        }
        else if(tipocuenta.equals("")){
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal();
        }else {
            if (deposito.equals("") && extraccion.equals("") && transaccion.equals("")) {
                System.out.println("No ha llevado a cabo ninguna operación.");
            } else {
                System.out.println("Estas son todas las operaciones que has realizado:");
                System.out.println("\nDEPOSITOS:\n ");
                if(deposito.equals("")){
                    System.out.println("No ha realizado ningún depósito.\n");
                }
                else{
                    System.out.println(deposito);
                }
                System.out.println("EXTRACCIONES:\n ");
                if(extraccion.equals("")){
                    System.out.println("No ha realizado ninguna extracción.\n");
                }
                else {
                    System.out.println(extraccion);
                }
                System.out.println("TRANSFERENCIAS:\n ");
                if(transaccion.equals("")){
                    System.out.println("No ha realizado ninguna transferencia.\n");
                }
                else {
                    System.out.println(transaccion);
                }
            }
            MenuPrincipal();
        }
    }
}
