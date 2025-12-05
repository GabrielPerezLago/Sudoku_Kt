
import com.gabri.sudoku.controller.JuegoPuntuacionesController
import com.gabri.sudoku.model.JuegoModel
import com.gabri.sudoku.objects.JuegoObj

suspend fun main() {
    val juego = JuegoPuntuacionesController()
    JuegoObj.nombre = "gabri"
    JuegoObj.tiempo = "90:00"
    JuegoObj.mode = "medio"
    val response = juego.postPuntuaciones(JuegoObj)
    if (response){
        println("Se inserto la puntuacion correctamente")
    } else {
        println("No se a podido crear la puntuacion")
    }
}