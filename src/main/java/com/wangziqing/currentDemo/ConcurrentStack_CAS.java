package com.wangziqing.currentDemo;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS完成非阻塞栈
 * 
 * @author wzq
 *
 */
public class ConcurrentStack_CAS<E> {
	// 数据用链表组织，所以只要保存头节点
	private AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = head.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!head.compareAndSet(oldHead, newHead));
		return oldHead.data;
	}

	public void push(E e) {
		Node<E> newHead = new Node<E>(e);
		Node<E> oldHead;
		do {
			// 这里面作为原子操作
			oldHead = head.get();
			newHead.next = oldHead;
		} while (!head.compareAndSet(oldHead, newHead));// CAS成功执行原子操作
	}

	/**
	 * 栈节点
	 * 
	 * @author wzq
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		T data;
		Node<T> next;
		Node<T> pre;

		Node(T data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
