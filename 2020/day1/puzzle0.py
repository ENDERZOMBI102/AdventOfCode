nums = []

for line in open('./input', 'r'):
    nums.append( int( line.replace('\n', '') ) )

for i in nums:
    for j in nums:
        if i + j == 2020:
            print( i * j )