class Solution {

    public String encode(List<String> strs) {
        int n = strs.size();

        StringBuilder st = new StringBuilder();

        // <length#<data>
        for (String str : strs) {
            st.append(str.length());
            st.append("#");
            st.append(str);
        }

        System.out.println(st.toString());

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

            int length = Integer.parseInt(str.substring(index, j)); // we are not including j as j now points ot '#'
            index = j + 1;
            j = index + length;
            result.add(str.substring(index, j));
            index = j; 
        }

        return result;
    }
}
