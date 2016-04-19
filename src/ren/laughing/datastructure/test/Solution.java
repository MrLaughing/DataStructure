package ren.laughing.datastructure.test;

public class Solution {
    public boolean Find(int [][] array,int target) {
		int m,n,x,y;
       	m = array.length;//行数
        n = array[0].length;//列数
        x=m-1;y=0;//坐标定在左下角
        while(x>=0 && y<=n-1){
          if(target<array[x][y]){
                       x--;//遇小上移
          }else if(target>array[x][y]){
                       y++;//遇大右移
               }
          else {
               return true;
             }
      	}
       return false;
    }
}
