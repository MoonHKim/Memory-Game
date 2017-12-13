import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ex extends JFrame {
	static int WIDTH = 900, HEIGHT = 550; // ���� �������� ũ��
	JPanel pn1, pn2;
	Button bt1, bt2, bt3, bt4;

	Label lb1;
	Cards cards;
	int type1, type2; // �̵��� �� ī���� Ÿ�� ���� �� ��.
	boolean RESET = false; // RESET=true�� �����ϰڴܰ���

	public ex() {
		// layout, component

		setSize(WIDTH, HEIGHT); // ���� �������� ũ��

		setLayout(new FlowLayout());
		pn1 = new JPanel();
		pn2 = new JPanel();

		// Reset ������ Cards ��ü ���� ����
		bt1 = new Button("Reset");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RESET = true;
				if (cards instanceof Easy)
					cards = new Easy();
				else if (cards instanceof Normal)
					cards = new Normal();
				else if (cards instanceof Hard)
					cards = new Hard();
				repaint();
			}
		});

		bt2 = new Button("EASY");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RESET = true;
				cards = new Easy();
				repaint();
			}
		});

		bt3 = new Button("NORMAL");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RESET = true;
				cards = new Normal();
				repaint();
			}
		});

		bt4 = new Button("HARD");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RESET = true;
				cards = new Hard();
				repaint();
			}
		});

		lb1 = new Label("SCORE :       ");
		pn1.setPreferredSize(new Dimension(620, 500));
		pn2.setLayout(new GridLayout(5, 1));
		pn2.add(lb1);
		pn2.add(bt1);
		pn2.add(bt2);
		pn2.add(bt3);
		pn2.add(bt4);

		add(pn1);
		add(pn2);
		// Cards ��ü �ϳ��� �����ϸ� �Ƕ���, �� ī�� ���� ������.
		cards = new Easy(); // ������ ����ȯ....!

		MouseKeeper mouse = new MouseKeeper();
		addMouseListener(mouse);
	}

	private class MouseKeeper extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// clickCount�� Ȧ���� ù Ŭ��, ¦���� �ι�° Ŭ��
			Point point = new Point(e.getPoint());
			for (int i = 0; i < Cards.MAX; i++) {
				if (cards.aCard[i].includes(point)) {
					if (!cards.aCard[i].correct)
						Cards.clickCount++;
					// ������ ī�尡 correct�� �ƴϾ�߸� ī��Ʈ �ø�

					cards.aCard[i].clicked = true;
					cards.aCard[i].flip = true;

					// Ŭ���� �ֵ��� type1�� type2�� ����
					if (Cards.clickCount % 2 == 1)
						type1 = cards.aCard[i].type;
					else
						type2 = cards.aCard[i].type;
				}

			}

			if (Cards.clickCount % 2 == 0)// �ι�° Ŭ���̸�
			{
				for (int i = 0; i < Cards.MAX - 1; i++) {
					for (int j = i + 1; j < Cards.MAX; j++) {
						if (i != j) {
							Cards.check(cards.aCard[i], cards.aCard[j]);
							lb1.setText("SCORE : " + (Cards.scoreplus * 100 - Cards.scoreminus * 30));

						}
					}
				}
			}

			repaint();

			if (Cards.scoreplus == (Cards.MAX / 2)) // MAXĭ�� 2���� 1����ŭ ¦�̴ϱ�
			{
				Ending ending = new Ending(Cards.scoreplus * 100 - Cards.scoreminus * 30);
			}

		}
	}

	public static void main(String[] args) {

		ex win1 = new ex();
		win1.setVisible(true);
	}

	public void paint(Graphics g) {

		// ���¸޼ҵ�
		if (RESET == true) {
			g.setColor(Color.white);

			// ������ �� ��, �� ���� ����~ ī�� ����*��, ī�� ����*�� ��ŭ ����, �׸��� �� �׸���
			g.fillRect(Cards.SIDE_MARGIN, Cards.TOP_MARGIN, (eachCard.WIDTH * 6) + 1, (eachCard.HEIGHT * 4) + 1);
			g.setColor(Color.black);
			Cards.drawMap(g);

			Cards.sleep(1000);

			RESET = false; // �ٽ� false��
		}
		Cards.drawMap(g);

		for (int i = 0; i < Cards.MAX; i++) {
			cards.draw(g, cards.aCard[i]);
		}

		// �ʱ⿡ 3�� �����ְ�, �ø����� �ٲ�
		if (Cards.clickCount == 0) {
			Cards.sleep(3000);
			for (int i = 0; i < Cards.MAX; i++) {
				cards.aCard[i].flip = false;
				cards.draw(g, cards.aCard[i]);
			}
			Cards.drawMap(g);

		}

		if (Cards.clickCount % 2 == 0) {
			if (Cards.clickCount != 0)
				Cards.sleep(1000);
			// Map �׸��� Ŭ��, correct�Ѿֵ� �׸��� 1�� ���

			for (int i = 0; i < Cards.MAX; i++) {
				cards.aCard[i].clicked = false;
				if (!cards.aCard[i].correct)
					cards.aCard[i].flip = false; // ������ ���� �ֵ��� �ٽ� ������������
			}

			Cards.drawMap(g);
			for (int i = 0; i < Cards.MAX; i++) {
				cards.draw(g, cards.aCard[i]);
			}
			// �ٽ� Ŭ���� false�� �ٲٰ� �� �׸��� Ư���� �ֵ� �׸�
		}
	}
}