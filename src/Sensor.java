public class Sensor {

    private String id;
    private String ubicacion;
    private boolean activo;
    private double ultimaMedicion;

    public Sensor(String id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.activo = true;
        this.ultimaMedicion = 0.0;
    }

    public String getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getUltimaMedicion() {
        return ultimaMedicion;
    }

    // Comportamiento para modificar la medición sin violar la encapsulación
    public void registrarMedicion(double nuevaMedicion) {
        this.ultimaMedicion = nuevaMedicion;
    }

    // Comportamiento base que especializarán las subclases
    public String evaluarMedicion() {
        return "Medición registrada: " + this.ultimaMedicion;
    }

    @Override
    public String toString() {
        return "[" + id + "] en " + ubicacion + " | Estado: " + (activo ? "Activo" : "Inactivo") +
                " | Última lectura: " + ultimaMedicion;
    }
}