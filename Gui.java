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
	private JButton[][] squares = new JButton[8][8];
	private boolean turn = false;

	public Gui(){
		initUi();
	}

	public void initUi(){
		contentPane = (JPanel) getContentPane();

		inputPane = new JPanel();
		tf1 = new JTextField();
		tf1.setText("Rows");
		tf2 = new JTextField();
		tf2.setText("Columns");
		tf3 = new JTextField();
		tf3.setText("Stones to win");
		calcBtn = new JButton("Compute");
		//Todo: Thing about how to adjust the parameters
		calcBtn.addActionListener((event) -> initField(2,2,2));
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
		outputPane= new JPanel(new GridLayout(8, 8));
		outputPane.setPreferredSize(new Dimension(1200, 1200));
        outputPane.setBorder(new LineBorder(Color.BLACK));

		for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                JButton b = new JButton();
                ImageIcon icon = new ImageIcon(
                    	new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                final int x = j;
                final int y = i;
                b.setBackground(Color.BLUE);
                b.addActionListener((event) -> addStone(b));
                squares[j][i] = b;
                outputPane.add(squares[j][i]);
            }
        }
        contentPane.add(outputPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        pack();
	}

	private void addStone(JButton b){
		BufferedImage circleImg = new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB);
      	Graphics2D g2 = circleImg.createGraphics();
      	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      	if(!turn){
      		g2.setColor(Color.RED);
      		turn = true;
      	}
      	else{
      		g2.setColor(Color.YELLOW);
      		turn = false;
      	}
      	g2.fillOval(4,4,115,115);
      	g2.dispose();
      	b.setIcon(new ImageIcon(circleImg));
	}

	public static void main(String[] args){
		EventQueue.invokeLater(() ->{
            Gui gui = new Gui();
            gui.setVisible(true);
        });
	}

}