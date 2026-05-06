class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;

            left++;
            right--;
         
            // while (left < n && !Character.isLetterOrDigit(s.charAt(left)) ) {
            //     left++;
            // }

            // while (right > 0 && !Character.isLetterOrDigit(s.charAt(right)) ) {
            //     System.out.println(s.charAt(right));
            //     right--;
            // }


            // if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            // left++;
            // right--;
        }

        return true;
    }
}
