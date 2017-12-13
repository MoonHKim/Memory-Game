import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//새게임마다 Card 객체 생성하면 되도록 변경.

public class Cards {

	static int COLUMN, ROW;
	static int MAX; // 원래는 이거였는데, 난이도 따라 MAX값 바뀌게 수정static int MAX=24;
	static int scoreplus;
	static int scoreminus;
	eachCard[] aCard;

	static int clickCount;
	static int[] typeCount; // for checking if the number of the type is 2

	static int TOP_MARGIN = 35, SIDE_MARGIN = 25; // 원래는 static int TOP_MARGIN=35, SIDE_MARGIN=25;

	// 생성자
	public Cards() // t는몇번째카드인가를 의미
	{
		// aCard=new eachCard[MAX]; //MAX값이 static으로 지정되어있긴하지만, 부모생성자가 먼저이므로 값을 지정해야함.
		// genNew();
	}

	public void genNew() {

		typeCount = new int[MAX / 2]; // ★이건 일단 24로 놔두자
		aCard = new eachCard[MAX];

		for (int i = 0; i < MAX; i++) {
			aCard[i] = new eachCard(i);
		}
		clickCount = 0;
		scoreplus = 0;
		scoreminus = 0;// 점수 초기화

	}

	public static void drawMap(Graphics g) {
		for (int i = 0; i <= COLUMN; i++) {
			g.setColor(Color.black);
			g.drawLine(SIDE_MARGIN, TOP_MARGIN + 120 * i, eachCard.WIDTH * ROW + SIDE_MARGIN, TOP_MARGIN + 120 * i);
		}

		for (int j = 0; j <= ROW; j++) {
			g.drawLine(SIDE_MARGIN + 100 * j, TOP_MARGIN, SIDE_MARGIN + 100 * j, eachCard.HEIGHT * COLUMN + TOP_MARGIN);
		}

	}

	// 그리기
	public void draw(Graphics g, eachCard A) {
		// 테두리
		if (A.correct) {
			g.setColor(Color.yellow);
			g.drawRect(A.center.x - 50, A.center.y - 60, 100, 120);
			g.setColor(Color.black);
		} else {
			if (A.clicked) {
				g.setColor(Color.blue);
				g.drawRect(A.center.x - 50, A.center.y - 60, 100, 120);
			} else {
				g.setColor(Color.black);
			}
		}

		if (A.flip) // flip true일때만 무늬 그림
		{
			g.setColor(Color.black);
			if (A.type == 0) {
				g.fillRect(A.center.x - 40, A.center.y - 40, 80, 80);
				g.setColor(Color.blue);
				g.fillRect(A.center.x - 30, A.center.y - 30, 60, 60);
				g.setColor(Color.black);
				g.fillRect(A.center.x - 20, A.center.y - 20, 40, 40);
			} else if (A.type == 1)
				g.drawRect(A.center.x - 25, A.center.y - 35, 50, 70);
			else if (A.type == 2)
				g.fillOval(A.center.x - 35, A.center.y - 20, 70, 40);
			else if (A.type == 3) {
				g.setColor(Color.red);
				g.fillOval(A.center.x - 25, A.center.y - 35, 50, 70);
				g.setColor(Color.black);
			} else if (A.type == 4) {
				int[] x = new int[3];
				int[] y = new int[3];
				x[0] = A.center.x;
				x[1] = A.center.x - 25;
				x[2] = A.center.x + 25;
				y[0] = A.center.y - 30;
				y[1] = A.center.y + 15;
				y[2] = A.center.y + 15;
				g.fillPolygon(x, y, 3);
			} else if (A.type == 5) {
				for (int i = 0; i < 4; i++) {
					g.setColor(Color.gray);
					g.drawLine(A.center.x - 30 + i * 20, A.center.y - 60, A.center.x - 30 + i * 20, A.center.y + 60);
					g.drawLine(A.center.x - 50, A.center.y - 36 + i * 24, A.center.x + 50, A.center.y - 36 + i * 24);
					g.setColor(Color.black);
				}
			} else if (A.type == 6) {
				g.drawString("APAP", A.center.x - 12, A.center.y);
			} else if (A.type == 7) {
				g.drawLine(A.center.x - 50, A.center.y - 30, A.center.x + 50, A.center.y - 30);
				g.drawLine(A.center.x - 50, A.center.y, A.center.x + 50, A.center.y);
				g.drawLine(A.center.x - 50, A.center.y + 30, A.center.x + 50, A.center.y + 30);
			} else if (A.type == 8) {
				g.setColor(Color.yellow);
				g.fillOval(A.center.x - 35, A.center.y - 20, 70, 40);
				g.setColor(Color.black);
			} else if (A.type == 9) {
				g.drawLine(A.center.x - 50, A.center.y - 60, A.center.x + 50, A.center.y + 60);
				g.drawLine(A.center.x - 50, A.center.y + 60, A.center.x + 50, A.center.y - 60);
			} else if (A.type == 10) {
				g.fillRect(A.center.x - 10, A.center.y - 30, 20, 80);
				g.fillRect(A.center.x - 25, A.center.y - 15, 50, 25);
			}

			else {
				g.drawOval(A.center.x - 20, A.center.y - 20, 40, 40);
			}

		}

		else // when flip==false, erase
		{
			g.setColor(Color.white);
			g.fillRect(A.center.x - 49, A.center.y - 59, 99, 119);
			g.setColor(Color.black);
		}

	}

	public static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void check(eachCard a, eachCard b) // check if it's correct
	{

		if (a.clicked) {
			if (b.clicked) {
				if (a.type == b.type) {
					a.correct = true;
					b.correct = true;
					Cards.scoreplus++; // 1점 증가
				}

				else
					Cards.scoreminus++;

			}
		}
	}

}

class Hard extends Cards {
	public Hard() {
		super();

		COLUMN = 4;
		ROW = 6;
		MAX = 24;
		genNew();
	}
}

class Normal extends Cards {
	public Normal() {
		super();

		COLUMN = 3;
		ROW = 6;
		MAX = 18;
		genNew();
	}
}

class Easy extends Cards {
	public Easy() {
		super();

		COLUMN = 3;
		ROW = 4;
		MAX = 12;

		genNew();
	}
}
