package webinar4

import market.Signal
import market.Venue
import market.data.BitMEXData
import market.executor.ExecutorFactory
import strategy.Source
import strategy.StrategyInfrastructure
import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import java.math.BigDecimal

class MeanReversion(val reversion: Double, val lookback: Int, val symbol: String, val qty: Int):
    StrategyInfrastructure(BitMEXData()) {

    override fun subscriptions() {
        data.subscribe(symbol, Signal.TRADE)
    }

    private val prices = ArrayDeque<Double>(lookback)
    private val executor = ExecutorFactory.createExecutor(Venue.BITMEX)
    private val ta = Core()
    private var elements = 0
    private var avg = 0.0
    private var std = 0.0
    override fun update(source: Source, marketData: Map<String, Any>) {
        val newPrice = (marketData["price"] as BigDecimal).toDouble()
        prices.addLast(newPrice)
        if(elements < lookback) {
            avg = (elements*avg + newPrice)/++elements
        } else {
            avg += (newPrice - prices.removeFirst())/elements
            std = stdDev(prices)
            val upperReversion = avg + reversion*std
            val lowerReversion = avg - reversion*std
            if(newPrice > upperReversion) {
                executor.market(symbol, "Sell", qty)
            }
            if(newPrice < lowerReversion) {
                executor.market(symbol, "Buy", qty)
            }
        }
    }

    private fun stdDev(prices: ArrayDeque<Double>): Double {
        var b = MInteger()
        var l = MInteger()
        var std = DoubleArray(1)
        var retCode = ta.stdDev(0, lookback-1, prices.toDoubleArray(), lookback, 1.0, b, l, std)
        return std[0]
    }
}

fun main() {
    val meanReversion = MeanReversion(4.0, 50, "XBTUSD", 100)
    meanReversion.subscriptions()
    meanReversion.execute()
}