import com.gabri.sudoku.controller.JuegoPuntuacionesController
import com.gabri.sudoku.objects.JuegoObj

suspend fun main() {
    val juego = JuegoPuntuacionesController()
    JuegoObj.mode = "facil"
    val lista = juego.getPuntuaciones("medio")
    println(lista)
}