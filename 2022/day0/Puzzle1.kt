package day0

import java.nio.file.Files
import java.nio.file.Path

fun main( argv: Array<String> ) {
    val elfs = mutableListOf( 0 )
    val lines = Files.readAllLines( Path.of("./2022/day0/input") )
    var index = 0

    lines.forEach { line ->
        if ( line == "" ) {
            elfs.add(0)
            index += 1
        } else
            elfs[index] += Integer.valueOf( line )
    }

    println( listOf( elfs.removeMax(), elfs.removeMax(), elfs.removeMax() ).sum() ) // 211805
}

fun MutableList<Int>.removeMax(): Int = this.max().also { this@removeMax.remove(it) }
