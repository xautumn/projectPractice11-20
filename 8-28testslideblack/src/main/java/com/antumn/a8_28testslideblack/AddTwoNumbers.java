package com.antumn.a8_28testslideblack;

/**
 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 * Created by wuqi on 2018/9/6.
 */
public class AddTwoNumbers {

    //測試
    public static void main(String[] args) {
        //初始化类
        AddTwoNumbers linkList = new AddTwoNumbers();
        //创建链表
        ListNode l1 = new ListNode(1);
        ListNode p = l1;
        p.next = new ListNode(8);
        p = p.next;
        p.next = new ListNode(7);
        p = p.next;
        p.next = new ListNode(6);

        ListNode l2 = new ListNode(9);
        ListNode q = l2;
        q.next = new ListNode(9);
        q = q.next;
        q.next = new ListNode(9);
        q = q.next;
        q.next = new ListNode(9);

        ListNode re = linkList.addTwoNumbers(l1, l2);
        while (re != null) {
            System.out.println(re.val);
            re = re.next;
        }
    }

    //实现
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
