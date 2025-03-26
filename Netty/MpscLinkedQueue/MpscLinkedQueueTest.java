//package MpscLinkedQueue;
//
//public class MpscLinkedQueueTest {
//    public static void main(String[] args) {
//        MpscLinkedQueue<Integer> queue = new MpscLinkedQueue.MpscLinkedQueue<Integer>();
//
//        // 模拟多个生产者线程
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                queue.offer(i);
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 10; i < 20; i++) {
//                queue.offer(i);
//            }
//        }).start();
//
//        // 模拟单个消费者线程
//        new Thread(() -> {
//            Integer value;
//            while ((value = queue.poll()) != null) {
//                System.out.println("Consumed: " + value);
//            }
//        }).start();
//    }
//}