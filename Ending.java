import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Ending extends JFrame 
{
	static int WIDTH=400, HEIGHT=300;
	JPanel PN1;//Big Panel, for Layout
	JPanel pn1, pn2;
	Button bt1;
	JLabel lb1;
	JTextArea ta1;
	
	public Ending(int score)
	{
		setVisible(true);
		setBackground(Color.black);
		setLocation((ex.WIDTH-this.WIDTH)/2, (ex.HEIGHT-this.HEIGHT)/2);
		setSize(400,300);
		setTitle("SOCRE");
		setLayout(new FlowLayout());
		pn1=new JPanel();
		pn2=new JPanel();
		
		bt1=new Button("CONFIRM");
		lb1=new JLabel("SCORE : "+score);
		ta1=new JTextArea("NAME", 1,10);
		
		bt1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				dispose();
			}
		});
		pn1.setLayout(new GridLayout(2,1));
		pn1.add(lb1);	pn1.add(ta1);
		pn2.add(bt1);
		add(pn1); add(pn2);
		
		
	}
	
}
