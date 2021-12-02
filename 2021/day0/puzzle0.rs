use std::fs::File;
use std::io::prelude::*;

fn main() -> std::io::Result<()> {
	let mut file = File::open("test")?;
	let mut contents = String::new();
	file.read_to_string(&mut contents)?;

	let mut depths: Vec<i32> = Vec::new();
	let mut raw_depths: Vec<String> = contents.split(' ').map(|s| s.to_string()).collect();

	for line in raw_depths {
		depths.append( line.parse().unwrap() )
	}

	let mut count = 0;
	let mut last_depth = depths.remove(0);

	for depth in depths {
		count += ( depth > last_depth ) as i32;
	}

	println!("{}", count);

	Ok(())
}