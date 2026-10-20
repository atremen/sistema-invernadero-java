# Análisis y diseño — Sistema de Invernadero Inteligente 🌱

## 1. Descripción del problema

El sistema a representar corresponde a un simulador de monitoreo ambiental y control para un invernadero inteligente. El objetivo central es supervisar las condiciones físicas críticas para el crecimiento vegetal y tomar decisiones operativas automáticas basadas en dichas lecturas.

### Información que maneja el sistema
El sistema gestiona tres variables ambientales principales:
1. **Temperatura del aire:** medida en grados Celsius (°C).
2. **Humedad ambiental:** medida como porcentaje relativo de vapor de agua en el aire (0 % a 100 %).
3. **Humedad del suelo:** medida como porcentaje volumétrico de agua en el sustrato (0 % a 100 %).

### Elementos que intervienen
- **Dispositivos de monitoreo (Sensores):** encargados de observar y registrar periódicamente los valores del entorno físico.
- **Dispositivos de actuación (Sistema de riego):** encargados de intervenir físicamente en el invernadero suministrando agua.
- **Controlador o módulo de gestión (Simulado en la aplicación):** encargado de coordinar las lecturas, evaluarlas y emitir órdenes al actuador.

### Operaciones generales que realiza
1. **Observar:** registrar mediciones simuladas provenientes de cada sensor instalado.
2. **Evaluar:** interpretar las lecturas cuantitativas comparándolas contra umbrales específicos para determinar si el estado es bajo, adecuado o alto.
3. **Actuar:** evaluar la condición del suelo para activar el sistema de riego cuando la humedad sea baja o desactivarlo/mantenerlo apagado si la humedad es adecuada o alta.
## 2. Identificación de objetos

Para este problema se identifican cuatro objetos conceptuales principales:

1. **Sensor de Temperatura**
    - *Qué representa:* El dispositivo físico instalado en el invernadero para medir la temperatura térmica del aire.
    - *Por qué debe existir como objeto:* Porque encapsula datos propios (su valor en °C) y la lógica particular para interpretar cuándo hace frío o calor en el invernadero.
    - *Responsabilidad:* Mantener el registro de la temperatura del aire y clasificarla cualitativamente.

2. **Sensor de Humedad Ambiental**
    - *Qué representa:* El higrómetro que cuantifica el vapor de agua en el aire.
    - *Por qué debe existir como objeto:* Permite vigilar el microclima aéreo de forma independiente sin mezclar sus umbrales con los de la tierra.
    - *Responsabilidad:* Mantener el porcentaje de humedad del aire y determinar si el ambiente está seco, óptimo o saturado.

3. **Sensor de Humedad del Suelo**
    - *Qué representa:* La sonda insertada en el sustrato o tierra de cultivo.
    - *Por qué debe existir como objeto:* Es la fuente de información directa para la toma de decisiones sobre el suministro de agua.
    - *Responsabilidad:* Mantener el porcentaje de agua en la tierra y advertir si el nivel hídrico requiere intervención.

4. **Sistema de Riego**
    - *Qué representa:* El actuador (bomba hidráulica o electroválvula) encargado de regar el cultivo.
    - *Por qué debe existir como objeto:* Modela una entidad activa que modifica el entorno, diferenciándose de los sensores que únicamente leen el estado pasivo.
    - *Responsabilidad:* Cambiar su estado operativo (activo o inactivo) e informar si está suministrando agua.

---

## 3. Estado y comportamiento

| Objeto propuesto | Responsabilidad | Información que debe conservar | Comportamientos que debe realizar |
|---|---|---|---|
| **Sensor de Temperatura** | Monitorear e interpretar la temperatura del aire | Identificador, ubicación, estado (activo/inactivo), última medición en °C | Registrar nueva medición, permitir consultar su última lectura, evaluar si la temperatura es baja (< 18 °C), adecuada (18–30 °C) o alta (> 30 °C) |
| **Sensor de Humedad Ambiental** | Monitorear e interpretar el vapor de agua en el aire | Identificador, ubicación, estado (activo/inactivo), última medición en % | Registrar nueva medición, permitir consultar su última lectura, evaluar si la humedad ambiental es baja (< 40 %), adecuada (40–70 %) o alta (> 70 %) |
| **Sensor de Humedad del Suelo** | Monitorear la humedad del sustrato y advertir necesidad de riego | Identificador, ubicación, estado (activo/inactivo), última medición en % | Registrar nueva medición, permitir consultar su última lectura, evaluar si la humedad del suelo es baja (< 30 %), adecuada (30–70 %) o alta (> 70 %) |
| **Sistema de Riego** | Controlar el suministro de agua al cultivo | Estado operativo actual (activo / inactivo) | Activar el suministro de agua, desactivar el suministro de agua, informar su estado actual |
## 4. Características comunes y especialización

Al comparar los tres sensores analizados se identifican los siguientes patrones:

1. **¿Qué información tienen en común?**
   Todos los sensores comparten:
    - Un identificador único (ID).
    - Una ubicación física dentro del invernadero.
    - Un estado operativo (activo o inactivo).
    - El almacenamiento de su última medición numérica.

2. **¿Qué comportamientos tienen en común?**
   Todos permiten:
    - Registrar y actualizar una nueva lectura.
    - Consultar su identificación, ubicación y estado.
    - Evaluar su medición para ofrecer un diagnóstico cualitativo.

3. **¿Qué características cambian dependiendo del tipo de sensor?**
    - La unidad física asociada a la medición (°C frente a %).
    - Los rangos de valores válidos.
    - Los criterios y umbrales de decisión para interpretar el dato. Un valor de 25 representa "temperatura adecuada" en el sensor de temperatura, pero representaría "humedad baja" en el sensor de humedad ambiental.

4. **¿Existe un concepto general que permita representar a todos los sensores?**
   Sí, el concepto abstracto y general de **Sensor**.

5. **¿Qué elementos podrían representar especializaciones de ese concepto?**
   Las especializaciones directas son:
    - `SensorTemperatura`
    - `SensorHumedad`
    - `SensorHumedadSuelo`

---

## 5. Relaciones entre objetos

### Distinción entre relaciones "ES UN" y "TIENE / UTILIZA UN"

- **Relación "ES UN" (Generalización y Especialización):**
    - Un sensor de temperatura **es un** sensor.
    - Un sensor de humedad ambiental **es un** sensor.
    - Un sensor de humedad del suelo **es un** sensor.  
      *Conclusión:* Estas relaciones deben modelarse mediante **herencia**, donde una superclase común concentre los atributos y métodos compartidos, mientras las subclases especialicen el comportamiento de evaluación.

- **Relación "TIENE / UTILIZA UN" (Asociación y Dependencia):**
    - El sistema general del invernadero **tiene** una colección de sensores y **tiene** un sistema de riego.
    - El proceso de toma de decisiones **utiliza** la lectura del sensor de humedad del suelo para determinar si el sistema de riego debe operar.

### Colaboración entre objetos
- El módulo de control lee el estado del `SensorHumedadSuelo`.
- Con base en el resultado cualitativo (humedad baja), el módulo envía la instrucción `activar()` o `desactivar()` al `SistemaRiego`.

---

### Pregunta clave: ¿Por qué `SistemaRiego` no debería ser una subclase de `Sensor`?

El `SistemaRiego` **no debe ser una subclase de `Sensor`** debido a las siguientes razones fundamentales de diseño orientado a objetos:

1. **Ruptura de la relación semántica "ES UN":**  
   Un sistema de riego no es un sensor. Un sensor es un componente de **entrada** (lectura pasiva del entorno), mientras que un sistema de riego es un componente de **salida o actuador** (modifica activamente el entorno).
2. **Herencia forzada e incoherente:**  
   Si `SistemaRiego` heredara de `Sensor`, estaría obligado a heredar atributos como `ultimaMedicion` o métodos como `evaluarMedicion()`, los cuales carecen por completo de sentido para una bomba o electroválvula de riego.
3. **Violación del Principio de Sustitución de Liskov (LSP):**  
   El sistema no puede tratar indistintamente a un actuador como si fuera un dispositivo de medición. Un sistema de riego no produce lecturas del entorno; colabora con los sensores mediante una relación de asociación o uso, no de parentesco jerárquico.
## 6. Reflexión sobre constructores y sobrescritura

### Constructores y super
1. **¿Los constructores de la superclase se heredan?**  
   No, los constructores no se heredan en Java. Cada subclase define sus propios constructores.
2. **Cuando creas un objeto de una subclase, ¿qué constructor se ejecuta primero?**  
   Primero se ejecuta el constructor de la superclase (`Sensor`) para inicializar el estado base del objeto, y posteriormente continúa el constructor de la subclase.
3. **¿Para qué utilizaste `super(...)`?**  
   Para reutilizar la lógica de inicialización de los atributos `id` y `ubicacion` definida en `Sensor`, evitando duplicar asignaciones y respetando la encapsulación de la superclase.

### Sobrescritura
1. **¿Qué método sobrescribiste?**  
   El método `evaluarMedicion()`.
2. **¿Qué indica `@Override`?**  
   Le indica al compilador que el método pretende sobrescribir una definición existente en la superclase. Si la firma cambia accidentalmente (por ejemplo, en el nombre o parámetros), el compilador marcará un error.
3. **¿Por qué utilizaste el mismo nombre de método en las diferentes subclases?**  
   Porque la acción conceptual es idéntica en todos los sensores: evaluar la lectura actual. Lo único que varía es la regla de negocio para clasificar esa lectura.
4. **¿Qué ventaja tiene esto frente a crear métodos con nombres distintos?**  
   Permite aplicar polimorfismo: podemos invocar `evaluarMedicion()` sobre cualquier referencia de tipo `Sensor` sin necesidad de conocer de antemano el tipo concreto del objeto ni escribir estructuras `switch` o `if/else`.
## 7. Polimorfismo y enlace dinámico

### Tabla de resolución dinámica

| Tipo de referencia | Tipo real del objeto | Método que se ejecuta |
|---|---|---|
| `Sensor` | `SensorTemperatura` | `SensorTemperatura.evaluarMedicion()` |
| `Sensor` | `SensorHumedad` | `SensorHumedad.evaluarMedicion()` |
| `Sensor` | `SensorHumedadSuelo` | `SensorHumedadSuelo.evaluarMedicion()` |

### Preguntas de análisis
1. **¿Por qué una referencia de tipo `Sensor` puede apuntar a un objeto `SensorTemperatura`?**  
   Porque existe una relación de herencia ("es un"). Debido al principio de sustitución de Liskov, cualquier instancia de una subclase es compatible con una variable tipada con su superclase.
2. **¿Puede una referencia `SensorTemperatura` apuntar directamente a cualquier objeto `Sensor`?**  
   No. Un `Sensor` genérico no garantiza poseer las características y comportamientos específicos de `SensorTemperatura`. Requiere una conversión explícita (casting) previa verificación.
3. **Cuando ejecutas `sensor.evaluarMedicion()`, ¿qué versión del método se ejecuta?**  
   Se ejecuta la versión de la clase real instanciada en memoria con `new` (`SensorTemperatura`, `SensorHumedad` o `SensorHumedadSuelo`), no la de la superclase `Sensor`.
4. **¿Cómo determina Java cuál implementación utilizar?**  
   Mediante enlace dinámico (*dynamic binding*). En tiempo de ejecución, la Máquina Virtual de Java (JVM) inspecciona el encabezado del objeto en memoria (*vtable*) para despachar la llamada al método sobrescrito correspondiente.
5. **¿Dónde puedes observar el polimorfismo?**  
   En la colección `Sensor[] sensores`, donde un mismo tipo de referencia almacena objetos de distintas clases especializadas.
6. **¿Dónde puedes observar el enlace dinámico?**  
   Dentro del ciclo `for (Sensor sensor : sensores)`, donde la misma línea de código `sensor.evaluarMedicion()` invoca tres comportamientos distintos de forma transparente.
## 8. Verificación de tipos, casting e integración del sistema

### Análisis de instanceof y casting
1. **¿Por qué fue necesario realizar el casting?**  
   Porque al trabajar con una colección de tipo `Sensor[]`, el compilador únicamente reconoce los métodos declarados en la superclase `Sensor`. Para invocar métodos exclusivos de las subclases (como `alertaHelada()` o `requiereRiego()`), es necesario realizar una conversión de tipo hacia abajo (*downcasting*).
2. **¿Qué comprueba `instanceof`?**  
   Comprueba en tiempo de ejecución si el objeto referenciado es una instancia de una clase particular o de una subclase de ella, evitando excepciones de tipo `ClassCastException`.
3. **¿Qué podría ocurrir si intentaras convertir un objeto a una subclase incorrecta?**  
   La JVM lanzaría un error en tiempo de ejecución (`ClassCastException`), deteniendo la ejecución del programa.
4. **¿Por qué no debes utilizar `instanceof` para sustituir innecesariamente el polimorfismo?**  
   Porque encadenar múltiples `if (sensor instanceof ...)` rompe el principio de abierto/cerrado (OCP). Si se agrega una nueva subclase, habría que modificar todos los bloques condicionales en lugar de permitir que el enlace dinámico resuelva el comportamiento automáticamente.

### Relación entre sensores y sistema de riego
* **¿El sistema de riego "es un" sensor o utiliza información proporcionada por los sensores?**  
  `SistemaRiego` **utiliza** información de los sensores. No es un sensor porque su responsabilidad no es medir ni registrar variables ambientales, sino actuar sobre el entorno físico (abrir o cerrar válvulas). Representa una relación de asociación/colaboración, no de herencia.
## 9. Evidencia de pruebas ejecutadas

| Caso de prueba | Entrada simulada | Resultado obtenido en ejecución | Estado |
|---|---|---|:---:|
| Creación de sensores | Constructor con ID y Ubicación | Objetos inicializados con medición 0.0 y activo en true | Superado |
| Temp. Baja (< 18 °C) | 14.0 °C | "Temperatura baja (14.0 °C): riesgo de frío para el cultivo." | Superado |
| Temp. Adecuada (18–30 °C) | 24.0 °C | "Temperatura adecuada (24.0 °C): condiciones óptimas." | Superado |
| Temp. Alta (> 30 °C) | 35.0 °C | "Temperatura alta (35.0 °C): activar ventilación o sombra." | Superado |
| Hum. Amb. Baja (< 40 %) | 30.0 % | "Humedad ambiental baja (30.0%): aire seco..." | Superado |
| Hum. Amb. Adecuada (40–70 %) | 55.0 % | "Humedad ambiental adecuada (55.0%): entorno equilibrado." | Superado |
| Hum. Amb. Alta (> 70 %) | 80.0 % | "Humedad ambiental alta (80.0%): riesgo de hongos." | Superado |
| Hum. Suelo Baja (< 30 %) | 20.0 % | "Humedad del suelo baja (20.0%): suelo seco..." | Superado |
| Hum. Suelo Adecuada (30–70 %) | 50.0 % | "Humedad del suelo adecuada (50.0%): nivel óptimo." | Superado |
| Hum. Suelo Alta (> 70 %) | 85.0 % | "Humedad del suelo alta (85.0%): suelo saturado..." | Superado |
| Arreglo polimórfico | `Sensor[]` con 3 subclases | Cada instancia despacha su propio método en tiempo de ejecución | Superado |
| `instanceof` y Casting | `(SensorHumedadSuelo) s` | Se accede a `requiereRiego()` sin `ClassCastException` | Superado |
| Control de Riego (Suelo seco) | 15.0 % | `SistemaRiego` detecta nivel crítico y abre válvula | Superado |
| Control de Riego (Suelo óptimo)| 55.0 % | `SistemaRiego` detecta nivel adecuado y cierra válvula | Superado |

## 10. Cambios realizados al diseño (UML Inicial vs. Final)

Durante la implementación en Java se incorporaron las siguientes mejoras técnicas respecto al diseño inicial:
1. **Adición de `registrarMedicion(double)` en `Sensor`:**  
   Se implementó para mantener la encapsulación pura (atributos `private`), evitando modificar directamente los atributos de medición desde clases externas.
2. **Métodos específicos en subclases:**  
   Se agregó `alertaHelada()` en `SensorTemperatura` y `requiereRiego()` en `SensorHumedadSuelo` para justificar de forma técnica y no forzada el uso de `instanceof` y `casting`.
3. **Firma de colaboración en `SistemaRiego`:**  
   El método `evaluarYEjecutar(SensorHumedadSuelo)` formalizó la relación de dependencia/asociación con el sensor de suelo, desacoplando el actuador de la jerarquía de herencia.

## Diagrama UML final

![Diagrama UML final](uml-final.png)