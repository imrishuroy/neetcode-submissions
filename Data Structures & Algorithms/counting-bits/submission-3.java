class Solution {
    
    
    public int[] countBits(int n) {
        int[] result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }

        // for (int i = 0; i <= n; i++) {
        //     result[i] = count1Bits(i);
        // }

        return result;
    }

    private int count1Bits(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }
}
