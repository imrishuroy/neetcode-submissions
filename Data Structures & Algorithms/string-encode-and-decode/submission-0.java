class Solution {

    public String encode(List<String> strs) {
        StringBuilder strB = new StringBuilder();
        for (String str : strs) {
            strB.append(str.length()).append("#").append(str);        
        }

        System.out.println(strB.toString());
        return strB.toString();
    }

    public List<String> decode(String str) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < str.length()) {
            int j = i;
            System.out.println("current j " + j);

            while (str.charAt(j) != '#') {
                System.out.println("now j " + j);
                j++;
            }
            int len = Integer.parseInt(str.substring(i, j));
            j++; // skip '#'

            ans.add(str.substring(j, j + len));
            i = j + len;
        }

        return ans;
    }
}
