package li.doerf.marsrover

class Rover(var x: Int, var y: Int, var d: String) {

    private val directions: Array<String> = arrayOf("w", "n", "e", "s")

    fun move(input: String) {
        input.indices.forEach{ i: Int ->
            step(input[i])
        }
    }

    private fun step(input: Char) {
        when (input) {
            'l' -> turn(input)
            'r' -> turn(input)
            'b' -> move(input)
            else -> move(input)
        }
    }

    private fun move(direction: Char) {
        val s = if (direction == 'f')  1 else -1
        when(d) {
            "s" -> y -= s
            "e" -> x += s
            "w" -> x -= s
            else -> y += s
        }
        wrapPosition()

    }

    private fun wrapPosition() {
        wrapX()
        wrapY()
    }

    private fun wrapX() {
        if (x < 0) {
            x = 9
        } else if (x > 9) {
            x = 0
        }
    }

    private fun wrapY() {
        if (y < 0) {
            y = 9
        } else if (y > 9) {
            y = 0
        }
    }

    private fun turn(direction: Char) {
        val cur = directions.indexOf(d)
        val new = (moveIndex(cur, direction)).remn(directions.size)
        d = directions[new]
    }

    private fun moveIndex(cur: Int, direction: Char): Int {
        if (direction == 'r') {
            return cur + 1
        }
        return cur - 1
    }

    /**
     * same as Int.rem, but wrapped to positive, when < 0
     */
    private fun Int.remn(div: Int): Int {
        var n = (this).rem(div)
        if ( n < 0) n += div
        return n
    }

}