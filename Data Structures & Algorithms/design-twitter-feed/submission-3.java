class Twitter {
    /*
        Important PriorityQueue Logic
        PriorityQueue<Map.Entry<Integer, Integer> tweet = new PriorityQueue<>((a,b)-> Integer.compare(b.getKey(),a.getKey()));


        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b[0],a[0]));


        postTweet:   Time O(1),        Space O(1)
        follow:      Time O(1),        Space O(1)
        unfollow:    Time O(1),        Space O(1)
        getNewsFeed: Time O(T log T),  Space O(T)

        Overall storage: O(N + R) which is twitter functiom
        N = nos of tweets
        R = nos of followers



    */


    private Map<Integer,Set<Integer>> follow;
    private Map<Integer, List<int[]>> tweet;
    private int time;

    public Twitter() {
        follow = new HashMap<>();
        tweet = new HashMap<>();
        time = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        tweet.putIfAbsent(userId, new ArrayList<>());
        tweet.get(userId).add(new int[]{time, tweetId});
        time++;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b[0],a[0]));

        if(tweet.containsKey(userId)){
            for(int[] t : tweet.get(userId)){
                maxHeap.offer(t);
            }
        }

        if(follow.containsKey(userId)){
            for(int followedId : follow.get(userId)){
                if(tweet.containsKey(followedId)){
                    for(int[] t : tweet.get(followedId)){
                        maxHeap.offer(t);
                    }
                }
            }
        }

        // Get latest 10 tweets
        while (!maxHeap.isEmpty() && result.size() < 10) {
            int[] curr = maxHeap.poll();
            result.add(curr[1]); // tweetId
        }

        return result;
            

        
    }
    
    //Stoing in each follower whom he follows as this will help to get the list in func getNewsFeed
    //suppose we need list of users whom user1 follows we can get that
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        if(follow.containsKey(followerId)){
            follow.get(followerId).add(followeeId);
            return;
        }
        Set<Integer> set = new HashSet<>();
        set.add(followeeId);
        follow.put(followerId,set);
        return;
    }

    //Stoing in each follower whom he follows as this will help to get the list in func getNewsFeed
    //suppose we need list of users whom user1 follows we can get that
    public void unfollow(int followerId, int followeeId) {

        if(!follow.containsKey(followerId)){
            return;
        }
        if (followerId == followeeId) {
            return;
        }

        follow.get(followerId).remove(followeeId);
        return;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */