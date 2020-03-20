package com.exercise.ssm_crud.bean;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用的返回类
 * 涉及get、put等各种操作
 * 需要发送给浏览器的状态码等
 * @author 为所欲为
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	//状态码，比如返回100为成功，200为失败
	private int code;
	//提示信息
	private String msg;
	//用户返回给浏览器的数据，在前端页面中添加键值对
	private Map<String, Object> extend=new HashMap<String, Object>();
	
	//生成成功消息
	public static Message success() {
		Message result=new Message();
		result.setCode(100);
		result.setMsg("success");
		return result;
	}
	//生成失败消息
	public static Message fail() {
		Message result=new Message();
		result.setCode(200);
		result.setMsg("fail");
		return result;
	}
	
	//使业务代码能够链式操作，.add().add()的添加信息
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
}
