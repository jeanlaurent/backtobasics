# Remove duplicate characters in a given string keeping only the first occurrences. For example, if the input is ‘tree traversal’ the output will be ‘tre avsl’. - See more at: http://www.ardendertat.com/2012/01/09/programming-interview-questions/#sthash.H0JW5qet.dpuf

removeDuplicate = (source) ->
  chars = []
  result = ""
  for char in source
    unless chars[char]?
      chars[char] = 1
      result += char
  result

console.log removeDuplicate "Tree Traversal"
