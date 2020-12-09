package market.executor

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Hex

import java.time.Instant
import khttp.request
import org.json.JSONObject

class BitMEXExecutor : Executor {
    override fun market(symbol: String, side: String, orderQty: Int): MutableMap<String, Any> {
        val json_order = JSONObject(mapOf(
            "symbol" to symbol, "side" to side, "orderQty" to orderQty, "ordType" to "Market"))
        return post_order(json_order) }

    override fun limit(symbol: String, side: String, orderQty: Int, price: Float): MutableMap<String, Any> {
        val json_order = JSONObject(mapOf(
            "symbol" to symbol, "side" to side, "orderQty" to orderQty, "ordType" to "Limit", "price" to price))
        return post_order(json_order) }

    private val domain = "http://www.bitmex.com"
    private val method = "POST"
    private val path = "/api/v1/order" // See https://www.bitmex.com/api/explorer/#!/Order/Order_new
    private fun post_order(json_order: JSONObject): MutableMap<String, Any> {
        val expiry = (Instant.now().epochSecond + 5).toString()
        val body = method + path + expiry + json_order.toString()
        val headers = mapOf("api-expires" to expiry, "api-key" to api_key,
            "api-signature" to signature(body, api_secret), "Content-Length" to body.length.toString())
        return request(method, domain + path, headers, json = json_order).jsonObject.toMap() }

    private val api_key = System.getenv("bitmex_api_key")   // See https://www.bitmex.com/app/apiKeys
    private val api_secret = System.getenv("bitmex_secret")
    private fun signature(data: String, key: String): String {
        val code = "HmacSHA256"
        val hmac = Mac.getInstance(code)
        val secretKey = SecretKeySpec(key.toByteArray(), code)
        hmac.init(secretKey)
        return Hex.encodeHexString(hmac.doFinal(data.toByteArray())) }
}