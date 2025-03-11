import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CabinaTelefonica {
    private int idCabina;
    private int numeroLlamadas;
    private int duracionTotalLlamadas;
    private double costoTotalLlamadas;

    private static final double Tlocal = 50.0;
    private static final double Tldistancia = 350.0;
    private static final double Tcelular = 150.0;

    //almacenar llamada y cuanto dura
    private List<Map<String, Integer>> llamadas = new ArrayList<>();

    private static List<CabinaTelefonica> cabinas = new ArrayList<>();
    private static int contadorCabinas = 0;

    public CabinaTelefonica() {
        this.idCabina = contadorCabinas++;
        this.numeroLlamadas = 0;
        this.duracionTotalLlamadas = 0;
        this.costoTotalLlamadas = 0.0;
        cabinas.add(this);
        System.out.println("Cabina " + this.idCabina + " creada ");
    }

    public void registrarLlamada(String tipoLlamada, int duracion) {
        this.numeroLlamadas++;
        this.duracionTotalLlamadas += duracion;

        //llamada y duracion
        Map<String, Integer> llamada = new HashMap<>();
        llamada.put(tipoLlamada.toLowerCase(), duracion);
        llamadas.add(llamada);

        switch (tipoLlamada.toLowerCase()) {
            case "local":
                this.costoTotalLlamadas += duracion * Tlocal;
                break;
            case "largadistancia":
                this.costoTotalLlamadas += duracion * Tldistancia;
                break;
            case "celular":
                this.costoTotalLlamadas += duracion * Tcelular;
                break;
            default:
                System.out.println("Tipo de llamada invalido.");
        }
    }

    public void mostrarInformacion() {
        System.out.println("Id de cabina: " + idCabina);
        System.out.println("Número de llamadas: " + numeroLlamadas);
        System.out.println("Duración total de llamadas: " + duracionTotalLlamadas + " minutos ");
        System.out.println("Costo total de llamadas: " + costoTotalLlamadas);

        System.out.println("Detalle de llamada: ");
        for (Map<String, Integer> llamada : llamadas) {
            for (Map.Entry<String, Integer> entry : llamada.entrySet()) {
                System.out.println(" - Tipo: " + entry.getKey() + "; Duración: " + entry.getValue() + " minutos");
            }
        }
    }
//reiniciarla cabina
    public void reiniciarCabina() {
        this.numeroLlamadas = 0;
        this.duracionTotalLlamadas = 0;
        this.costoTotalLlamadas = 0.0;
        this.llamadas.clear(); // Reiniciar la lista de llamadas
        System.out.println("Cabina " + idCabina + " Reiniciada ");
    }

    public int getIdCabina() {
        return idCabina;
    }

    // Métodos estáticos
    public static CabinaTelefonica seleccionarCabina(int idCabina) {
        for (CabinaTelefonica cabina : cabinas) {
            if (cabina.getIdCabina() == idCabina) {
                return cabina;
            }
        }
        System.out.println("No se encuentra la cabina solicitada");
        return null;
    }

    public static void mostrarConsolidado() {
        int totalLlamadas = 0;
        int totalDuracion = 0;
        double totalCosto = 0.0;

        // Detalle por tipo de llamada
        int llamadasLocales = 0;
        int duracionLocales = 0;
        int llamadasLargaDistancia = 0;
        int duracionLargaDistancia = 0;
        int llamadasCelular = 0;
        int duracionCelular = 0;
//ciclopara tener detalles de cada cabina
        for (CabinaTelefonica cabina : cabinas) {
            totalLlamadas += cabina.numeroLlamadas;
            totalDuracion += cabina.duracionTotalLlamadas;
            totalCosto += cabina.costoTotalLlamadas;

            //tipos de llamadas
            for (Map<String, Integer> llamada : cabina.llamadas) {
                for (Map.Entry<String, Integer> entry : llamada.entrySet()) {
                    switch (entry.getKey()) {
                        case "local":
                            llamadasLocales++;
                            duracionLocales += entry.getValue();
                            break;
                        case "larga distancia":
                            llamadasLargaDistancia++;
                            duracionLargaDistancia += entry.getValue();
                            break;
                        case "celular":
                            llamadasCelular++;
                            duracionCelular += entry.getValue();
                            break;
                    }
                }
            }
        }
//informcion de consolidado
        System.out.println("Consolidado de todas las cabinas:");
        System.out.println("Número de llamadas: " + totalLlamadas);
        System.out.println("Duración total de llamadas: " + totalDuracion + " minutos");
        System.out.println("Costo total de llamadas: " + totalCosto);

        //detalles adicionales de llamadas
        System.out.println(" Detalle por tipo de llamada:");
        System.out.println(" llamadas locales:  " + llamadasLocales + "Duración: " + duracionLocales + " minutos");
        System.out.println(" llamadas larga distancia:  " + llamadasLargaDistancia + "Duración: " + duracionLargaDistancia + " minutos");
        System.out.println(" llamadas a celular:  " + llamadasCelular + "Duración: " + duracionCelular + " minutos");
    }
}