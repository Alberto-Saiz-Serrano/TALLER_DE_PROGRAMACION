package UPMBank_Entrega_2;

import java.util.Scanner;

public class UPMBank {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String [] arg){
        LetrasMenus.letrasBienvenida();
        Menus.sacarCS(scan);
        Menus.MenuPrincipal(scan);
    }
}