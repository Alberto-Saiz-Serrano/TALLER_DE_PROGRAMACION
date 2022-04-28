package UPMBank_Entrega_2;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Menus {
    private static int CS;
    private static Cliente cliente = null;
    private static Cuenta cuenta = null;
    private static Fecha f = null;
    private static final ListaClientes lClientes = new ListaClientes(20);
    private static final ListaCuentas lCuentas = new ListaCuentas(200);

    public static void MenuPrincipal(Scanner scan) {
        LetrasMenus.letrassubMenuPrincipal();
        int elegirquehacer = scan.nextInt();
        scan.nextLine();
        Switchs.SwitchMenuPrincipal(scan, elegirquehacer);
    }

    public static void MenuCliente(Scanner scan) {
        String nombre, apellidos, dni, correo;

        if (lClientes.getNumClientes() == lClientes.getMAX_CLIENTES()) {
            System.out.println("Ya ha creado el límite de 20 clientes bancarias.");
            MenuPrincipal(scan);
        } else {
            nombre = MenuNombre(scan);
            apellidos = MenuApellidos(scan);
            MenuFecha(scan);
            correo = MenuCorreo(scan);
            dni = MenuDNI(scan);
            System.out.println("""

                    -----------------------------
                    ~~Dado de alta correctamente~~
                    -----------------------------
                    """);
            cliente = new Cliente(nombre, apellidos, dni, correo, f); //creo el constructor del cliente
            lClientes.addCliente(cliente);
        }
        MenuPrincipal(scan);
    }

    private static String MenuNombre(Scanner scan) { //Pedir los apellidos
        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = scan.nextLine();
        } while (!Cliente.ComprobarNombre(nombre));
        return nombre;
    }

    private static String MenuApellidos(Scanner scan) { //Pedir los apellidos
        String apellidos;
        do {
            System.out.print("Apellidos: ");
            apellidos = scan.nextLine();
        } while (!Cliente.ComprobarApellidos(apellidos));
        return apellidos;
    }

    private static String MenuDNI(Scanner scan) { //Pedir el dni
        int dninumeros;
        char letradni;
        String dni;
        do {
            do {
                System.out.print("Introduce los números del DNI: ");
                dninumeros = scan.nextInt();
            } while (String.valueOf(dninumeros).length() > 8);

            System.out.print("Introduce la letra del DNI: ");
            letradni = scan.next().charAt(0);
            letradni = Character.toUpperCase(letradni); //Por si pone la letra en minúscula
        } while (!Cliente.ComprobarDNI(dninumeros, letradni) || comprobarDniRepetido(dninumeros, letradni));
        dni = String.valueOf(dninumeros) + letradni;
        return dni;
    }

    private static void MenuFecha(Scanner scan) {
        int dia, mes, year; //Valores que deben leerse por teclado
        do {
            System.out.println("Fecha de Nacimiento.");
            System.out.print("Dia: ");
            dia = scan.nextInt();
            System.out.print("Mes: ");
            mes = scan.nextInt();
            System.out.print("Año: ");
            year = scan.nextInt();
            if (Fecha.comprobarFecha(dia, mes, year)) {
                f = new Fecha(dia, mes, year);
            }
            if (f != null)
                f.imprimir();
        } while (f == null);
    }

    private static String MenuCorreo(Scanner scan) {
        String correo;
        do {
            System.out.print("Introduce tu correo de la UPM: ");
            correo = scan.next();
        } while (!Cliente.ComprobarCorreo(correo) || comprobarCorreoRepetido(correo));
        return correo;
    }

    public static void crearCuentaBancaria(Scanner scan) {
        String iban, nombre, apellidos;
        TipoCuenta.Tipo tipocuenta;
        if (cliente == null) {
            System.out.println("Debes crear un cliente primero");
        } else {
            Cliente clienteEncontrado = buscarCliente(scan);
            if (clienteEncontrado.getlCuentas().getNumCuentas() >= clienteEncontrado.getlCuentas().getMAX_CUENTAS()) {
                System.out.println("Ya has alcanzado el número máximo de cuentas bancarias.");
                MenuPrincipal(scan);
            } else {
                System.out.println("Vamos a crear una cuenta para el cliente:");
                clienteEncontrado.imprimir();
                tipocuenta = MostrarTipoCuenta(scan);
                MenuIban(tipocuenta);
                nombre = clienteEncontrado.getNombre();
                apellidos = clienteEncontrado.getApellidos();
                iban = cuenta.getIban();
                System.out.println(nombre + " " + apellidos + " el iban de su cuenta_" + (clienteEncontrado.getlCuentas().getNumCuentas() + 1) + " es [" + iban + "].");
                clienteEncontrado.getlCuentas().addCuentas(cuenta);
                lCuentas.addCuentas(cuenta);
            }
        }
        MenuPrincipal(scan);
    }

    public static Cliente buscarCliente(Scanner scan) {
        String buscardni;
        Cliente clienteEncontrado = null;

        System.out.print("Introduce el dni del cliente al que quieres crear una cuenta: ");
        buscardni = scan.nextLine();
        if (lClientes.getCliente(buscardni) == null) {
            System.out.println("El cliente no existe.");
            MenuPrincipal(scan);
        } else {
            clienteEncontrado = lClientes.getCliente(buscardni);
        }
        return clienteEncontrado;
    }

    public static void sacarCS(Scanner scan) { //lee el fichero donde muestra las sucursales disponibles
        String[][] partes = new String[4][2];
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Sucursales.txt"));
            String linea;
            int i = 0, opcion;
            System.out.println("Estos son todos los campus desde donde puedes acceder:\n");
            while ((linea = br.readLine()) != null) {
                partes[i][0] = linea.split("=")[0];
                partes[i][1] = linea.split("=")[1];
                System.out.printf("[%d] %s.\n", (i + 1), partes[i][0]);
                i++;
            }

            do {
                System.out.print("\nIntroduce el número de la Sucursal en la que estas: ");
                opcion = scan.nextInt();
                scan.nextLine();
                if(opcion < 1 || opcion > 4){
                    System.out.println("Introduce un número valido.");
                }
            } while (opcion < 1 || opcion > 4);

            CS = Integer.parseInt(partes[opcion - 1][1]);
            System.out.println("Gracias por acceder desde " + partes[opcion - 1][0]);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void MenuIban(TipoCuenta.Tipo tipocuenta) {
        long nc;

        do {
            nc = Cuenta.FuncionNC();
        } while (comprobarNCRepetido(nc));

        int dc = Cuenta.generarDC(nc, CS);
        String iban = Cuenta.SacarIban(CS, dc, nc);
        cuenta = new Cuenta(CS, dc, nc, iban, tipocuenta);
    }

    public static TipoCuenta.Tipo MostrarTipoCuenta(Scanner scan) {
        int tipocuentaelegida;
        TipoCuenta.Tipo tipocuenta;
        do {
            LetrasMenus.letrassubTipoCuenta();
            tipocuentaelegida = scan.nextInt();
            scan.nextLine();
            tipocuenta = Switchs.SwitchMostrarTipoCuenta(tipocuentaelegida);
        } while (tipocuenta == null);

        return tipocuenta;
    }

    public static void MenuSecundario(Scanner scan) {
        int operation;
        if (cliente == null) {
            System.out.println("Debe crear un cliente primero");
            MenuPrincipal(scan);
        } else if (cuenta == null) {
            System.out.println("Debe crear una cuenta primero");
            MenuPrincipal(scan);
        } else {
            LetrasMenus.letrassubMenuSecundario();
            operation = scan.nextInt();
            Switchs.SwitchMenuSecundario(scan, operation);
        }
    }

    public static void MenuMovimientos(Scanner scan) {
        String dni, iban;
        Cliente clienteOperaciones;
        Cuenta cuentaOperaciones;
        System.out.print("Introduzca el dni del cliente para buscar la cuenta: ");
        dni = scan.next();
        clienteOperaciones = lClientes.getCliente(dni);
        if(clienteOperaciones == null){
            System.out.println("No existe ningún cliente con ese dni.");
            Menus.MenuSecundario(scan);
        }else{
            System.out.println("Estos son sus datos:");
            clienteOperaciones.imprimir();
            System.out.print("Introduzca el iban de la cuenta con la que quiere operar: ");
            iban = scan.next();
            cuentaOperaciones = clienteOperaciones.getlCuentas().getCuenta(iban);
            if(cuentaOperaciones == null){
                System.out.println("No existe nignuna cuenta con ese iban.");
                Menus.MenuSecundario(scan);
            } else if (cuentaOperaciones.getlMovimientos().getNumMovimientos() >= cuentaOperaciones.getlMovimientos().getMAX_MOVIMIENTOS()) {
                System.out.println("Has alcanzado el número máximo de movimientos.");
                MenuSecundario(scan);
            } else {
                LetrasMenus.letrasMenuMovimientos();
                int operation = scan.nextInt();
                Switchs.SwitchMenuMovimientos(scan, operation, cuentaOperaciones);
            }
        }

    }

    public static Movimiento MenuDeposito(Scanner scan, Cuenta cuenta, Movimiento.TipoMovimiento movimiento) {
        double importe;
        do {
            System.out.println("¿Cuanto dinero desea depositar en la cuenta?: ");
            importe = scan.nextDouble();
            if (importe <= 0) {
                System.out.println("Introduzca un importe válido.");
            }
        } while (importe <= 0);
        cuenta.setSaldo(cuenta.getSaldo() + importe);
        System.out.println("Se ha realizado el depósito con éxito.");

        return new Movimiento(movimiento, importe);
    }

    public static Movimiento MenuExtraccion(Scanner scan, Cuenta cuenta, Movimiento.TipoMovimiento movimiento) {
        double importe;
        do {
            System.out.println("¿Cuanto dinero desea extraer de la cuenta?: ");
            importe = scan.nextDouble();
            if (importe <= 0) {
                System.out.println("Introduzca un importe válido.");
            }
        } while (importe <= 0);
        cuenta.setSaldo(cuenta.getSaldo() - importe);
        System.out.println("Se ha realizado la extracción con éxito.");

        return new Movimiento(movimiento, importe);
    }

    public static void MenuTransferencias(Scanner scan) {
        Cuenta cuentaEmisor, cuentaReceptor;
        double importe;
        cuentaEmisor = buscarCuentaEmisorTransferencia(scan);
        if (cuentaEmisor == null) {
            System.out.println("No existe ninguna cuenta con ese iban.");
            MenuTransferencias(scan);
        } else if (cuentaEmisor.getlTransferenciasEmitidas().getNumTransferencias() >= cuentaEmisor.getlTransferenciasEmitidas().getMAX_TRANSFERENCIAS()) {
            System.out.println("Has alcanzado el máximo de transferencias con esta cuenta");
            MenuSecundario(scan);
        } else {
            cuentaReceptor = buscarCuentaReceptorTransferencia(scan);
            if (cuentaReceptor == null) {
                System.out.println("No existe ninguna cuenta con ese iban.");
                MenuTransferencias(scan);
            } else {
                System.out.println("Perfecto, este es el saldo de la cuenta que va a realizar la transferencia.");
                System.out.println("Saldo: " + cuentaEmisor.getSaldo());
                do {
                    System.out.println("¿De cuánto dinero desea que sea la transferencia?");
                    importe = scan.nextDouble();
                    if (cuentaEmisor.getSaldo() < importe) {
                        System.out.println("Introduzca una cantidad válida.");
                    }
                } while (cuentaEmisor.getSaldo() < importe);
                System.out.println("Realizando la transferencia...");
                operacionesTransferencia(cuentaEmisor, cuentaReceptor, importe);
            }
        }
    }

    public static Cuenta buscarCuentaEmisorTransferencia(Scanner scan) {
        Cliente clienteEmisor;
        Cuenta cuentaEmisor = null;
        System.out.print("Introduce el dni del cliente que va a realizar la transferencia: ");
        String dni = scan.next();
        if (lClientes.getCliente(dni) == null) {
            System.out.println("No existe ningún cliente con ese dni.");
            MenuSecundario(scan);
        } else {
            clienteEmisor = lClientes.getCliente(dni);
            System.out.println("Estas son todos los datos y las cuentas del cliente: ");
            clienteEmisor.imprimir();
            System.out.print("Introduce el iban de la cuenta con la que quieres realizar la transferencia: ");
            String iban = scan.next();
            cuentaEmisor = clienteEmisor.getlCuentas().getCuenta(iban);
        }
        return cuentaEmisor;
    }

    public static Cuenta buscarCuentaReceptorTransferencia(Scanner scan) {
        Cliente clienteReceptor;
        Cuenta cuentaReceptor = null;
        System.out.print("Introduce el dni del cliente al que vas a realizar la transferencia: ");
        String dni = scan.next();
        if (lClientes.getCliente(dni) == null) {
            System.out.println("No existe ningún cliente con ese dni.");
            MenuSecundario(scan);
        } else {
            clienteReceptor = lClientes.getCliente(dni);
            System.out.println("Estas son todos los datos y las cuentas del cliente: ");
            clienteReceptor.imprimir();
            System.out.print("Introduce el iban de la cuenta al que quieres realizar la transferencia: ");
            String iban = scan.next();
            cuentaReceptor = clienteReceptor.getlCuentas().getCuenta(iban);
        }
        return cuentaReceptor;
    }

    public static void operacionesTransferencia(Cuenta cuentaEmisor, Cuenta cuentaReceptor, double importe) {
        Transferencia transferenciaEmitida, transferenciaRecibida;
        cuentaEmisor.setSaldo(cuentaEmisor.getSaldo() - importe);
        cuentaReceptor.setSaldo(cuentaReceptor.getSaldo() + importe);
        transferenciaEmitida = new Transferencia(cuentaEmisor.getIban(), importe, cuentaReceptor.getIban());
        transferenciaRecibida = new Transferencia(cuentaEmisor.getIban(), importe, cuentaReceptor.getIban());
        cuentaEmisor.getlTransferenciasEmitidas().addTransferencia(transferenciaEmitida);
        cuentaReceptor.getlTransferenciasRecibidas().addTransferencia(transferenciaRecibida);
    }

    public static void MenuPrestamos(Scanner scan) {
        int numeroYear;
        float capital, interesAnual;
        Cuenta cuentaPrestamo;
        Prestamo prestamo;

        cuentaPrestamo = buscarCuentaPrestamo(scan);
        if (cuentaPrestamo == null) {
            System.out.println("No existe ninguna cuenta con ese iban.");
        } else if (cuentaPrestamo.getlPrestamos().getNumPrestamos() >= cuentaPrestamo.getlPrestamos().getMAX_PRESTAMOS()) {
            System.out.println("Has alcanzado el máximo de préstamos con esta cuenta");
            MenuSecundario(scan);
        } else {
            System.out.println("¿De que cantidad quiere que sea el préstamo?");
            capital = scan.nextFloat();
            System.out.println("¿En cuántos años desea devolver el préstamo?");
            numeroYear = scan.nextInt();
            System.out.println("¿Que interés anual(%) va a tener el préstamo?");
            interesAnual = scan.nextFloat();
            prestamo = new Prestamo(capital, numeroYear, interesAnual);
            cuentaPrestamo.setSaldo(cuentaPrestamo.getSaldo() + capital);
            cuentaPrestamo.getlPrestamos().addPrestamo(prestamo);
            tablaAmortizaciones(prestamo);
        }
    }

    public static void tablaAmortizaciones(Prestamo prestamo) {
        int contador = 0;
        float cuotaInicial = 0, interesAbonado = 0, capitalAmortizado = 0, capitalVivo = prestamo.getCapital();
        System.out.println("\t\t  PAGO MENSUAL\t\t  INTERESES\t\t  AMORTIZADO\t\tCAPITAL VIVO");
        System.out.printf("%4d. %16.2f %16.2f %16.2f %16.2f\n", contador, cuotaInicial, interesAbonado, capitalAmortizado, capitalVivo);
        contador++;
        for (int i = 1; i <= prestamo.getNumeroMeses(); i++) {
            interesAbonado = capitalVivo * prestamo.getInteresMensual();
            capitalAmortizado = prestamo.getCuota() - interesAbonado;
            capitalVivo -= capitalAmortizado;
            System.out.printf("%4d. %16.2f %16.2f %16.2f %16.2f\n", contador, prestamo.getCuota(), interesAbonado, capitalAmortizado, capitalVivo);
            contador++;
        }
    }

    public static Cuenta buscarCuentaPrestamo(Scanner scan) {
        Cliente clientePrestamo;
        Cuenta cuentaPrestamo = null;
        System.out.print("Introduce el dni del cliente que va a pedir el préstamo: ");
        String dni = scan.next();
        if (lClientes.getCliente(dni) == null) {
            System.out.println("No existe ningún cliente con ese dni.");
            MenuSecundario(scan);
        } else {
            clientePrestamo = lClientes.getCliente(dni);
            System.out.println("Estas son todos los datos y las cuentas del cliente: ");
            clientePrestamo.imprimir();
            System.out.print("Introduce el iban de la cuenta con la que quieres realizar el préstamo: ");
            String iban = scan.next();
            cuentaPrestamo = clientePrestamo.getlCuentas().getCuenta(iban);
        }
        return cuentaPrestamo;
    }

    public static void MenuMostrarDatosClientes(Scanner scan) {
        int operation;
        if (cliente == null) {
            System.out.println("Debe crear un cliente primero");
            MenuPrincipal(scan);
        } else {
            LetrasMenus.letrasMenuMostrarDatosClientes();
            operation = scan.nextInt();
            Switchs.SwitchMenuMostrarDatosClientes(scan, operation);
        }
    }

    public static void DatosTodosClientes() {
        lClientes.imprimirClientes();
    }

    public static void DatosCliente(Scanner scan) {
        String dni;
        Cliente clienteMostrar;

        System.out.print("Introduce el dni del cliente: ");
        dni = scan.next();
        clienteMostrar = lClientes.getCliente(dni);
        if(clienteMostrar == null){
            System.out.println("No se ha encontrado a nadie con ese dni.");
        }else{
            System.out.println();
            clienteMostrar.imprimirTodoClientes();
        }

    }

    public static boolean comprobarNCRepetido(long nc) { // comprueba que no exista el NC en todo el banco
        boolean repetido = false;
        int x = 0, y = 0;
        while (y < lClientes.getNumClientes() && !repetido) {
            while (x < lClientes.getCliente(y).getlCuentas().getNumCuentas() && !repetido) { //De la lista de clientes (lClientes) cojo los clientes con el while(y) y de cada cliente cojo su ListaCuentas(get lCuentas) y de su ListaCuentas cojo el array de cuentas(get cuentas)
                if (lClientes.getCliente(y).getlCuentas().getCuenta(x).getNC() == nc) {
                    repetido = true;
                }
                x++;
            }
            y++;
        }
        return repetido;
    }

    public static boolean comprobarCorreoRepetido(String correo) {
        boolean repetido = false;
        int x = 0;
        while (x < lClientes.getNumClientes() && !repetido) {
            if (lClientes.getCliente(x).getCorreo().equals(correo)) {
                repetido = true;
            }
            x++;
        }
        return repetido;
    }

    public static boolean comprobarDniRepetido(int nums, char letra) {
        boolean repetido = false;
        String dni = String.valueOf(nums) + letra;
        dni = Cliente.rellenarCeros(dni);
        int x = 0;
        while (x < lClientes.getNumClientes() && !repetido) {
            if (lClientes.getCliente(x).getDni().equals(dni)) {
                repetido = true;
            }
            x++;
        }
        return repetido;
    }

    public static void sacarFicheroTransacciones(){
        BufferedWriter bw = null;

        try{
            bw = new BufferedWriter(new FileWriter("Transferencias.txt"));

            // IMPRIMIR IBANES
            for(int x = 0; x < lCuentas.getNumCuentas(); x++){
                bw.write(lCuentas.getCuenta(x).getIban() + "\n");
            }
            // IMPRIMIR SALDOS
            for(int x = 0; x < lCuentas.getNumCuentas(); x++){
                bw.write("\n");
                for(int i = 0; i < lCuentas.getNumCuentas(); i++){
                    double saldo = lCuentas.getCuenta(x).metodoTotalEnviado(lCuentas.getCuenta(i));
                    bw.write(String.format("%10.2f", saldo));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            try{
                if(bw != null){
                    bw.close();
                }
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}