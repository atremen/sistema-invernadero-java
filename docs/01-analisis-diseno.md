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