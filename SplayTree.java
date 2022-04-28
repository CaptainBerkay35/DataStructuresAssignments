package Dersler.Assignment.Assignment7;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    private int count = 0;

    public SplayTree()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void clear()
    {
        root = null;
        count = 0;
    }

    public void insert(int ele)
    {
        Node root1 = root;
        Node p = null;
        while (root1 != null)
        {
            p = root1;
            if (ele > p.element)
                root1 = root1.right;
            else
                root1 = root1.left;
        }
        root1 = new Node();
        root1.element = ele;
        root1.parent = p;
        if (p == null)
            root = root1;
        else if (ele > p.element)
            p.right = root1;
        else
            p.left = root1;
        Splay(root1);
        count++;
    }

    public void makeLeftChildParent(Node c, Node p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    public void makeRightChildParent(Node c, Node p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    public void Splay(Node x)
    {
        while (x.parent != null)
        {
            Node Parent = x.parent;
            Node GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            }
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    else
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }
    public StringBuilder printLevelOrder(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        String s="";
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {

            Node tempNode = queue.poll();

            sb.append(tempNode.element + " ");

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }return sb;
    }

    public void remove(Node node)
    {
        if (node == null)
            return;

        Splay(node);
        if( (node.left != null) && (node.right !=null))
        {
            Node min = node.left;
            while(min.right!=null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        }
        else if (node.right != null)
        {
            node.right.parent = null;
            root = node.right;
        }
        else if( node.left !=null)
        {
            node.left.parent = null;
            root = node.left;
        }
        else
        {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        count--;
    }

    public Node findNode(int ele)
    {
        Node PrevNode = null;
        Node root1 = root;
        while (root1 != null)
        {
            PrevNode = root1;
            if (ele > root1.element)
                root1 = root1.right;
            else if (ele < root1.element)
                root1 = root1.left;
            else if(ele == root1.element) {
                Splay(root1);
                return root1;
            }

        }
        if(PrevNode != null)
        {
            Splay(PrevNode);
            return null;
        }
        return null;
    }
}


