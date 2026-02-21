class Node{
        int data;
        Node next;
        Node(int data1,Node next1){
            this.data=data1;
            this.next=next1;
        }
        Node(int data1){
            this.data=data1;
            this.next=null;
        }
    }

class Solution{
    public Node insertAtHead(Node head, int newData){
        Node newNode=new Node(newData,head);
        return newNode;
    }
    public void printList(Node head){
        Node temp=head;
        while (temp!=null) {
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
}
public class basic {
    public static void main(String[] args) {
        Solution sol=new Solution();
        Node head=new Node(1);
        head.next=new Node(2);
        // int[] arr={1,2,3,4};
        // Node y=new Node(arr[0]);
        // System.out.println(y);
        // System.out.println(y.data);
        System.out.println("original List");
        sol.printList(head);
        head=sol.insertAtHead(head, 4);
        sol.printList(head);
    }
}
