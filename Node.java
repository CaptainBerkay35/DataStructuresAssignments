package Dersler.Assignment.Assignment7;

public class Node {
    public String data;
    Node left, right, parent;
    int element;

    public Node()
    {
        this(0, null, null, null);
    }
    public Node(int ele)
    {
        this(ele, null, null, null);
    }
    public Node(int ele, Node left, Node right, Node parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.element = ele;
    }
}

