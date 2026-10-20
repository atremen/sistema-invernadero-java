public class SensorTemperatura extends Sensor {

    public SensorTemperatura(String id, String ubicacion) {
        super(id, ubicacion);
    }

    @Override
    public String evaluarMedicion() {
        double valor = getUltimaMedicion();
        if (valor < 18.0) {
            return "Temperatura baja (" + valor + " °C): riesgo de frío para el cultivo.";
        } else if (valor <= 30.0) {
            return "Temperatura adecuada (" + valor + " °C): condiciones óptimas.";
        } else {
            return "Temperatura alta (" + valor + " °C): activar ventilación o sombra.";
        }
    }

    // Comportamiento exclusivo para la Fase 5 (instanceof y casting)
    public boolean alertaHelada() {
        return getUltimaMedicion() < 5.0;
    }
}