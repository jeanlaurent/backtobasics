#http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
theMatrix =
  [
    [13, 10,  1, 3, 5],
    [ 6, 18,100,10, 3],
    [31, 97, 88, 6, 5],
    [42, 51, 81,22,51],
    [10,999,103,42,67]
  ]

matrixSum = (matrix, x, y, sumWidth, sumHeight) ->
  width = matrix.length
  height = matrix[0].length
  sum = 0
  for i in [x...(x + sumHeight)]
    for j in [y...(y + sumWidth)]
      sum += matrix[i][j]
  sum

# 473
console.log matrixSum theMatrix, 1, 1, 3, 3
