package day5

import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.asSequence

fun main() {
	val score = Files.readString( Path.of( "./2022/day5/input" ) )
		.toCharArray()
		.run {
			var markerIndex = 0
			for ( index in 0 until this.size - 14 )
				if ( this.slice( index  until index + 14 ).toSet().size == 14 ) {
					markerIndex = index + 14
					break
				}
			markerIndex
		}

	println( score ) // 3380
}
