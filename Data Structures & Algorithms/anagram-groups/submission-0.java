class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = sortString(str);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> str : map.values()) {
            result.add(str);
        }

        return result;
    }

    public String sortString(String str) {
        char[] chArray = str.toCharArray();
        Arrays.sort(chArray);
        String result = new String(chArray);
        return result;
    }
}
