let stats = {
	x: 0,
	depth: 0,
	aim: 0
}

function down(x) {
	stats.aim += x
}

function up(x) {
	stats.aim -= x
}

function forward(x) {
	stats.x += x
	stats.depth += stats.aim * x
}

const input = require('fs').readFileSync('./input', 'utf8')
for ( let inp of input.split('\n') ) {
	let data = inp.split(' ')
	eval( data[0] + '(' + data[1] + ')' )
}

console.log( stats, stats.x * stats.depth )