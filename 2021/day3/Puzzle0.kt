package day3

import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess


typealias Board0 = ArrayList< ArrayList< Int? > >

fun main() {
	val boards = ArrayList<Board0>()
	val lines = Files.readAllLines( Path.of("./2021/day3/input") )

	fun toList( splitter: Char = ' ' ): ArrayList<Int?> {
		val list = ArrayList<Int?>()
		for ( x in lines.removeAt(0).removeSuffix("\n").split(splitter) ) {
			try {
				list.add( Integer.valueOf(x) )
			} catch ( ignored: NumberFormatException ) {}
		}
		return list
	}

	fun checkRow(board0: Board0, row: Int ): Boolean {
		return ! board0[row].stream().anyMatch { it != null }
	}

	fun checkCol(board0: Board0, col: Int ): Boolean {
		for ( row in board0 ) {
			if ( row[col] != null )
				return false
		}
		return true
	}

	fun replaceNums(board0: Board0, num: Int ): Boolean {
		for ( i in 0 .. 4 ) {
			for ( y in 0 .. board0[0].size - 1 ) {
				if ( board0[i][y] == num )
					board0[i][y] = null
			}
		}

		for ( i in 0 .. 4 ) {
			if ( checkRow( board0, i ) || checkCol( board0, i ) )
				return true
		}

		return false
	}

	val nums = toList(',')

	while ( lines.size > 0 ) {
		lines.removeAt(0)
		boards.add(
			ArrayList(
				listOf(
					toList(),
					toList(),
					toList(),
					toList(),
					toList()
				)
			)
		)
	}

	for ( num in nums ) {
		for ( board in boards ) {
			if ( replaceNums( board, num!! ) ) {
				println(
					(
							board[0] +
							board[1] +
							board[2] +
							board[3] +
							board[4]
					).stream()
						.filter { it != null }
						.mapToInt { it!! }
						.sum() * num
				)
				exitProcess(0)
			}
		}
	}
}