class TimeMap {

    class Pair {
        String value;
        int timestamp;

        public Pair(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private Map<String, ArrayList<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {
            ArrayList<Pair> set = map.get(key);

            int left = 0;
            int right = set.size() - 1;
            String res = "";

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (set.get(mid).timestamp <= timestamp) {
                    res = set.get(mid).value;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return res;

        } else {
            return "";
        }
    }
}
