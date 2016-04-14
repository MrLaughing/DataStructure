package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.List;

/**
 * 创建Huffman树
 * 生成Huffman编码
 * @author Laughing_Lz
 * @time 2016年4月14日
 */
public class HuffmanTree {
	/**
	 * 创建Huffman树
	 * @param nodes
	 * @return
	 */
	public HuffmanTreeNode buildHuffmanTree(HuffmanTreeNode[] nodes){
		int n = nodes.length;
		if(n < 2){
			return nodes[0];
		}
		List list = new ArrayList();
		for(int i = 0;i<nodes.length;i++){
			insertList(list,nodes[i]);//按照nodes的weight从大到小顺序存入list中
		}
		for(int i = 1;i<n;i++){//选择 weight 最小的两棵树合并，循环 n-1 次
			HuffmanTreeNode minNode1 = (HuffmanTreeNode) list.remove(list.getSize()-1);
			HuffmanTreeNode minNode2 = (HuffmanTreeNode) list.remove(list.getSize()-1);
			HuffmanTreeNode newNode = new HuffmanTreeNode(minNode1.getWeight()+minNode2.getWeight(), "root");
			newNode.setLChild(minNode1);
			newNode.setRChild(minNode2);
			insertList(list, newNode);//重新插入list中
		}
		return (HuffmanTreeNode) list.get(0);
	}
	/**
	 * 将huffmanTreeNode按weight从大到小存入list中
	 * @param list
	 * @param huffmanTreeNode
	 */
	private void insertList(List list, HuffmanTreeNode node) {
		for(int i = 0;i<list.getSize();i++){
			if(node.getWeight()>((HuffmanTreeNode)list.get(i)).getWeight()){//★
				list.insert(i, node);
				return;
			}
		}
		list.insert(list.getSize(), node);//若node的weight最小，放在list最后
		return;
	}
	/**
	 * 生成Huffman编码
	 * @param root
	 */
	public void generateHuffmanCode(HuffmanTreeNode root){
		if(root == null){
			return;
		}
		if(root.hasParent()){
			if(root.isLChild()){
				root.setCoding(root.getParent().getCoding()+"0");
			}
			if(root.isRChild()){
				root.setCoding(root.getParent().getCoding()+"1");
			}
		}
		generateHuffmanCode(root.getLChild());
		generateHuffmanCode(root.getRChild());
	}
	public static void main(String[] args) {
		HuffmanTreeNode[] nodes = creatHuffmanNodes();
		HuffmanTree huffmanTree = new HuffmanTree();
		HuffmanTreeNode root = huffmanTree.buildHuffmanTree(nodes);
		System.out.println("构建的Huffman树根结点是"+root.getData()+",高度为："+root.getHeight()+",权值为："+root.getWeight());
		huffmanTree.generateHuffmanCode(root);
		System.out.println(root.getRChild().getRChild().getLChild().getCoding());//★Huffman编码针对叶子结点，root结点无编码
	}
	/**
	 * 创建HuffmanNode结点组合
	 * @return
	 */
	public static HuffmanTreeNode[] creatHuffmanNodes(){
		HuffmanTreeNode[] nodes = new HuffmanTreeNode[7];
		HuffmanTreeNode node1 = new HuffmanTreeNode(1, 'A');
		HuffmanTreeNode node2 = new HuffmanTreeNode(2, 'B');
		HuffmanTreeNode node3 = new HuffmanTreeNode(3, 'C');
		HuffmanTreeNode node4 = new HuffmanTreeNode(4, 'D');
		HuffmanTreeNode node5 = new HuffmanTreeNode(5, 'E');
		HuffmanTreeNode node6 = new HuffmanTreeNode(6, 'F');
		HuffmanTreeNode node7 = new HuffmanTreeNode(7, 'G');
		nodes[0] = node2;
		nodes[1] = node5;
		nodes[2] = node1;
		nodes[3] = node3;
		nodes[4] = node4;
		nodes[5] = node7;
		nodes[6] = node6;
		return nodes;
	}
}
