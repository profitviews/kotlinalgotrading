package market.data

import market.Signal
import strategy.Source

interface Data {
    fun subscribe(symbol: String, signal: Signal): Data
    fun onTrade(updater: (source: Source, marketData: Map<String, Any>) -> Unit)
}