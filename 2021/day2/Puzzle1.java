package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Puzzle1 {
	public static void main(String[] argv) {
		try {
			var lines0 = Files.readAllLines( Path.of("./2021/day2/input") );
			var lines1 = List.copyOf( lines0 );
			int oxy;
			{
				var index = 0;
				while ( lines0.size() > 1 ) {
					int zeros = 0, ones = 0;

					for ( var line : lines0 ) {
						if ( line.charAt(index) == '0' )
							zeros++;
						else
							ones++;
					}

					int finalIndex = index;
					if ( ones != zeros ) {
						var tokeep = ones < zeros ? '0' : '1';
						lines0 = lines0.stream().filter(line -> line.charAt(finalIndex) == tokeep ).toList();
					} else {
						lines0 = lines0.stream().filter(line -> line.charAt(finalIndex) == '1' ).toList();
					}
					index++;
				}
				System.out.println( lines0.get(0) + " : " + ( oxy = Integer.valueOf( lines0.get(0), 2 ) ) );
			}
			int co2;
			{
				var index = 0;
				while ( lines1.size() > 1 ) {
					int zeros = 0, ones = 0;

					for ( var line : lines1 ) {
						if ( line.charAt(index) == '0' )
							zeros++;
						else
							ones++;
					}

					int finalIndex = index;
					if ( ones != zeros ) {
						var tokeep = ones > zeros ? '0' : '1';
						lines1 = lines1.stream().filter(line -> line.charAt(finalIndex) == tokeep ).toList();
					} else {
						lines1 = lines1.stream().filter(line -> line.charAt(finalIndex) == '0' ).toList();
					}
					index++;
				}
				System.out.println( lines1.get(0) + " : " + ( co2 = Integer.valueOf( lines1.get(0), 2 ) ) );
			}
			System.out.println( oxy * co2 );
		} catch (IOException e) { e.printStackTrace(); }
	}
}