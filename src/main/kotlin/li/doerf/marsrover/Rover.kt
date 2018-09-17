package li.doerf.marsrover

import java.lang.IllegalStateException

class Rover(var x: Int, var y: Int, var d: String, val obstacles: Array<Obstacle>) {

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
        val newP = calculateNewPosition(direction)
        if(checkForObstacle(newP.first, newP.second)) {
            throw IllegalStateException("obstacle detected")
        }
        x = newP.first
        y = newP.second
    }

    private fun checkForObstacle(x: Int, y: Int): Boolean {
        for(o in obstacles) {
            if (o.x == x && o.y == y) {
                return true
            }
        }
        return false
    }

    fun calculateNewPosition(direction: Char): Pair<Int,Int> {
        val s = if (direction == 'f') 1 else -1
        var xNew = x
        var yNew = y
        when (d) {
            "s" -> yNew -= s
            "e" -> xNew += s
            "w" -> xNew -= s
            else -> yNew += s
        }
        return wrapPosition(Pair(xNew, yNew))
    }

    private fun wrapPosition(p: Pair<Int,Int>): Pair<Int, Int> {
        return Pair(wrap(p.first), wrap(p.second))
    }

    private fun wrap(i: Int): Int {
        if (i < 0) {
            return 9
        } else if (i > 9) {
            return 0
        }
        return i
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