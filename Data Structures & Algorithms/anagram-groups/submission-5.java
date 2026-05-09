class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = sortStr(str);
            // map.putIfAbsent(key, new ArrayList<>(key));
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }

        System.out.println(map);


        return new ArrayList<>(map.values());


    }

    private String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
