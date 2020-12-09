package strategy

import market.Signal
import market.Venue
import market.data.Data

class Source(val venue: Venue, val symbol: String, val signal: Signal)

interface Strategy {
    fun registrations()
    fun subscriptions()
    fun update(source: Source, marketData: Map<String, Any>)
}

abstract class StrategyInfrastructure(protected val data: Data) : Strategy {
    override fun registrations() {
        data.onTrade(::update)
    }
    fun execute() {
        registrations()
        subscriptions()
    }
}