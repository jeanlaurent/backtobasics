euler1 = -> #O(n)
  multiples = []

  for i in [1...1000]
    if (i % 3 == 0)
      multiples.push i
    else if (i % 5 == 0)
      multiples.push i

  multiples.reduce(((n,n1) -> n+n1),0)

euler1v2 = -> #O(n)
  multiples = []

  for i in [1...1000]
    if (i % 3 == 0) or (i % 5 == 0)
      multiples.push i

  multiples.reduce(((n,n1) -> n+n1),0)

euler1v3 = -> #O(n)
  sum = 0
  for i in [1...1000]
    if (i % 3 == 0) or (i % 5 == 0)
      sum += i
  sum


euler2 = -> # O(log(n)
  n1 = 0
  n2 = 1
  end = false
  sum = 0
  until end
    n3 = n1 + n2
    if n3 < 1000 * 1000 * 4
      sum += n3 if (n3 % 2 == 0)
      n1 = n2
      n2 = n3
    else
      end = true
  sum

euler3 = -> # O(log(n)/ log(log(n))
  target = 600851475143
  biggest = -1
  divisor = 2
  while target > 1
    while (target % divisor == 0)
      if divisor > biggest
        biggest = divisor
      target /= divisor
    divisor++
  biggest

euler4 = -> # O(n^2)
  biggest = -1
  isPalindrome = (number) ->
    string = "#{number}"
    for i in [0..string.length / 2]
      if string[i] != string[string.length - 1 - i]
        return false
    true
  for i in [100..999]
    for j in [100..999]
      if j > i
        product = i * j
        if isPalindrome(product) and product > biggest
          biggest = product
  biggest

euler5Naive = (number)->
  found = false
  until found
    number++
    found = false
    console.log "trying with #{number}"
    for i in [1..20]
      found = (number % i) == 0
      break unless found
    return number if found

findFactor = (target) ->
  prime = []
  divisor = 2
  while target > 1
    while (target % divisor == 0)
      if prime[divisor]?
        prime[divisor]++
      else
        prime[divisor] = 1
      target /= divisor
    divisor++
  return prime

euler5 = ->
  maxFactors = []
  for number in [2..20]
    factors = findFactor number
    for factor,count of factors
      if maxFactors[factor]?
        if maxFactors[factor] < count
          maxFactors[factor] = count
      else
        maxFactors[factor] = count
  number = 1
  for factor, count of maxFactors
    number *= Math.pow(factor, count)
  return number

euler6 = ->
  sumOfSquare = 0
  squareOfSum = 0
  for i in [1..100]
    sumOfSquare += i*i
    squareOfSum += i
  squareOfSum *= squareOfSum
  squareOfSum - sumOfSquare

isPrime = (target) ->
  if target - 1 > 2
    for divisor in [target-1..2]
      return false if (target % divisor == 0)
  true

euler7 = ->
  #BRUTE FORCE !
  number = 2
  primeFoundCount = 0
  until primeFoundCount == 10001
    if isPrime number
      primeFoundCount++
      console.log "#{number} is a prime number ##{primeFoundCount}"
    number++
  return --number

euler8 = ->
  biggestChain = "none"
  biggestProduct = -1
  digits = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450"
  console.log
  stringLength = 13
  length = digits.length - stringLength
  for index in [0..length]
    numberAsString = digits.substring(index, index + stringLength)
    product = 1
    chain = ""
    for char in numberAsString
      product *= +char
      if product > biggestProduct
        biggestChain = numberAsString.split("").join(" x ")
        biggestProduct = product
  return "#{biggestChain} = #{biggestProduct}"

euler9 = ->
  for a in [1..1000]
    for b in [1..1000]
      if a < b
        c = Math.sqrt(a*a + b*b)
        console.log "#{a}, #{b}, #{c}"
        if (a+b+c) == 1000
          console.log "found"
          return a*b*c

isFastPrime = (target) ->
  if target - 1 > 2
    for divisor in [Math.floor(Math.sqrt(target)+1)..2]
      return false if (target % divisor == 0)
  true

euler10 = ->
  sum = 0
  for i in [2..2000000]
    if isFastPrime i
      console.log "#{i} is a prime number (current sum is #{sum})"
      sum += i
  sum

euler11 = ->
  grid = '''
  08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
  49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
  81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
  52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
  22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
  24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
  32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
  67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
  24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
  21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
  78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
  16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
  86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
  19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
  04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
  88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
  04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
  20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
  20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
  01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48
  '''
  numbers = []
  strings = grid.split('\n')
  for string in strings
    chars = string.split(' ')
    for char in chars
      numbers.push +char
  width = 20
  height = 20
  biggestOperation = ""
  biggest = -1

  value = (xx,yy) ->
    numbers[yy*width + xx]
  recordBiggest = (number, operation) ->
      if number > biggest
        biggest = number
        biggestOperation = operation
  for y in [0...height]
    for x in [0..width - 4]
      product = value(x,y) * value(x+1,y) * value(x+2,y) * value(x+3,y)
      recordBiggest product, "#{value(x,y)} * #{value(x+1,y)} * #{value(x+2,y)} * #{value(x+3,y)} "
  for x in [0...width]
    for y in [0..height-4]
      product = value(x,y) * value(x,y+1) * value(x,y+2) * value(x,y+3)
      recordBiggest product, "#{value(x,y)} * #{value(x,y+1)} * #{value(x,y+2)} * #{value(x,y+3)} "
  for x in [0..width-4]
    for y in [0..width-4]
      product = value(x,y) * value(x+1,y+1) * value(x+2,y+2) * value(x+3,y+3)
      recordBiggest product, "#{value(x,y)} * #{value(x+1,y+1)} * #{value(x+2,y+2)} * #{value(x+3,y+3)} "
  for x in [3...width]
    for y in [0..height-4]
      product = value(x,y) * value(x-1,y+1) * value(x-2,y+2) * value(x-3,y+3)
      recordBiggest product, "#{value(x,y)} * #{value(x-1,y+1)} * #{value(x-2,y+2)} * #{value(x-3,y+3)} "
  console.log biggestOperation
  return biggest

console.log euler11()
