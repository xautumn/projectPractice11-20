package com.antumn.a8_28testslideblack;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.antumn.a8_28testslideblack.tcp.TCPClient;
import com.antumn.a8_28testslideblack.tcp.TCPServer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private TCPClient tcpClient;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View viewById = findViewById(R.id.tv);
        View write = findViewById(R.id.write);
        viewById.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Log.i("wq", "start socket ");
                tcpClient = new TCPClient("172.28.180.73",12000);
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wq", "write  ");
                tcpClient.write("i want to write mes" + i);
                i++;
            }
        });
        String path = Environment.getExternalStorageDirectory().getPath();
        Log.i("wq", "path = " + path);
        File file = new File(path);
        file.delete();


    }

    //测试
    private int[] l = new int[2];

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    l[0] = i;
                    l[1] = j;
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     示例：
     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出：7 -> 0 -> 8
     原因：342 + 465 = 807
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    private void test() {
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(0);
        Solution solution = new Solution();
        solution.addTwoNumbers(listNode1,listNode2);

        List<Object> objects = new ArrayList<>();
        objects.get(1);
        objects.add("");
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode listNode = new ListNode(0);


            return listNode;
        }
    }

}
