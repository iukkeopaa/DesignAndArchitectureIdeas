//package MpscLinkedQueue;
//
//public class MpscLinkedQueueTest {
//    public static void main(String[] args) {
//        MpscLinkedQueue<Integer> queue = new MpscLinkedQueue.MpscLinkedQueue<Integer>();
//
//        // ģ�����������߳�
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
//        // ģ�ⵥ���������߳�
//        new Thread(() -> {
//            Integer value;
//            while ((value = queue.poll()) != null) {
//                System.out.println("Consumed: " + value);
//            }
//        }).start();
//    }
//}