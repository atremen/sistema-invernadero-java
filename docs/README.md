# Sistema de Invernadero Inteligente — Práctica 5

Aplicación de consola en Java que modela el monitoreo ambiental y la automatización de riego en un invernadero inteligente, aplicando principios de Programación Orientada a Objetos: herencia, encapsulación, polimorfismo y enlace dinámico.

---

## Clases implementadas y responsabilidades

* **`Sensor` (Superclase):** Modela las características base de cualquier sensor (identificador, ubicación física, estado de actividad y última medición). Aplica encapsulación mediante atributos privados y declara el comportamiento base `evaluarMedicion()`.
* **`SensorTemperatura` (Subclase):** Especializa la evaluación térmica en grados Celsius (<18 °C frío, 18–30 °C óptimo, >30 °C calor) e incorpora el comportamiento específico `alertaHelada()`.
* **`SensorHumedad` (Subclase):** Evalúa la humedad relativa del aire (<40 % seco, 40–70 % óptimo, >70 % húmedo).
* **`SensorHumedadSuelo` (Subclase):** Monitorea la humedad del sustrato (<30 % seco, 30–70 % óptimo, >70 % saturado) y provee el método `requiereRiego()`.
* **`SistemaRiego` (Colaborador / Actuador):** Modela el sistema físico de riego. No hereda de `Sensor`. Consulta el estado del suelo mediante colaboración para abrir o cerrar sus válvulas.
* **`Main` / `PruebasSistema`:** Clases ejecutoras que configuran escenarios de simulación, colecciones polimórficas y comprobación de casos de prueba.

---

## Instrucciones para ejecutar el programa

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/TU-USUARIO/sistema-invernadero-java.git](https://github.com/TU-USUARIO/sistema-invernadero-java.git)
## Reflexión final

1. **¿Cuál es la superclase de tu sistema y cuáles son sus subclases?**
   La superclase es `Sensor`. Sus subclases son `SensorTemperatura`, `SensorHumedad` y `SensorHumedadSuelo`.

2. **¿Por qué existe una relación de herencia entre ellas?**
   Porque satisfacen la relación conceptual **"es un"** (*is-a*). Cada sensor especializado es, en esencia, un sensor que comparte atributos y comportamientos base: un identificador único, una ubicación física en el invernadero, un estado operativo y el registro de mediciones.

3. **¿Qué atributos o comportamientos decidiste colocar en la superclase y por qué?**
   Coloqué los atributos `id`, `ubicacion`, `activo` y `ultimaMedicion` (todos con modificador `private`), junto con sus métodos de acceso (*getters*), el método mutador `registrarMedicion(double)` y la declaración base del método `evaluarMedicion()`. Se ubicaron en la superclase para centralizar la información común, asegurar la encapsulación y evitar redundancia en las subclases.

4. **¿Para qué utilizaste `super`?**
   Utilicé `super(id, ubicacion)` en la primera línea de los constructores de `SensorTemperatura`, `SensorHumedad` y `SensorHumedadSuelo` para invocar al constructor de la superclase `Sensor`. Esto permite que la superclase inicialice sus propios atributos encapsulados de forma controlada antes de que se ejecute la lógica propia de la subclase.

5. **¿Qué método sobrescribiste?**
   Sobrescribí el método `evaluarMedicion()`, marcado con la anotación `@Override` en cada una de las tres subclases. Cada una implementa su propia regla de negocio para clasificar la lectura: rangos de temperatura en grados Celsius, porcentajes de humedad ambiental relativa y niveles de humedad en el suelo.

6. **¿Dónde puedes observar polimorfismo en tu programa?**
   Se observa en la clase principal al declarar una colección tipada con la superclase: `Sensor[] sensores = new Sensor[3]`, en la cual se almacenan instancias de `SensorTemperatura`, `SensorHumedad` y `SensorHumedadSuelo`. El sistema manipula objetos de diferentes clases concretas a través de un único tipo de referencia común (`Sensor`).

7. **¿Dónde puedes observar enlace dinámico?**
   Se observa dentro del ciclo iterativo al ejecutar `for (Sensor sensor : sensores) { System.out.println(sensor.evaluarMedicion()); }`. En tiempo de compilación la llamada se asocia con el tipo `Sensor`, pero en tiempo de ejecución la Máquina Virtual de Java (JVM) resuelve dinámicamente y despacha la versión de `evaluarMedicion()` correspondiente al tipo real del objeto instanciado en memoria, sin requerir condicionales `if/else`.

8. **¿Para qué utilizaste `instanceof` y casting?**
   Se utilizaron para recuperar temporalmente el tipo específico de un objeto (*downcasting*) y acceder a métodos exclusivos que no existen en la superclase. Verifiqué `sensor instanceof SensorTemperatura` para invocar con seguridad el método `alertaHelada()`, y `sensor instanceof SensorHumedadSuelo` para enlazar el sensor al método `evaluarYEjecutar(sensorSuelo)` de `SistemaRiego`. El operador `instanceof` previene errores de tipo `ClassCastException` en tiempo de ejecución.

9. **¿Qué cambio realizaste entre tu UML inicial y tu UML final?**
   Se incorporó el método `registrarMedicion(double)` en `Sensor` para respetar la encapsulación estricta sin manipular atributos privados directamente; se añadieron métodos especializados en las subclases (`alertaHelada()` y `requiereRiego()`) para justificar técnicamente el uso de casting; y se formalizó la relación de asociación/dependencia entre `SistemaRiego` y `SensorHumedadSuelo`, evitando acoplarlos por herencia indebida.

10. **Si tuvieras que agregar un `SensorLuminosidad`, ¿qué partes del programa tendrías que modificar?**
    Únicamente tendría que crear una nueva clase `SensorLuminosidad` que herede de `Sensor` e implementar su versión sobrescrita de `evaluarMedicion()` con los rangos en luxes. El bucle polimórfico en `Main` procesaría el nuevo sensor automáticamente sin modificar su lógica existente, cumpliendo con el principio Abierto/Cerrado (*Open/Closed Principle*).