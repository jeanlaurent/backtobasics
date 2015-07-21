# There is an array of non-negative integers. A second array is formed by shuffling the elements of the first array and deleting a random element. Given these two arrays, find which element is missing in the second array. - See more at: http://www.ardendertat.com/2012/01/09/programming-interview-questions/#sthash.O5ROTbyu.dpuf

findMissing = (longArray, shortArray) -> #O(n^2x)
# does not work with duplicates
  for char in longArray
    for target in shortArray
      found = char == target
      break if found
    return char unless found

findMissingWithSum = (longArray, shortArray) -> #0(n)
  #may overflow...
  longSum = 0
  longSum += value for value in longArray
  shortSum = 0
  shortSum += value for value in shortArray
  return longSum - shortSum

findMissingWithHash = (longArray, shortArray) -> #0(n)
  map = []
  for value in longArray
    unless map[value]?
      map[value] = 1
    else
      map[value]++
  map[value]-- for value in shortArray
  for key, value of map
    return key if value == 1


#source = [1,2,3,7,0,6,9]
#missing = [2,3,7,0,9,1]

#console.log findMissing source, missing

source = [1,2,2,3]
missing = [2,3,1]

console.log findMissingWithSum source, missing
