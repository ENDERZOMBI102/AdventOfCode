package day2

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
	val lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz"
	val uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

	val score = Files.readAllLines( Path.of( "./2022/day2/input" ) )
		.chunked(3)
		.sumOf { group ->
			group[0]
				.toCharArray()
				.toSet()
				.filter { it in group[1] && it in group[2] }
				.sumOf {
					if ( it.isUpperCase() )
						uppercaseAlphabet.indexOf(it) + 27
					else
						lowercaseAlphabet.indexOf(it) + 1
				}
		}

	println( score ) // 2703
}
