public class PruebasSistema {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   SUITE DE PRUEBAS DEL SISTEMA DE INVERNADERO   ");
        System.out.println("=================================================\n");

        // 1. Prueba de creación y conservación de estado
        System.out.println("--- 1. Creación de sensores y consulta de estado ---");
        SensorTemperatura st = new SensorTemperatura("ST-TEST", "Sector Pruebas");
        SensorHumedad sh = new SensorHumedad("SH-TEST", "Sector Pruebas");
        SensorHumedadSuelo shs = new SensorHumedadSuelo("SHS-TEST", "Sector Pruebas");
        System.out.println(st);
        System.out.println(sh);
        System.out.println(shs);

        // 2. Evaluación de rangos de Temperatura (<18, 18-30, >30)
        System.out.println("\n--- 2. Casos de prueba: SensorTemperatura ---");
        st.registrarMedicion(14.0);
        System.out.println("[Baja]     " + st.evaluarMedicion());
        st.registrarMedicion(24.0);
        System.out.println("[Adecuada] " + st.evaluarMedicion());
        st.registrarMedicion(35.0);
        System.out.println("[Alta]     " + st.evaluarMedicion());

        // 3. Evaluación de rangos de Humedad Ambiental (<40, 40-70, >70)
        System.out.println("\n--- 3. Casos de prueba: SensorHumedad ---");
        sh.registrarMedicion(30.0);
        System.out.println("[Baja]     " + sh.evaluarMedicion());
        sh.registrarMedicion(55.0);
        System.out.println("[Adecuada] " + sh.evaluarMedicion());
        sh.registrarMedicion(80.0);
        System.out.println("[Alta]     " + sh.evaluarMedicion());

        // 4. Evaluación de rangos de Humedad de Suelo (<30, 30-70, >70)
        System.out.println("\n--- 4. Casos de prueba: SensorHumedadSuelo ---");
        shs.registrarMedicion(20.0);
        System.out.println("[Baja]     " + shs.evaluarMedicion());
        shs.registrarMedicion(50.0);
        System.out.println("[Adecuada] " + shs.evaluarMedicion());
        shs.registrarMedicion(85.0);
        System.out.println("[Alta]     " + shs.evaluarMedicion());

        // 5. Colección polimórfica y enlace dinámico
        System.out.println("\n--- 5. Colección polimórfica (Sensor[]) ---");
        Sensor[] coleccion = new Sensor[]{ st, sh, shs };
        for (Sensor s : coleccion) {
            System.out.println("ID: " + s.getId() + " -> " + s.evaluarMedicion());
        }

        // 6. Prueba de instanceof, casting y activación de riego
        System.out.println("\n--- 6. Integración con SistemaRiego vía instanceof y casting ---");
        SistemaRiego riego = new SistemaRiego("Sector Pruebas");

        // Caso suelo seco -> activa riego
        shs.registrarMedicion(15.0);
        for (Sensor s : coleccion) {
            if (s instanceof SensorHumedadSuelo) {
                SensorHumedadSuelo sensorSuelo = (SensorHumedadSuelo) s;
                riego.evaluarYEjecutar(sensorSuelo);
            }
        }

        // Caso suelo recuperado -> desactiva riego
        shs.registrarMedicion(55.0);
        for (Sensor s : coleccion) {
            if (s instanceof SensorHumedadSuelo) {
                SensorHumedadSuelo sensorSuelo = (SensorHumedadSuelo) s;
                riego.evaluarYEjecutar(sensorSuelo);
            }
        }
    }
}