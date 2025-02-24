package basic_java.homework.starcraft;

import java.util.*;

public class JBDJ09018_Starcraft {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("내 종족을 결정하세요(0: terran, 1: protos, 2: zerg): ");
		int inputRace = scanner.nextInt();
		scanner.nextLine();

		String[] raceNames = {"Terran", "Protos", "Zerg"};
		String playerRaceName = raceNames[inputRace];
		String enemyRaceName = raceNames[new Random().nextInt(raceNames.length)];

		Race player = new Race(playerRaceName);
		Race enemy = new Race(enemyRaceName);

		System.out.println("적군: " + playerRaceName);
		displayUnits(player.getUnits());

		System.out.println("\n아군: " + enemyRaceName);
		displayUnits(enemy.getUnits());

		while (!player.getUnits().isEmpty() && !enemy.getUnits().isEmpty()) {
			System.out.print("\n상대 유닛과 공격할 유닛을 입력하십시오(내 유닛, 상대 유닛): ");
			String select = scanner.nextLine();

			try {
				String[] indices = select.split(",");
				if (indices.length != 2) {
					throw new InputMismatchException("잘못된 입력 형식입니다. 예: 1,2");
				}

				int playerUnitIndex = Integer.parseInt(indices[0].trim());
				int enemyUnitIndex = Integer.parseInt(indices[1].trim());

				Unit playerUnit = player.getUnits().get(playerUnitIndex);
				Unit enemyUnit = enemy.getUnits().get(enemyUnitIndex);

				if (!enemyUnit.canFly() || playerUnit.canAttackFlying()) {
					playerUnit.attack(enemyUnit);
					if (enemyUnit.isDead()) {
						enemy.getUnits().remove(enemyUnitIndex);
						System.out.println(enemyUnit.getName() + " 유닛이 파괴되었습니다!");
					}
				} else {
					System.out.println("공격에 실패했습니다.");
				}

				if (!enemy.getUnits().isEmpty()) {
					Random random = new Random();
					int enemyAttackUnitIndex = random.nextInt(enemy.getUnits().size());
					int playerTargetUnitIndex = random.nextInt(player.getUnits().size());

					System.out.println(playerUnitIndex + "번 유닛이 " + enemyUnitIndex + "번 유닛을 공격합니다.");

					Unit enemyAttackUnit = enemy.getUnits().get(enemyAttackUnitIndex);
					Unit playerTargetUnit = player.getUnits().get(playerTargetUnitIndex);

					System.out.println("\n컴퓨터가 공격합니다.");
					System.out.println(enemyAttackUnitIndex + "번 유닛이 " + playerTargetUnitIndex + "번 유닛을 공격했습니다.");

					enemyAttackUnit.attack(playerTargetUnit);
					if (playerTargetUnit.isDead()) {
						player.getUnits().remove(playerTargetUnitIndex);
						System.out.println(playerTargetUnit.getName() + " 유닛이 파괴되었습니다!");
					}
				}

			} catch (InputMismatchException | NumberFormatException | IndexOutOfBoundsException e) {
				System.out.println("잘못된 입력입니다. 올바른 형식: 숫자,숫자 (예: 1,2)");
			}

			System.out.println("\n적군: " + playerRaceName);
			displayUnits(player.getUnits());

			System.out.println("\n아군: " + enemyRaceName);
			displayUnits(enemy.getUnits());
		}

		if (player.getUnits().isEmpty()) {
			System.out.println("\n패배했습니다.");
		} else {
			System.out.println("\n승리했습니다.");
		}

		System.out.println("적군: " + playerRaceName);
		displayUnits(player.getUnits());

		System.out.println("\n아군: " + enemyRaceName);
		displayUnits(enemy.getUnits());
	}

	private static void displayUnits(List<Unit> units) {
		for (int i = 0; i < units.size(); i++) {
			Unit unit = units.get(i);
			System.out.println(i + "." + unit.getName() +
				" (현재 방어력: " + unit.getDefense() +
				", 공격력: " + unit.power + ")");
		}
	}
}
