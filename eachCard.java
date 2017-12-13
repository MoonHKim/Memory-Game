import java.awt.*;
import java.util.Random;


public class eachCard 
{
	final static int WIDTH = 100, HEIGHT = 120;
	boolean flip = true;
	boolean correct = false;
	boolean clicked = false;
	Point center;
	int type; // types of cards
	Random random;

	public eachCard(int num) // num�� ���° ī�������� �ǹ�
	{
		center = new Point();
		center.x = Cards.SIDE_MARGIN + WIDTH / 2 + (num % Cards.ROW) * WIDTH;
		center.y = Cards.TOP_MARGIN + HEIGHT / 2 + (num / Cards.ROW) * HEIGHT;
		random = new Random();

		// type ����
		// HARD�϶�
		if (Cards.MAX == 24) {
			while (true) {
				type = random.nextInt(12); // ���̵� ���� �ٸ�
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type�� ���� ���̵� ���� ���������� �����غ���.
				case 1:
					Cards.typeCount[1]++;
					break;
				case 2:
					Cards.typeCount[2]++;
					break;
				case 3:
					Cards.typeCount[3]++;
					break;
				case 4:
					Cards.typeCount[4]++;
					break;
				case 5:
					Cards.typeCount[5]++;
					break;
				case 6:
					Cards.typeCount[6]++;
					break;
				case 7:
					Cards.typeCount[7]++;
					break;
				case 8:
					Cards.typeCount[8]++;
					break;
				case 9:
					Cards.typeCount[9]++;
					break;
				case 10:
					Cards.typeCount[10]++;
					break;
				case 11:
					Cards.typeCount[11]++;
					break;
				}

				if (Cards.typeCount[type] <= 2)
					break;
				else
					Cards.typeCount[type]--;

				// �� Ÿ���� 2�� �̻󳪿����� ���� ����Ż��
				// �װ� �ƴϸ� �Լ� ���� ����
			}
		}

		// NORMAL�϶�
		else if (Cards.MAX == 18) {
			while (true) {
				type = random.nextInt(9); // ���̵� ���� �ٸ�
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type�� ���� ���̵� ���� ���������� �����غ���.
				case 1:
					Cards.typeCount[1]++;
					break;
				case 2:
					Cards.typeCount[2]++;
					break;
				case 3:
					Cards.typeCount[3]++;
					break;
				case 4:
					Cards.typeCount[4]++;
					break;
				case 5:
					Cards.typeCount[5]++;
					break;
				case 6:
					Cards.typeCount[6]++;
					break;
				case 7:
					Cards.typeCount[7]++;
					break;
				case 8:
					Cards.typeCount[8]++;
					break;
				}

				if (Cards.typeCount[type] <= 2)
					break;
				else
					Cards.typeCount[type]--;

				// �� Ÿ���� 2�� �̻󳪿����� ���� ����Ż��
				// �װ� �ƴϸ� �Լ� ���� ����
			}
		}

		// EASY�� ��
		else if (Cards.MAX == 12) {
			while (true) {
				type = random.nextInt(6); // ���̵� ���� �ٸ�
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type�� ���� ���̵� ���� ���������� �����غ���.
				case 1:
					Cards.typeCount[1]++;
					break;
				case 2:
					Cards.typeCount[2]++;
					break;
				case 3:
					Cards.typeCount[3]++;
					break;
				case 4:
					Cards.typeCount[4]++;
					break;
				case 5:
					Cards.typeCount[5]++;
					break;
				}

				if (Cards.typeCount[type] <= 2)
					break;
				else
					Cards.typeCount[type]--;

				// �� Ÿ���� 2�� �̻󳪿����� ���� ����Ż��
				// �װ� �ƴϸ� �Լ� ���� ����
			}
		}

	}

	// Ŭ������ �ī�带 �����ϴ°�
	public boolean includes(Point point) {
		if (center.x - WIDTH / 2 < point.x && point.x < center.x + WIDTH / 2) {
			if (center.y - HEIGHT / 2 < point.y && point.y < center.y + HEIGHT / 2)
				return true;
			else
				return false;
		}

		else
			return false;
	}
	
}
