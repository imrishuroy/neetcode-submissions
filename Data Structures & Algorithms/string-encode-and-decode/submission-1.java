class Solution {

    /*
     * =========================
     * ENCODE
     * =========================
     *
     * Intuition:
     * We need to convert a list of strings into a single string
     * in such a way that it can be decoded back without ambiguity.
     *
     * Problem with naive delimiter approach:
     * If we only use a delimiter like '#', strings themselves may
     * contain '#', which breaks decoding.
     *
     * Key Idea:
     * Use LENGTH PREFIXING.
     * For every string, store:
     *     <length>#<actual_string>
     *
     * Example:
     * ["neet", "code"]  →  "4#neet4#code"
     *
     * Why this works:
     * - Length tells us exactly how many characters to read
     * - '#' clearly separates length from content
     * - Works for ANY characters (including '#', UTF-8, emojis)
     */
    public String encode(List<String> strs) {
        StringBuilder strB = new StringBuilder();

        for (String str : strs) {
            // Append length of the string
            strB.append(str.length());

            // Append a delimiter to mark end of length
            strB.append("#");

            // Append the actual string
            strB.append(str);
        }

        return strB.toString();
    }

    /*
     * =========================
     * DECODE
     * =========================
     *
     * Intuition:
     * Read the encoded string sequentially.
     * For each segment:
     *   1. Parse the length (digits before '#')
     *   2. Read exactly 'length' characters as the original string
     *
     * Pointer explanation:
     * - i → start index of the length
     * - j → moves forward to find '#'
     *
     * Decoding steps:
     * "4#neet4#code"
     *
     * i = 0
     * j moves until '#'
     * substring(i, j) → "4" → length = 4
     * next 4 chars → "neet"
     * move i forward and repeat
     */
    public List<String> decode(String str) {
        List<String> ans = new ArrayList<>();
        int i = 0; // Pointer to the start of length

        while (i < str.length()) {

            // j is used to find the '#' delimiter
            int j = i;

            // Move j until we reach '#'
            // This extracts the full length (handles multi-digit lengths)
            while (str.charAt(j) != '#') {
                j++;
            }

            // Parse the length of the upcoming string
            int len = Integer.parseInt(str.substring(i, j));

            // Move j past '#'
            j++;

            // Extract the actual string of size 'len'
            ans.add(str.substring(j, j + len));

            // Move i to the start of the next encoded segment
            i = j + len;
        }

        return ans;
    }
}
