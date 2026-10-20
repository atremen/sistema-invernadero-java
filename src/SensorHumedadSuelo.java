public class SensorHumedadSuelo extends Sensor {

    public SensorHumedadSuelo(String id, String ubicacion) {
        super(id, ubicacion);
    }

    @Override
    public String evaluarMedicion() {
        double valor = getUltimaMedicion();
        if (valor < 30.0) {
            return "Humedad del suelo baja (" + valor + "%): suelo seco, requiere riego inmediato.";
        } else if (valor <= 70.0) {
            return "Humedad del suelo adecuada (" + valor + "%): nivel de agua óptimo.";
        } else {
            return "Humedad del suelo alta (" + valor + "%): suelo saturado, suspender riego.";
        }
    }

    // Método utilitario para que el sistema de riego pueda consultar directamente
    public boolean requiereRiego() {
        return getUltimaMedicion() < 30.0;
    }
}