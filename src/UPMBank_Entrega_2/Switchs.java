package UPMBank_Entrega_2;

import java.util.Scanner;

public class Switchs {

    public static void SwitchMenuPrincipal(Scanner scan, int elegirquehacer){
        switch(elegirquehacer){
            case 1:
                Menus.MenuCliente(scan);
                break;
            case 2:
                Menus.crearCuentaBancaria(scan);
                break;
            case 3:
                Menus.MenuSecundario(scan);
                break;
            case 4:
                Menus.MenuMostrarDatosClientes(scan);
                break;
            case 0:
                System.out.println("\n---------------------");
                System.out.println("~~SESION FINALIZADA~~");
                System.out.println("---------------------");
                Menus.sacarFicheroTransacciones();
                break;
            default:
                System.out.println("El numero que ha introducido es incorrecto.");
                Menus.MenuPrincipal(scan);
        }
    }

    public static void SwitchMenuSecundario(Scanner scan, int operation){
        switch(operation) {
            case 1:
                Menus.MenuMovimientos(scan);
                break;
            case 2:
                Menus.MenuTransferencias(scan);
                Menus.MenuSecundario(scan);
                break;
            case 3:
                Menus.MenuPrestamos(scan);
                Menus.MenuSecundario(scan);
                break;
            case 0:
                Menus.MenuPrincipal(scan);
                break;
            default:
                System.out.println("El numero que ha ingresado no es válido.");
                Menus.MenuSecundario(scan);
        }
    }

    public static TipoCuenta.Tipo SwitchMostrarTipoCuenta(int tipoCuentaElegida){
        TipoCuenta.Tipo tipocuenta = null;
        switch (tipoCuentaElegida) {
            case 1:
                tipocuenta = TipoCuenta.Tipo.Corriente;
                System.out.println("Ha elegido la Cuenta " + tipocuenta + ".");
                break;
            case 2:
                tipocuenta = TipoCuenta.Tipo.Ahorro;
                System.out.println("Ha elegido la Cuenta " + tipocuenta + " .");
                break;
            case 3:
                tipocuenta = TipoCuenta.Tipo.Remunerada;
                System.out.println("Ha elegido la Cuenta " + tipocuenta + ".");
                break;
            default:
                System.out.println("El número es incorrecto.");

                break;
        }
        return tipocuenta;
    }

    public static void SwitchMenuMovimientos(Scanner scan, int operation, Cuenta cuentaOperaciones){
        Movimiento movimiento;
        Movimiento.TipoMovimiento tipoMovimiento;
        switch (operation){
            case 1:
                tipoMovimiento = Movimiento.TipoMovimiento.Deposito;
                movimiento = Menus.MenuDeposito(scan,cuentaOperaciones, tipoMovimiento);
                cuentaOperaciones.getlMovimientos().addMovimiento(movimiento);
                Menus.MenuSecundario(scan);
                break;
            case 2:
                tipoMovimiento = Movimiento.TipoMovimiento.Extraccion;
                movimiento = Menus.MenuExtraccion(scan,cuentaOperaciones,tipoMovimiento);
                cuentaOperaciones.getlMovimientos().addMovimiento(movimiento);
                Menus.MenuSecundario(scan);
                break;
            default:
                System.out.println("Introduce un número válido.");
        }
    }

    public static void SwitchMenuMostrarDatosClientes(Scanner scan, int operation){
        switch (operation){
            case 1:
                Menus.DatosCliente(scan);
                Menus.MenuMostrarDatosClientes(scan);
                break;
            case 2:
                Menus.DatosTodosClientes();
                Menus.MenuMostrarDatosClientes(scan);
                break;
            case 0:
                Menus.MenuPrincipal(scan);
                break;
            default:
                System.out.println("Introduce un número válido.");
                Menus.MenuMostrarDatosClientes(scan);
                break;
        }
    }
}