package market.executor

import market.Venue

fun main() {
    val demo_executor = ExecutorFactory.createExecutor(Venue.BITMEX)
    println(demo_executor.market("XBTUSD", "Buy", 4))
}