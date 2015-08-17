class HashItem
  constructor: (@key, @value) ->

class HashMap
  constructor: (@hashFunction) ->
    @storage = []

  add: (key, value) ->
    index = @hashFunction key
    @storage[index] = new HashItem(key, value) # this should be an array in case of collision

  get: (key) ->
    index = @hashFunction key
    return @storage[index].value

hashCodeForString = (string) ->
    hash = 7
    for index in [0...string.length]
      hash = hash * 31 + string.charCodeAt(index)
    return hash

assertEqual = (object1, object2) ->
  throw "#{object1} is not equal to #{object2}" unless object1 == object2
  return object2

should = (name, callback) ->
  try
    callback()
  catch error
    console.log error
    console.log "should #{name} : fail"
  console.log "should #{name} : ok"

should "add new element", () ->
  hashMap = new HashMap(hashCodeForString)
  hashMap.add("foo","bar")
  assertEqual hashMap.get("foo"), "bar"

should "add not erase new element" , () ->
  hashMap = new HashMap(hashCodeForString)
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEqual hashMap.get("foo"), "bar"
  assertEqual hashMap.get("bar"), "foo"
