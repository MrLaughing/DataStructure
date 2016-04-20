package ren.laughing.datastructure.baseImpl;

/**
 * 自平衡二叉查找树AVL 继承二叉查找树
 * 
 * @author Laughing_Lz
 * @time 2016年4月20日
 */
public class AVLTree extends BSTree {
	/**
	 * 统一平衡方法 旋转操作
	 * 
	 * @param z
	 *            z是失衡的结点
	 * @return 返回平衡后子树的根结点
	 */
	private BinTreeNode rotate(BinTreeNode z) {
		BinTreeNode y = heightSubT(z);// 取y为z更高的孩子
		BinTreeNode x = heightSubT(y);// 取x为y更高的孩子
		boolean isLeft =z.isLChild();//标记z是否是左孩子
		BinTreeNode p = z.getParent();//取z的父结点
		BinTreeNode a,b,c;
		BinTreeNode t0,t1,t2,t3;
		//以下分四种情况重命名
		if(y.isLChild()){//1如果y是左孩子
			c = z;
			t3 = z.getRChild();
			if(x.isLChild()){//1.1如果x是左孩子(左左失衡)
				a = x;
				b = y;
				t0 = x.getLChild();
				t1 = x.getRChild();
				t2 = y.getRChild();
			}else{//1.2如果x是右孩子(左右失衡)
				b = x;
				a = y;
				t1 = x.getLChild();
				t2 = x.getRChild();
				t0 = y.getLChild();
			}
		}else{//2如果y是右孩子
			a = z;
			t0 = z.getLChild();
			if(x.isRChild()){//2.1如果x是右孩子(右右失衡)
				c = x;
				b = y;
				t2 = x.getLChild();
				t3 = x.getRChild();
				t1 = y.getLChild();
			}else{//2.2如果x是左孩子(右左失衡)
				b = x;
				c = y;
				t1 = x.getLChild();
				t2 =x.getRChild();
				t3 = y.getRChild();
			}
		}
//		x.server();//疑问，已经赋值给a b c 还有何用？★
//		y.server();
//		z.server();
		a.server();//断开与父结点的连接
		b.server();
		c.server();
		if(t0 != null){
			t0.server();
		}
		if(t1 != null){
			t1.server();
		}
		if(t2 != null){
			t2.server();
		}
		if(t3 != null){
			t3.server();
		}
		b.setLChild(a);//重新设置左右孩子，平衡
		b.setRChild(c);
		a.setLChild(t0);
		a.setRChild(t1);
		c.setLChild(t2);
		c.setRChild(t3);
		if(isLeft){
			p.setLChild(b);
		}else{
			p.setRChild(b);
		}
		return b;//返回平衡后子树的根
	}

	/**
	 * 获取结点v较高的子树
	 * 
	 * @param v
	 * @return
	 */
	private BinTreeNode heightSubT(BinTreeNode v) {
		if (v == null) {
			return null;
		}
		int lH = -1;// 若没有左子树，默认左子树高度为-1
		if (v.hasLChild()) {
			lH = v.getLChild().getHeight();
		}
		int rH = -1;// 若没有右子树，默认右子树高度为-1
		if (v.hasRChild()) {
			rH = v.getRChild().getHeight();
		}
		if (lH > rH) {
			return v.getLChild();
		} else if (lH < rH) {
			return v.getRChild();
		} else {// 若两边子树等高
			if (v.isLChild()) {
				return v.getLChild();
			} else {
				return v.getRChild();
			}
		}
	}
	/**
	 * 重写BSTree的insert方法
	 * insert之后要重新平衡
	 */
	@Override
	public void insert(Object obj) {
		super.insert(obj);
		root = reBalance(startBN);//重新平衡树
	}
	/**
	 * 重新平衡AVL树
	 * @param startBN
	 * @return 返回平衡后的AVL树根结点
	 */
	private BinTreeNode reBalance(BinTreeNode startBN) {
		if(startBN == null){
			return root;
		}
		BinTreeNode c = startBN;
		while(startBN != null){//从startBN开始，向上逐一检查 z 的祖先★
			if(!isBalance(startBN)){//若startBN失衡，则旋转使之重新平衡★
				startBN = rotate(startBN);
			}
			c = startBN;
			startBN = startBN.getParent();//继续检查其父亲
		}
		return c;
	}
	/**
	 * 判断该结点是否平衡
	 * @param startBN
	 * @return
	 */
	private boolean isBalance(BinTreeNode startBN) {
		if(startBN == null){
			return true;
		}
		int lH = startBN.hasLChild()?startBN.getLChild().getHeight():-1;
		int rH = startBN.hasRChild()?startBN.getRChild().getHeight():-1;
		return (Math.abs(lH-rH) <= 1);
	}
	/**
	 * 重写BSTree的remove方法
	 */
	@Override
	public Object remove(Object ele) {
		Object obj = super.remove(ele);
		root = reBalance(startBN);//重新平衡AVL树
		return obj;
	}
}