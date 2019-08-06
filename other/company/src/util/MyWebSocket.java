package util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//��ע������ָ��һ��url
@ServerEndpoint("/websocket")
public class MyWebSocket {

	// Set���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	public static Set<MyWebSocket> set = new HashSet<MyWebSocket>();

	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;

	/**
	 * ���ӽ����ɹ����õķ���
	 * 
	 * @param session
	 *            ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		set.add(this); // ����set��
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.println(format.format(date)+" �������Ӽ��룡");
	}

	/**
	 * ���ӹرյ��õķ���
	 */
	@OnClose
	public void onClose() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		set.remove(this); // ��set��ɾ��
		System.out.println(format.format(date)+" ��һ���ӹرգ�");
	}

	/**
	 * ��������ʱ����
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("��������");
		error.printStackTrace();
	}

	public static void sendMessageAll(String message1, String message2) {
		for (MyWebSocket item : set) {
			item.sendMessage(message1, message2);
		}
	}

	public void sendMessage(String message1, String message2) {
		try {
			session.getBasicRemote().sendText(message1);
			session.getBasicRemote().sendText(message2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
