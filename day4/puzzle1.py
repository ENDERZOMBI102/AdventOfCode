class Passport:
	byr: str = ''
	iyr: str = ''
	eyr: str = ''
	hgt: str = None
	hcl: str = None
	ecl: str = None
	pid: str = ''
	cid: str = ''
	data: str = None

	def __init__( self, data: str ):
		self.data = data
		buf = data.replace( '\n', ' ' ).strip().split( ' ' )
		for i in buf:
			if ':' in i:
				setattr( self, i.split( ':' )[ 0 ], i.split( ':' )[ 1 ] )
			else:
				print( buf )

	def isValid( self ) -> bool:
		if not ( ('byr' in self.data) and ('iyr' in self.data) and ('eyr' in self.data) and ('hgt' in self.data) and ('hcl' in self.data) and ('ecl' in self.data) and ('pid' in self.data) ):
			return False
		if not checkNumber(self.byr, 1920, 2002, 4):
			return False
		elif not checkNumber( self.iyr, 2010, 2020, 4 ):
			return False
		elif not checkNumber( self.eyr, 2020, 2030, 4 ):
			return False
		elif not self.checkHeight():
			return False
		elif not self.checkHair():
			return False
		elif self.ecl not in ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']:
			return False
		elif ( not self.pid.isnumeric() ) and ( len(self.byr) != 9 ):
			return False
		return True

	def checkHair( self ) -> bool:
		if self.hcl is None:
			return False
		if not self.hcl.startswith( '#' ):
			return False
		if len( self.hcl[ 1: ] ) != 6:
			return False
		if not self.hcl[ 1: ].isalnum():
			return False
		for i in self.hcl[ 1: ]:
			if i not in '0123456789abcdef':
				return False
		return True

	def checkHeight( self ) -> bool:
		if self.hgt is None:
			return False
		if not ( self.hgt[:-2] ).isnumeric():
			return False
		height = int( self.hgt[:-2] )
		if self.hgt.endswith('cm'):
			if ( height <= 150 ) or ( height >= 193 ):
				return False
		elif self.hgt.endswith('in'):
			if ( height <= 59 ) or ( height >= 76 ):
				return False
		else:
			return False
		return True


def checkNumber( num: str, minn: int, maxx: int, lenght: int ) -> bool:
	if not num.isnumeric():
		return False
	nam = int( num )
	if ( nam <= minn ) or ( nam >= maxx ):
		return False
	return len( num ) == lenght


if __name__ == '__main__':
	buffer: str = ''
	valid: int = 0
	for line in open( 'input', 'r' ):
		if line == '\n':
			valid += 1 if Passport( buffer ).isValid() else 0
			buffer = ''
		else:
			buffer += line
	valid += 1 if Passport( buffer ).isValid() else 0
	print( valid )
