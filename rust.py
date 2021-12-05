import os
import sys


os.chdir( os.path.dirname( sys.argv[1] ) )
filename = os.path.basename( sys.argv[1] )
executable = f'{filename[:-3]}.exe'

if os.path.exists( executable ):
	os.remove( executable )

os.system(
	f'rustc {filename} --edition 2021'
)
if os.path.exists( executable ):
	os.system(
		executable
	)
