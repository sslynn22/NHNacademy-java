import java.util.List;
import java.util.ArrayList;

class Race {
	private List<Unit> units = new ArrayList<>();

	public Race(String type) {
		if (type.equalsIgnoreCase("Terran")) {
			Terran terran = new Terran();
			units.add(terran.new Marine());
			units.add(terran.new Tank());
			units.add(terran.new Goliath());
			units.add(terran.new Wraith());
			units.add(terran.new Valkyrie());
		}
		else if (type.equalsIgnoreCase("Protos")) {
			Protos protos = new Protos();
			units.add(protos.new Zealot());
			units.add(protos.new Dragoon());
			units.add(protos.new HighTempler());
			units.add(protos.new Scout());
			units.add(protos.new Corsair());
		}
		else if (type.equalsIgnoreCase("Zerg")) {
			Zerg zerg = new Zerg();
			units.add(zerg.new Zergling());
			units.add(zerg.new Hydralisk());
			units.add(zerg.new Ultralisk());
			units.add(zerg.new Mutalisk());
			units.add(zerg.new Guardian());
		}
	}

	public List<Unit> getUnits() {
		return units;
	}
}
