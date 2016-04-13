package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Node;
/**
 * 二叉树存储结构结点定义：三叉链表
 * 三个指针域包含父结点、左孩子、右孩子
 * @author Laughing_Lz
 * @time 2016年4月13日
 */
public class BinTreeNode implements Node{
	private Object data;//数据域
	private BinTreeNode parent;//父结点
	private BinTreeNode lChild;//左孩子
	private BinTreeNode rChild;//右孩子
	private int height;//以该结点为根的子树高度
	private int size;//该结点子孙数，包含该结点本身

	public BinTreeNode() {
		this(null);
	}

	public BinTreeNode(Object data) {
		this.parent = null;
		this.lChild = null;
		this.rChild = null;
		this.size = 1;
		this.height = 0;
		this.data = data;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object obj) {
		data = obj;
	}
	//has
	public boolean hasParent(){
		return parent != null;
	}
	public boolean hasLChild(){
		return lChild != null;
	}
	public boolean hasRChild(){
		return rChild != null;
	}
	//is
	public boolean isLeaf(){
		return !hasLChild()&&!hasRChild();
	}
	public boolean isLChild(){
		return hasParent()&&this == parent.lChild;
	}
	public boolean isRChild(){
		return hasParent()&&this == parent.rChild;
	}
	//get
	public int getHeight(){
		return height;
	}
	public int getSize(){
		return size;
	}
	public BinTreeNode getLChild(){
		return lChild;
	}
	public BinTreeNode getRChild(){
		return rChild;
	}
	public BinTreeNode getParent(){
		return parent;
	}
	
	//operate
	//★更新以结点为根的子树高度
	public void updateHeight(){
		int newH = 0;
		if (hasLChild()) {
			newH = Math.max(newH, getLChild().getHeight()+1);
		}
		if (hasRChild()) {
			newH = Math.max(newH, getRChild().getHeight()+1);
		}
		if(newH == height){
			return;
		}
		height = newH;
		if(hasParent()){
			this.getParent().updateHeight();//★递归更新父结点高度
		}
	}
	//更新该结点的子孙数
	public void updateSize(){
		size = 1;
		if(hasLChild()){
			size = size+getLChild().size;
		}
		if(hasRChild()){
			size= size+getRChild().size;
		}
		if(hasParent()){
			this.getParent().updateSize();
		}
	}
	//断开与父结点的关联
	public void server(){
		if(hasParent()){
			if(this == getParent().getLChild()){
				getParent().lChild = null;
			}
			if(this == getParent().getRChild()){
				getParent().rChild = null;
			}
			getParent().updateHeight();
			getParent().updateSize();
			parent = null;
		}
	}
	public BinTreeNode setLChild(BinTreeNode lc){
		BinTreeNode oldLChild = lChild;
		if(hasLChild()){
			lChild.server();
		}
		if(lc != null){
			lc.server();
			this.lChild = lc;
			lc.parent = this;
			this.updateHeight();
			this.updateSize();
		}
		return oldLChild;
	}
	public BinTreeNode setRChild(BinTreeNode rc){
		BinTreeNode oldRChild = rChild;
		if(hasRChild()){
			rChild.server();
		}
		if(rc != null){
			rc.server();
			this.rChild = rc;
			rc.parent = this;
			this.updateHeight();
			this.updateSize();
		}
		return oldRChild;
	}
}
