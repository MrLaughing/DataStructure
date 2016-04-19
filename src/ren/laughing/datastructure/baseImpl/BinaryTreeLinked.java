package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Iterator;
import ren.laughing.datastructure.base.LinkedList;
import ren.laughing.datastructure.base.Queue;
import ren.laughing.datastructure.base.Stack;

/**
 * 关于二叉树的三种遍历+层次遍历+查找元素 ⑴ 遍历左子树，访问根，遍历右子树（LDR）中根（序）遍历 ⑵
 * 遍历左子树，遍历右子树，访问根（LRD）后根（序）遍历 ⑶ 访问根，遍历左子树，遍历右子树（DLR）先根（序）遍历 ⑷
 * 访问根，遍历右子树，遍历左子树（DRL） ⑸ 遍历右子树，遍历左子树，访问根（RLD） ⑹ 遍历右子树，访问根，遍历左子树（RDL）
 * 
 * @author Laughing_Lz
 * @time 2016年4月13日
 */
public class BinaryTreeLinked {
	private BinTreeNode root;// 二叉树根结点

	public BinaryTreeLinked() {
		super();
	}

	public BinaryTreeLinked(BinTreeNode root) {
		super();
		this.root = root;
	}

	/**
	 * 先序遍历
	 * 
	 * @return
	 */
	public Iterator preOrder() {
		LinkedList list = new DLinkedList();
		// preOrderRecursion(list,root);//先序遍历的递归算法
		preOrderTraverse(list, root);// 先序遍历的非递归算法
		return list.elements();
	}

	/**
	 * 先序遍历的递归算法
	 * 
	 * @param list
	 *            遍历后存入list中
	 * @param btn
	 *            要遍历的树结点
	 */
	public void preOrderRecursion(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		list.insertLast(btn);
		preOrderRecursion(list, btn.getLChild());
		preOrderRecursion(list, btn.getRChild());

	}

	/**
	 * 先序遍历的非递归算法 先遍历根结点，左结点 将右结点依次存入栈中 再出栈遍历
	 * 
	 * @param list
	 * @param btn
	 */
	public void preOrderTraverse(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		Stack s = new StackLinked();// ★
		BinTreeNode p = btn;
		while (p != null) {
			while (p != null) {
				list.insertLast(p);
				if (p.hasRChild()) {
					s.push(p.getRChild());
				}
				p = p.getLChild();
			}
			if (!s.isEmpty()) {
				p = (BinTreeNode) s.pop(); // ★右子树根退栈遍历右子树
			}
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @return
	 */
	public Iterator inOrder() {
		LinkedList list = new DLinkedList();
		// inOrderRecursion(list, root);
		inOrderTraverse(list, root);
		return list.elements();
	}

	/**
	 * 中序遍历的递归算法
	 * 
	 * @param list
	 * @param btn
	 */
	public void inOrderRecursion(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		inOrderRecursion(list, btn.getLChild());
		list.insertLast(btn);
		inOrderRecursion(list, btn.getRChild());
	}

	/**
	 * 中序遍历的非递归算法
	 * 
	 * @param list
	 * @param btn
	 */
	public void inOrderTraverse(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		Stack s = new StackLinked();
		BinTreeNode p = btn;
		while (p != null || !s.isEmpty()) {// ★当在第一次出栈时，叶子结点的右结点为null，所以这里用||
			while (p != null) {
				s.push(p);
				p = p.getLChild();
			}
			if (!s.isEmpty()) {
				p = (BinTreeNode) s.pop();
				list.insertLast(p);
				p = p.getRChild();

			}
		}

	}

	/**
	 * 后序遍历
	 * 
	 * @return
	 */
	public Iterator postOrder() {
		LinkedList list = new DLinkedList();
		// postOrderRecursion(list, root);
		postOrderTraverse(list, root);
		return list.elements();
	}

	/**
	 * 后序遍历的递归算法
	 * 
	 * @param list
	 * @param btn
	 */
	public void postOrderRecursion(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		postOrderRecursion(list, btn.getLChild());
		postOrderRecursion(list, btn.getRChild());
		list.insertLast(btn);
	}

	/**
	 * 后序遍历的非递归算法
	 * 
	 * @param list
	 * @param btn
	 */
	public void postOrderTraverse(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		Stack s = new StackLinked();
		BinTreeNode p = btn;
		while (p != null || !s.isEmpty()) {
			while (p != null) {// 先左后右不断深入
				s.push(p);// 将根节点入栈
				if (p.hasLChild()) {
					p = p.getLChild();
				} else {
					p = p.getRChild();
				}
			}
			if (!s.isEmpty()) {
				p = (BinTreeNode) s.pop();
				list.insertLast(p);
			}
			// ★满足条件时，说明栈顶根节点右子树已访问，应出栈访问之
			while (!s.isEmpty() && ((BinTreeNode) s.peek()).getRChild() == p) {
				p = (BinTreeNode) s.pop();
				list.insertLast(p);
			}
			// ★转向栈顶根结点的右子树继续后序遍历
			if (!s.isEmpty()) {
				p = ((BinTreeNode) s.peek()).getRChild();
			} else {
				p = null;
			}
		}
	}

	/**
	 * 层次遍历
	 * 
	 * @return
	 */
	public Iterator levelOrder() {
		LinkedList list = new DLinkedList();
		levelOrderTraverse(list, root);
		return list.elements();
	}

	/**
	 * 层次遍历的非递归算法
	 * 
	 * @param list
	 * @param btn
	 */
	public void levelOrderTraverse(LinkedList list, BinTreeNode btn) {
		if (btn == null) {
			return;
		}
		Queue q = new QueueArray();// 使用队列完成层次遍历
		q.enqueue(btn);
		while (!q.isEmpty()) {
			BinTreeNode p = (BinTreeNode) q.dequeue();// 取出队首结点 p 并访问
			list.insertLast(p);
			if (p.hasLChild()) {
				q.enqueue(p.getLChild());// ★先进先出
			}
			if (p.hasRChild()) {
				q.enqueue(p.getRChild());
			}
		}
	}

	/**
	 * 遍历查找元素,返回树结点
	 * 
	 * @param obj
	 *            要查找的元素
	 * @return
	 */
	public BinTreeNode find(Object obj) {
		return searchBTN(root, obj);
	}

	/**
	 * 递归查找元素
	 * 
	 * @param btn
	 * @param obj
	 * @return
	 */
	private BinTreeNode searchBTN(BinTreeNode btn, Object obj) {
		if (btn == null) {
			return null;
		}
		if (((Integer) btn.getData()).equals((Integer) obj)) {// 先比较根节点元素
			return btn;
		}
		BinTreeNode p = searchBTN(btn.getLChild(), obj);// 否则在左子树查找
		if (p == null) {
			p = searchBTN(btn.getRChild(), obj);// 没找到，在右子树查找
		}
		return p;
	}

	public static void main(String[] args) {
		BinTreeNode root = creatTree();
		BinaryTreeLinked btl = new BinaryTreeLinked(root);
		System.out.println("先序遍历：");
		Iterator pre = btl.preOrder();
		while (!pre.isDone()) {
			System.out.print(((BinTreeNode) pre.currentItem()).getData() + " ");
			pre.next();
		}
		System.out.println("\n中序遍历：");
		Iterator in = btl.inOrder();
		while (!in.isDone()) {
			System.out.print(((BinTreeNode) in.currentItem()).getData() + " ");
			in.next();
		}
		System.out.println("\n后序遍历：");
		Iterator post = btl.postOrder();
		while (!post.isDone()) {
			System.out.print(((BinTreeNode) post.currentItem()).getData() + " ");
			post.next();
		}
		System.out.println("\n层次遍历：");
		Iterator level = btl.levelOrder();
		while (!level.isDone()) {
			System.out.print(((BinTreeNode) level.currentItem()).getData() + " ");
			level.next();
		}
		System.out.println("\n查找元素：");
		BinTreeNode result = btl.find(3);
		System.out.println("查找结果：元素" + result.getData() + ",位于第" + (root.getHeight() - result.getHeight() + 1) + "行");
	}

	// 生成树
	public static BinTreeNode creatTree() {
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
