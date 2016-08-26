package html5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

/*
 * websocket服务器
 * */
public class SimpleServer {

	public SimpleServer() throws IOException, NoSuchAlgorithmException{
		//创建serversocket,准备接受客户端连接
		ServerSocket ss=new ServerSocket(30000);
		//接受客户端链接
		Socket socket=ss.accept();
		//得到socket的输入流
		InputStream in=socket.getInputStream();
		//得到socket的输出流
		OutputStream out=socket.getOutputStream();
		
		byte[] buff=new byte[1024];
		int count=-1;
		String req="";
		
		//读取数据，建立与websocket的 握手
		count=in.read(buff);
		//将数据转化为字符串
		req=new String(buff, 0, count);
		System.out.println("握手请求:"+req);
		//获取websocket的key
		String secKey=getSecWebSocketKey(req);
		System.out.println("secKey:"+secKey);
		String response="HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
				+ "Sec-WebSocket-Accept: "+getSecWebSocketAccept(secKey)+"\r\n\r\n";
		System.out.println("secAccept:"+getSecWebSocketAccept(secKey));
		out.write(response.getBytes());
		//再次读取websocket发送过来的数据
		count=in.read(buff);
		System.out.println("接收到字节数:"+count);
		/*
		 * websocket发送过来的数据遵循一定的协议格式
		 * 其中第3-6个字节是数据掩码
		 * 从第7个字节开始才是真正有效的数据
		 * 因此程序使用第3-6个字节后后面的数据进行了处理
		 * */
		for (int i = 0; i <count-6; i++) {
			buff[i+6]=(byte) (buff[i%4+2]^buff[i+6]);
		}
		//显示读取到的数据
		System.out.println("接收到的内容："+new String(buff,6,count-6,"UTF-8"));
		
		//发送数据时，第一个字节必须与读取到的第一个字节相同
		byte[] pushHead=new byte[2];
		pushHead[0]=buff[0];
		String pushmsg="你的数据收到了，欢迎加入WebSocket!";
		//发送数据时，第二个字节记录发送数据的长度
		pushHead[1]=(byte) pushmsg.getBytes("UTF-8").length;
		//发送前两个字节
		out.write(pushHead);
		//发送有效数据
		out.write(pushmsg.getBytes("UTF-8"));
		//关闭socket
		socket.close();
		//关闭serversocket
		ss.close();
		
	}
	
	//获取websocket请求的seckey
	private String getSecWebSocketKey(String req){
		//构建正则表达式，获取sec-websocket-key:后面的内容
		Pattern p=Pattern.compile("^(Sec-WebSocket-Key:).+",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
		Matcher m=p.matcher(req);
		if(m.find()){
			//提取 Sec-WebSocket-Key
			String foundstring =m.group();
			return foundstring.split(":")[1].trim();
		}else {
			return null;
		}
	}
	//根据websocket请求的seckey计算secaccpet
	private String getSecWebSocketAccept(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		//C930ED3C-49EF-4C81-B6A1-D1A001B127B0     258EAFA5-E914-47DA-95CA-C5AB0DC85B11
		String guid="258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		key+=guid;
		MessageDigest md=MessageDigest.getInstance("SHA-1");
		md.update(key.getBytes("ISO-8859-1"), 0, key.length());
		byte[] sha1hash=md.digest();
		BASE64Encoder encode=new BASE64Encoder();
		return encode.encode(sha1hash);
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		new SimpleServer();
	}

}
