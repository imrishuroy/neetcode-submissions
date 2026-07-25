class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        int totalTank = 0;
        int totalCost = 0;
        int currTank = 0;
        int startStation = 0;

        // total gas >= total cost

        for (int i = 0; i < gas.length; i++) {
            int netGain = gas[i] - cost[i];

            totalTank += gas[i];
            totalCost += cost[i];
            currTank += netGain;

            if (currTank < 0) {
                startStation = i + 1;
                currTank = 0;
            }
        }

        return totalTank >= totalCost ? startStation : -1;

    }
}
