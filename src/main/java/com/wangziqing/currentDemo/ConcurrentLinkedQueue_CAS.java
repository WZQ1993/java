package com.wangziqing.currentDemo;
import java.util.concurrent.atomic.AtomicReference;
/**
 * CAS实现并发链表
 * 
 * @author wzq
 *
 */
public class ConcurrentLinkedQueue_CAS<T> {
	private static class Node<T> {
		final T data;
		final AtomicReference<Node<T>> next;//无法改变引用指向，但是指向的值可以改变？

		Node(T data) {
			// TODO Auto-generated constructor stub
			this.data = data;
			next = new AtomicReference<Node<T>>();
		}
	}

	private AtomicReference<Node<T>> head = new AtomicReference<Node<T>>(new Node<T>(null));
	private AtomicReference<Node<T>> tail = head;

	public T put(T item) {
		Node<T> newTail = new Node<T>(item);
		while (true) {
			/**
			 * 保留现场。 要点：多线程执行会改变的值为tail，保留tail==oldtail
			 * 保留的oldtail也可能会更改，保留old.next()
			 */
			Node<T> oldTail = tail.get();
			Node<T> nullNode = oldTail.next.get();
			/**
			 * 两步CAS操作1.修改尾节点的下一个节点2.修改尾指针
			 * why顺序?
			 * 若为2-1，线程在执行2成功，1失败的情况下，其他线程无法帮助
			 */
			if (oldTail == tail.get()) {// 判断此时现场2没被破坏
				if (nullNode == null) {// 判断此时现场1没被破坏
					// 两步CAS
					if (oldTail.next.compareAndSet(null, newTail)) {
						tail.compareAndSet(oldTail, nullNode);
					}
				}else {//有其他线程已经执行完第一步CAS
					//帮助线程完成第二步,此时nullNode已经不是空节点，而是其他线程修改的新的尾节点
					tail.compareAndSet(oldTail, nullNode);
				}
			}
		}
	}
}
