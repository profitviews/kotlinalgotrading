# Algo Trading in Kotlin

All the code required for writing an algo trading systems in Kotlin

## Installation and Build

See [Install](install.md).

## Discussion and Help

For help of any type, to discuss ideas or suggest improvements, please join our 
[ProfitView Discord](https://discord.gg/34Jy8STJRg).

The webinar itself: [Algorithmic Trading with Python](https://www.crowdcast.io/e/kotlin_algo_trading).

In the webinar I used this set of [slides](https://docs.google.com/presentation/d/1x5niHnWBhFn39x99F67pWIpjpOglRQjour4-wDBg9JQ/edit?usp=sharing).

## Overview

The code we present shows that you can put together a functional algo with very little coding infrastructure.
We've provided a simple framework consisting of packages `market.data`, `market.executor` and `strategy` - but you may 
well prefer to adapt it or work out different coding idioms.

As you'll see in the code, execution is essentially just
```kotlin
val domain = "http://www.bitmex.com"
fun market_order(symbol: String, side: String, orderQty: Int): MutableMap<String, Any> {
    val json_order = JSONObject(
        mapOf("symbol" to symbol, "side" to side, "orderQty" to orderQty, "ordType" to "Market"))
    val method = "POST"
    val path = "/api/v1/order" // See https://www.bitmex.com/api/explorer/#!/Order/Order_new
    val expiry = (Instant.now().epochSecond + 5).toString()
    val body = method + path + expiry + json_order.toString()
    val headers = mapOf("api-expires" to expiry, "api-key" to api_key,
        "api-signature" to signature(body, api_secret), "Content-Length" to body.length.toString())
    return request(method, domain + path, headers, json = json_order).jsonObject.toMap()
}
```
To build an algo, you need to react to the changing market.  With our solution that's even easier:
```kotlin
val socket = IO.socket("https://markets.profitview.net?api_key=" + System.getenv("profitview_api_key"))
socket.connect().on(Socket.EVENT_CONNECT) { socket.emit("subscribe", arrayOf("trade:bitmex:XBTUSD"))}
socket.on("trade") { parameters -> println(parameters[0] as JSONObject) } // replace the println() with an algo
```
Essentially, integrating these two sections with some logic (in fact, the algo) is all you need.

# [ProfitView](https://profitview.net)

If, as a serious trader, you decide to trade algorithmically, it will be on the basis of clear trading strategies.
Given market competition, it is always necessary to test and monitor such strategies.  Most traders keep spreadsheets 
of their orders and use various means to keep track of executions, typically downloading them from exchanges.  They then 
must be reconciled with the orders and the returns calculated.  This data should then be analyzed against the 
expected results of the strategies under which the orders were entered.

Clearly this process takes time.  It is also error-prone - for instance when aligning the data or writing spreadsheet 
macros.  That is time during which the market remains active unobserved.  Some traders write their own bespoke software
to ease the process.  This will also take time and require tuning and maintenance and will have bugs.

[ProfitView](https://profitview.net) provides all this for the trader, and more, in real-time and graphically.
