nums = []

for line in open('./input', 'r'):
    nums.append( int( line.replace('\n', '') ) )

for i in nums:
    for j in nums:
        for y in nums:
            if i + j + y == 2020:
                print( i * j * y )