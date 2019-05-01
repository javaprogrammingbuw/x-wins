
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;



import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Gui extends JFrame{

	private JPanel contentPane;
	private JPanel inputPane;
	private JPanel outputPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JButton calcBtn;
	private int rowInt;
	private int colInt;
	private int stnInt;
	private String row ;
	private String col ;
	private String stn;
	private JButton[][] squares;
	private boolean [][] state;
	private boolean  turn;
	private int [][] color;
	public Gui(){
		initUi();
	}
	
// To initialize the required JFrame objects like button,textbox etc.
	public void initUi(){
		contentPane = (JPanel) getContentPane();

		inputPane = new JPanel();
		tf1 = new JTextField();
		tf1.setText("0");
		
		tf2 = new JTextField();
		tf2.setText("0");
		
		tf3 = new JTextField();
		tf3.setText("0");
		
		calcBtn = new JButton("Compute");
	
		calcBtn.addActionListener((event) ->{
			row = tf1.getText();
			rowInt=Integer.parseInt(row);
			col = tf2.getText();
			colInt=Integer.parseInt(col);
			stn=tf3.getText();
			stnInt=Integer.parseInt(stn);
			squares=new JButton[rowInt][colInt];
			state= new boolean[rowInt][colInt];
			color= new int [rowInt][colInt];
		   initField(rowInt,colInt,stnInt);
		   
		});
		
		inputPane.add(tf1);
		inputPane.add(tf2);
		inputPane.add(tf3);
		inputPane.add(calcBtn);
		inputPane.setLayout(new GridLayout(1,4));
		
		

		contentPane.add(inputPane);

		pack();

        //Position in screen center
        setLocationRelativeTo(null);
        //Close Application when window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
// To create a feild of buttons.
	private void initField(int width, int height, int stones){
		
		
		
		outputPane= new JPanel(new GridLayout(width, height));
		outputPane.setPreferredSize(new Dimension(1200*stones/width,1200*stones/height));
		outputPane.setBorder(new LineBorder(Color.BLACK));

		for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                JButton b = new JButton();
                ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
               
                 final int x = j;
                 final int y = i;
                 
                 squares[j][i]=b;
                 state [j][i]=false;   
                 color[j][i]=0;
                b.addActionListener((event) -> {
                	         addStone(b,x,y);
             	
                });
             
                b.setIcon(icon);
                b.setBackground(Color.BLUE);
                outputPane.add(squares[j][i]);
            }
		}
  
      
        contentPane.add(outputPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
       
        pack();
	}

    //To add stones to a lowest possible row of a column.
	
	private void addStone(JButton  b,int x,int y){
		BufferedImage circleImg = new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB);
      	Graphics2D g2 = circleImg.createGraphics();
      	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
      
   	 for ( int r=y;r<=squares.length-1;r++) {
 		
   		 if(state[x][r]==false) {
	 	  b=squares[x][r];
	 	  y = r;
   		 }
   	}
   	 
    
   	 
		if(!turn){
		
      		g2.setColor(Color.RED);
      		state[x][y] = true;
      		turn = true;
      	    color[x][y]=1;
      	  
      	}
      		else{
      		
      		g2.setColor(Color.YELLOW);
      		state[x][y] = true;
      		turn = false;
      		color[x][y]=2;
      	
      		}
		
      	g2.fillOval(4,4,95,95);
      	g2.dispose();
      	b.setIcon(new ImageIcon(circleImg));
   	    b.setVisible(true);
   	      getWinner(x,y);
	 }
	void getWinner(int x,int y) { 	

		int s1 = 0, s2 = 0; //counter for player 1 und 2
		
		// horizontal check

		for (int i = 0; i <rowInt ; i++) { // y direction
			
				if (state[i][y]==true && color [i][y]==1){
					s1+=1;
					for (int h = 1; h<stnInt; h++){
						if (i+h<rowInt && state[i+h][y]==true && color [i+h][y]==1 ) {
							s1+=1;
							
						}
						 	else {
							s1=0; //necesary?
						 	}
					}
				}
					else {
					s1=0;
					}
				
				if(s1==stnInt) {
					JOptionPane.showMessageDialog (null, "red won!");
					this.getContentPane().removeAll();
					initUi();
				
					
				}
				
		if (state[i][y]==true && color[i][y]==2  ){
					s2+=1;
					for (int h = 1; h<stnInt; h++){
						if (i+h<rowInt && state [i+h][y]==true && color[i+h][y]==2 ) {
							s2+=1;
						}
							else {
							s2=0;
							}
					}
				}
					else {
					s2=0;
					}
				if(s2==stnInt) {
					JOptionPane.showMessageDialog (null, "yellow won!");
					this.getContentPane().removeAll();
					initUi();
				
					
				}
			}
		
		
		// vertical check
				
					for (int j = 0; j < colInt; j++) {// x direction
						if (state[x][j]==true && color[x][j]==1 ){
							s1+=1;
							for (int h = 1; h<stnInt; h++){
								if (j+h<colInt && state[x][j+h]==true && color[x][j+h]==1) {
									s1+=1;
								}
									else {
									s1=0;
									}
							}
						}
							else {
							s1=0;
							}
						if(s1==stnInt) {
							JOptionPane.showMessageDialog (null, "red won!");
							this.getContentPane().removeAll();
							initUi();
							
							
						}
						
						
						if (state[x][j]==true && color[x][j]==2  ){
							s2+=1;
							for (int h = 1; h<stnInt; h++){
								if (j+h<colInt && state[x][j+h]==true && color [x][j+h]==2) {
									s2+=1;
								}
									else {
									s2=0;
									}
							}
						}
							else {
							s2=0;
							}
						if(s2==stnInt) {
							JOptionPane.showMessageDialog (null, "yellow won!");
							this.getContentPane().removeAll();
							initUi();
						
						}
						
					}	
				
	
			// diagonal check left top to right bottom

				for (int i = 0; i < rowInt; i++) { // y direction
					for (int j = 0; j < colInt; j++) {//// x direction
						if (state[i][j]==true && color [i][j]==1 ){
							s1+=1;
							for (int h = 1; h<stnInt; h++){
								if (j+h<colInt && i+h<rowInt && state [i+h][j+h]==true && color [i+h][j+h]==1) {
									s1+=1;
								}
								else {
									s1=0;
								}
							}
						}
							else {
							s1=0;
							}
						if(s1==stnInt) {
							JOptionPane.showMessageDialog (null, "red won!");
							this.getContentPane().removeAll();
							initUi();
							
							
						}
					
						
						if (state[i][j]==true && color [i][j]==2){
							s2+=1;
							for (int h = 1; h<stnInt; h++){
								if (j+h<colInt &&i+h<rowInt&& state [i+h][j+h]==true && color [i+h][j+h]==2 ) {
									s2+=1;
								}
									else {
									s2=0;
									}
							}
						}
							else {
							s2=0;
							}
						if(s2==stnInt) {
							JOptionPane.showMessageDialog (null, "yellow won!");
							this.getContentPane().removeAll();
							initUi();
							
						}
					
					}	
				}
   	
			// diagonal check right top to left bottom
				for (int i = 0; i < rowInt; i++) { // y direction
					for (int j = 0; j < colInt; j++) {// x direction
						if (state[i][j]==true && color[i][j]==1){
							s1+=1;
							for (int h = 1; h<stnInt; h++){
								if (j-h>0 && i+h<colInt && state[i+h][j-h]==true && color [i+h][j-h]==1) {
									s1+=1;
								}
									else {
									s1=0;
									}
							}
						}
							else {
							s1=0;
							}
						if(s1==stnInt) {
							JOptionPane.showMessageDialog (null, "red won!");
							this.getContentPane().removeAll();
							initUi();
							
							
							
						}
					
						
						if (state[i][j]==true && color [i][j]==2 ){
							s2+=1;
							for (int h = 1; h<stnInt; h++){
								if (j-h>0 && i+h<colInt && state [i+h][j-h]==true && color [i+h][j-h]==2 ) {
									s2+=1;
								}
									else {
									s2=0;
									}
							}
						}
							else {
							s2=0;
							}
						if(s2==stnInt) {
							JOptionPane.showMessageDialog (null, "yellow won!");
							this.getContentPane().removeAll();
							initUi();
							
							
						}
						
					}	
				}
				
			}
		

	public static void main(String[] args){
		EventQueue.invokeLater(() ->{
            Gui gui = new Gui();
            
            gui.setVisible(true);
        });
	}

}
