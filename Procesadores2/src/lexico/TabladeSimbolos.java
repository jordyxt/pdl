package lexico;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TabladeSimbolos {
	private Map<String,EntradaTS> tabla;
	private int posicion;
	public TabladeSimbolos() {
		// TODO Auto-generated constructor stub
		this.tabla= new LinkedHashMap<String,EntradaTS>();
		this.posicion=0;
	}
	public Map<String, EntradaTS> getTabla() {
		return tabla;
	}
	public int getPosicion() {
		return posicion;
	}
	public boolean agregarentrada(String id,boolean funcion){
		if (tabla.containsKey(id)) {
			return false;
		}
		EntradaTS entrada=new EntradaTS(id,this.posicion,funcion);
		this.posicion++;
		//entrada.setpuntero(puntero);
		tabla.put(id, entrada);
		return true;
	}
	public boolean contieneEntrada(String id){
		if (tabla.containsKey(id)) {
			return true;
		}
		return false;
	}
	public Tipo sabertipo(String id){
		return this.tabla.get(id).getTipo();
	}
	public void agregartipo(String id,Tipo tipo){
		this.tabla.get(id).setTipo(tipo);	
	}
	public Stack<Tipo> saberparametros(String id){
		return this.tabla.get(id).getParametros();
	}
	public void agregarparametros(String id,Stack<Tipo>pila){
		this.tabla.get(id).setParametros(pila);
	}
	public boolean saberesfuncion(String id){
		return this.tabla.get(id).isFuncion();
	}
	public int saberposicion(String id){
		return this.tabla.get(id).getPosicion();
	}
}
