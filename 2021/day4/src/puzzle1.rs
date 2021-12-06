#[warn(unused_must_use)]
use std::fmt::{Display, Formatter};
use std::io::{BufRead, BufReader, Write};
use std::fs::File;


type Canvas = Vec< Vec< i64 > >;


#[derive( Clone, PartialEq, Eq )]
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

impl Display for Point {
	fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
		f.write_fmt( format_args!( "Point(x={}, y={})", &self.x, &self.y ) )
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
		// both differ
		let mut mid_point: Point = self.from.clone();
		while mid_point != self.to {
			canvas[mid_point.y][mid_point.x] += 1;
			if mid_point.y < self.to.y {
				mid_point.y += 1;
			} else if mid_point.y > self.to.y {
				mid_point.y -= 1;
			}
			if mid_point.x < self.to.x {
				mid_point.x += 1;
			} else if mid_point.x > self.to.x {
				mid_point.x -= 1;
			}
		}
		canvas[mid_point.y][mid_point.x] += 1;
	}
}


fn main() -> Result< (), std::io::Error >  {
	let reader = BufReader::new( File::open("./../input").expect("Cannot open file") );
	let text_lines = reader.lines();

	let mut lines: Vec<Line> = Vec::new();

	for raw_line in text_lines {
		lines.push( Line::new( raw_line.unwrap() ) )
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

	let mut twos: i64 = 0;

	// write canvas to file
	let mut file = File::create("./../output").expect("Cannot open file");
	for row in canvas {
		for int in row {
			if int > 0 {
				if int >= 2 {
					twos += 1
				}
				write!( &mut file, "{}", int ).expect("Cannot write to file");
			} else {
				write!( &mut file, "." ).expect("Cannot write to file");
			}
		}
		write!( &mut file, "\n" ).expect("Cannot write to file");
	}

	println!( "Twos: {}", twos );

	Ok( () )

}