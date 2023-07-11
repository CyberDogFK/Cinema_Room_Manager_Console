fun calculateBrakingDistance(v1: String, a: String): Int {
    return runCatching {
        val v2 = 0
        val v1 = v1.toInt(10)
        val a = a.toInt(10)
        (v2 * v2 - v1 * v1) / (2 * a)
    }.onFailure {
        when (it) {
            is ArithmeticException -> println("The car does not slow down!")
            else -> println(it.message)
        }
    }.getOrDefault(-1)
}
