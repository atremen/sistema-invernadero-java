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