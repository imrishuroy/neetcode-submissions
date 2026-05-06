class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            int[] arr = new int[26];
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                arr[ch - 'a']++;
            }
            
            StringBuilder st = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                st.append("#").append(arr[i]);
            }
            
            String key = st.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }



    // O(n * k(logk))
    public List<List<String>> groupAnagrams2(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = sortStr(str);

            map.putIfAbsent(key, new ArrayList<>());

            map.get(key).add(str);

            // if (map.containsKey(key)) {
            //     map.get(key).add(str);
            // } else {
            //     map.put(key, new ArrayList<>());
            //     map.get(key).add(str);
            // }
        }

        List<List<String>> result = new ArrayList<>();

        System.out.println(map);

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    public String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }
}
