package com.antumn.a8_28testslideblack.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wuqi on 2018/9/7.
 */
public class TCPClient {

    //连接的服务器IP
    private String ip;
    //连接服务器的端口
    private int port;
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
            //创建对象
            Socket socket = null;
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            try {
                //实例化对象
                socket = new Socket(ip, port);
                //获取socket的输出流，以便将内容发送给服务器
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"US-ASCII"));
                //从控制台接收内容
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //要发送的内容
                String message = null;
                //一直循环
                while (true) {
                    //提示输入
                    System.out.print("我说：");
                    //如果用户要发送的信息不为null
                    if((message = bufferedReader.readLine()) != null){
                        //检查用户是否要退出
                        if("exit".equals(message.toLowerCase()) || "close".equals(message.toLowerCase())){
                            System.out.println("退出会话成功");
                            break;
                        }
                        //向服务器写入要发送的消息
                        bufferedWriter.write(message);
                        //注意：这里是重点，换行
                        bufferedWriter.newLine();
                        //清空缓存将信息发送过去
                        bufferedWriter.flush();

                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭流
                try {
                    if(bufferedWriter != null)
                        bufferedWriter.close();
                    if(bufferedReader != null)
                        bufferedReader.close();
                    if(socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        new TCPClient("127.0.0.1",12000);
    }
}
