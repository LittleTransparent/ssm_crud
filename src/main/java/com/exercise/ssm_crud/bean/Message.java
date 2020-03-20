package com.exercise.ssm_crud.bean;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ͨ�õķ�����
 * �漰get��put�ȸ��ֲ���
 * ��Ҫ���͸��������״̬���
 * @author Ϊ����Ϊ
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	//״̬�룬���緵��100Ϊ�ɹ���200Ϊʧ��
	private int code;
	//��ʾ��Ϣ
	private String msg;
	//�û����ظ�����������ݣ���ǰ��ҳ������Ӽ�ֵ��
	private Map<String, Object> extend=new HashMap<String, Object>();
	
	//���ɳɹ���Ϣ
	public static Message success() {
		Message result=new Message();
		result.setCode(100);
		result.setMsg("success");
		return result;
	}
	//����ʧ����Ϣ
	public static Message fail() {
		Message result=new Message();
		result.setCode(200);
		result.setMsg("fail");
		return result;
	}
	
	//ʹҵ������ܹ���ʽ������.add().add()�������Ϣ
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
}
