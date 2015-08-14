# http://www.ardendertat.com/2011/11/17/programming-interview-questions-16-anagram-strings/

countCharInString = (string) ->
    hashCount = []
    for char in string
      if hashCount[char]?
        hashCount[char] += 1
      else
        hashCount[char] = 1
    hashCount

areAnagram = (string1, string2) ->
  count1 = countCharInString string1
  count2 = countCharInString string2
  for letter,value of count1
    return false unless count2[letter] == value
  for letter,value of count2
    return false unless count1[letter] == value
  true

console.log areAnagram "hello world", "hillo wirld"
console.log areAnagram "eleven plus two", "twelve plus one"
