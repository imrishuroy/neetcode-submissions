class Twitter {

    // Represents a tweet
    class Tweet {
        int tweetId;
        int timestamp;

        Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }

    /*
     * Node stored inside the Priority Queue.
     *
     * Besides the tweet itself, we also remember:
     * 1. Which user's tweet list it belongs to.
     * 2. The index of this tweet inside that list.
     *
     * This lets us push the next older tweet from the same user
     * after removing the current newest one.
     */
    class PQNode {
        Tweet tweet;
        int userId;
        int index;

        PQNode(Tweet tweet, int userId, int index) {
            this.tweet = tweet;
            this.userId = userId;
            this.index = index;
        }
    }

    // follower -> followees
    private final Map<Integer, Set<Integer>> following;

    // user -> list of tweets (oldest -> newest)
    private final Map<Integer, List<Tweet>> userTweets;

    private int timestamp;

    public Twitter() {
        following = new HashMap<>();
        userTweets = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {

        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new Tweet(tweetId, timestamp++));

        // userTweets
        //         .computeIfAbsent(userId, k -> new ArrayList<>())
        //         .add(new Tweet(tweetId, timestamp++));

    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<PQNode> pq =
                new PriorityQueue<>(
                        (a, b) -> Integer.compare(
                                b.tweet.timestamp,
                                a.tweet.timestamp));

        // Add current user's latest tweet
        addLatestTweet(userId, pq);

        // Add latest tweet of every followee
        Set<Integer> followees = following.get(userId);

        if (followees != null) {
            for (int followee : followees) {
                addLatestTweet(followee, pq);
            }
        }

        List<Integer> feed = new ArrayList<>();

        while (!pq.isEmpty() && feed.size() < 10) {

            PQNode current = pq.poll();

            feed.add(current.tweet.tweetId);

            /*
             * Push the next older tweet from the same user.
             */
            if (current.index > 0) {

                List<Tweet> tweets = userTweets.get(current.userId);

                pq.offer(
                        new PQNode(
                                tweets.get(current.index - 1),
                                current.userId,
                                current.index - 1));
            }
        }

        return feed;
    }

    private void addLatestTweet(int userId,
                                PriorityQueue<PQNode> pq) {

        List<Tweet> tweets = userTweets.get(userId);

        if (tweets == null || tweets.isEmpty()) {
            return;
        }

        int lastIndex = tweets.size() - 1;

        pq.offer(
                new PQNode(
                        tweets.get(lastIndex),
                        userId,
                        lastIndex));
    }

    public void follow(int followerId, int followeeId) {

        if (followerId == followeeId) {
            return;
        }

        following
                .computeIfAbsent(followerId, k -> new HashSet<>())
                .add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        Set<Integer> followees = following.get(followerId);

        if (followees != null) {
            followees.remove(followeeId);
        }
    }
}