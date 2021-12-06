import os
from pathlib import Path

for path in Path('.').rglob('*'):
	if not path.exists():
		continue
	
	if path.is_dir() and path.name == 'out':
		for child in path.rglob('*'):
			os.remove(child)
		os.remove( path )
	
	elif not path.is_dir():
		if path.name.endswith( ( '.exe', '.pdb', 'output' ) ):
			os.remove( path )
