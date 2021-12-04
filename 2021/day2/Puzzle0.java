package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Puzzle0 {
	public static void main(String[] argv) {
		try {
			var lines = Files.readAllLines( Path.of("./2021/day2/input") );
			String gammaRate = "", epsilonRate = "";

			for ( var index = 0; index < lines.get(0).length(); index++ ) {
				int zeros = 0, ones = 0;
				for (var line : lines) {
					switch (line.charAt(index)) {
						case '0' -> zeros++;
						case '1' -> ones++;
					}
				}
				if (zeros > ones)
					gammaRate = gammaRate + "0";
				else
					gammaRate = gammaRate + "1";
			}

			for ( var index = 0; index < lines.get(0).length(); index++ ) {
				int zeros = 0, ones = 0;
				for (var line : lines) {
					switch (line.charAt(index)) {
						case '0' -> zeros++;
						case '1' -> ones++;
					}
				}
				if ( zeros < ones )
					epsilonRate = epsilonRate + "0";
				else
					epsilonRate = epsilonRate + "1";
			}


			System.out.println( Integer.parseInt( gammaRate, 2 ) * Integer.parseInt( epsilonRate, 2 ) );

		} catch (IOException e) { e.printStackTrace(); }
	}
}