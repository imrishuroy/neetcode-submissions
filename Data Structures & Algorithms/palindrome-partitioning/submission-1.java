class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        backtrack(0, new ArrayList<>(), s, result);

        return result;
    }

    private void backtrack(int start, List<String> path, String s, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            String str = s.substring(start, end + 1);
            if (isPalindrome(start, end, s)) {
                path.add(str);
                backtrack(end + 1, path, s, result);
                path.remove(path.size() - 1);
            }
        }

    }

    private boolean isPalindrome(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
