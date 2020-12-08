package market.executor

import market.Venue
import java.lang.IllegalArgumentException

class ExecutorFactory {
    companion object {
        fun createExecutor(venue: Venue): Executor = when(venue) {
            Venue.BITMEX -> BitMEXExecutor()
            else -> throw IllegalArgumentException("Trading venue not yet implemented")
        }
    }
}