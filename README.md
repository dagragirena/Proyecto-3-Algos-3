# Proyecto 3: Mundo Cubo

**Curso:** CI-2693 - Laboratorio de Algoritmos y Estructuras III

## Integrantes

* Victor Hernandez, Carnet: 20-10349
* Daniela Gragirena, Carnet: 19-10543

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de un algoritmo en Kotlin diseñado para calcular la cantidad total de cubos de agua que puede retener la "Ciudad Perdida de la Atlántida" dentro del universo de Mundo Cubo.

La ciudad se representa como una matriz de torres de distintas alturas. Dado que el agua fluye hacia los niveles más bajos y se derrama fuera de los límites de la ciudad (altura 0), el algoritmo determina el volumen atrapado basándose en la cota mínima de escape de cada celda hacia el exterior, asegurando que la ciudad esté tan llena de agua como sea físicamente posible.

## Arquitectura y Estructuras de Datos

La solución modela el problema utilizando una variante del **Algoritmo de Dijkstra** para caminos de costo mínimo:

* **Vértices:** Cada posición $(i, j)$ leída del archivo `atlantis.txt` se instancia como un objeto de la clase `Celda`, la cual guarda su ubicación y su nivel de inundación actual.
* **Aristas (Adyacencia):** Se consideran conexiones implícitas entre celdas adyacentes (Norte, Sur, Este y Oeste). Se utiliza un par de arreglos de desplazamiento (`df` y `dc`) para sistematizar la exploración de vecinos.
* **Búsqueda (Estrategia Voraz):** El algoritmo utiliza una **Cola de Prioridad** (`PriorityQueue`) para procesar la ciudad desde el perímetro hacia el interior.
    1. Se inicia insertando todas las celdas del borde como puntos de escape iniciales (frontera).
    2. En cada iteración, se extrae la celda con el nivel de inundación más bajo.
    3. Se revisan sus vecinos no visitados: si un vecino es más bajo que el nivel actual, se acumula la diferencia como agua atrapada y se actualiza su nivel de inundación.
    4. La "relajación" en este contexto define que el nivel de un vecino es el máximo entre su altura original y el nivel de la celda desde la cual se explora.

## Complejidad

* **Cálculo de Inundación:** $O(V \log V)$, donde $V$ es el número total de vértices ($N \times M$). Cada celda se inserta y extrae exactamente una vez de la cola de prioridad.
* **Espacio:** $O(V)$ para mantener la matriz de alturas, la matriz de visitados y el estado de la cola de prioridad durante la ejecución.

## Requisitos y Compilación

* **Lenguaje:** Kotlin (JDK 11 o superior).
* **Archivos requeridos:** El ejecutable requiere de un archivo llamado `atlantis.txt` en el directorio raíz, con la matriz que representa las alturas de las torres (dígitos pegados por fila).

**Para compilar y ejecutar mediante consola:**

```bash
kotlinc AlfonsoJose.kt -include-runtime -d AlfonsoJose.jar
java -jar AlfonsoJose.jar
```
