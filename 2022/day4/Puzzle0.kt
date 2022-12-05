package day4

import java.nio.file.Files
import java.nio.file.Path

fun main() {
	val score = Files.readAllLines( Path.of( "./2022/day4/input" ) )
		.run {
			val numLine = indexOf("") - 1
			val integer = { value: String -> value.takeIf { it.all( Char::isDigit ) }?.toInt() }

			// make grid
			val grid = mutableListOf<MutableList<Char>>()
			var x = 1

			while ( x < this[numLine].length ) {
				if ( this[numLine][x].isDigit() ) {
					var y = numLine
					val column = mutableListOf<Char>()
					while ( --y >= 0 && this[y].length > x )
						this[y][x].takeIf( Char::isLetter )?.also { column.add( it ) }
					grid.add( column )
				}
				x++
			}

			printGrid( grid )

			// pass into lines exec mode
			subList( numLine + 2, size )
				.map { line -> line.split(" ").mapNotNull { integer( it ) } }
				.forEach { ( amount, from, to ) ->
					grid[ to - 1 ] += grid[ from - 1 ].run { takeLast( amount ).also { removeAll( it ) }.reversed() }
				}
			grid.map { it.lastOrNull() ?: " " }.joinToString( separator = "" )
		}
	println( score ) //
}

fun printGrid( grid: List<List<Char>> ) {
	val string = buildString {
		val lineLength = grid.size * 4 + 1
		append( "${" ".repeat( lineLength - 1 )}\n".repeat( grid.maxOf { it.size } + 1 ) )
		val row = { num: Int -> lineLength * num }
		grid.forEachIndexed { index, column ->
			column.forEachIndexed { rowIndex, value ->
				val char = index * 4 + row( grid.size - 2 - rowIndex )
				setRange( char, char + 3, "[$value]" )
			}
			set( index * 4 + row( grid.size - 1 ) + 1, ( index + 1 ).toString()[0] )
		}
	}
	println(string)
}
