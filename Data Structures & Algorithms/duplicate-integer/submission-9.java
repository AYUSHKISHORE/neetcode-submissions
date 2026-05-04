class Solution {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }
}

//different function of set 
/*
1) add(E e) -> returns true if element was added, false if it already existed

2) contains(Object o) -> returns true if element exists, else false

3) remove(Object o) -> returns true if element was removed, false if it was not present

4) size() -> returns number of elements in the set

5) isEmpty() -> returns true if set has no elements, else false

6) clear() -> removes all elements from the set

7) iterator() -> returns an iterator to traverse the set
    Iterator<Integer> it = set.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }

8) addAll(Collection c) -> returns true if set changed (elements added), else false

9) removeAll(Collection c) -> returns true if set changed (elements removed), else false
    Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    Set<Integer> other = new HashSet<>(Arrays.asList(3, 4));
    set.removeAll(other);
    System.out.println(set); // [1, 2]

10) retainAll(Collection c) -> returns true if set changed (kept only common elements), else false

    Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    Set<Integer> other = new HashSet<>(Arrays.asList(3, 4, 5, 6));

    set.retainAll(other);

    System.out.println(set); // [3,4]

    Only 3 and 4 are common in both sets - 1 and 2 are removed

11) containsAll(Collection c) -> returns true if all elements of c exist in the set, else false

12) toArray() -> returns an array containing all elements of the set


Note - 
E e → used when adding elements
Object o → used when checking/removing elements
add(E e) -> means add(Integer e) - Java enforces type safety when inserting elements.

Q) Why contains(Object o) and remove(Object o)?

    Because Java allows checking/removing using any object, not just type E.

    Example:
    Set<Integer> set = new HashSet<>();
    set.add(10);

    System.out.println(set.contains(10));     // true
    System.out.println(set.contains("10"));   // false (no error!)

*/


