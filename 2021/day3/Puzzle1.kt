package day3

import java.nio.file.Files
import java.nio.file.Path


class Board1( list: List< ArrayList< Int? > > ) : ArrayList< ArrayList< Int? > >( list ) {
	var win = false
}

fun main() {
	val boards = ArrayList<Board1>()
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

	fun checkRow(board: Board1, row: Int ): Boolean {
		return ! board[row].stream().anyMatch { it != null }
	}

	fun checkCol(board: Board1, col: Int ): Boolean {
		for ( row in board ) {
			if ( row[col] != null )
				return false
		}
		return true
	}

	fun replaceNums(board: Board1, num: Int ): Boolean {
		for ( i in 0 .. 4 ) {
			for ( y in 0 .. board[0].size - 1 ) {
				if ( board[i][y] == num )
					board[i][y] = null
			}
		}

		for ( i in 0 .. 4 ) {
			if ( checkRow( board, i ) || checkCol( board, i ) ) {
				board.win = true
				return true
			}
		}

		return false
	}

	val nums = toList(',')

	while ( lines.size > 0 ) {
		lines.removeAt(0)
		boards.add(
			Board1(
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
			if ( (! board.win ) && replaceNums( board, num!! ) ) {
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
			}
		}
	}
}