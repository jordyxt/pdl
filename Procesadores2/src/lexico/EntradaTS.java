package lexico;

import java.util.Stack;

public class EntradaTS {
	private String nombre;
	private int desplazamiento;
	private int posicion;
	private Tipo tipo;
	private Stack<Tipo>parametros;
	private int numparametros;
	private boolean funcion;
	public EntradaTS(String nombre, int posicion,boolean funcion) {
		// TODO Auto-generated constructor stub
		this.nombre=nombre;
		this.posicion=posicion;
		this.funcion=funcion;
	}
	public void setDesplazamiento(int desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
	public int getDesplazamiento() {
		return desplazamiento;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setTipo(Tipo tipo){
		this.tipo=tipo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setParametro(Tipo parametro) {
		this.parametros.push(parametro);
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
	public boolean isFuncion() {
		return funcion;
	}
	
}
