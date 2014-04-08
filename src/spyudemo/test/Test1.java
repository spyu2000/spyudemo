package spyudemo.test;

import com.alibaba.fastjson.JSON;


public class Test1 {

	 public static void main(String[] args){
	    	
	    	
	    	User user=new User();
	    	user.setId(1);
	     	user.setName("name");
	    	String test=JSON.toJSONString(user);
	    	System.out.println(test);
	    	
	    	User user1=JSON.parseObject(test, User.class);
	    	
	    	System.out.println(user1.getName()+"");
	    	
	    }
}
