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

	public eachCard(int num) // num은 몇번째 카드인지를 의미
	{
		center = new Point();
		center.x = Cards.SIDE_MARGIN + WIDTH / 2 + (num % Cards.ROW) * WIDTH;
		center.y = Cards.TOP_MARGIN + HEIGHT / 2 + (num / Cards.ROW) * HEIGHT;
		random = new Random();

		// type 배정
		// HARD일때
		if (Cards.MAX == 24) {
			while (true) {
				type = random.nextInt(12); // 난이도 별로 다름
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type로 할지 난이도 별로 설정할지는 생각해볼것.
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

				// 그 타입이 2번 이상나왔으면 난수 생성탈출
				// 그게 아니면 게속 난수 생성
			}
		}

		// NORMAL일때
		else if (Cards.MAX == 18) {
			while (true) {
				type = random.nextInt(9); // 난이도 별로 다름
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type로 할지 난이도 별로 설정할지는 생각해볼것.
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

				// 그 타입이 2번 이상나왔으면 난수 생성탈출
				// 그게 아니면 게속 난수 생성
			}
		}

		// EASY일 때
		else if (Cards.MAX == 12) {
			while (true) {
				type = random.nextInt(6); // 난이도 별로 다름
				switch (type) {
				case 0:
					Cards.typeCount[0]++;
					break;// Cards.type로 할지 난이도 별로 설정할지는 생각해볼것.
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

				// 그 타입이 2번 이상나왔으면 난수 생성탈출
				// 그게 아니면 게속 난수 생성
			}
		}

	}

	// 클릭점이 어떤카드를 선택하는가
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
