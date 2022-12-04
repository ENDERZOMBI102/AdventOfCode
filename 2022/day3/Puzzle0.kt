package day3

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
	val score = Files.readAllLines( Path.of( "./2022/day3/input" ) )
		.map { line ->
			line.split(",")
				.map {
					it.split("-")
						.run { this[0].toInt() .. this[1].toInt() }
				}
				.run { this[0] to this[1] }
		}
		.count { pair -> pair.first.all { it in pair.second } || pair.second.all { it in pair.first } }

	println( score ) // 509
}
