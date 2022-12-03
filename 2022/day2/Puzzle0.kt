package day2

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

fun main( argv: Array<String> ) {
	val charsL = "abcdefghijklmnopqrstuvwxyz"
	val charsU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

	val score = Files.readAllLines( Path.of( "./2022/day2/input" ) )
		.map { line -> line.substring( 0 until line.length / 2 ) to line.substring( line.length / 2 ) }
		.sumOf { pair ->
			pair.first
				.toCharArray()
				.toSet()
				.filter { it in pair.second }
				.sumOf {
					if ( it.isUpperCase() )
						charsU.indexOf(it) + 27
					else
						charsL.indexOf(it) + 1
				}
		}

	println( score ) // 7795
}
