import static java.lang.Integer.sum;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class menu {
 
    public static void main(String[] args) {
               
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        
        while (!salir) {
 
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Salir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Suma");
                        System.out.println("Primer entero");
                        System.out.println("Segundo entero");
                        break;
                    case 2:
                        System.out.println("Resta");
                        System.out.println("Primer entero");
                        System.out.println("Segundo entero");
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
 
    }
}
