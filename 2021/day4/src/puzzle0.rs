use std::io::{BufRead, BufReader};
use std::fs::File;


type Canvas = Vec< Vec< i64 > >;


struct Point {
	x: usize,
	y: usize
}

impl Point {
	fn new( value: String ) -> Point {
		let ( a, b ) = value.split_once(",").unwrap();

		Self { x: a.parse().unwrap(), y: b.parse().unwrap() }
	}
}

struct Line {
	from: Point,
	to: Point,
}

impl Line {
	fn new( value: String ) -> Line {
		let ( a, b ) = value.split_once(" -> ").unwrap();

		Self {
			from: Point::new( a.to_string() ),
			to: Point::new( b.to_string() )
		}
	}

	fn draw(&self, canvas: &mut Canvas) -> () {
		if self.from.x == self.to.x {
			// y differs
			for y in self.from.y .. self.to.y + 1{
				println!( "{}, {}", self.to.x, y );
				canvas[ y ][ self.to.x ] += 1;
			}
		} else {
			// x differs
			for x in self.from.x .. self.to.x + 1 {
				println!( "{}, {}", x, self.from.y );
				canvas[ self.from.y ][ x ] += 1;
			}
		}
	}
}


fn main() -> Result< (), std::io::Error >  {
	let reader = BufReader::new(File::open("./../test").expect("Cannot open file"));
	let text_lines = reader.lines();

	let mut lines: Vec<Line> = Vec::new();

	for raw_line in text_lines {
		let line = Line::new( raw_line.unwrap() );
		if line.to.x == line.from.x || line.to.y == line.from.y {
			lines.push( line )
		}
	}

	// get canvas dimensions
	let mut max_y = 0;
	let mut max_x = 0;
	for line in &lines {
		if max_y < line.from.y {
			max_y = line.from.y
		}
		if max_y < line.to.y {
			max_y = line.to.y
		}
		if max_x < line.from.x {
			max_x = line.from.x
		}
		if max_x < line.to.x {
			max_x = line.to.x
		}
	}

	// create canvas
	let mut canvas: Canvas = Vec::new();

	for _y in 0 .. max_y + 1 {
		let mut vec: Vec<i64> = Vec::new();
		for _x in 0 .. max_x + 1 {
			vec.push(0);
		}
		canvas.push( vec )
	}

	// draw lines
	for line in &lines {
		line.draw( &mut canvas )
	}

	// print canvas
	for row in canvas {
		for int in row {
			if int > 0 {
				print!( " {} ", int )
			} else {
				print!( " . " )
			}
		}
		print!("\n")
	}

	Ok( () )

}