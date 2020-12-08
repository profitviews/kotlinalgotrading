package market

enum class Venue {
    BITMEX,
    BINANCE,
}

enum class Signal {
    TRADE, SNAP, LIQUIDATION,
}

internal val venueMap = mapOf(Venue.BITMEX to "bitmex")
