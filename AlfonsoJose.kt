import java.io.File
import java.io.BufferedReader

class Celda(altura: Int, fila: Int, col: Int): Comparable<Celda> {
    override fun compareTo(otro: Celda): Int = this.altura.compareTo(otro.altura)
}

fun leerEntrada(input: String): {
    val archivo = File(input)
    if (!archivo.exists) {
        println("El archivo $input no existe")
        return null
    }
    
    val bufferedReader: BufferedReader = archivo.bufferedReader()
    val matriz = mutableListOf<IntArray>()

    var linea = bufferedReader.readLine()
    while (linea != null) {
        val fila = linea.trim().split("\\s+".toRegex()).map { it.toInt() }.toIntArray()
        matriz.add(fila)
        linea = bufferedReader.readLine()
    }
    bufferedReader.close()

    return matriz.toTypedArray()
}

fun calcularAgua(matriz: Array<IntArray>): Int {
    val n = matriz.size
    val m = matriz[0].size
    val visitado = Array(n) {BooleanArray(m)}
    val cola = PriorityQueue<Celda>()

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                cola.add(Celda(matriz[i][j], i, j))
                visitado[i][j] = true
            }
        }
    }

    val contador = 0

    while (cola.isNotEmpty()) {
        val actual = cola.poll()
    }
}

fun main() {
    val ciudad = leerEntrada("atlantis.txt")

}