class Solution {

    // a car can not pass another car ahed of it
    // when a car catch up with the ahead car it will become a fleet
    // a car fleet is some not empty set of cars driving at the 
    // same position and same speed
    // a single car is also a car fleet

    // Intuation
    // Speed = Distance / Time
    // Time = Distance / Speed
    // In this context = target - position / speed

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];

        for (int i = 0; i < n; i++) {
            cars[i] = new int[]{position[i], speed[i]};
        }

        Arrays.sort(cars, (a, b) -> b[0] - a[0]);

        Stack<Double> stack = new Stack<>();


        for (int i = 0; i < n; i++) {
            double time = (double) (target - cars[i][0]) / cars[i][1];
            
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
        }

        return stack.size();

    }
}
