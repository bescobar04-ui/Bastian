## 🎰 Iteración 08: Persistencia e Inversión de Dependencias (DIP)

### 🎯 Objetivos Implementados
En esta iteración se logró desacoplar por completo la lógica matemática del juego de la gestión del historial de resultados, aplicando de manera estricta el **Principio de Inversión de Dependencias (DIP)**.

1. **Abstracción por Contrato (`IRepositorioResultados`):** Se definió una interfaz pura que establece las operaciones fundamentales de persistencia (`guardarResultado` y `obtenerHistorial`), aislando el núcleo de la aplicación de los detalles de infraestructura.
2. **Polimorfismo de Persistencia:** 
   * **`RepositorioEnMemoria`:** Almacena y gestiona las jugadas en estructuras volátiles de ejecución (`List<Resultado>`).
   * **`RepositorioArchivo`:** Implementa la persistencia de datos persistentes mediante la escritura y lectura de flujos en un archivo local plano en formato **CSV** (`historial_ruleta.csv`).
3. **Inyección de Dependencias:** Las clases de alto nivel (`Ruleta`, `RuletaController` y `ResultadoController`) ya no instancian componentes rígidos. El repositorio elegido se inyecta directamente a través del constructor del motor en el `Launcher`, permitiendo alternar el mecanismo de almacenamiento de datos sin modificar una sola línea de código lógico.

---

### 🛠️ Arquitectura del Modelo UML (Trazabilidad)

El diseño arquitectónico respeta la relación del desacoplamiento mediante el uso de conectores normados:
* **Realización (`---▷`):** Las clases concretas `RepositorioEnMemoria` y `RepositorioArchivo` realizan formalmente el contrato estipulado por `IRepositorioResultados`.
* **Asociación Dirigida (`──>`):** El modelo de dominio (`Ruleta`) y el módulo de análisis (`Estadistica` / `ResultadoController`) dependen exclusivamente de la abstracción de la interfaz.

> **Nota de Robustez:** Si el archivo CSV llega a corromperse o es inaccesible entre sesiones, el sistema implementa la flexibilidad de mutar dinámicamente al repositorio en memoria en tiempo de inicialización, asegurando la continuidad operativa del casino virtual.
