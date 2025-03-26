package MpscLinkedQueue;

import java.util.concurrent.atomic.AtomicReference;



class MpscLinkedQueue<T> {

    class Node<T> {
        T value;
        AtomicReference<Node<T>> next;

        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public MpscLinkedQueue() {
        Node<T> dummy = new Node<T>(null);
        head.set(dummy);
        tail.set(dummy);
    }

    public void offer(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> oldTail;
        do {
            oldTail = tail.get();
        } while (!tail.get().next.compareAndSet(null, newNode));
        tail.compareAndSet(oldTail, newNode);
    }

    public T poll() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            Node<T> oldHeadNext = oldHead.next.get();
            if (oldHeadNext == null) {
                return null;
            }
            newHead = oldHeadNext;
        } while (!head.compareAndSet(oldHead, newHead));
        return newHead.value;
    }
}

