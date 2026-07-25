class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean firstFound = false;
        boolean secondFound = false;
        boolean thridFound = false;

        for (int[] triplet : triplets) {

            // ignore the triplets that exceeds the target,
            // since values only increase after merging,
            // we can never reduce them back
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }

            // check whether this valid triplet contributes one of the required target value
            if (triplet[0] == target[0]) {
                firstFound = true;
            }

            if (triplet[1] == target[1]) {
                secondFound = true;
            }

            if (triplet[2] == target[2]) {
                thridFound = true;
            }
        }

        return firstFound && secondFound && thridFound;
    }
}
