package aufgabe3;

public class TelVerbindung {

	public final TelKnoten u;
	public final TelKnoten v;
	
	/**
	 * Legt eine neue Telefonverbindung von u nach v an.
	 */
	public TelVerbindung(TelKnoten u, TelKnoten v) {
		this.u = u;
		this.v = v;
	}

	
}
