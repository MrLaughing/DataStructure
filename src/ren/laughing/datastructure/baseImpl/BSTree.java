package ren.laughing.datastructure.baseImpl;

import java.util.Comparator;

import ren.laughing.datastructure.base.Node;

/**
 * 二叉搜索树的实现 ★该二叉搜索树按中序遍历，元素增序
 * 
 * @author Laughing_Lz
 * @time 2016年4月19日
 */
public class BSTree extends BinaryTreeLinked implements Comparator<Integer> {
	private BinTreeNode root;// 树的根结点
	private BinTreeNode startBN;// ★待平衡出发点

	public BSTree() {
		super();
	}

	public BSTree(BinTreeNode root) {
		super();
		this.root = root;
	}

	/**
	 * 查找元素在二叉搜索树中的结点位置
	 * 
	 * @param obj
	 * @return
	 */
	public Node serch(Object obj) {
		return BSTreeRecursion(root, obj);
	}

	/**
	 * 查找二叉搜索树中元素位置的递归算法
	 * 
	 * @param root
	 * @param obj
	 * @return
	 */
	private Node BSTreeRecursion(BinTreeNode root, Object obj) {
		if (root == null) {
			return null;
		}
		switch (compare((Integer) obj, (Integer) root.getData())) {
		case 0:// 等于
			return root;
		case -1:// 小于
			return BSTreeRecursion(root.getLChild(), obj);
		default:// 大于
			return BSTreeRecursion(root.getRChild(), obj);
		}
	}

	/**
	 * 获取以btn为根的二叉搜索树最小元素位置结点
	 * 
	 * @param btn
	 * @return
	 */
	public Node min(BinTreeNode btn) {
		if (btn != null) {
			while (btn.hasLChild()) {
				btn = btn.getLChild();
			}
		}
		return btn;
	}

	/**
	 * 获取以btn为根的二叉搜索树最大元素位置结点
	 * 
	 * @param btn
	 * @return
	 */
	public Node max(BinTreeNode btn) {
		if (btn != null) {
			while (btn.hasRChild()) {
				btn = btn.getRChild();
			}
		}
		return btn;
	}

	/**
	 * 获取某结点btn在中序遍历序列中的后续结点
	 * 
	 * @param btn
	 * @return
	 */
	public BinTreeNode getSuccessor(BinTreeNode btn) {
		if (btn == null) {
			return null;
		}
		if (btn.hasRChild()) {// 1、如果有右子树，后续结点是右子树中元素最小的结点
			return (BinTreeNode) min(btn.getRChild());
		}
		while (btn.isRChild()) {// 2、如果没有右子树，且存在后续结点，则是从该结点到root根结点路径上第一个作为左孩子结点的父结点
			btn = btn.getParent();
		}
		return btn.getParent();// 2&3、若没右子树，且不是右孩子，则后续结点是此结点的父结点
	}

	/**
	 * 获取某结点btn在中序遍历序列中的前驱结点
	 * 
	 * @param btn
	 * @return
	 */
	public BinTreeNode getPredecessor(BinTreeNode btn) {
		if (btn == null) {
			return null;
		}
		if (btn.hasLChild()) {// 1、如果有左子树，前驱结点是左子树中元素最大的结点
			return (BinTreeNode) max(btn.getLChild());
		}
		while (btn.isLChild()) {// 2、如果没有左子树，且存在前驱结点，则是从该结点到root根节点路径上第一个作为右孩子结点的父结点
			btn = btn.getParent();
		}
		return btn.getParent();// 2&3、若没左子树，且不是左孩子,则前驱结点是此结点的父结点
	}

	/**
	 * 插入元素，只能插在叶子结点
	 * 
	 * @param obj
	 */
	public void insert(Object obj) {
		BinTreeNode p = null;
		BinTreeNode current = root;
		while (current != null) {// 找到待插入位置
			p = current;
			if (compare((Integer) obj, (Integer) current.getData()) < 0) {// 小于
				current = current.getLChild();
			} else {// 大于
				current = current.getRChild();
			}
		}
		startBN = p;// 待平衡出发点 ★暂时不考虑
		if (p == null) {
			root = new BinTreeNode(obj);// ★感觉没意义？
		} else if (compare((Integer) obj, (Integer) p.getData()) < 0) {
			p.setLChild(new BinTreeNode(obj));
		} else {
			p.setRChild(new BinTreeNode(obj));
		}
	}

	/**
	 * 删除结点：三种情况 第一种，该结点是叶子结点，直接删除 第二种，该结点只有左孩子或者右孩子，令其左/右孩子为其父结点的左/右孩子即可
	 * 第三种，该结点既有左孩子又有右孩子，需要使用前驱/后续结点的元素替换要删除结点元素，再按第一/二种情况删除替换的结点
	 * 
	 * @param obj
	 * @return
	 */
	public Object remove(Object obj) {
		BinTreeNode btn = (BinTreeNode) BSTreeRecursion(root, obj);
		if (btn == null) {// 查找不到该元素
			return null;
		}
		BinTreeNode del = null; // 待删结点
		BinTreeNode subT = null; // ★待删结点的子树
		if (!btn.hasLChild() || !btn.hasRChild()) // 若该结点没有左孩子或没有右孩子,或者该结点没有孩子
			del = btn;// 则要删除的结点即为该结点本身
		else {// 若该结点既有左孩子又有右孩子，则先用前驱结点的元素(后续结点也可)替换，再将替换结点删除
			del = getPredecessor(btn);// 要删除的结点为替换结点
			Object old = btn.getData();
			btn.setData(del.getData());// ★此处使用前驱结点的元素替换
			del.setData(old);// ★此处为统一将移除元素返回return★
		}
		startBN = del.getParent(); // ★待平衡出发点，暂不考虑
		// ★此时待删结点只有左子树或右子树 why？
		if (del.hasLChild()) {
			subT = del.getLChild();
		} else {
			subT = del.getRChild();
		}
		if (del == root) { // 若待删结点为根 ★★
			if (subT != null) {
				subT.server();// 与原根结点断开
			}
			root = subT;
		} else if (subT != null) {// 若待删结点不为根结点且不是叶子结点
			if (del.isLChild()) {
				del.getParent().setLChild(subT);
			} else {
				del.getParent().setRChild(subT);
			}
		} else {// 若待删结点为叶子结点
			del.server();
		}
		return del.getData();// 返回删除结点的元素(注意第三种情况)
	}

	/**
	 * 实现Comparator接口 默认数据元素(getData)为int类型
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}
}
