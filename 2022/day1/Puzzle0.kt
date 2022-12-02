package day1

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
	val score = Files.readAllLines( Path.of( "./2022/day1/input" ) )
		.map { line ->
			line.split(" ")
				.map {
					when (it) {
						"X" -> "A"
						"Y" -> "B"
						"Z" -> "C"
						else -> it
					}
				}
				.run { this[1] to this[0] }
		}
		.sumOf {
			when ( it ) { // played - opponent
				"A" to "B", "B" to "C", "C" to "A" -> 0
				"A" to "C", "B" to "A", "C" to "B" -> 6
				else -> 3
			} + when ( it.first ) {
				"A" -> 1
				"B" -> 2
				"C" -> 3
				else -> error("")
			}
		}

	println( score ) // 11906
}
