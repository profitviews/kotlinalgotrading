package market.data

import io.socket.client.IO
import io.socket.client.Socket
import market.Signal
import org.json.JSONObject
import strategy.Source
import market.Venue
import market.venueMap

class BitMEXData : Data {
    val socket = IO.socket("https://markets.profitview.net?api_key=" + System.getenv("profitview_api_key"))

    override fun subscribe(symbol: String, signal: Signal): Data {
        socket.connect().on(Socket.EVENT_CONNECT) {
            socket.emit("subscribe", makeSubscription(signal, symbol))
        }
        return this
    }

    override fun onTrade(updater: (source: Source, marketData: Map<String, Any>) -> Unit) {
        socket.on(signalMap[Signal.TRADE]) {
            parameters -> for(parameter in parameters) {
                val j = parameter as JSONObject
                updater(Source(Venue.BITMEX, j["sym"].toString(), Signal.TRADE), mapOf(
                    "side" to j["side"], "size" to j["size"], "price" to j["price"]))
            }
        }
    }

    private val signalMap = mapOf(Signal.TRADE to "trade", )

    private fun makeSubscription(signal: Signal, symbol: String): Array<String> {
        return arrayOf(arrayOf(signalMap[signal], venueMap[Venue.BITMEX], symbol).joinToString(":"))
    }
}