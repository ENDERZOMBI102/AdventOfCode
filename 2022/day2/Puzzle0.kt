package day2

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
	val lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz"
	val uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

	val score = Files.readAllLines( Path.of( "./2022/day2/input" ) )
		.map { line -> line.substring( 0 until line.length / 2 ) to line.substring( line.length / 2 ) }
		.sumOf { pair ->
			pair.first
				.toCharArray()
				.toSet()
				.filter { it in pair.second }
				.sumOf {
					if ( it.isUpperCase() )
						uppercaseAlphabet.indexOf(it) + 27
					else
						lowercaseAlphabet.indexOf(it) + 1
				}
		}

	println( score ) // 7795
}
