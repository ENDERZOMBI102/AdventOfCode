const fs = require('fs')

let stats = {
	x: 0,
	depth: 0
}

function down(x) {
	stats.depth += x
}

function up(x) {
	stats.depth -= x
}

function forward(x) {
	stats.x += x
}


const input = fs.readFileSync('./input', 'utf8')
for ( let inp of input.split('\n') ) {
	let data = inp.split(' ')
	eval( data[0] + '(' + data[1] + ')' )
}

console.debug( stats, stats.x * stats.depth )