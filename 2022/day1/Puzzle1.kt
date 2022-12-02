package day1

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
	val score = Files.readAllLines( Path.of( "./2022/day1/input" ) )
		.map { line ->
			line.split(" ").run {
				this[0] to this[1]
			}
		}
		.sumOf { round -> // opponent - wanted result
			when ( round.second ) {
				"X" -> when ( round.first ) { "A" -> 3; "B" -> 1; "C" -> 2; else -> error("") } + 0  // lose
				"Y" -> when ( round.first ) { "A" -> 1; "B" -> 2; "C" -> 3; else -> error("") } + 3  // draw
				"Z" -> when ( round.first ) { "A" -> 2; "B" -> 3; "C" -> 1; else -> error("") } + 6  // win
				else -> error("")
			}
		}

	println( score ) // 11186
}
