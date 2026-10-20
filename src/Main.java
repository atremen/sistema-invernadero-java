public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE INVERNADERO INTELIGENTE ===\n");

        // 1. Colección polimórfica (referencias de la superclase Sensor)
        Sensor[] sensores = new Sensor[3];
        sensores[0] = new SensorTemperatura("ST-01", "Sector Norte - Camas");
        sensores[1] = new SensorHumedad("SH-01", "Sector Central - Altura");
        sensores[2] = new SensorHumedadSuelo("SHS-01", "Sector Sur - Raíz");

        // 2. Simulación de lecturas
        sensores[0].registrarMedicion(3.5);  // Temperatura crítica para helada (< 5°C)
        sensores[1].registrarMedicion(62.0); // Humedad ambiental adecuada
        sensores[2].registrarMedicion(18.0); // Humedad suelo baja (< 30%)

        // 3. Procesamiento polimórfico (Enlace Dinámico)
        System.out.println("--- 1. EVALUACIÓN GENERAL (POLIMORFISMO) ---");
        for (Sensor sensor : sensores) {
            System.out.println(sensor);
            System.out.println("Diagnóstico: " + sensor.evaluarMedicion());
            System.out.println();
        }

        // 4. Instanciación del sistema de riego (Colaboración)
        SistemaRiego riegoSur = new SistemaRiego("Sector Sur");
        System.out.println("--- 2. OPERACIONES ESPECÍFICAS (INSTANCEOF Y CASTING) ---");

        for (Sensor sensor : sensores) {
            // Caso A: Método exclusivo de SensorTemperatura
            if (sensor instanceof SensorTemperatura) {
                SensorTemperatura sensorTemp = (SensorTemperatura) sensor; // Downcasting
                if (sensorTemp.alertaHelada()) {
                    System.out.println(">> ALERTA CRÍTICA: " + sensorTemp.getId() +
                            " reporta riesgo de helada inmediata (" + sensorTemp.getUltimaMedicion() + " °C). Encender calefactores.");
                }
            }

            // Caso B: Detección del sensor de suelo para controlar el actuador de riego
            if (sensor instanceof SensorHumedadSuelo) {
                SensorHumedadSuelo sensorSuelo = (SensorHumedadSuelo) sensor; // Downcasting
                riegoSur.evaluarYEjecutar(sensorSuelo);
            }
        }

        // 5. Verificación de cambio de estado: el suelo se recupera
        System.out.println("\n--- 3. SEGUNDA LECTURA TRAS EL RIEGO ---");
        sensores[2].registrarMedicion(50.0); // Sube a 50%
        if (sensores[2] instanceof SensorHumedadSuelo) {
            riegoSur.evaluarYEjecutar((SensorHumedadSuelo) sensores[2]);
        }
    }
}