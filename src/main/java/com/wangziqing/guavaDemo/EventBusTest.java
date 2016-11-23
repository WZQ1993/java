package com.wangziqing.guavaDemo;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
/*
 * EventBusTest本身是事件发布者，也是事件监听者（处理者）
 */
public class EventBusTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建事件总线
		EventBus eventBus=new EventBus();
		//注册事件监听者
		eventBus.register(new EventBusTest());
		//向事件总线发布事件
		eventBus.post(new MyEvent("事件1"));
	}	
	//MyEvent事件处理方法
	@Subscribe public void dealWithMyEvent(MyEvent event){
		System.out.println("正在处理事件："+event.message);
	}
}
/*
 * 事件
 */
class MyEvent{
	MyEvent(String message){
		this.message=message;
	}
	String message;
}