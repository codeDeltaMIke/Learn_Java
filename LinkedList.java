//LinkedList
class Node<T>{
    T data;
    Node<T> next;
    
    Node(T data){
        this.data=data;
        this.next = null;
    }
}


class LinkedList<T>{
    private Node<T> head;
    
    LinkedList(){
        this.head = null;
    }
    
    public void add(T data){
        Node<T> node = new Node<>(data);
        if(head == null) head = node;
        else{
            Node<T> newNode = head;
            while(newNode.next != null){
                newNode = newNode.next;
            }
            newNode.next = node;
        }
    }
    
    public void remove(T data){
        while(head != null && head.data.equals(data)){
            head = head.next;
        }
        Node<T> node = head;
        while(node != null && node.next != null){
            if(node.next.data.equals(data)) node.next = node.next.next; 
            else node = node.next;
        }
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        Node<T> newNode = head;
        while(newNode != null){
            sb.append("[").append(newNode.data).append("]").append("->");
            newNode = newNode.next;
        }sb.append("Null");
        return sb.toString();
    }
}
public class Main
{
	public static void main(String[] args) {
		LinkedList<Integer> lk = new LinkedList<>();
		
		lk.add(10);lk.add(445);lk.add(3432);lk.add(3);lk.remove(3);
		System.out.println(lk.print());
	}
}
