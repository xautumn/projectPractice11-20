package com.antumn.a8_28testslideblack.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 支持并发多用户连接
 * Created by wuqi on 2019/3/22.
 */
public class TcpRealServer {
    private static final int PORT = 9999;
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null; // 创建一个线程池

    public static void main(String[] args) {
        new TcpRealServer();
    }

    public TcpRealServer() {
        try {
            server = new ServerSocket(PORT);
            mExecutorService = Executors.newCachedThreadPool(); // 实例化一个线程池
            System.out.println("服务器已启动，等待加入...");
            Socket client = null;
            while (true) {
                client = server.accept();
                // 把客户端放入客户端集合中
                mList.add(client);
                mExecutorService.execute(new Service(client)); // 开启一个新线程
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private String msg = "";

        public Service(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 客户端只要一连到服务器，便向客户端发送下面的信息。
                msg = "服务器" + this.socket.getInetAddress() + "加入；此时总连接：" + mList.size() + "（服务器发送）";
                this.sendmsg();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if ((msg = in.readLine()) != null) {
                        // 当客户端发送的信息为：exit时，关闭连接
                        if (msg.trim().equals("exit")) {
                            System.out.println("GAME OVER");
                            mList.remove(socket);
                            in.close();
                            msg = "服务器" + socket.getInetAddress() + "退出；此时总连接：" + mList.size() + "（服务器发送）";
                            socket.close();
                            this.sendmsg();
                            break;
                        } else {
                            // 接收客户端发过来的信息msg，然后发送给客户端。
                            msg = socket.getInetAddress() + "：" + msg + "（服务器发送）";
                            this.sendmsg();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 循环遍历客户端集合，给每个客户端都发送信息。
         */
        public void sendmsg() {
            // 在服务器上打印
            System.out.println(msg);
            // 遍历打印到每个客户端上
            int num = mList.size();
            for (int i = 0; i < num; i++) {
                Socket mSocket = mList.get(i);
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())), true);
                    out.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
