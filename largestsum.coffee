#Given an array of integers (positive and negative) find the largest continuous sum. - See more at: http://www.ardendertat.com/2011/09/24/programming-interview-questions-3-largest-continuous-sum/#sthash.HePYjMm3.dpuf

largestSum = (source) ->
  sum = 0
  maxSum = 0
  for number in source
    sum = Math.max(sum + number, number)
    maxSum = Math.max(maxSum, sum)
  maxSum

console.log largestSum [1,2,3,-4,4,6] #12
console.log largestSum [1,2,3,-6,4,1] #6
console.log largestSum [-1,-2,-3,-6,-4,-1] #0
console.log largestSum [-3,1,2,3] #6
