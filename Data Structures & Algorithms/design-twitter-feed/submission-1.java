class Twitter {
    class Tweet {
        int userId;
        int tweetId;
        int timestamp;

        public Tweet(int userId, int tweetId, int timestamp) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }

    Map<Integer, Set<Integer>> followerMap;
    Map<Integer, Set<Tweet>> tweetsMap;
    int timestampCounter;

    public Twitter() {
        followerMap = new HashMap<>();
        tweetsMap = new HashMap<>();
        timestampCounter = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweetsMap.putIfAbsent(userId, new HashSet<>());

        Tweet tweet = new Tweet(userId, tweetId, timestampCounter++);

        tweetsMap.get(userId).add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);

        Set<Integer> followers = followerMap.get(userId);

        if (followers != null) {
            for (int follower : followers) {
                pq.addAll(tweetsMap.get(follower));
            }
        }

        Set<Tweet> currUsetTweets = tweetsMap.get(userId);
        if (currUsetTweets != null) {
            pq.addAll(tweetsMap.get(userId)); // add current user tweet also
        }

        List<Integer> tweets = new ArrayList<>();

        int index = 0;
        while (!pq.isEmpty() && index < 10) {
            tweets.add(pq.poll().tweetId);
            index++;
        }

        return tweets;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        followerMap.putIfAbsent(followerId, new HashSet<>());

        followerMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (followerMap.containsKey(followerId)) {
            Set<Integer> followers = followerMap.get(followerId);
            followers.remove(followeeId);
        }
    }
}
