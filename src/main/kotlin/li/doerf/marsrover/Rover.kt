package li.doerf.marsrover

import java.lang.IllegalStateException

class Rover(var position: Position, var d: String, val obstacles: Array<Obstacle>) {

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
        if(checkForObstacle(newP)) {
            throw IllegalStateException("obstacle detected")
        }
        position = newP
    }

    private fun checkForObstacle(position: Position): Boolean {
        for(o in obstacles) {
            if (o.position.x == position.x && o.position.y == position.y) {
                return true
            }
        }
        return false
    }

    private fun calculateNewPosition(direction: Char): Position {
        val s = if (direction == 'f') 1 else -1
        val newPos = Position(position.x, position.y)
        when (d) {
            "e" -> newPos.x += s
            "w" -> newPos.x -= s
            "s" -> newPos.y -= s
            else -> newPos.y += s
        }
        return wrapPosition(newPos)
    }

    private fun wrapPosition(p: Position): Position {
        return Position(wrap(p.x), wrap(p.y))
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