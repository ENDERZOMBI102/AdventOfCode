import scala.io.Source

object Puzzle0 {
	def main(args: Array[String] ): Unit = {
		var nums = Source.fromFile("./2021/day5/input")
			.getLines()
			.next()
			.split(",")
			.map(Integer.valueOf)
			.toList;
		var day = 0;

		println(s"Initial state $nums")
		while (day < 80) {
			day += 1;
			val max = nums.length - 1;

			for ( i <- 0 to max ) {
				if (nums(i) == 0) {
					nums = nums.updated(i, 6);
					nums = nums.appended(8);
				} else {
					nums = nums.updated(i, nums(i) - 1);
				}
			}

			println(s"After $day ${if (day == 1) "day" else "days"}: $nums")
		}
		println(s"There is ${nums.length} fishes")
	}
}
