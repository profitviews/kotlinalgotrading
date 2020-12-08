package webinar3

import market.data.BitMEXData
import market.Signal
import market.Venue
import market.executor.ExecutorFactory
import com.tictactec.ta.lib.Core
import strategy.Source
import strategy.StrategyInfrastructure

class Simple : StrategyInfrastructure(BitMEXData()) {
    override fun subscriptions() {
        data.subscribe("XBTUSD", Signal.TRADE).subscribe("ETHUSD", Signal.TRADE)
    }

    override fun update(source: Source, marketData: Map<String, Any>) {
        if(!traded) {
            val inReal = DoubleArray(50)
            val executor = ExecutorFactory.createExecutor(Venue.BITMEX)
            val core = Core()
            println(executor.market("XBTUSD", "Buy", 1))
            traded = true
        }
        println("Venue: ${source.venue}, Symbol: ${source.symbol}, Signal: ${source.signal}")
        println("Market data:")
        println(marketData)
    }

    private var traded = false
}

fun main() {
    val simpleStrategy = Simple()
    simpleStrategy.subscriptions()
    simpleStrategy.execute()
}