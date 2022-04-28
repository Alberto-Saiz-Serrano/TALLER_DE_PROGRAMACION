package UPMBank_Entrega_2;

public class LetrasMenus {
    public static void letrasBienvenida(){
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
        System.out.println("~~Bienvenido a la UPMBank~~");
    }

    public static void letrassubMenuPrincipal() {
        System.out.println("\n[1] Darse de alta como nuevo cliente.");
        System.out.println("[2] Crear una cuenta bancaria.");
        System.out.println("[3] Realizar operaciones en una cuenta bancaria.");
        System.out.println("[4] Mostrar datos de clientes.");
        System.out.println("[0] Salir de la UPMBank.");
        System.out.print("\nElija un número entre 0 y 6: ");
    }

    public static void letrassubMenuSecundario() {
        System.out.println("\nBienvenido al apartado de operaciones, estas son las operaciones que puede realizar: ");
        System.out.println("\n|1| Depósito/Extracción.");
        System.out.println("|2| Transferencia.");
        System.out.println("|3| Préstamos Hipotecarios.");
        System.out.println("|0| Volver al Menu Principal.");
        System.out.print("\nElija un número entre 0 y 4: ");
    }

    public static void letrasMenuMovimientos() {
        System.out.println("Esos son los tipos de movimientos disponibles.");
        System.out.println("(1) Depósito.");
        System.out.println("(2) Extracción");
        System.out.print("Introduzca el número de la operación: ");
    }

    public static void letrasMenuMostrarDatosClientes() {
        System.out.println("\n|1| Ver los datos de un cliente.");
        System.out.println("|2| Ver los datos de todos los clientes.");
        System.out.println("|0| Volver al menu principal.");
        System.out.print("Elija el número que desee realizar: ");
    }

    public static void letrassubTipoCuenta() {

        System.out.println("Estos son los tipos de cuentas que poseemos: ");
        System.out.println("\n1- Corriente");
        System.out.println("2- Ahorro");
        System.out.println("3- Remunerada");
        System.out.print("\nIntroduce el número de la cuenta que deseas: ");
    }
}