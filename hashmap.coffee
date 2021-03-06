class HashItem
  constructor: (@key, @value) ->

class HashMap
  constructor: (@hashFunction, @initialSize = 101, @loadFactor = .75) ->
    @size = @findNearestPrime @initialSize
    @load = 0
    @flatStorage = new Array @size

  hash: (key) ->
    @hashFunction(key) % @size

  add: (key, value) ->
    if @insert key, value, @flatStorage
        @load++
        @checkLoad()

  delete: (key) ->
    index = @hash key
    return false unless @flatStorage[index]?
    rank = rank for item, rank in @flatStorage[index] when item.key == key
    return false unless rank?
    @flatStorage[index].splice(rank - 1, 1)
    @load--
    true

  insert: (key, value, storage) ->
    index = @hash key
    if storage[index]?
      indexFound = i for item, i in storage[index] when item.key == key
      if indexFound?
        storage[index][indexFound] = new HashItem(key, value)
        return false
      else
        storage[index].push new HashItem(key, value)
        return true
    else
      storage[index] = [ new HashItem(key, value) ]
      return true

  get: (key) ->
    index = @hash key
    return undefined unless @flatStorage[index]?
    found = item for item in @flatStorage[index] when item.key == key
    found?.value

  checkLoad: () ->
    return unless @load > @size * @loadFactor
    newStorage = [] # this could be done in place, by flagging the moved element, and cleaning them aftewards
    previousSize = @size
    @size = @findNearestPrime @size * 2
    for index in [0...previousSize]
      if @flatStorage[index]?
        @flatStorage[index].forEach (item) => @insert item.key, item.value, newStorage
    @flatStorage = newStorage

  findNearestPrime: (someNumber) ->
    isPrime = (target) ->
      if target - 1 > 2
        for divisor in [Math.floor(Math.sqrt(target)+1)..2]
          return false if (target % divisor == 0)
      true
    number = someNumber
    number++ until isPrime number
    number

hashFunctionForString = (string) ->
    hash = 11
    hash = hash * 31 + string.charCodeAt(index) for index in [0...string.length]
    hash

# tinytest lib

assertEquals = (object1, object2) ->
  throw "#{object1} is not equal to #{object2}" unless object1 == object2

assertTrue = (object1) ->
  throw "#{object1} is not true" unless object1 == true

assertUndefined = (object1) ->
  throw "#{object1} is not undefined" unless object1 == undefined

should = (name, callback) ->
  try
    callback()
  catch error
    console.log "fail : should #{name}"
    if error.stack?
      console.log "      >> #{error.stack}"
    else
      console.log "      >> #{error}"
    return
  console.log " ok  : should #{name}"

# tests

should "add new element", ->
  hashMap = new HashMap(hashFunctionForString, 10)
  hashMap.add("foo","bar")
  assertEquals hashMap.get("foo"), "bar"

should "return undefined when element is not found", ->
  hashMap = new HashMap hashFunctionForString
  assertUndefined hashMap.get "foo"

should "add not erase new element" , ->
  hashMap = new HashMap(hashFunctionForString, 10)
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEquals hashMap.get("foo"), "bar"
  assertEquals hashMap.get("bar"), "foo"

should "erase element if key is same", ->
  hashMap = new HashMap hashFunctionForString
  hashMap.add("foo","bar")
  hashMap.add("foo", "qix")
  assertEquals hashMap.get("foo"), "qix"

should "avoid collision when collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEquals hashMap.get("foo"), "bar"
  assertEquals hashMap.get("bar"), "foo"

should "replace the right element eventhoug a collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("foo", "qiz")
  assertEquals hashMap.get("foo"), "qiz"

should "checkload somehow" , ->
  randomId = (hundred) -> Math.floor(Math.random() * 10) + (hundred + 1) * 100
  hashMap = new HashMap hashFunctionForString ,10
  hashMap.add("foo#{randomId(i)}", "bar#{i}") for i in [0...7]
  assertEquals(hashMap.load, 7)
#  console.log hashMap.flatStorage
#  console.log "------"
  hashMap.add("foo#{randomId(i)}", "bar#{i}") for i in [7..9]
  assertEquals(hashMap.size, 23)
  assertEquals(hashMap.load, 10)
#  console.log hashMap.flatStorage

should "grow load when adding item", ->
  hashMap = new HashMap hashFunctionForString ,10
  hashMap.add "foo", "bar"
  assertEquals hashMap.load, 1
  hashMap.add "bar", "bar"
  assertEquals hashMap.load, 2
  hashMap.add "qix", "bar"
  assertEquals hashMap.load, 3

should "not grow load when replacing item", ->
  hashMap = new HashMap hashFunctionForString ,10
  hashMap.add "foo", "bar"
  assertEquals hashMap.load, 1
  hashMap.add "foo", "qix"
  assertEquals hashMap.load, 1

should "find nearest prime", ->
  hashMap = new HashMap -> 7
  assertEquals(hashMap.findNearestPrime(10), 11)
  assertEquals(hashMap.findNearestPrime(100), 101)

should "delete an element", ->
  hashMap = new HashMap hashFunctionForString ,10
  hashMap.add "foo", "123"
  hashMap.add "bar", "456"
  hashMap.add "qix", "789"
  assertEquals hashMap.load, 3
  hasBeenDeleted = hashMap.delete "bar"
  assertTrue hasBeenDeleted
  assertUndefined hashMap.get "bar"
  assertEquals hashMap.load, 2
