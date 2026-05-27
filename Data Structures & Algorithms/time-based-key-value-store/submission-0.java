//Approach 1
/*
Time Complexity
 -> get -> O(1)
 -> set -> O(logn)

Space Complexity 
    O(m * n)
    m = nos of keys
    n = nos of timestamps

 Note - for a single timestamp we have single valu

Binary Search is used for set as it will reduce time complexity from O(n) to O(logn)

*/

class TimeMap {

    class Pair{
        int timestamp;
        String value;

        Pair(int timestamp, String value){
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        Pair p = new Pair(timestamp, value);
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(p);
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)){
            return "";
        }

        List<Pair> lst = map.get(key);
        int left = 0;
        int right = lst.size() - 1;

        String result = "";
        while(left<=right){
            int mid = left + (right - left)/2;

            if(lst.get(mid).timestamp<=timestamp){
                result = lst.get(mid).value;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return result;

    }
}



//Approach 2 - Failed because of TLE
//- Set function it taking around O(n)
/*
    Time Complexity
        Get -> O(1)
        Set -> O(n)
    
    Space Complexity 
        O(m * n)
        m = nos of keys
        n = nos of timestamps

*/

/*
class TimeMap {
    Map<String,Map<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new HashMap<Integer, String>());
        map.get(key).putIfAbsent(timestamp, value);
    }
    
    public String get(String key, int timestamp) {

        if(!map.containsKey(key)){
            return "";
        }

        Map<Integer, String> internalMap = map.get(key);

        int seen = 0;
        for(int time : internalMap.keySet()){
            if(time<=timestamp){
                seen = Math.max(seen, time);
            }
        }

            if(internalMap.containsKey(seen)){
                return internalMap.get(seen);
            }
            return "";
        
        
    }
}
*/




