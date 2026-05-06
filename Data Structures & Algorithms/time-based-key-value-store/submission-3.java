class TimeMap {

    Map<String, List<Pair>> timeMap; 

    public TimeMap() {
        this.timeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        
        timeMap.putIfAbsent(key, new ArrayList<>());
        List<Pair> list = timeMap.get(key);
        list.add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) return "";
        List<Pair> list = timeMap.get(key);
        int left = 0;
        int right = list.size() - 1;

        String value = "";

        // binary search to get the value
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).time <= timestamp) {
                value = list.get(mid).value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return value;
     }

    class Pair {
        int time;
        String value;

        public Pair(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }
}
