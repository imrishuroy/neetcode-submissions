class Solution {

    public String encode(List<String> strs) {
        StringBuilder st = new StringBuilder();

        for (String str : strs) {
            st.append(str.length());
            st.append("#");
            st.append(str);
        }

        return st.toString();

    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < str.length()) {
            int j = index;

            while (str.charAt(j) != '#') {
                j++;
            }

            int length = Integer.parseInt(str.substring(index, j));
            index = j + 1;
            j = index + length;
            result.add(str.substring(index, j));
            index = j;
        }


        return result;
    }
}
