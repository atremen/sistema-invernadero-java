public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE INVERNADERO INTELIGENTE ===\n");

        // 1. Colección polimórfica: referencia de la superclase Sensor
        Sensor[] sensores = new Sensor[3];

        // 2. Instanciación con tipos concretos (subclases)
        sensores[0] = new SensorTemperatura("ST-01", "Sector Norte - Camas");
        sensores[1] = new SensorHumedad("SH-01", "Sector Central - Altura");
        sensores[2] = new SensorHumedadSuelo("SHS-01", "Sector Sur - Raíz");

        // 3. Simulación de mediciones mediante método encapsulado
        sensores[0].registrarMedicion(32.5); // Temperatura alta (> 30°C)
        sensores[1].registrarMedicion(55.0); // Humedad ambiental adecuada (40-70%)
        sensores[2].registrarMedicion(22.0); // Humedad de suelo baja (< 30%)

        // 4. Procesamiento polimórfico (Enlace Dinámico)
        // No hay if/else: Java resuelve en tiempo de ejecución qué método ejecutar
        System.out.println("--- Evaluando estado del invernadero ---");
        for (Sensor sensor : sensores) {
            System.out.println(sensor.toString());
            System.out.println("Diagnóstico: " + sensor.evaluarMedicion());
            System.out.println();
        }
    }
}