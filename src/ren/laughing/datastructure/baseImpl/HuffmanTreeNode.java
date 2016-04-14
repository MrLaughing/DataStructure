package ren.laughing.datastructure.baseImpl;
/**
 * Huffman树结点定义
 * 继承二叉树的性质
 * @author Laughing_Lz
 * @time 2016年4月14日
 */
public class HuffmanTreeNode extends BinTreeNode{
	private int weight;//权值
	private String coding = "";//编码

	public HuffmanTreeNode(int weight) {
		this(weight,null);
	}

	public HuffmanTreeNode(int weight, Object data) {
		super(data);
		this.weight = weight;
	}
	//改写父类方法
	@Override
	public HuffmanTreeNode getParent() {
		return (HuffmanTreeNode)super.getParent();
	}
	@Override
	public HuffmanTreeNode getLChild() {
		return (HuffmanTreeNode)super.getLChild();
	}
	@Override
	public HuffmanTreeNode getRChild() {
		return (HuffmanTreeNode)super.getRChild();
	}
	//get&set方法
	public int getWeight(){
		return this.weight;
	}
	public String getCoding(){
		return this.coding;
	}
	public void setCoding(String coding){
		this.coding = coding;
	}
	
}
