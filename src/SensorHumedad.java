public class SensorHumedad extends Sensor {

    public SensorHumedad(String id, String ubicacion) {
        super(id, ubicacion);
    }

    @Override
    public String evaluarMedicion() {
        double valor = getUltimaMedicion();
        if (valor < 40.0) {
            return "Humedad ambiental baja (" + valor + "%): aire seco, riesgo de transpiración excesiva.";
        } else if (valor <= 70.0) {
            return "Humedad ambiental adecuada (" + valor + "%): entorno equilibrado.";
        } else {
            return "Humedad ambiental alta (" + valor + "%): riesgo de proliferación de hongos.";
        }
    }
}