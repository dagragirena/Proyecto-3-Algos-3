import java.io.File
import java.util.PriorityQueue

data class Celda(val altura: Int, val fila: Int, val columna: Int): Comparable<Celda> {
    override fun compareTo(other: Celda): Int = this.altura.compareTo(other.altura)
}

class AlfonsoJose {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val instancia = AlfonsoJose()
            val ciudad = instancia.leerEntrada("atlantis.txt")
            
            if (ciudad != null) {
                val resultado = instancia.calcularAgua(ciudad)
                println(resultado)
            } else {
                System.err.println("Error: No se pudo procesar el archivo 'atlantis.txt'.")
                System.err.println("Asegúrate de que el archivo exista y que todas las filas tengan el mismo tamaño.")
            }
        }
    }

    fun leerEntrada(input: String): Array<IntArray>? {
        val archivo = File(input)
        if (!archivo.exists()) {
            System.err.println("Error: El archivo $input no existe")
            return null
        }

        val matriz = mutableListOf<IntArray>()
        var anchoEsperado = -1

        val lineas = archivo.readLines()

        for (linea in lineas) {
            if (linea.isNotBlank()) {
                val contenido = linea.trim()

                if (anchoEsperado == -1) {
                    anchoEsperado = contenido.length
                } else if (contenido.length != anchoEsperado) {
                    System.err.println("Error de formato: La fila '${matriz.size + 1}' tiene un tamaño distinto.")
                    return null
                }

                try {
                    val fila = contenido.map { it.toString().toInt() }.toIntArray()
                    matriz.add(fila)
                } catch (e: Exception) {
                    System.err.println("Error: Caracter no numérico encontrado.")
                    return null
                }   
            }
       }

        return if (matriz.isEmpty()) null else matriz.toTypedArray()
    }

    fun calcularAgua(matriz: Array<IntArray>): Int {
        val n = matriz.size
        val m = matriz[0].size
        val visitado = Array(n) { BooleanArray(m) }
        val cola = PriorityQueue<Celda>()

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    cola.add(Celda(matriz[i][j], i, j))
                    visitado[i][j] = true
                }
            }
        }

        var totalAgua = 0
        val direccionFila = intArrayOf(-1, 1, 0, 0)
        val direccionColumna = intArrayOf(0, 0, -1, 1)

        while (cola.isNotEmpty()) {
            val actual = cola.poll()

            for (i in 0 until 4) {
                val nuevaFila = actual.fila + direccionFila[i]
                val nuevaColumna = actual.columna + direccionColumna[i]

                if (nuevaFila in 0 until n && nuevaColumna in 0 until m && !visitado[nuevaFila][nuevaColumna]){
                    visitado[nuevaFila][nuevaColumna] = true

                    if (matriz[nuevaFila][nuevaColumna] < actual.altura) {
                        totalAgua += (actual.altura - matriz[nuevaFila][nuevaColumna])
                    }

                    val nuevoNivel = maxOf(matriz[nuevaFila][nuevaColumna], actual.altura)
                    cola.add(Celda(nuevoNivel, nuevaFila, nuevaColumna))
                }
            }
        }
        return totalAgua
    }
}