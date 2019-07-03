package com.lwj.project;

import com.lwj.project.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectAmpqApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 创建转换器、消息队列、绑定
     */
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("ampqAdmin.exchange"));
//        System.out.println("转换器创建完成！");

//        amqpAdmin.declareQueue(new Queue("ampqAdmin.queue",true));
//        System.out.println("消息队列创建完成！");

        amqpAdmin.declareBinding(new Binding("ampqAdmin.queue",Binding.DestinationType.QUEUE,"ampqAdmin.exchange","amqpAdmin.test",null));


    }

    /**
     * 1.点对点
     */
    @Test
    public void contextLoads() {
        //object 默认当成消息体，只要传入发送的对象，自动发送
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是一个测试消息");
        map.put("data", Arrays.asList("hello",123,true));
        rabbitTemplate.convertAndSend("exchange.direct","test",new Book("西游","11"));
    }

    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("test");
        System.out.println(o);
        System.out.println(o.getClass());
    }


}
