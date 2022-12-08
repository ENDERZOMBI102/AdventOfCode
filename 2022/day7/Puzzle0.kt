package day7

import java.nio.file.Files
import java.nio.file.Path

fun main() {
	val score = Files.readAllLines( Path.of( "./2022/day7/input" ) )
		.map { line -> line.toList().map { Integer.valueOf("$it") } }
		.run {
			var visible = this[0].size * 2 + ( this.size * 2 - 4 )

			for ( y in 1 until this.size - 1 )
				for ( x in 1 until this.size - 1 )
					if (
						( -x until 0 )      .map { this[y][x + it] }.all { it < this[y][x] } || // left
						( 1 until size - x ).map { this[y][x + it] }.all { it < this[y][x] } || // right
						( -y until 0 )      .map { this[y + it][x] }.all { it < this[y][x] } || // top
						( 1 until size - y ).map { this[y + it][x] }.all { it < this[y][x] }    // bottom
					) visible++

			visible
		}

	println( score ) // 1854
}
