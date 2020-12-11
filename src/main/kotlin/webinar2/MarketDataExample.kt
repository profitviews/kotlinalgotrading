package webinar2

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject

fun main() {
    val socket = IO.socket("https://markets.profitview.net?api_key=" + System.getenv("profitview_api_key"))
    socket.connect().on(Socket.EVENT_CONNECT) { socket.emit("subscribe", arrayOf("trade:bitmex:XBTUSD"))}
    socket.on("trade") { parameters -> println(parameters[0] as JSONObject) }// replace the println() with an algo
}