/**
 * Created by jianming.xiao on 9/28/14.
 */
public class Subset {
    public static void main(String[] args) {
        String outputCountString = args[0];
        int outputCount = Integer.parseInt(outputCountString);

        RandomizedQueue<String> queue = new RandomizedQueue<String>(outputCount);
        int size = 0;
        while (!StdIn.isEmpty()) {
            int randomIndex = StdRandom.uniform(size);
            if (randomIndex < outputCount) {
                queue.enqueue(StdIn.readString());
            }
            size++;
        }

        for (int i = 0; i < outputCount; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
