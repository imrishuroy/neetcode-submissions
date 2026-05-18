class Solution {
    public int carFleet(int target, int[] position, int[] speed) {

        // The main idea here is
        // cars cannot overtake
        // so if a faster car behind catches a slower car ahead, they become one fleet
        // we only care about time to reach target

        // time = target - position / speed

        int n = position.length;

        double[][] cars = new double[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }

        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        Stack<Double> stack = new Stack();

        for (double[] car : cars) {
            double time = car[1];

            // new fleet
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
        }

        return stack.size();
        
    }
}
