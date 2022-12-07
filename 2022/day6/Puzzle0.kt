package day6

import java.nio.file.Files
import java.nio.file.Path

fun main() {
	val score = Files.readAllLines( Path.of( "./2022/day6/input" ) )
		.run {
			val root = mutableMapOf( "/" to 0 )
			var currentPath = ""
			var index = 0

			while ( index < this.size ) {
				val parts = this[index++].split(" ")

				when ( parts[1] ) {
					"cd" -> currentPath = when {
						parts[2] == ".." -> currentPath.removeSuffix("/").substringBeforeLast("/") + "/"
						parts[2].startsWith("/") -> parts[2]
						else -> "$currentPath${parts[2]}/"
					}
					"ls" -> {
						while ( index < this.size && !this[index].startsWith("$") ) {
							val ( meta, name ) = this[index++].split(" ")

							if ( meta[0].isDigit() ) // file
								root["$currentPath$name"] = meta.toInt()
							else
								root["$currentPath$name/"] = 0
						}
					}
				}
			}
			root
		}
		.also { root ->
			root.filter { it.key.endsWith("/") }
				.forEach { entry ->
					root.filter { it.key.startsWith( entry.key ) && !it.key.endsWith( "/" ) }
						.map { root[entry.key] = root[entry.key]!! + it.value }
				}
		}
		.toList()
		.sumOf { ( key, value ) -> if ( key.endsWith("/") && value < 100000 ) value else 0 }

	println( score ) // 1723892
}
