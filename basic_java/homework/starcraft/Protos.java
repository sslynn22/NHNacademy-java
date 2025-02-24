package basic_java.homework.starcraft;

public class Protos {
	public class Zealot extends Unit {
		public Zealot() {
			super("Zealot", 5, 20, false, false);
		}
	}

	public class Dragoon extends Unit {
		public Dragoon() {
			super("Dragoon", 3, 15, false, true);
		}
	}

	public class HighTempler extends Unit {
		public HighTempler() {
			super("HighTempler", 10, 2, false, false);
		}
	}

	public class Scout extends Unit {
		public Scout() {
			super("Scout", 5, 10, true, true);
		}
	}

	public class Corsair extends Unit {
		public Corsair() {
			super("Corsair", 4, 12, true, true);
		}
	}
}