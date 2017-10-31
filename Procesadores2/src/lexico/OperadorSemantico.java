package lexico;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class OperadorSemantico {

// private Stack<TabladeSimbolos> pilats;
private Map<String,TabladeSimbolos> ts;
private boolean resultadoSintactico;  
private boolean resultadoSemantico;
private Stack<ConsecuenteS> p;
private Stack<ConsecuenteS> aux;
private TabladeSimbolos actualTS;
private String actualTSs;
private boolean fin;
private Queue<Integer>listaparse;
public OperadorSemantico() {
	// TODO Auto-generated constructor stub
	this.ts=new LinkedHashMap<String,TabladeSimbolos>();
	this.resultadoSintactico=false;
	this.resultadoSemantico=false;
	this.fin=false;
	this.p=new Stack<ConsecuenteS>();
	this.p.push(new ConsecuenteS(Terminales.$));
	this.p.push(new ConsecuenteS(NoTerminales.PP));
	this.aux=new Stack<ConsecuenteS>();
	this.actualTSs="Tabla Principal";
	this.listaparse=new LinkedList<Integer>();
}
public Map<String, TabladeSimbolos> getTs() {
	return ts;
}
public boolean isResultadoSemantico() {
	return resultadoSemantico;
}
public boolean isResultadoSintactico() {
	return resultadoSintactico;
}
public boolean isFin() {
	return fin;
}
public TabladeSimbolos getActualTS() {
	return actualTS;
}
public String getActualTSs() {
	return actualTSs;
}
public Queue<Integer> getListaparse() {
	return listaparse;
}
public void realizaroperacion(Terminales token, String valor){
	boolean seguir=true;
	//System.out.println("Token entrante: "+token);
	while (!this.resultadoSintactico &&  !this.resultadoSemantico && seguir && !fin) {
		AntescedentesS regla;
		AntescedentesParse parse;
		ConsecuenteS cimapila=this.p.pop();
		if (cimapila.esNoTerminal()) {
			this.aux.push(cimapila);
			//System.out.println(cimapila.getNt());
			//System.out.println("imprimir regla");
			regla=new AntescedentesS(cimapila.getNt());
			parse=new AntescedentesParse(cimapila.getNt());
			//al momento de buscar la regla per esta no esta se produce un error sintactico
			Stack<ConsecuenteS>con=regla.buscar(token);
			if(con!=null){
				//System.out.println(parse.buscar(token));
				listaparse.add(parse.buscar(token));
				while (!con.empty()) {
					ConsecuenteS cs=con.pop();								
					if (cs.esNoTerminal()) {
						//System.out.println(" "+cs.getNt());
						this.p.push(new ConsecuenteS(cs.getNt()));
					}
					else if(cs.esTerminal()){
						//Si es lambda se obvia y no se pasa a la pila
						//System.out.println(" "+cs.getT());	
						if (cs.getT()!=Terminales.lambda) {
							this.p.push(new ConsecuenteS(cs.getT()));
						}
					}else {
						//System.out.println(" "+cs.getAcc());
						this.p.push(new ConsecuenteS(cs.getAcc()));
					}
				}
			}else{
				
				//System.out.println("error sintactico");
				this.resultadoSintactico=true;	
			}
		}else if (cimapila.esAccion()) {
			//System.out.println(cimapila.getAcc());
			this.resultadoSemantico=!realizaraccion(cimapila.getAcc(), valor);
		}else {
			//System.out.println(cimapila.getT());
			if (cimapila.getT()==token) {
				if (cimapila.getT()==Terminales.ID) {
					cimapila.setValor(valor);
				}
				this.aux.push(cimapila);
				//System.out.println("pedir otro token");
				seguir=false;
			}
			else {
				//System.out.println("No se va a dar");
				//System.out.println("token $");
				if(cimapila.getT()==Terminales.$){
					seguir=false;
					this.fin=true;	
				}else{
					this.resultadoSintactico=true;
				}
			}
		}
		
	}

	}

public boolean realizaraccion(AccionSem accion,String valor){
	boolean res=true;
	switch (accion) {
	case PP1:
		//System.out.println("Crear la pila Principal");
		TabladeSimbolos nueva=new TabladeSimbolos();
		this.ts.put("Tabla Principal",nueva);
		this.actualTS=this.ts.get("Tabla Principal");
		this.actualTSs="Tabla Principal";
		break;
	case PP2:
		this.aux.pop();
		break;
	case P11:
		ConsecuenteS conBp11=this.aux.peek();
		//System.out.println("Tipo: "+conBp11.getTipo());
		if (conBp11.getTipo()!=Tipo.NO) {
			return false;
		}
		break;
	case P12:
	case P2: 
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		break;
	case P3:
		this.aux.pop();
		this.aux.pop();
		break;
	case P4:
		this.aux.pop();
		break;
	case T1:
		this.aux.pop();
		this.aux.peek().setTipo(Tipo.INT);
		break;
	case T2:
		this.aux.pop();
		this.aux.peek().setTipo(Tipo.CHARS);
		break;
	case T3:
		this.aux.pop();
		this.aux.peek().setTipo(Tipo.BOOL);
		break;
	case B1:
		ConsecuenteS conidb1=this.aux.pop();
		//System.out.println("id: "+conidb1.getValor());
		if(!actualTS.agregarentrada(conidb1.getValor(),false))return false;
		ConsecuenteS contipob1=this.aux.pop();
		Tipo tipo=contipob1.getTipo();
		//System.out.println("Tipo: "+tipo);
		actualTS.agregartipo(conidb1.getValor(),tipo);
		this.aux.pop();
		ConsecuenteS conBb1=this.aux.peek();
		conBb1.setTipo(Tipo.NO);
		break;
	case B21:
		ConsecuenteS conEb21=this.aux.peek();
		if (conEb21.getTipo()!=Tipo.BOOL) {
			return false;
		}
		break; 
	case B22:
		ConsecuenteS conSb22=this.aux.pop();
		this.aux.pop();
		//ConsecuenteS conBb22=this.aux.get(this.aux.size()-5);
		//conSb22.setTipo(conBb22.getTipo());
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		ConsecuenteS conBb22=this.aux.peek();
		conBb22.setTipo(conSb22.getTipo());
		conBb22.setSentenciacond(true);
		break; 

	case B31:
		ConsecuenteS conEb31=this.aux.peek();
		//System.out.println("Tipo: "+conEb31.getTipo()+" "+conEb31.getNt());
		//conEb31.setTipo(Tipo.BOOL);
		if (conEb31.getTipo()!=Tipo.BOOL) {
			return false;
		}
		break;
	case B32:
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		ConsecuenteS conCb32=this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		ConsecuenteS conBb32=this.aux.peek();
		conBb32.setTipo(conCb32.getTipo());
		conBb32.setSentenciacond(true);
		break;
	case B41:
		ConsecuenteS conSb41=this.aux.pop();
		ConsecuenteS conBb41=this.aux.peek();
		conBb41.setTipo(conSb41.getTipo());
		//System.out.println("Tipo: "+conBb41.getTipo());
		break;
	case C11:
		ConsecuenteS conC1c11=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conBc11=this.aux.pop();
		ConsecuenteS conC2c11=this.aux.peek();
		if((conBc11.getTipo()!=Tipo.NO && !conBc11.isSentenciacond() && conC1c11.getTipo()!=null)
			|| (conC1c11.getTipo()!=null && conBc11.getTipo()!=Tipo.NO && conC1c11.getTipo()!=Tipo.NO 
			&& conBc11.getTipo()!=conC1c11.getTipo())){
			return false;
		}
		if ((conBc11.getTipo()!=Tipo.NO )||
			(conBc11.getTipo()==Tipo.NO && conC1c11.getTipo()==null)) {
			conC2c11.setTipo(conBc11.getTipo());
		}else {
			conC2c11.setTipo(conC1c11.getTipo());
		}
		break;
	case S11:
		ConsecuenteS conXs11=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conSs11=this.aux.peek();
		conSs11.setTipo(conXs11.getTipo());
		break;
	case S21:
		ConsecuenteS conSPs21=this.p.peek();
		ConsecuenteS conids21=this.aux.peek();
		//System.out.println("id: "+conids21.getValor() );
		if(this.actualTS.contieneEntrada(conids21.getValor())){
			conSPs21.setTipo(this.actualTS.sabertipo(conids21.getValor()));
			//System.out.println("Tipo: "+conSPs21.getTipo());
			if(this.actualTS.saberesfuncion(conids21.getValor())){
				conSPs21.setParametros(this.actualTS.saberparametros(conids21.getValor()));
				conSPs21.setFuncion(true);
			}
		}else{
			if (this.ts.get("Tabla Principal").contieneEntrada(conids21.getValor())) {
				conSPs21.setTipo(this.ts.get("Tabla Principal").sabertipo(conids21.getValor()));
				//System.out.println("Tipo: "+conSPs21.getTipo());
				if(this.ts.get("Tabla Principal").saberesfuncion(conids21.getValor())){
					conSPs21.setParametros(this.ts.get("Tabla Principal").saberparametros(conids21.getValor()));
					conSPs21.setFuncion(true);
				}
			}else{
				return false;
			}
		}
		break;
	case S22:
		this.aux.pop();
		this.aux.pop(); 
		ConsecuenteS conSs22=this.aux.peek();
		conSs22.setTipo(Tipo.NO);
		break;
	case S31:
		this.aux.pop();
		this.aux.pop(); 
		this.aux.pop();
		this.aux.pop(); 
		ConsecuenteS conSs31=this.aux.peek();
		conSs31.setTipo(Tipo.NO);
		break;
	case S41:
		this.aux.pop();
		ConsecuenteS conids41=this.aux.pop();
		if (!this.actualTS.contieneEntrada(conids41.getValor()) || 
			this.actualTS.saberesfuncion(conids41.getValor()) || 
			this.actualTS.sabertipo(conids41.getValor())==Tipo.BOOL){
			return false;
		}
		this.aux.pop();
		this.aux.pop();
		ConsecuenteS conSs41=this.aux.peek();
		conSs41.setTipo(Tipo.NO);
		break;
		
//	case W12:
//		ConsecuenteS conidw11=this.aux.peek();
//		ConsecuenteS conWPw11=this.p.peek();
//		if(this.actualTS.contieneEntrada(conidw11.getValor())){
//			conidw11.setTipo(this.actualTS.sabertipo(conidw11.getValor()));
//			conidw11.setParametros(this.actualTS.saberparametros(conidw11.getValor()));
//			conWPw11.setParametros(conidw11.getParametros());
//		}else {
//			return false;
//		}
//		
//		break;
	case SP1:
		ConsecuenteS conEsp1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conSPsp1=this.aux.peek();
		//System.out.println("Tipo: "+conEsp1.getTipo()+" "+conEsp1.getNt() );
		//System.out.println("Tipo: "+conSPsp1.getTipo()+" "+conSPsp1.getNt());
		if (conSPsp1.getTipo()!=conEsp1.getTipo() || conSPsp1.isFuncion()) {
			return false;
		}
		break;
	case SP2:
		this.aux.pop();
		ConsecuenteS conLsp2=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conSPsp2=this.aux.peek();
		if ( !conSPsp2.isFuncion()||!conSPsp2.comparaparamentros(conLsp2.getParametros())) {
			return false;
		}
		break;
	case SP3:
		ConsecuenteS conEsp3=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conSPsp3=this.aux.peek();
		if (conEsp3.getTipo()!=Tipo.BOOL || conSPsp3.getTipo()!=Tipo.BOOL ||
				conSPsp3.isFuncion()) {
			return false;
		}
		break;
	case X1:
		ConsecuenteS conEx1=this.aux.pop();
		ConsecuenteS conXx1=this.aux.peek();
		conXx1.setTipo(conEx1.getTipo());
		break;
	case X2:
		ConsecuenteS conXx2=this.aux.peek();
		conXx2.setTipo(Tipo.VOID);
		break;
	case L1:
		ConsecuenteS conQl1=this.aux.pop();
		ConsecuenteS conEl1=this.aux.pop();
		ConsecuenteS conLl1=this.aux.peek();
		conQl1.setParametro(conEl1.getTipo());
		conLl1.setParametros(conQl1.getParametros());
		break;
	case Q1:
		ConsecuenteS conQq1=this.aux.pop();
		ConsecuenteS conEq1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conLq1=this.aux.peek();
		conQq1.setParametro(conEq1.getTipo());
		conLq1.setParametros(conQq1.getParametros());
		break;
	case E1:
		ConsecuenteS conEPe1=this.aux.pop();
		ConsecuenteS conRe1=this.aux.pop();
		ConsecuenteS conEe1=this.aux.peek();
		//System.out.println("Tipo: "+ conRe1.getTipo()+" "+ conRe1.getNt());
		//System.out.println("Tipo: "+conEPe1.getTipo()+" "+conEPe1.getNt());
		if(conEPe1.getTipo()!=null && conRe1.getTipo()!=Tipo.BOOL){
			return false;
		}
		conEe1.setTipo(conRe1.getTipo());
		//System.out.println("Tipo: "+conEe1.getTipo()+" "+conEe1.getNt());
		break;
	case EP1:
		this.aux.pop();
		ConsecuenteS conRep1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conEP2ep1=this.aux.peek();
		if (conRep1.getTipo()!=Tipo.BOOL) {
			return false;
		}
		conEP2ep1.setTipo(Tipo.BOOL);
		break;
	case R1:
		ConsecuenteS conRPr1=this.aux.pop();
		ConsecuenteS conUr1=this.aux.pop();
		ConsecuenteS conRr1=this.aux.peek();
		//System.out.println("Tipo: "+ conUr1.getTipo()+" "+ conUr1.getNt());
		//System.out.println("Tipo: "+conRPr1.getTipo()+" "+conRPr1.getNt());
		if(conRPr1.getTipo()!=null && conUr1.getTipo()!=Tipo.INT){
			return false;
		}
		if (conRPr1.getTipo()!=null ) {
			conRr1.setTipo(conRPr1.getTipo());
		}else{
			conRr1.setTipo(conUr1.getTipo());
		}
		break;
	case RP1:
		//ConsecuenteS conRP1rp1=this.aux.pop();
		ConsecuenteS conUrp1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conRP2rp1=this.aux.peek();
		//System.out.println("Tipo: "+ conUrp1.getTipo()+" "+ conUrp1.getNt());
		if (conUrp1.getTipo()!=Tipo.INT ) {
			return false;
		}
		conRP2rp1.setTipo(Tipo.BOOL);
		break;
	case U1:
		ConsecuenteS conUPu1=this.aux.pop();
		ConsecuenteS conVu1=this.aux.pop();
		ConsecuenteS conUu1=this.aux.peek();
		if(conUPu1.getTipo()!=null && conVu1.getTipo()!=Tipo.INT){
			return false;
		}
		if (conUPu1.getTipo()!=null ) {
			conUu1.setTipo(conUPu1.getTipo());
		}else{
			conUu1.setTipo(conVu1.getTipo());
		}
		break;
	case UP1:
		this.aux.pop();
		ConsecuenteS conVup1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conUP2up1=this.aux.peek();
		if (conVup1.getTipo()!=Tipo.INT) {
			return false;
		}
		conUP2up1.setParametro(Tipo.INT);
		break;
	case V1:
		ConsecuenteS conVPv1=this.aux.pop();
		ConsecuenteS conWv1=this.aux.pop();
		ConsecuenteS conVv1=this.aux.peek();
		if(conVPv1.getTipo()!=null && conWv1.getTipo()!=Tipo.INT){
			return false;
		}
		if (conVPv1.getTipo()!=null ) {
			conVv1.setTipo(conVPv1.getTipo());
		}else{
			conVv1.setTipo(conWv1.getTipo());
		}
		break;
	case VP1:
		this.aux.pop();
		ConsecuenteS conWvp1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conVP2vp1=this.aux.peek();
		if (conWvp1.getTipo()!=Tipo.INT) {
			return false;
		}
		conVP2vp1.setParametro(Tipo.INT);
		break;
	case W11:
		ConsecuenteS conWPw11=this.p.peek();
		ConsecuenteS conidw11=this.aux.peek();
		if(this.actualTS.contieneEntrada(conidw11.getValor())){
			conidw11.setTipo(this.actualTS.sabertipo(conidw11.getValor()));
			conWPw11.setTipo(this.actualTS.sabertipo(conidw11.getValor()));
			if(this.actualTS.saberesfuncion(conidw11.getValor())){
				conWPw11.setParametros(this.actualTS.saberparametros(conidw11.getValor()));
				conWPw11.setFuncion(true);
			}
		}else{
			if(this.ts.get("Tabla Principal").contieneEntrada(conidw11.getValor())){
				conidw11.setTipo(this.ts.get("Tabla Principal").sabertipo(conidw11.getValor()));
				conWPw11.setTipo(this.ts.get("Tabla Principal").sabertipo(conidw11.getValor()));
				if(this.ts.get("Tabla Principal").saberesfuncion(conidw11.getValor())){
					conWPw11.setParametros(this.ts.get("Tabla Principal").saberparametros(conidw11.getValor()));
					conWPw11.setFuncion(true);
				}
			}else{
				return false;
			}
		}
		break;
	case W12:
		this.aux.pop();
		ConsecuenteS conidw12=this.aux.pop(); 
		ConsecuenteS conWw12=this.aux.peek();
		conWw12.setTipo(conidw12.getTipo());
		break;
//	case W11:
//		ConsecuenteS conWPw11=this.aux.pop();
//		ConsecuenteS conidw11=this.aux.pop(); 
//		if (this.actualTS.contieneEntrada(conidw11.getValor())) {
//			if(this.actualTS.saberesfuncion(conidw11.getValor())){
//				conidw11.setParametros(this.actualTS.saberparametros(conidw11.getValor()));
//				if(conWPw11.getParametros()==null || !conWPw11.comparaparamentros(conidw11.getParametros())){
//					return false;
//				}
//			}else {
//				if(conWPw11.getParametros()!=null){
//					return false;
//				}
//			}
//			conidw11.setTipo(this.actualTS.sabertipo(conidw11.getValor()));
//		}else {
//			return false;
//		}
//		ConsecuenteS conWw11=this.aux.peek();
//		conWw11.setTipo(conidw11.getTipo());
//		break;
	case W21:
		this.aux.pop();
		ConsecuenteS conWw21=this.aux.peek();
		conWw21.setTipo(Tipo.INT);
		break;
	case W31:
		this.aux.pop();
		ConsecuenteS conWw31=this.aux.peek();
		conWw31.setTipo(Tipo.CHARS);
		break;
	case W41:
		this.aux.pop();
		ConsecuenteS conEw41=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conWw41=this.aux.peek();
		conWw41.setTipo(conEw41.getTipo());
		break; 
	case WP11:
//		this.aux.pop();
//		ConsecuenteS conLwp11=this.aux.pop();
//		this.aux.pop();
//		ConsecuenteS conWPwp11=this.aux.peek();
//		conWPwp11.setParametros(conLwp11.getParametros());
//		break;
		this.aux.pop();
		ConsecuenteS conLwp11=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conWPwp11=this.aux.peek();
		if ( !conWPwp11.isFuncion()||!conWPwp11.comparaparamentros(conLwp11.getParametros())) {
			return false;
		}
		break;
	case WP21:
		ConsecuenteS conWPwp21=this.aux.peek();
		if (conWPwp21.isFuncion()) {
			return false;
		}
		break;
	case H11:
		ConsecuenteS conTh11=this.aux.pop();
		ConsecuenteS conHh11=this.aux.peek();
		conHh11.setTipo(conTh11.getTipo());
		break;
	case H12:
		ConsecuenteS conHh12=this.aux.peek();
		conHh12.setTipo(Tipo.VOID);
		break;
	case A11:
		ConsecuenteS conida11=this.aux.peek();
		ConsecuenteS conTa11=this.aux.get(this.aux.size()-2);
		if (!this.actualTS.agregarentrada(conida11.getValor(), false)) {
			return false;
		}
		this.actualTS.agregartipo(conida11.getValor(),conTa11.getTipo());
		break;
	case A12:
		ConsecuenteS conKa1=this.aux.pop();
		ConsecuenteS conida1=this.aux.pop();
		ConsecuenteS conTa1=this.aux.pop();
		ConsecuenteS conAa1=this.aux.peek();
//		if (!this.actualTS.agregarentrada(conida1.getValor(), false)) {
//			return false;
//		}
//		this.actualTS.agregartipo(conida1.getValor(),conTa1.getTipo());
		conKa1.setParametro(conTa1.getTipo());
		conAa1.setParametros(conKa1.getParametros());
		break;
	case K11:
		ConsecuenteS conidk11=this.aux.peek();
		ConsecuenteS conTk11=this.aux.get(this.aux.size()-2);
		if (!this.actualTS.agregarentrada(conidk11.getValor(), false)) {
			return false;
		}
		this.actualTS.agregartipo(conidk11.getValor(),conTk11.getTipo());
		break;
	case K12:
		ConsecuenteS conKk1=this.aux.pop();
		ConsecuenteS conidk1=this.aux.pop();
		ConsecuenteS conTk1=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conAk1=this.aux.peek();
//		if (!this.actualTS.agregarentrada(conidk1.getValor(), false)) {
//			return false;
//		}
//		this.actualTS.agregartipo(conidk1.getValor(),conTk1.getTipo());
		conKk1.setParametro(conTk1.getTipo());
		conAk1.setParametros(conKk1.getParametros());
		break;
	case F11:
		ConsecuenteS conidf11=this.aux.peek();
//		ConsecuenteS conHf11=this.aux.get(this.aux.size()-2);
//		if (!this.actualTS.agregarentrada(conidf11.getValor(), true)) {
//			return false;
//		}
//		this.actualTS.agregartipo(conidf11.getValor(), conHf11.getTipo());
		this.ts.put(conidf11.getValor(), new TabladeSimbolos());
		this.actualTS=this.ts.get(conidf11.getValor());
		this.actualTSs=conidf11.getValor();
		break;
	case F12:
		ConsecuenteS conAf12=this.aux.peek();
		ConsecuenteS conidf12=this.aux.get(this.aux.size()-3);
		ConsecuenteS conHf12=this.aux.get(this.aux.size()-4);
		if(!this.ts.get("Tabla Principal").agregarentrada(conidf12.getValor(), true)){
			return false;
		}
		this.ts.get("Tabla Principal").agregartipo(conidf12.getValor(), conHf12.getTipo());
		this.ts.get("Tabla Principal").agregarparametros(conidf12.getValor(), conAf12.getParametros());
		break;
	case F13:
		this.aux.pop();
		ConsecuenteS conCf13=this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		this.aux.pop();
		ConsecuenteS conAf13=this.aux.pop();
		this.aux.pop();
		ConsecuenteS conidf13=this.aux.pop();
		ConsecuenteS conHf13=this.aux.pop();
		this.aux.pop();
		this.actualTS=this.ts.get("Tabla Principal");
		this.actualTSs="Tabla Principal";
//		if (!this.actualTS.agregarentrada(conidf13.getValor(), true)) {
//			return false;
//		}
//		this.actualTS.agregartipo(conidf13.getValor(), conHf13.getTipo());
//		this.actualTS.agregarparametros(conidf13.getValor(), conAf13.getParametros());
		//System.out.println("Tipo: "+(conHf12.getTipo()+" "+conHf12.getNt()));
		//System.out.println("Tipo: "+(conCf12.getTipo()+" "+conCf12.getNt()));
		if((conHf13.getTipo()==Tipo.VOID &&conCf13.getTipo()!=Tipo.NO && conCf13.getTipo()!=Tipo.VOID) ||
		   (conHf13.getTipo()!=Tipo.VOID && conHf13.getTipo()!=conCf13.getTipo())) {
			return false;
		}
		break;
	case Z1:
	case ZP1:
		this.aux.pop();
		this.aux.pop();
		break;
	default:
		
		break;
	}
	
	return res;
}
}
