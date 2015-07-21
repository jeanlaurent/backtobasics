#Find the first non-repeated (unique) character in a given string. - See more at: http://www.ardendertat.com/2011/11/14/programming-interview-questions-15-first-non-repeated-character-in-string/#sthash.rnXPFFOK.dpuf

find = (string) -> #O(n^2)
  for index in [0...string.length]
    for targetIndex in [0...string.length]
      unless index == targetIndex
        unique = string[index] != string[targetIndex]
        console.log "#{string[index]}(#{index}) vs #{string[targetIndex]}(#{targetIndex}) -> #{unique}"
        break unless unique
    return string[index] if unique

findWithHash = (string) -> #O(n)
  map = []
  for char in string
    unless map[char]?
      map[char] = 1
    else
      map[char]++
  for char, count of map
    return char if count == 1
  return "none"



console.log findWithHash "AACBBDEFCZFDFE"
