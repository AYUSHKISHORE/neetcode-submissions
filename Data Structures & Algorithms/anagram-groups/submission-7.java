class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for(String s : strs){
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String keyString = new String(temp);
            if(!map.containsKey(keyString)){
                map.put(keyString,new ArrayList<>());
            }
            map.get(keyString).add(s);
        }

        
        List<List<String>> results = new ArrayList<>();
        for(String s : map.keySet()){
            results.add(map.get(s));
        }

        return results;
        

        //return new ArrayList<>(map.values());

    }
}

/*

🔹 Core methods
put(K key, V value) -> inserts or updates a key-value pair, returns old value or null
get(Object key) -> returns value for the key, or null if not present
containsKey(Object key) -> returns true if key exists, else false
containsValue(Object value) -> returns true if value exists, else false
remove(Object key) -> removes entry by key, returns removed value or null
size() -> returns number of key-value pairs
isEmpty() -> returns true if map has no elements
clear() -> removes all entries from the map
getOrDefault(key, defaultValue) -> returns value for key if present, otherwise returns defaultValue

🔹 Update / conditional methods
putIfAbsent(K key, V value) -> adds value only if key is not already present
replace(K key, V value) -> replaces value if key exists
replace(K key, V oldValue, V newValue) -> replaces only if old value matches
compute(K key, BiFunction) -> recomputes value for a key
computeIfAbsent(K key, Function) -> adds value only if key is missing
computeIfPresent(K key, BiFunction) -> updates only if key exists

🔹 Iteration methods
keySet() -> returns all keys as a Set
values() -> returns all values as a Collection
entrySet() -> returns all key-value pairs
Example:
for (Map.Entry<Integer, Integer> e : map.entrySet()) {    System.out.println(e.getKey() + " " + e.getValue());}


*/
