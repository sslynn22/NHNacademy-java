package basic_java.homework.starcraft;

public class Unit implements Behave {
	protected String name;
	protected int power;
	protected int defense;
	protected boolean canFly;
	protected boolean canAttackFlying;

	public Unit(String name, int power, int defense, boolean canFly, boolean canAttackFlying) {
		this.name = name;
		this.power = power;
		this.defense = defense;
		this.canFly = canFly;
		this.canAttackFlying = canAttackFlying;
	}

	public void attack(Unit target) {
		if (!target.canFly() || this.canAttackFlying) {
			target.reduceDefense(this.power);
		}
	}

	public int getDefense() {
		return defense;
	}

	public void reduceDefense(int amount) {
		this.defense -= amount;
	}

	public boolean isDead() {
		return defense <= 0;
	}

	public String getName() {
		return name;
	}

	public boolean canFly() {
		return canFly;
	}

	public boolean canAttackFlying() {
		return canAttackFlying;
	}

	@Override
	public void attack(Behave target) {
		attack((Unit) target);
	}

	@Override
	public boolean canAttackFly() {
		return canAttackFlying;
	}
}
