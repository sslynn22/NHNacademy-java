package basic_java.homework.starcraft;

public class Zerg {
	public class Zergling extends Unit {
		public Zergling() {
			super("Zergling", 2, 2, false, false);
		}
	}

	public class Hydralisk extends Unit {
		public Hydralisk() {
			super("Hydralisk", 3, 7, false, true);
		}
	}

	public class Ultralisk extends Unit {
		public Ultralisk() {
			super("Ultralisk", 5, 15, false, false);
		}
	}

	public class Mutalisk extends Unit {
		public Mutalisk() {
			super("Mutalisk", 2, 8, true, true);
		}
	}

	public class Guardian extends Unit {
		public Guardian() {
			super("Guardian", 3, 6, true, true);
		}
	}
}

