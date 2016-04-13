package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Iterator;
import ren.laughing.datastructure.base.LinkedList;
import ren.laughing.datastructure.base.Stack;

/**
 * ⑴ 遍历左子树，访问根，遍历右子树（LDR）中根（序）遍历
 * ⑵ 遍历左子树，遍历右子树，访问根（LRD）后根（序）遍历
 * ⑶ 访问根，遍历左子树，遍历右子树（DLR）先根（序）遍历
 * ⑷ 访问根，遍历右子树，遍历左子树（DRL）
 * ⑸ 遍历右子树，遍历左子树，访问根（RLD）
 * ⑹ 遍历右子树，访问根，遍历左子树（RDL）
 * @author Laughing_Lz
 * @time 2016年4月13日
 */
public class TreeInteator {
	/**
	 * 先序遍历
	 * @param root 树的根结点
	 * @return
	 */
	public Iterator preOrder(BinTreeNode root){
		LinkedList list = new DLinkedList();
//		preOrderRecursion(list,root);//先序遍历的递归算法
		preOrderTraverse(list, root);//先序遍历的非递归算法
		return list.elements();
	}
	/**
	 * 先序遍历的递归算法
	 * @param list 遍历后存入list中
	 * @param btn 要遍历的树结点
	 */
	public void preOrderRecursion(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		list.insertLast(btn);
		preOrderRecursion(list, btn.getLChild());
		preOrderRecursion(list, btn.getRChild());
		
	}
	/**
	 * 先序遍历的非递归算法
	 * 先遍历根结点，左结点
	 * 将右结点依次存入栈中
	 * 再出栈遍历
	 * @param list
	 * @param btn
	 */
	public void preOrderTraverse(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		Stack s = new StackLinked();//★
		BinTreeNode p = btn;
		while(p !=null){
			while(p != null){
				list.insertLast(p);
				if(p.hasRChild()){
					s.push(p.getRChild());
				}
				p=p.getLChild();
			}
			if (!s.isEmpty()) {
				p = (BinTreeNode)s.pop(); //★右子树根退栈遍历右子树
			}
		}
	}
	/**
	 * 中序遍历
	 * @return
	 */
	public Iterator inOrder(BinTreeNode root){
		LinkedList list = new DLinkedList();
//		inOrderRecursion(list, root);
		inOrderTraverse(list, root);
		return list.elements();
	}
	/**
	 * 中序遍历的递归算法
	 * @param list
	 * @param btn
	 */
	public void inOrderRecursion(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		inOrderRecursion(list, btn.getLChild());
		list.insertLast(btn);
		inOrderRecursion(list, btn.getRChild());
	}
	/**
	 * 中序遍历的非递归算法
	 * @param list
	 * @param btn
	 */
	public void inOrderTraverse(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		Stack s = new StackLinked();
		BinTreeNode p = btn;
		while(p != null||!s.isEmpty()){//★当在第一次出栈时，叶子结点的右结点为null，所以这里用||
			while(p != null){
				s.push(p);
				p = p.getLChild();
			}
			if(!s.isEmpty()){
				p=(BinTreeNode) s.pop();
				list.insertLast(p);
				p = p.getRChild();
				
			}
		}
		
	}
	/**
	 * 后序遍历
	 * @return
	 */
	public Iterator postOrder(BinTreeNode root){
		LinkedList list = new DLinkedList();
//		postOrderRecursion(list, root);
		postOrderTraverse(list,root);
		return list.elements();
	}
	/**
	 * 后序遍历的递归算法
	 * @param list
	 * @param btn
	 */
	public void postOrderRecursion(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		postOrderRecursion(list, btn.getLChild());
		postOrderRecursion(list, btn.getRChild());
		list.insertLast(btn);
	}
	/**
	 * 后序遍历的非递归算法
	 * @param list
	 * @param btn
	 */
	public void postOrderTraverse(LinkedList list,BinTreeNode btn){
		if(btn == null){
			return;
		}
		Stack s = new StackLinked();
		BinTreeNode p = btn;
		while(p != null||!s.isEmpty()){
			while(p != null){//先左后右不断深入
				s.push(p);//将根节点入栈
				if (p.hasLChild()){
					p = p.getLChild();
				}else{
					p = p.getRChild();
				}
			}
			if(!s.isEmpty()){
				p = (BinTreeNode) s.pop();
				list.insertLast(p);
			}
			//★满足条件时，说明栈顶根节点右子树已访问，应出栈访问之
			while(!s.isEmpty()&&((BinTreeNode)s.peek()).getRChild() == p){
				p = (BinTreeNode) s.pop();
				list.insertLast(p);
			}
			//★转向栈顶根结点的右子树继续后序遍历
			if(!s.isEmpty()){
				p = ((BinTreeNode)s.peek()).getRChild();
			}else{
				p = null;
			}
		}
	}
	public static void main(String[] args) {
		TreeInteator ti = new TreeInteator();
		BinTreeNode root = creatTree();
		System.out.println("先序遍历：");
		Iterator pre = ti.preOrder(root);
		while(!pre.isDone()){
			System.out.print(((BinTreeNode)pre.currentItem()).getData()+" ");
			pre.next();
		}
		System.out.println("\n中序遍历：");
		Iterator in = ti.inOrder(root);
		while(!in.isDone()){
			System.out.print(((BinTreeNode)in.currentItem()).getData()+" ");
			in.next();
		}
		System.out.println("\n后序遍历：");
		Iterator post = ti.postOrder(root);
		while(!post.isDone()){
			System.out.print(((BinTreeNode)post.currentItem()).getData()+" ");
			post.next();
		}
		
	}
	//生成树
	public static BinTreeNode creatTree(){
		BinTreeNode leaf1 = new BinTreeNode(1, null, null, null);
		BinTreeNode leaf2 = new BinTreeNode(2, leaf1, null, null);
		BinTreeNode leaf3 = new BinTreeNode(3, leaf1, null, null);
		leaf1.setLChild(leaf2);
		leaf1.setRChild(leaf3);
		BinTreeNode leaf4 = new BinTreeNode(4, leaf2, null, null);
		BinTreeNode leaf5 = new BinTreeNode(5, leaf2, null, null);
		leaf2.setLChild(leaf4);
		leaf2.setRChild(leaf5);
		BinTreeNode leaf6 = new BinTreeNode(6, leaf3, null, null);
		BinTreeNode leaf7 = new BinTreeNode(7, leaf3, null, null);
		leaf3.setLChild(leaf6);
		leaf3.setRChild(leaf7);
		return leaf1;
	}
	
}
