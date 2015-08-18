class HashItem
  constructor: (@key, @value) ->

class HashMap
  constructor: (@hashFunction, @size = 100) ->
    @flatStorage = new Array(@size)

  hash: (key) ->
    score = @hashFunction(key) % @size

  add: (key, value) ->
    index = @hash key
    if @flatStorage[index]?
      indexFound = i for item, i in @flatStorage[index] when item.key == key
      if indexFound?
        @flatStorage[index][indexFound] = new HashItem(key, value)
      else
        @flatStorage[index].push new HashItem(key, value)
    else
      @flatStorage[index] = [ new HashItem(key, value) ]

  get: (key) ->
    index = @hash key
    return undefined unless @flatStorage[index]?
    found = item for item in @flatStorage[index] when item.key == key
    return undefined unless found?
    found.value


hashFunctionForString = (string) ->
    hash = 11
    hash = hash * 31 + string.charCodeAt(index) for index in [0...string.length]
    hash

# tinytest lib

assertEqual = (object1, object2) ->
  throw "#{object1} is not equal to #{object2}" unless object1 == object2

should = (name, callback) ->
  try
    callback()
  catch error
    console.log "fail : should #{name}"
    console.log "      >> #{error}"
    return
  console.log " ok  : should #{name}"

# tests

should "add new element", ->
  hashMap = new HashMap(hashFunctionForString, 10)
  hashMap.add("foo","bar")
  assertEqual hashMap.get("foo"), "bar"

should "return undefined when element is not found", ->
  hashMap = new HashMap hashFunctionForString
  assertEqual hashMap.get "foo", undefined

should "add not erase new element" , ->
  hashMap = new HashMap(hashFunctionForString, 10)
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEqual hashMap.get("foo"), "bar"
  assertEqual hashMap.get("bar"), "foo"

should "erase element if key is same", ->
  hashMap = new HashMap hashFunctionForString
  hashMap.add("foo","bar")
  hashMap.add("foo", "qix")
  assertEqual hashMap.get("foo"), "qix"

should "avoid collision when collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEqual hashMap.get("foo"), "bar"
  assertEqual hashMap.get("bar"), "foo"

should "replace the right element eventhoug a collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("foo", "qiz")
  assertEqual hashMap.get("foo"), "qiz"
