import os
import sys


os.chdir( os.path.dirname( sys.argv[1] ) )
filename = os.path.basename( sys.argv[1] )
os.system(
	f'rustc {filename} --edition 2021'
)
os.system(
	f'{filename[:-3]}.exe'
)
