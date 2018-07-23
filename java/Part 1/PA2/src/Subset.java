/**
 * Created by jianming.xiao on 9/28/14.
 */
public class Subset {
    public static void main(String[] args) {
        String outputCountString = args[0];
        int outputCount = Integer.parseInt(outputCountString);
        if (outputCount == 0) return;

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        int queueSize = queue.size() > outputCount ? outputCount : queue.size();
        for (int i = 0; i < queueSize; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
