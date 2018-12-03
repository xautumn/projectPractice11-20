package com.antumn.a8_28testslideblack.tcp;

import android.net.http.HttpResponseCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by wuqi on 2018/9/7.
 */
public class TCPServer {

    public int port;

    //构造方法
    public TCPServer(int port) {
        super();
        this.port = port;
        //创建线程并启动
        Server server = new Server();
        Thread thread = new Thread(server);
        thread.start();
    }

    class Server implements Runnable{

        @Override
        public void run() {

            //Volley volley = new Volley();
            //创建对象
            //new HttpsURLConnection();
            ServerSocket serverSocket = null;
            Socket socket = null;
            BufferedReader reader =null;
            try {
                //创建tcp连接
                serverSocket = new ServerSocket(port);
                //提示服务端启动
                System.out.println("端口号为"+port+"的服务启动成功,等待客户端连接");
                //监听客户端请求
                socket = serverSocket.accept();
                //获取字节输入流（二进制流）
                InputStream inputStream = socket.getInputStream();
                //将字节流转换为字符流（字节流编码转换后字符流）
                reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
                //获取IP
                InetAddress inetAddress = serverSocket.getInetAddress();
                //接收到的信息
                String message = null;
                //一直循环获取消息
                while (true) {
                    //如果接收到的消息不为Null
                    if((message = reader.readLine()) != null){
                        //如果接收到的消息为close或exit，则退出对话
                        if("close".equals(message.toLowerCase()) || "exit".equals(message.toLowerCase())){
                            System.out.println(inetAddress.getHostAddress()+" 的用户已退出会话");
                            break;
                        }
                        //输出会话的内容
                        System.out.println(inetAddress.getHostAddress()+" 的用户说："+message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //关闭流
                try {
                    if(reader != null)
                        reader.close();
                    if(socket != null)
                        socket.close();
                    if(serverSocket != null)
                        serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        //启动服务端
        new TCPServer(12000);
    }

}
