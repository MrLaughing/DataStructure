package ren.laughing.datastructure.Instance;

public class RecuisionInstace {
	/**
	 * 汉诺塔问题
	 * 结束条件：当只有一个棋盘时候从x移动到z
	 * n个棋盘可看作：将n-1个棋盘借助z从x挪到y上
	 * 然后将x上最下面的棋盘挪到z上
	 * 再将n-1个棋盘借助x从y挪到z上
	 * @param n 棋盘数
	 * @param x 起始点
	 * @param y 过渡点
	 * @param z 落脚点
	 */
	public void hanio(int n,char x,char y,char z){
		if(n == 1){
			System.out.println("从"+x+"挪到"+z);
		}else{
			hanio(n-1, x, z, y);
			System.out.println("从"+x+"挪到"+z);
			hanio(n-1, y, x, z);
		}
	}
	/**
	 * n位布尔型数的可能组合
	 * 结束条件：若已递归到n为0时候，对m[0]赋值，再打印产生的组合
	 * @param m 存放可能产生的组合
	 * @param n n为m数组的下标最大值
	 */
	public void coding(int[] m,int n){
		if(n == 0){
			m[n] = 0;
			for (int i=0;i<m.length;i++)
				System.out.print(m[i]);
				System.out.println();
			m[n] = 1;
			for (int i=0;i<m.length;i++)
				System.out.print(m[i]);
				System.out.println();
		}else{
			m[n] = 0;coding(m, n-1);//先对第m[n]位赋值为0，继续递归
			m[n] = 1;coding(m, n-1);//结束后再对m[n]位赋值为1，继续递归
		}
	}
	public static void main(String[] args) {
		RecuisionInstace r  = new RecuisionInstace();
		r.hanio(4, 'x', 'y', 'z');
		r.coding(new int[3], 2);
	}
}
