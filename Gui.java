
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
	private boolean turn;
	
	public Gui(){
		initUi();
	}

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
		
		
		
		
		//Todo: Thing about how to adjust the parameters
		calcBtn.addActionListener((event) ->{
			row = tf1.getText();
			rowInt=Integer.parseInt(row);
			col = tf2.getText();
			colInt=Integer.parseInt(col);
			stn=tf3.getText();
			stnInt=Integer.parseInt(stn);
			squares=new JButton[rowInt][colInt];
			state= new boolean[rowInt][colInt];
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
      	
      	  
      		}
      		else{
      		
      		g2.setColor(Color.YELLOW);
      		state[x][y] = true;
      		turn = false;
      		
      	
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
		// delete one for loop
		for (int i = y; i <rowInt ; i++) { // y direction
			for (int j = x; j < colInt; j++) {// x direction
				if (state[i][j]==true && turn== true){
					s1+=1;
					for (int h = 1; h<stnInt; h++){
						if (i+h<rowInt && state[i+h][j]==true && turn == true ) {
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
				
				//think about where to place this if condition
				if(s1==stnInt) {
					JOptionPane.showMessageDialog (null, "red won!");
					
				}
				
		if (state[i][j]==true && turn == false ){
					s2+=1;
					for (int h = 1; h<stnInt; h++){
						if (i+h<rowInt && state [i+h][j]==true && turn == false ) {
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
					
				}
			}	
		}
		// vertical check
		for (int i = y; i < rowInt; i++) { // y direction
			for (int j = x; j < colInt; j++) {// x direction
				if (state[i][j]==true && turn == true ){
					s1+=1;
					for (int h = 1; h<stnInt; h++){
						if (j+h<colInt && state[i][j+h]==true && turn == true ) {
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
				}
				
				
				if (state[i][j]==true && turn == false  ){
					s2+=1;
					for (int h = 1; h<stnInt; h++){
						if (j+h<colInt && state[i][j+h]==true && turn == false) {
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
				}
				
			}	
		}
		
		// diagonal check left top to right bottom
		//Two Options:
		//A: 1 Up 1 Down
		//B: Calc top left position and then check for whole grid
		for (int i = y; i < rowInt; i++) { // y direction
			for (int j = x; j < colInt; j++) {//// x direction
				if (state[i][j]==true && turn == true ){
					s1+=1;
					for (int h = 1; h<stnInt; h++){
						if (j+h<colInt && i+h<rowInt && state [i+h][j+h]==true && turn ==true  ) {
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
				}
			
				
				if (state[i][j]==true && turn == false ){
					s2+=1;
					for (int h = 1; h<stnInt; h++){
						if (j+h<colInt &&i+h<rowInt&& state [i+h][j+h]==true && turn == false ) {
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
				}
			
			}	
		}
		
		// diagonal check right top to left bottom
		//Hint: Look at MagicSquare Exercise from Java Programming Class
		for (int i = 0; i < rowInt; i++) { // y direction
			for (int j = 0; j < colInt; j++) {// x direction
				if (state[i][j]==true && turn == true){
					s1+=1;
					for (int h = 1; h<stnInt; h++){
						if (j-h>0 && i+h<7 && state[i+h][j-h]==true && turn ==true) {
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
				}
			
				
				if (state[i][j]==true && turn == false ){
					s2+=1;
					for (int h = 1; h<stnInt; h++){
						if (j-h>0 &&i+h<7 && state [i+h][j-h]==true && turn == false ) {
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
