package lexico;

public class Consecuente {
	private Terminales t;
	private NoTerminales nt;
	private Tipo tipo;
	public Consecuente(Terminales t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	public Consecuente(NoTerminales nt) {
		// TODO Auto-generated constructor stub
		this.nt=nt;
	}
	public Terminales getT() {
		return t;
	}
	public NoTerminales getNt() {
		return nt;
	}
	public boolean esNoTerminal(){
		if (this.t==null) {
			return true;
		}
		return false;
	}

}
