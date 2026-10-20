public class SistemaRiego {

    private String sector;
    private boolean regando;

    public SistemaRiego(String sector) {
        this.sector = sector;
        this.regando = false;
    }

    public String getSector() {
        return sector;
    }

    public boolean isRegando() {
        return regando;
    }

    public void activar() {
        if (!this.regando) {
            this.regando = true;
            System.out.println("[SistemaRiego - " + sector + "] Válvula ABIERTA: Riego iniciado.");
        } else {
            System.out.println("[SistemaRiego - " + sector + "] El riego ya se encontraba activo.");
        }
    }

    public void desactivar() {
        if (this.regando) {
            this.regando = false;
            System.out.println("[SistemaRiego - " + sector + "] Válvula CERRADA: Riego detenido.");
        } else {
            System.out.println("[SistemaRiego - " + sector + "] El riego ya se encontraba inactivo.");
        }
    }

    // Método de colaboración: toma decisiones con base en el estado del sensor
    public void evaluarYEjecutar(SensorHumedadSuelo sensorSuelo) {
        System.out.println("[SistemaRiego - " + sector + "] Verificando lectura de " + sensorSuelo.getId() + "...");
        if (sensorSuelo.requiereRiego()) {
            System.out.println("[SistemaRiego - " + sector + "] Alerta: Nivel crítico (" + sensorSuelo.getUltimaMedicion() + "%).");
            activar();
        } else {
            System.out.println("[SistemaRiego - " + sector + "] Nivel óptimo (" + sensorSuelo.getUltimaMedicion() + "%). No se requiere riego.");
            desactivar();
        }
    }

    @Override
    public String toString() {
        return "SistemaRiego [Sector: " + sector + " | Estado: " + (regando ? "REGANDO" : "INACTIVO") + "]";
    }
}