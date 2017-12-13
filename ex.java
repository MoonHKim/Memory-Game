import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ex extends JFrame {
	static int WIDTH = 900, HEIGHT = 550; // 메인 프레임의 크기
	JPanel pn1, pn2;
	Button bt1, bt2, bt3, bt4;

	Label lb1;
	Cards cards;
	int type1, type2; // 이따가 두 카드의 타입 비교할 때 씀.
	boolean RESET = false; // RESET=true면 리셋하겠단거임

	public ex() {
		// layout, component

		setSize(WIDTH, HEIGHT); // 메인 프레임의 크기

		setLayout(new FlowLayout());
		pn1 = new JPanel();
		pn2 = new JPanel();

		// Reset 누르면 Cards 객체 새로 생성
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
		// Cards 객체 하나만 생성하면 판떼기, 새 카드 묶음 생성임.
		cards = new Easy(); // 오지는 형변환....!

		MouseKeeper mouse = new MouseKeeper();
		addMouseListener(mouse);
	}

	private class MouseKeeper extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// clickCount가 홀수면 첫 클릭, 짝수면 두번째 클릭
			Point point = new Point(e.getPoint());
			for (int i = 0; i < Cards.MAX; i++) {
				if (cards.aCard[i].includes(point)) {
					if (!cards.aCard[i].correct)
						Cards.clickCount++;
					// 선택한 카드가 correct가 아니어야만 카운트 늘림

					cards.aCard[i].clicked = true;
					cards.aCard[i].flip = true;

					// 클릭된 애들을 type1과 type2로 지정
					if (Cards.clickCount % 2 == 1)
						type1 = cards.aCard[i].type;
					else
						type2 = cards.aCard[i].type;
				}

			}

			if (Cards.clickCount % 2 == 0)// 두번째 클릭이면
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

			if (Cards.scoreplus == (Cards.MAX / 2)) // MAX칸의 2분의 1개만큼 짝이니까
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

		// 리셋메소드
		if (RESET == true) {
			g.setColor(Color.white);

			// 옆여백 뺀 곳, 위 여백 뺀곳~ 카드 가로*열, 카드 세로*행 만큼 지움, 그리고 맵 그리기
			g.fillRect(Cards.SIDE_MARGIN, Cards.TOP_MARGIN, (eachCard.WIDTH * 6) + 1, (eachCard.HEIGHT * 4) + 1);
			g.setColor(Color.black);
			Cards.drawMap(g);

			Cards.sleep(1000);

			RESET = false; // 다시 false로
		}
		Cards.drawMap(g);

		for (int i = 0; i < Cards.MAX; i++) {
			cards.draw(g, cards.aCard[i]);
		}

		// 초기에 3초 보여주고, 플립으로 바꿈
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
			// Map 그리고 클릭, correct한애들 그리고 1초 대기

			for (int i = 0; i < Cards.MAX; i++) {
				cards.aCard[i].clicked = false;
				if (!cards.aCard[i].correct)
					cards.aCard[i].flip = false; // 맞추지 못한 애들은 다시 뒤집어져야지
			}

			Cards.drawMap(g);
			for (int i = 0; i < Cards.MAX; i++) {
				cards.draw(g, cards.aCard[i]);
			}
			// 다시 클릭을 false로 바꾸고 맵 그리고 특이한 애들 그림
		}
	}
}