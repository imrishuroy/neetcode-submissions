class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            int[] arr = new int[26];

            for (char ch : str.toCharArray()) {
                arr[ch - 'a']++;
            }

            StringBuilder st = new StringBuilder();

            for (int i = 0; i < 26; i++) {
                st.append(arr[i]);
                st.append("#");
            }

            String key = st.toString();

            System.out.println(key);



            // String key = sortStr(str);
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
