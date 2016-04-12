package ren.laughing.datastructure.Instance;

import ren.laughing.datastructure.base.Stack;
import ren.laughing.datastructure.baseImpl.StackLinked;

/**
 * 关于栈的一些实例
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class StackInstance {
	/**
	 * 十进制转换为八进制
	 * 使用Stack倒序输出i%8的余数，即为八进制数
	 * @param i 十进制数
	 */
	public void baseConversion(int i){
		Stack s = new StackLinked();
		for(;i>0;i=i/8){
			s.push(i%8);
		}
		while(!s.isEmpty()){
			System.out.print(s.pop());
		}
		System.out.println();
	}
	/**
	 * 括号匹配检测{[()]}：
	 * 此方法仅对括号匹配进行检测，对于运算符号不做检测
	 * @param str 需要匹配括号的字符串
	 */
	public void bracketmatch(String str){
		Stack s = new StackLinked();
		for(int i = 0;i<str.length();i++){
			char n = str.charAt(i);
			if("{[()]}".indexOf((n)) == -1){
				System.out.print(n);
			}else if("{([".indexOf(n) != -1){
				s.push(n);
				System.out.print(n);
			}else if("})]".indexOf(n) != -1){
				switch (n) {
				case ')':
					if (!s.isEmpty() && s.pop().equals('(')) {
						System.out.print(n);
					} else {
						System.out.print("匹配错误");
						return;
					}
					break;
				case '}':
					if (!s.isEmpty() && s.pop().equals('{')) {
						System.out.print(n);
					} else {
						System.out.print("匹配错误");
						return;
					}
					break;
				case ']':
					if (!s.isEmpty() && s.pop().equals('[')) {
						System.out.print(n);
					} else {
						System.out.print("匹配错误");
						return;
					}
					break;
				default:
					break;
				}
			}
		}
		System.out.println();
		if(!s.isEmpty()){
			System.out.println("\n匹配错误，左括号多余");
		}
	}
	/**
	 * 迷宫解法 (类似DFS，深度优先遍历)
	 * @param maze 迷宫数组
	 * @param sx 起点x坐标
	 * @param sy 起点y坐标
	 * @param ex 终点x坐标
	 * @param ey 终点y坐标
	 */
	public void mazeExit(char[][] maze,int sx,int sy,int ex,int ey){
		Cell[][] cells = creatMaze(maze);//创建迷宫
		System.out.println("起点为("+sx+","+sy+")，终点为("+ex+","+ey+")，迷宫图：");
		printMaze(cells);//打印迷宫
		Stack s = new StackLinked();//定义堆栈
		Cell startCell = cells[sx][sy];//起点
		Cell endCell = cells[ex][ey];//终点
		s.push(startCell);//起点入栈
		startCell.visited = true;//标记起点被
		while(!s.isEmpty()){//while判断
			int x = ((Cell)s.peek()).x;
			int y = ((Cell)s.peek()).y;
			if(s.peek()!=endCell){//如果不是终点
				int count = 0;
				//走法：遍历下、右、上、左是否访问，若没访问且不是墙，则访问
				if (cells[x + 1][y].c == '0' && !cells[x + 1][y].visited) {// 下
					s.push(cells[x + 1][y]);
					cells[x + 1][y].visited = true;
					count++;
				}  if (cells[x][y + 1].c == '0' && !cells[x][y + 1].visited) {// 右
					s.push(cells[x][y + 1]);
					cells[x][y + 1].visited = true;
					count++;
				}  if (cells[x - 1][y].c == '0' && !cells[x - 1][y].visited) {// 上
					s.push(cells[x - 1][y]);
					cells[x - 1][y].visited = true;
					count++;
				}  if (cells[x][y - 1].c == '0' && !cells[x][y - 1].visited) {// 左
					s.push(cells[x][y - 1]);
					cells[x][y - 1].visited = true;
					count++;
				} 
				if(count == 0){
					s.pop();//都没有，就要回退，出栈
				}
			}else{//如果已经到终点,开始回退打印路线
				while(!s.isEmpty()){
					Cell cell = (Cell) s.peek();
					cell.c = '*';
					s.pop();
					while(!s.isEmpty()&&!isAjoinCell((Cell)s.peek(),cell)){//★将回退的单元与上一单元比较是否相邻，若不相邻，直接出栈而不标记为路径
						s.pop();
					}
					
				}
				System.out.println("开始打印路线");
				printMaze(cells);
				return ;
			}
		}
	}
	/**
	 * 判断两个单元是否相邻
	 * @param cell1 回退后的单元
	 * @param cell2 上一单元
	 * @return
	 */
	private boolean isAjoinCell(Cell cell1, Cell cell2) {
		if (cell1.x == cell2.x && Math.abs(cell1.y - cell2.y) < 2)
			return true;
		if (cell1.y == cell2.y && Math.abs(cell1.x - cell2.x) < 2)
			return true;
		return false;
	}
	/**
	 * 打印迷宫
	 * @param cells
	 */
	private void printMaze(Cell[][] cells) {
		for(int i = 0;i<cells.length;i++){
			for(int j = 0;j<cells[i].length;j++){
				System.out.print(cells[i][j].c+" ");
			}
			System.out.println();
		}
	}
	/**
	 * 创建迷宫
	 * @param maze
	 * @return
	 */
	private Cell[][] creatMaze(char[][] maze) {
		Cell[][] cells = new Cell[maze.length][];
		for(int i = 0;i<maze.length;i++){
			cells[i] = new Cell[maze[i].length];//★定义数组y的长度
			for(int j = 0;j<maze[i].length;j++){
				cells[i][j] = new Cell(i, j, false, maze[i][j]);
//				cells[i][j].c = maze[i][j];//定义是墙/路/路径
//				cells[i][j].visited = false;//初始均未访问
			}
		}
		return cells;
	}
	
	public static void main(String[] args) {
		StackInstance si =new StackInstance();
		System.out.println("1、10进制转换为8进制：");
		si.baseConversion(800);//1440
		System.out.println("2、括号匹配检测");
		si.bracketmatch("(12+{34*(32-54)/[76*(32-99)]})+55");
		System.out.println("3、迷宫解法");
		char[][] maze1 = { { '1', '1', '1', '1', '1', '1', '1', '1' }, { '1', '0', '1', '1', '0', '1', '1', '1' },
				{ '1', '0', '0', '0', '0', '1', '0', '1' }, { '1', '1', '1', '0', '0', '0', '0', '1' },
				{ '1', '0', '0', '0', '0', '0', '0', '1' }, { '1', '0', '0', '0', '0', '0', '0', '1' },
				{ '1', '1', '0', '0', '0', '0', '0', '1' }, { '1', '1', '1', '1', '1', '1', '1', '1' } };
		char[][] maze2 ={{'1','1','1','1','1'},{'1','0','1','0','1'},{'1','0','0','0','1'},{'1','0','1','0','1'},{'1','1','1','1','1'}};
		si.mazeExit(maze1, 4, 1, 1, 1);	
		si.mazeExit(maze2, 3, 1, 1, 1);
	}
	/**
	 * 迷宫单元定义
	 * @author Laughing_Lz
	 * @time 2016年4月12日
	 */
	class Cell{
		int x = 0;//x坐标
		int y = 0;//y坐标
		boolean visited = false;//是否被访问
		char c = '0';//'1'是墙，'0'是路，'*'是路径 （初始为0,无意义？）
		public Cell(int x, int y, boolean visited, char c) {
			this.x = x;
			this.y = y;
			this.visited = visited;
			this.c = c;
		}
		
	}
}
