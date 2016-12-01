package com.jacoulin.date_2016_12_01;

/**
 * Created by Jacoulin on 2016/12/1.
 */
public class Ex1 {
    public static class Node{
        public int value;
        public Node next = null;
        Node(int value){
            this.value = value;
        }
        Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
        public void setValue(int value){
            this.value = value;
        }
    }

    public static Node reverseLinkList(Node head){
        if (null == head) {
            return head;
        }
        Node pre = head;
        Node cur = head.next;
        Node nex;
        while (null != cur) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }

        head.next = null;
        head = pre;

        return head;
    }

    private static void printLinkList(Node head) {
        Node p = head;
        StringBuffer sb = new StringBuffer();
        while (p.next != null){
            sb.append(p.value);
            sb.append("->");
            p = p.next;
        }
        sb.append(p.value);
        System.out.println(sb);
    }

    public static void main( String[] args ){
        int[] arr = {1,3,5,7,2,4};
        Node head = createLinkList(arr);
        printLinkList(head);
        reverseLinkList(head);
        printLinkList(head);
    }
    private static Node createLinkList(int[] arr) {
        // TODO Auto-generated method stub
        Node[] linkArr = new Node[arr.length];
        for (int i=0; i<arr.length; i++){
            linkArr[i] = new Node(arr[i]);
        }
        for (int i=0; i<arr.length; i++){
            linkArr[i].setValue(arr[i]);
            if (i == arr.length - 1){
                linkArr[i].next = null;
            }
            else{
                linkArr[i].next = linkArr[i+1];
            }
        }
        return linkArr[0];
    }
}
