package ren.laughing.datastructure.test;

public class TraverTree {
	static class Item{
		int text;
		Item(int i){
          this.text=i;			
		}
		private void visit(){
			System.out.print(this.text+",");
		}
	}//class Item
	static class Node{
		Item item;
		Node right;
		Node left;
		Node(Item item,Node l,Node r){
			this.item=item;
			this.right=r;
			this.left=l;
		}		
	}//class Node
    private static void traverM(Node n){
    	if(n==null)return;    	
    	traverM(n.left);
    	n.item.visit();
    	traverM(n.right);
    }//中序遍历
    private static void traverF(Node n){
    	if(n==null)return;
    	n.item.visit();
    	traverF(n.left);
    	traverF(n.right);
    }//先序遍历
    private static void traverL(Node n){
    	if(n==null)return;    	
    	traverL(n.left);
    	traverL(n.right);
    	n.item.visit();
    }//后序遍历  
 
	public static void main(String[] args) {
		Node root=createTree();
		System.out.print("中序遍历的结果是:");
		traverM(root);
 
		System.out.println();
		System.out.print("先序遍历的结果是:");
		traverF(root);
		
		System.out.println();
		System.out.print("后序遍历的结果是:");
		traverL(root);
	}
	public static Node createTree(){
		//生成叶子节点
		Item item=new Item(6);
		Node leaf_1=new Node(item,null,null);
		
		item=new Item(7);
		Node leaf_2=new Node(item,null,null);
		
		item=new Item(10);
		Node leaf_3=new Node(item,null,null);
		
		item=new Item(12);
		Node leaf_4=new Node(item,null,null);
		
		item=new Item(5);
		leaf_1=new Node(item,leaf_1,leaf_2);
		
		item=new Item(8);
		leaf_2=new Node(item,leaf_3,leaf_4);
		
		item=new Item(1);
		Node root=new Node(item,leaf_1,leaf_2);
		
		return root;	//返回根节点	
	}
 
}