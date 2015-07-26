#Given an integer array, one element occurs even number of times and all others have odd occurrences. Find the element with even occurrences. - See more at: http://www.ardendertat.com/2012/01/09/programming-interview-questions/#sthash.Vd1SbaYj.dpuf

array = [1,1,2,2,3,3,3,4,4,5,5,6,6,6,6]

even = (source) ->
  map = []
  for number in source
    unless map[number]?
      map[number] = 1
    else
      map[number]++
  for index, value of map
    return index unless value % 2 == 0

console.log even array
