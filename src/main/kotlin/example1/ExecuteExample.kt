package example1

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Hex

import java.time.Instant
import khttp.request
import org.json.JSONObject

val domain = "http://www.bitmex.com"
fun market_order(symbol: String, side: String, orderQty: Int): MutableMap<String, Any> {
    val json_order = JSONObject(mapOf(
        "symbol" to symbol, "side" to side, "orderQty" to orderQty, "ordType" to "Market"))
    val method = "POST"
    val path = "/api/v1/order" // See https://www.bitmex.com/api/explorer/#!/Order/Order_new
    val expiry = (Instant.now().epochSecond + 5).toString()
    val body = method + path + expiry + json_order.toString()
    val headers = mapOf("api-expires" to expiry, "api-key" to api_key,
        "api-signature" to signature(body, api_secret), "Content-Length" to body.length.toString())
    return request(method, domain + path, headers, json = json_order).jsonObject.toMap()
}

val api_key = System.getenv("bitmex_api_key")   // See https://www.bitmex.com/app/apiKeys
val api_secret = System.getenv("bitmex_secret")
fun signature(data: String, key: String): String {
    val code = "HmacSHA256"
    val hmac = Mac.getInstance(code)
    val secretKey = SecretKeySpec(key.toByteArray(), code)
    hmac.init(secretKey)
    return Hex.encodeHexString(hmac.doFinal(data.toByteArray()))
}

fun main() {
    println(market_order("XBTUSD", "Buy", 10))
}