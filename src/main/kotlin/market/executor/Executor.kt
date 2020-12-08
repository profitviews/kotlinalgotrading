package market.executor

interface Executor {
    fun market(symbol: String, side: String, orderQty: Int) : MutableMap<String, Any>
    fun limit(symbol: String, side: String, orderQty: Int, price: Float) : MutableMap<String, Any>?
}