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


console.log euler8()
