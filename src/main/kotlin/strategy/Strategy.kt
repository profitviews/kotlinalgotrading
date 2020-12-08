package strategy

import market.Signal
import market.Venue
import market.data.Data

class Source(venue: Venue, symbol: String, signal: Signal) {
    val venue = venue
    val symbol = symbol
    val signal = signal
}

interface Strategy {
    fun registrations()
    fun subscriptions()
    fun update(source: Source, marketData: Map<String, Any>)
}

abstract class StrategyInfrastructure(data: Data) : Strategy {
    protected val data = data
    override fun registrations() {
        data.onTrade(::update)
    }
    fun execute() {
        registrations()
        subscriptions()
    }
}