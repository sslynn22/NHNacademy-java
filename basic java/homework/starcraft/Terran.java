package basic_java.homework.starcraft;

public class Terran {
	public class Marine extends Unit {
		public Marine() {
			super("Marine", 3, 10, false, false);
		}
	}

	public class Tank extends Unit {
		public Tank() {
			super("Tank", 7, 15, false, false);
		}
	}

	public class Goliath extends Unit {
		public Goliath() {
			super("Goliath", 5, 15, false, true);
		}
	}

	public class Wraith extends Unit {
		public Wraith() {
			super("Wraith", 3, 10, true, true);
		}
	}

	public class Valkyrie extends Unit {
		public Valkyrie() {
			super("Valkyrie", 4, 12, true, true);
		}
	}
}
