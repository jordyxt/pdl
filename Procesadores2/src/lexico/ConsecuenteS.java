package lexico;

import java.util.Stack;

public class ConsecuenteS {
	private Terminales t;
	private NoTerminales nt;
	private AccionSem acc;
	private Tipo tipo;
	private String valor;
	private Stack<Tipo>parametros=new Stack<Tipo>();;
	private int numparametros;
	private boolean sentenciacond=false;
	private boolean funcion=false;
	public ConsecuenteS(Terminales t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	public ConsecuenteS(NoTerminales nt) {
		// TODO Auto-generated constructor stub
		this.nt=nt;
	}
	public ConsecuenteS(AccionSem acc) {
		// TODO Auto-generated constructor stub
		this.acc=acc;
	}
	public Terminales getT() {
		return t;
	}
	public NoTerminales getNt() {
		return nt;
	}
	public AccionSem getAcc() {
		return acc;
	}
	public boolean esNoTerminal(){
		if (this.t==null && this.acc==null) {
			return true;
		}
		return false;
	}
	public boolean esTerminal(){
		if (this.nt==null && this.acc==null) {
			return true;
		}
		return false;
	}
	public boolean esAccion(){
		if (this.t==null && this.nt==null) {
			return true;
		}
		return false;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValor() {
		return valor;
	}
	public void setParametro(Tipo parametro) {
		this.parametros.add(0, parametro);
		this.numparametros=this.parametros.size();
	}
	public void setParametros(Stack<Tipo> parametros) {
		this.parametros = parametros;
	}
	public void setNumparametros(int numparametros) {
		this.numparametros = numparametros;
	}
	public int getNumparametros() {
		return numparametros;
	}
	public Stack<Tipo> getParametros() {
		return parametros;
	}
	public boolean comparaparamentros(Stack<Tipo> pila){
		if(this.parametros.size()!=pila.size()) return false;
		boolean encontrado=false;
		for (int i = 0; i < this.parametros.size() &&!encontrado; i++) {
			if (this.parametros.get(i)!=pila.get(i)) {
					encontrado=true;		
			}
		}
		return encontrado?false:true;
	}
	public boolean isSentenciacond() {
		return sentenciacond;
	}
	public void setSentenciacond(boolean sentenciacond) {
		this.sentenciacond = sentenciacond;
	}
	public void setFuncion(boolean funcion) {
		this.funcion = funcion;
	}
	public boolean isFuncion() {
		return funcion;
	}
}
