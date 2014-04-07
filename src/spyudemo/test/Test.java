package spyudemo.test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * @Company:www.shopin.net User: minxr Date: 11-9-9 Time: ����5:44
 */

public class Test extends TestCase {

    JedisPool pool;
    Jedis jedis;
    

    @Before
    public void setUp() {
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
        jedis = pool.getResource();
        jedis.auth("password");
    
        
    }

    /**
     * Redis�洢�������ַ��� CRUD
     */
    // @Test
    public void testBasicString() {
    	
    	System.out.println("testString");
        // -----�������----------
        jedis.set("name", "minxr");// ��key-->name�з�����value-->minxr
        System.out.println(jedis.get("name"));// ִ�н����minxr

        // -----�޸�����-----------
        // 1����ԭ���������޸�
        jedis.append("name", "jarorwar"); // ��ֱ�ۣ�����map ��jarorwar
                                            // append���Ѿ��е�value֮��
        System.out.println(jedis.get("name"));// ִ�н��:minxrjarorwar

        // 2��ֱ�Ӹ���ԭ��������
        jedis.set("name", "������");
        System.out.println(jedis.get("name"));// ִ�н����������

        // ɾ��key��Ӧ�ļ�¼
        jedis.del("name");
        System.out.println(jedis.get("name"));// ִ�н����null

        /**
         * mset�൱�� jedis.set("name","minxr"); jedis.set("jarorwar","������");
         */
        jedis.mset("name", "minxr", "jarorwar", "������");
        System.out.println(jedis.mget("name", "jarorwar"));

    }

    /**
     * jedis����Map
     */
    // @Test
    public void testMap() {
    	
    	System.out.println("testMap");
        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "minxr");
        user.put("pwd", "password");
        jedis.hmset("user", user);
        // ȡ��user�е�name��ִ�н��:[minxr]-->ע������һ�����͵�List
        // ��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ����
        List<String> rsmap = jedis.hmget("user", "name");
        System.out.println(rsmap);

        // ɾ��map�е�ĳ����ֵ
        // jedis.hdel("user","pwd");
        System.out.println(jedis.hmget("user", "pwd")); // ��Ϊɾ���ˣ����Է��ص���null
        System.out.println(jedis.hlen("user")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���1
        System.out.println(jedis.exists("user"));// �Ƿ����keyΪuser�ļ�¼ ����true
        System.out.println(jedis.hkeys("user"));// ����map�����е�����key [pwd, name]
        System.out.println(jedis.hvals("user"));// ����map�����е�����value [minxr,
                                                // password]

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }

    }

    /**
     * jedis����List
     */
    // @Test
    public void testList() {
    	
    	System.out.println("testList");
        // ��ʼǰ�����Ƴ����е�����
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        // ����key java framework�д����������
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        // ��ȡ����������jedis.lrange�ǰ���Χȡ����
        // ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    /**
     * jedis����Set
     */
    // @Test
    public void testSet() {
    	
    	System.out.println("testSet");
        // ���
        jedis.sadd("sname", "minxr");
        jedis.sadd("sname", "jarorwar");
        jedis.sadd("sname", "������");
        jedis.sadd("sanme", "noname");
        // �Ƴ�noname
        jedis.srem("sname", "noname");
        jedis.srem("sname", "test");
        System.out.println(jedis.smembers("sname"));// ��ȡ���м����value
        System.out.println(jedis.sismember("sname", "minxr"));// �ж� minxr
                                                                // �Ƿ���sname���ϵ�Ԫ��
        System.out.println(jedis.srandmember("sname"));
        System.out.println(jedis.scard("sname"));// ���ؼ��ϵ�Ԫ�ظ���
        
        
        jedis.sadd("sname1", "minxr");
        jedis.sadd("sname1", "test");
        
        System.out.println("sinter:"+jedis.sinter("sname", "sname1"));
        
        System.out.println("sdiff:"+jedis.sdiff("sname", "sname1"));
        
        System.out.println("sunion:"+jedis.sunion("sname", "sname1"));
    }
    // @Test
    public void testPs(){
    	System.out.println("test ps1111111111111111");
    	new Thread(){
    		public void run(){
    			pool.getResource().subscribe(new JedisPubSub() { 
    				
    				@Override
    				public void onUnsubscribe(String arg0, int arg1) {
    					// TODO Auto-generated method stub
    					System.out.println("onUnsubscribe");
    				}
    				
    				@Override
    				public void onSubscribe(String arg0, int arg1) {
    					// TODO Auto-generated method stub
    					System.out.println("onSubscribe");
    				}
    				
    				@Override
    				public void onPUnsubscribe(String arg0, int arg1) {
    					// TODO Auto-generated method stub
    					System.out.println("onPUnsubscribe");
    				}
    				
    				@Override
    				public void onPSubscribe(String arg0, int arg1) {
    					// TODO Auto-generated method stub
    					System.out.println("onPSubscribe");
    				}
    				
    				@Override
    				public void onPMessage(String arg0, String arg1, String arg2) {
    					// TODO Auto-generated method stub
    					System.out.println("onPMessage");
    				}
    				
    				@Override
    				public void onMessage(String arg0, String arg1) {
    					// TODO Auto-generated method stub
    					System.out.println("onMessage"+arg0+"   "+arg1);
    				}
    			}, "111");
    		}
    	}.start();
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	jedis.publish("111", "bbb");
    	
    }
    // @Test
    public void testPs1(){
    	System.out.println("newTest----------------");
    }
    // @Test
    public void test() throws InterruptedException {
    	
    	System.out.println("test++++++++++++++");
        // keys�д���Ŀ�����ͨ���
        System.out.println(jedis.keys("*")); // ���ص�ǰ�������е�key [sose, sanme, name,
                                                // jarorwar, foo, sname, java
                                                // framework, user, braand]
        System.out.println(jedis.keys("*name"));// ���ص�sname [sname, name]
        System.out.println(jedis.del("sanmdde"));// ɾ��keyΪsanmdde�Ķ��� ɾ���ɹ�����1
                                                    // ɾ��ʧ�ܣ����߲����ڣ����� 0
        System.out.println(jedis.ttl("sname"));// ���ظ���key����Чʱ�䣬�����-1���ʾ��Զ��Ч
        jedis.setex("timekey", 10, "min");// ͨ���˷���������ָ��key�Ĵ���Чʱ�䣩 ʱ��Ϊ��
        Thread.sleep(5000);// ˯��5���ʣ��ʱ�佫Ϊ<=5
        System.out.println(jedis.ttl("timekey")); // ������Ϊ5
        jedis.setex("timekey", 5, "min"); // ��Ϊ1�������ٿ�ʣ��ʱ�����5��
        System.out.println(jedis.ttl("timekey")); // ������Ϊ5
        System.out.println(jedis.exists("key"));// ���key�Ƿ����
//        System.out.println(jedis.rename("timekey", "time111"));
        System.out.println(jedis.get("timekey"));// ��Ϊ�Ƴ�������Ϊnull
        System.out.println(jedis.get("time")); // ��Ϊ��timekey ������Ϊtime ���Կ���ȡ��ֵ
                                                // min

        // jedis ����
        // ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
        jedis.del("a");// ��������ݣ��ټ������ݽ��в���
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //�����������
        System.out.println(jedis.lrange("a", 0, -1));    	
    }
    

    
    
    @After
    public void destory() {
        pool.returnResource(jedis);// ʹ����֮�����ӷ��ص����ӳ���
        pool.destroy();// �������ӳ��е���������
    }
    
   
    
    public static void main(String[] args){
    	Jedis jedis=new Jedis("127.0.0.1", 6379);
    	jedis.lpush("1", "1");
    	
    	System.out.println(jedis.lpop("1"));    	
    }
}
