package example4

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
    private var mean = 0.0
    override fun update(source: Source, marketData: Map<String, Any>) {
        val newPrice = convertPrice(marketData["price"])
        prices.addLast(newPrice)
        if(elements < lookback) { // Until we have enough data for the mean
            mean = (elements*mean + newPrice)/++elements
        } else { // Move and recalculate mean
            mean += (newPrice - prices.removeFirst())/elements
            val stdReversion = reversion*stdDev(prices)
            if(newPrice > mean + stdReversion) { // Upper extreme - Sell!
                executor.market(symbol, "Sell", qty)
            }
            if(newPrice < mean - stdReversion) { // Lower extreme - Buy!
                executor.market(symbol, "Buy", qty)
            }
        }
    }

    private fun stdDev(prices: ArrayDeque<Double>): Double {
        val b = MInteger()
        val l = MInteger()
        val std = DoubleArray(1)
        var retCode = ta.stdDev(0, lookback-1, prices.toDoubleArray(), lookback, 1.0, b, l, std)
        return std[0]
    }
    private fun convertPrice(price: Any?) = (price as BigDecimal).toDouble()
}

fun main() {
    val meanReversion = MeanReversion(
        2.5,
        50,
        "XBTUSD",
        100)
    meanReversion.subscriptions()
    meanReversion.execute()
}