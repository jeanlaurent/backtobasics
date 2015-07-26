# Given an integer array, output all pairs that sum up to a specific value k. - See more at: http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/#sthash.NEtXF5Yh.dpuf

array = [10,2,8,7,5,4,9,1,6]

pairSum = (source, target) -> #0(n^2) (less than that but what the heck)
  matching = []
  for i in [0..source.length]
    for j in [i..source.length]
      unless i == j
        if source[i] + source[j] == target
          matching.push "#{source[i]},#{source[j]}"
  matching

pairSumWithSort = (source, target) -> #O(nLogN) (logn -> sort)
  matching = []
  source.sort (a,b) -> a - b #thank you javascript, for sorting number alphabetically
  start = 0
  finish = source.length - 1
  until start >= finish
    sum = source[start] + source[finish]
    start++ if sum < target
    finish-- if sum > target
    if sum == target
      matching.push "#{source[start]},#{source[finish]}"
      start++
  matching

pairWithHack = (source, target) -> #O(n)
  #no set in javascript by default, so we rely on array since we use number
  #Access in an array is still O(1), this won't work with 'real' object
  matching = []
  visited = []
  for number in source
    matchingNumber = target - number
    unless visited[matchingNumber]?
      visited[number] = true
    else
      matching.push "#{number},#{matchingNumber}"
  matching

console.log pairWithHack array, 10
