package UPMBank_Entrega_2;

public class Cliente {
    private String nombre;
    private String apellidos;
    private String dni;
    private String correo;
    private Fecha fecha_nacimiento;
    private ListaCuentas lCuentas;


    public Cliente(String nombre, String apellidos, String dni, String correo, Fecha fecha_nacimiento){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = rellenarCeros(dni);
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.lCuentas = new ListaCuentas(10);
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getDni() {
        return dni;
    }
    public String getCorreo() {
        return correo;
    }
    public ListaCuentas getlCuentas() {
        return lCuentas;
    }

    public void imprimir(){ //Lo uso para al buscar un cliente y encontrarlo mostrar sus datos con los ibanes de sus cuentas.
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.print("Fecha: "); fecha_nacimiento.imprimir();
        System.out.printf("DNI: %s\n",dni);
        System.out.println("Correo: " + correo);
        lCuentas.imprimirIbanesCuentas();
    }
    public void imprimirTodoClientes(){ //Lo uso para el apartado de mostrar los datos completos de los clientes
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.print("Fecha: "); fecha_nacimiento.imprimir();
        System.out.printf("DNI: %s\n",dni);
        System.out.println("Correo: " + correo);
        lCuentas.imprimirTodoCuentas();
    }

    //Metodos estaticos
    public static boolean ComprobarNombre(String nombre){ //Comprueba que el nombre no contenga números
        return !nombre.contains("0") && !nombre.contains("1") && !nombre.contains("2") && !nombre.contains("3") && !nombre.contains("4") && !nombre.contains("5") && !nombre.contains("6") && !nombre.contains("7") && !nombre.contains("8") && !nombre.contains("9") && !nombre.contains("!");
    }

    public static boolean ComprobarApellidos(String apellidos){ //Comprueba que los apellidos no contenga números
        return !apellidos.contains("0") && !apellidos.contains("1") && !apellidos.contains("2") && !apellidos.contains("3") && !apellidos.contains("4") && !apellidos.contains("5") && !apellidos.contains("6") && !apellidos.contains("7") && !apellidos.contains("8") && !apellidos.contains("9") && !apellidos.contains("!");
    }

    public static boolean ComprobarDNI(int dni, char letradni){ //Comprueba el DNI
        char [] letras = new char[] {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        boolean resultado = false;
        int i = dni % 23;
        if(letras[i] == letradni){
            resultado = true;
        }
        return resultado;
    }

    public static boolean ComprobarCorreo(String correo){
        boolean resultado = correo.endsWith("@alumnos.upm.es") || correo.endsWith("@upm.es");
        if (correo.startsWith("@") || correo.startsWith(" ")) {
            resultado = false;
        }
        return resultado;
    }

    public static String rellenarCeros(String dni){
        for(int x = dni.length(); x < 9; x++){
            dni = "0" + dni;
        }
        return dni;
    }
}