package li.doerf.marsrover

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RoverTest {

    @ParameterizedTest
    @CsvSource( "0, 0, 'n'", "0, 1, 'w'", "1, 0, 'e'", "1, 1, 's'" )
    fun roverInit(x: Int, y: Int, d: String) {
        val rover = Rover(x, y, d)
        assertEquals(x, rover.x)
        assertEquals(y, rover.y)
        assertEquals(d, rover.d)
    }

    @ParameterizedTest
    @CsvSource(
            "0, 0, n, f, 0, 1, n",
            "0, 1, n, f, 0, 2, n",
            "0, 0, n, l, 0, 0, w",
            "0, 0, n, r, 0, 0, e",
            "0, 1, n, b, 0, 0, n",
            "0, 0, n, ff, 0, 2, n",
            "0, 0, n, fl, 0, 1, w",
            "0, 0, n, fll, 0, 1, s",
            "0, 0, n, fllf, 0, 0, s",
            "0, 0, n, frrf, 0, 0, s",
            "0, 0, n, rf, 1, 0, e",
            "2, 0, n, lf, 1, 0, w",
            "2, 0, w, f, 1, 0, w",
            "2, 0, e, f, 3, 0, e",
            "0, 1, s, f, 0, 0, s",
            "0, 0, n, llll, 0, 0, n",
            "0, 0, w, rrrr, 0, 0, w",
            "0, 0, w, bb, 2, 0, w",
            "0, 0, w, brflllf, 2, 1, e",
            "0, 0, n, b, 0, 9, n",
            "0, 0, n, bbbbbbbbbbb, 0, 9, n",
            "0, 9, n, f, 0, 0, n",
            "0, 0, w, f, 9, 0, w",
            "9, 0, e, f, 0, 0, e"
    )
    fun move(inX: Int, inY: Int, inD: String, input: String, exX: Int, exY: Int, exD: String) {
        val rover = Rover(inX, inY, inD)
        rover.move(input)
        assertEquals(exX, rover.x)
        assertEquals(exY, rover.y)
        assertEquals(exD, rover.d)
    }

}

