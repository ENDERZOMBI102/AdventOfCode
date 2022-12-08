package day7

import java.nio.file.Files
import java.nio.file.Path

fun main() {
	val score = Files.readAllLines( Path.of( "./2022/day7/test" ) )
		.map { line -> line.toList().map { Integer.valueOf("$it") } }
		.run {
			val scores = mutableListOf<Int>()

			for ( y in 1 until size - 1 )
				for ( x in 1 until size - 1 ) {
					val current = this[y][x]

					var upScore = 0
					for ( value in ( 0 until y ).map { this[it][x] } ) {
						upScore += 1
						if ( value >= current )
							break
					}

					var leftScore = 0
					for ( value in ( 0 until x ).map { this[y][it] } ) {
						leftScore += 1
						if ( value >= current )
							break
					}
					// FIXME: Do 2-1 -> should be 1 1 2 2 but is 2 1 2 3
					var downScore = 0
					for ( value in (  y + 1 until size ).map { this[it][x] } ) {
						downScore += 1
						if ( value >= current )
							break
					}

					var rightScore = 0
					for ( value in ( x + 1 until size ).map { this[y][it] } ) {
						rightScore += 1
						if ( value >= current )
							break
					}

					println("this[$y][$x] == ${current} -> upScore: $upScore, leftScore: $leftScore, downScore: $downScore, rightScore: $rightScore -> ${upScore * leftScore * downScore * rightScore}")
					scores.add( upScore * leftScore * downScore * rightScore )
				}
			scores
		}
		.max()

	println( score ) //
}
