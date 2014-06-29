package spyudemo.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.management.Query;

public class TestQueue {

	public LinkedList queueList=new LinkedList();	
	private SendThread[] sendThreadArr=null;
	
	public TestQueue(int threadNum){
		sendThreadArr=new SendThread[threadNum];
		for(int i=0;i<threadNum;i++){
			sendThreadArr[i]=new SendThread("thread:"+i+"         ");
			sendThreadArr[i].start();
		}
	}
	
	public void addInfo(Object objcet){
		synchronized (queueList) {
			queueList.add(objcet);
			queueList.notifyAll();
		}
	}
	
	public class SendThread extends Thread{
		public SendThread(String name){
			this.setName(name);
		}
		
		public void run(){
			while(true){
				synchronized (queueList) {
					System.out.println(this.getName()+"11111111");
					if(queueList.isEmpty()){
						try {
							System.out.println(this.getName()+"22222222222");
							queueList.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(!queueList.isEmpty()){
						Object obj=queueList.removeFirst();
						System.out.println(this.getName()+"33333333333333333333");
					}	
				}
			}
			
		}
	}
	
	public static void main(String[] args){
		TestQueue test=new TestQueue(10);
		
		for(int i=0;i<10;i++){
			test.addInfo(new Object());
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
