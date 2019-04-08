package com.antumn.a8_28testslideblack.tcp;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;

/**
 * Created by wuqi on 2018/9/7.
 */
public class TCPClient {

    Socket socket = null;
    //连接的服务器IP
    private String ip;
    //连接服务器的端口
    private int port;
    String myMessage = null;

    //构造 方法
    public TCPClient(String ip,int port) {
        super();
        this.ip = ip;
        this.port = port;
        //创建线程并启动
        Client client = new Client();
        Thread thread = new Thread(client);
        thread.start();
    }

    class Client implements Runnable{

        @Override
        public void run() {
            String s = "122";
            byte[] bytes = s.getBytes();
            " "
            bytes.
            //创建对象
            Log.i("wq","client is run");
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            try {
                //实例化对象
                socket = new Socket(ip, port);
                socket.setKeepAlive(true);
                socket.setSoTimeout(100*1000);
                //获取socket的输出流，以便将内容发送给服务器
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"US-ASCII"));
                //从控制台接收内容
                //bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //要发送的内容
                String message = null;
                //一直循环
                while (true) {
                    //提示输入
                    System.out.print("wq 我说：");
                    //如果用户要发送的信息不为null
                    if(/*(message = bufferedReader.readLine()) != null || */!TextUtils.isEmpty(myMessage)){
                        //检查用户是否要退出
                      /*  if("exit".equals(message.toLowerCase()) || "close".equals(message.toLowerCase())){
                            System.out.println("wq退出会话成功");
                            break;
                        }*/
                        //向服务器写入要发送的消息
                        bufferedWriter.write(myMessage);
                        //注意：这里是重点，换行
                        bufferedWriter.newLine();
                        //清空缓存将信息发送过去
                        bufferedWriter.flush();
                        myMessage = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("wq", "socket error1 = " + e);
            } finally {
                //关闭流
                try {
                    if(bufferedWriter != null)
                        bufferedWriter.close();
                    if(bufferedReader != null)
                        bufferedReader.close();
                    if(socket != null)
                        socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("wq", "socket error2 = " + e);
                }
            }

        }

    }

    public void write(String  content) {
        myMessage = content;
    }

    public static void main(String[] args) {
        new TCPClient("202.105.193.109",12000);
    }
}
