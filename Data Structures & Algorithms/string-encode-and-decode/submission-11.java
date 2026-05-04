class Solution {

    //i/p = ["geeks", "for", "geeks"]
    //o/p = ["geeks", "for", "geeks"]

    //encoded string 5#geeks3#for5#geeks

    //Important EdgeCase
    //i/p = ["geeksforgeeks", "coding"]
    //encoded string = 13#geeksforgeeks6#coding
    //So you can see here we can N digits for number, all first find # then get length

    public String encode(List<String> strs) {
        if(strs.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(s.length()).append("#").append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {

        if(str.equals(""))return new ArrayList<>();

        List<String> res = new ArrayList<>();
        int i = 0;
        while(i<str.length()){
            int j = i;
            while(str.charAt(j)!='#'){
                j++;
            }
            int length = Integer.parseInt(str.substring(i,j));
            j++;
            res.add(str.substring(j,j+length));
            i=j+length;
        }
        return res;

    }
}
