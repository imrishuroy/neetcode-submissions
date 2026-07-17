class MedianFinder {
    
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());
        
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }

        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}

// class MedianFinder {

//     PriorityQueue<Double> pq;

//     public MedianFinder() {
//         pq = new PriorityQueue<>();
//     }

//     public void addNum(int num) {
//         pq.add((double) num);
//     }

//     public double findMedian() {
//         // if one number in the stream return that
//         // what to do if even len in stream
//         // what to do if odd len in stream

//         int size = pq.size();

//         if (size == 1) {
//             return pq.peek();
//         } else if (size % 2 == 0) { // event
//             List<Double> temp = new ArrayList<>();
//             int n = (size / 2) + 1;

//             for (int i = 0; i < n; i++) {
//                 temp.add(pq.poll());
//             }

//             System.out.println(temp);

//             double median = temp.get(temp.size() - 1) + temp.get(temp.size() - 2);
//             pq.addAll(temp);
//             return  median / 2;

//         } else {
//             List<Double> temp = new ArrayList<>();
//             int n = (size / 2) + 1;

//             for (int i = 0; i < n; i++) {
//                 temp.add(pq.poll());
//             }

//             System.out.println(temp);

//             double median = temp.get(temp.size() - 1);
//             pq.addAll(temp);
//             return median;
//         }

//     }
// }
