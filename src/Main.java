import java.util.Scanner;


import static java.lang.Math.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CabinaTelefonica cabinaSeleccionada = null;

        //metodos
        while (true) {
            System.out.println(" Gastos telefonicos  ");
            System.out.println("1 Crear cabina");
            System.out.println("2 Escoger cabina ");
            System.out.println("3 Registrar llamada");
            System.out.println("4 Mostrar información de la cabina actual");
            System.out.println("5 Mostrar consolidado de cabinas");
            System.out.println("6 Reiniciar cabina actual");
            System.out.println("7 Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    new CabinaTelefonica();
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la cabina: ");
                    int idCabina = scanner.nextInt();
                    cabinaSeleccionada = CabinaTelefonica.seleccionarCabina(idCabina);
                    break;
                case 3:
                    if (cabinaSeleccionada != null) {
                        System.out.print("Ingrese el tipo de llamada (local,largadistancia, celular): ");
                        String tipoLlamada = scanner.next();
                        System.out.print("Ingrese la duración de la llamada en minutos: ");
                        int duracion = (int) (Math.random()*10)+1;
                        System.out.print("Duracion"+ duracion);

                        cabinaSeleccionada.registrarLlamada(tipoLlamada, duracion);
                    } else {
                        System.out.println("Primero elija una cabina");
                    }
                    break;
                case 4:
                    if (cabinaSeleccionada != null) {
                        cabinaSeleccionada.mostrarInformacion();
                    } else {
                        System.out.println("Primero elija una cabina.");
                    }
                    break;
                case 5:
                    CabinaTelefonica.mostrarConsolidado();
                    break;
                case 6:
                    if (cabinaSeleccionada != null) {
                        cabinaSeleccionada.reiniciarCabina();
                    } else {
                        System.out.println("Primero elija una cabina.");
                    }
                    break;
                case 7:
                    System.out.println("gracias por usar el programa");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción invalida,intente de nuevo");
            }
        }
    }
}