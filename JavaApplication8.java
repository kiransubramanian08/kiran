
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.*;
import java.awt.event.*;
import static javaapplication8.JavaApplication8.row_max;
import static javaapplication8.JavaApplication8.list;

import javax.swing.*;

class Seat extends JFrame{
    static final Color[] color = {Color.WHITE,Color.BLUE, Color.GREEN,Color.RED};
     private LifeLabel[][] label;
     private boolean mouseDown = false;

     public Seat(int x,int y,int[][] arr){
         super("Seating Plan");
          JPanel panel = new JPanel(new GridLayout(x, y, 1, 1));
                panel.setBackground(Color.BLACK);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                for(int i=0;i<row_max;i++) {
			
			for(int j=0;j<arr.length;j++) {
		
				for(int k=0;k<arr[j][1];k++) {
					
					if(i<arr[j][0])
					    if(list.get(j).seats[i][k][0]==0)
                                            panel.add(new LifeLabel(list.get(j).seats[i][k][1],""));    
                                            else
                                            panel.add(new LifeLabel(list.get(j).seats[i][k][1],Integer.toString(list.get(j).seats[i][k][0])));
                                        else
                                        panel.add(new LifeLabel(0,
                                                ""));
					
					
				}
			}
                        }

     
     
                add(panel, BorderLayout.CENTER);
                 setVisible(true);

     }
      

class LifeLabel extends JLabel{
        private int state, newState;
        LifeLabel(int col,String data){
             state = newState = 0;
             setOpaque(true);
             if(col==1)
             setBackground(color[1]);
             else if(col==2)
             setBackground(color[2]);
             else if(col==3)
             setBackground(color[3]);
             else
             setBackground(color[0]);
             setFont(new Font("Serif",Font.PLAIN,20));
             setHorizontalAlignment(SwingConstants.CENTER);
             setVerticalAlignment(SwingConstants.CENTER);
             setText(data);
             

        }
                                


}

}

class seat_set{
	
	int row,col;
	int[][][] seats;
	char type;
	
	public seat_set(int row,int col) {
		
		this.type='s';
		this.row=row;
		this.col=col;
		
		seats = new int[row][col][2];
		
	}
	
	
	
	
	
}

public class JavaApplication8 {

	static ArrayList<seat_set> list= new ArrayList<>();
	static int pass_count,row_max,col_max,count=1;
	
	static void fillaisle(int[][] arr) {
		
        for(int i=0;i<row_max&&count<=pass_count;i++) {
			
			for(int j=0;j<arr.length&&count<=pass_count;j++) {
		
			if(j==0&&i<arr[j][0]) {
			list.get(j).seats[i][arr[j][1]-1][0]=count++;
			list.get(j).seats[i][arr[j][1]-1][1]=1;
                        }
			else if(j==arr.length-1&&i<arr[j][0]&&count<=pass_count){
			list.get(j).seats[i][0][0]=count++;	
			list.get(j).seats[i][0][1]=1;
                        }
			else if(i<arr[j][0]&&count<=pass_count) {			
			list.get(j).seats[i][0][0]=count++;
			list.get(j).seats[i][0][1]=1;
                        if(count<=pass_count)
			list.get(j).seats[i][arr[j][1]-1][0]=count++;
			list.get(j).seats[i][arr[j][1]-1][1]=1;
                        }
			}
		
		}
		
		
	}
	
	static void fillwindow(int[][] arr) {
		
        for(int i=0;i<row_max&&count<=pass_count;i++) {
			
			if(i<arr[0][0]){
				list.get(0).seats[i][0][0]=count++;
                                list.get(0).seats[i][0][1]=2;
                        }
			if(i<arr[arr.length-1][0]&&count<=pass_count) {
				int j = arr[arr.length-1][1]-1;
				list.get(arr.length-1).seats[i][j][0]=count++;
			        list.get(arr.length-1).seats[i][j][1]=2;
			}
        	
        	
		}

		
		
		
	}
	
	static void fillmiddle(int[][] arr) {
		
		
        for(int i=0;i<row_max&&count<=pass_count;i++) {
			
			for(int j=0;j<arr.length&&count<=pass_count;j++) {
		
				for(int k=1;k<arr[j][1]-1&&count<=pass_count&&i<arr[j][0];k++) {
					
					
					list.get(j).seats[i][k][0]=count++;
					list.get(j).seats[i][k][1]=3;
					
					
				}
			
			
			
			}
			}

	}
	
    static void print_output() {
    	

	System.out.println("        ");
    	System.out.println("******* Allocation of seats ******");
	System.out.println("       ");
	System.out.println("       ");
    	
    	for(seat_set arr : list) {
    		
    		for(int i=0;i<arr.seats.length;i++)
    			{for(int j=0;j<arr.seats[0].length;j++)
    			{
    				System.out.print(arr.seats[i][j][0]+"    ");
    				
    			}
    			System.out.println(" ");
    			}
    	    		
    		System.out.println(" ");
    		
    		
    	}
    	
    	
    }
    
	public static void main(String args[]) {
 
                 int n,i,j;
                 Scanner in=new Scanner(System.in);
                 System.out.println("Enter the no of entries");
                 n=in.nextInt();
                 int arr[][]=new int[n][2];
                 System.out.println("Enter the row and column values");
                 for(i=0;i<n;i++){
                 for(j=0;j<2;j++){
                     arr[i][j]=in.nextInt();
                       }
                     }
		
               Comparator<int[]> c=new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0])
				return o2[1]-o1[1];
				return o1[0]-o2[0];
			}
		};
		 Arrays.sort(arr,c);
		 
		System.out.println("Enter the passengers count");
		pass_count = in.nextInt();
		
		for(int[] seat : arr) {
			if(seat[0]>row_max)
				row_max=seat[0];
			col_max=seat[1]+col_max;
			list.add(new seat_set(seat[0],seat[1]));
			
		}
		
		fillaisle(arr);
		fillwindow(arr);
		fillmiddle(arr);
                print_output();
		new Seat(row_max,col_max,arr);
	}
	
}

