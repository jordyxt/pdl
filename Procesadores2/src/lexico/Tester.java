package lexico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

import lexico.Token;
import lexico.AnalizadorLexico;;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Reader reader = new BufferedReader(new FileReader("testeo.js"));
        AnalizadorLexico lexer = new AnalizadorLexico(reader);
        //File file1=new File("lexicotest.txt");
	   BufferedWriter bw = new BufferedWriter(new FileWriter("lexicotokens"));
	   BufferedWriter bw2 = new BufferedWriter(new FileWriter("TablaSimbolos"));
	   BufferedWriter bw3 = new BufferedWriter(new FileWriter("Parse"));
	   BufferedWriter bw4 = new BufferedWriter(new FileWriter("Errores"));
	   boolean errorLexico=false;
	   boolean errorSintactico=false;
	   boolean errorSemantico=false;
	   OperadorSemantico op=new OperadorSemantico();
	   //Estructura Sintactico
	   Stack<Consecuente>pila=new Stack<Consecuente>();
	   Queue<Terminales>pilatokens=new LinkedList<Terminales>();
	   Queue<String>pilatabla=new LinkedList<String>();
	   Queue<String>pilalex=new LinkedList<String>();
		pila.push(new Consecuente(Terminales.$));
		pila.push(new Consecuente(NoTerminales.P));
		boolean fin=false;
		boolean eof=false;
		int linea=1;
		boolean saltar=false;
		while (!eof&&!fin && !errorLexico && !errorSintactico &&  !errorSemantico) {
			Terminales token =lexer.yylex();
			if(saltar)linea++;
			saltar=false;
			boolean comentario=false;
			if (token==null) {
				pilatokens.add(Terminales.EOF);
				pilatabla.add(op.getActualTSs());
				pilalex.add("eof");
				token=Terminales.EOF;	
				eof=true;
				//System.out.println("eof");
			}else{
				switch (token) {
				case ERROR:
					//System.out.println("Error lexico");
					errorLexico=true;
					break;
				case COMENTARIO:
					//se entiende que son los comentarios
					comentario=true;
					break;
				case ENT:
					if (Integer.parseInt(lexer.yytext())>32767) {
						errorLexico=true;
					}
					
					break;
				case SALTO:
					saltar=true;
					break;
				}
				pilatokens.add(token);
				pilatabla.add(op.getActualTSs());
				pilalex.add(lexer.yytext());
			}

			if (!errorLexico &&!comentario) {
				op.realizaroperacion(token, lexer.yytext());
				errorSintactico=op.isResultadoSintactico();
				errorSemantico=op.isResultadoSemantico();
				fin=op.isFin();
			}
		}
		
		if(errorLexico){
			System.out.println("Error Lexico");
			bw4.write("Error Lexico");
			bw4.newLine();
			System.out.println("En linea: "+ linea);
			bw4.write("En linea: "+ linea);
			bw4.newLine();
		}
		if (errorSintactico) {
			System.out.println("Error Sintactico");
			bw4.write("Error Sintactico");
			bw4.newLine();
			System.out.println("En linea: "+ linea);
			bw4.write("En linea: "+ linea);
			bw4.newLine();
		}
		if (errorSemantico) {
			System.out.println("Error Semantico");
			bw4.write("Error Semantico");
			bw4.newLine();
			System.out.println("En linea: "+ linea);
			bw4.write("En linea: "+ linea);
			bw4.newLine();
		}
		while (!pilatokens.isEmpty()) {
			Terminales tokend=pilatokens.poll();
			String tablad=pilatabla.poll();
			String lex=pilalex.poll();
			switch (tokend) {
			case IF:
				bw.write("<PALRES,if>");
				bw.newLine();
				break;
			case WHILE:
				bw.write("<PALRES,while>");
				bw.newLine();
				break;
			case DO:
				bw.write("<PALRES,do>");
				bw.newLine();
				break;
			case FUNCTION:
				bw.write("<PALRES,function>");
				bw.newLine();
				break;
			case RETURN:
				bw.write("<PALRES,return>");
				bw.newLine();
				break;
			case VAR:
				bw.write("<PALRES,var>");
				bw.newLine();
				break;
			case INT:
				bw.write("<PALRES,int>");
				bw.newLine();
				break;
			case CHARS:
				bw.write("<PALRES,chars>");
				bw.newLine();
				break;
			case BOOL:
				bw.write("<PALRES,bool>");
				bw.newLine();
				break;
			case WRITE:
				bw.write("<PALRES,write>");
				bw.newLine();
				break;
			case PROMPT:
				bw.write("<PALRES,prompt>");
				bw.newLine();
				break;
			case PARENIZ:
				bw.write("<PARENIZ,>");
				bw.newLine();
				break;
			case PARENDE:
				bw.write("<PARENDE,>");
				bw.newLine();
				break;
			case LLAVEIZ:
				bw.write("<LLAVEIZ,>");
				bw.newLine();
				break;
			case LLAVEDE:
				bw.write("<LLAVEDE,>");
				bw.newLine();
				break;
			case COMA:
				bw.write("<COMA,>");
				bw.newLine();
				break;
			case SALTO:
				bw.write("<SALTO,>");
				bw.newLine();
				break;
			case ID:
				int p=0;
				//System.out.println(tablad);
				if(op.getTs().get(tablad).contieneEntrada(lex)){
					//System.out.println("Estoy en la tabla");
					p=op.getTs().get(tablad).saberposicion(lex);
				}else {
					if (op.getTs().get("Tabla Principal").contieneEntrada(lex)) {
						//System.out.println("tabla principal");
						p=op.getTs().get("Tabla Principal").saberposicion(lex);
					}
				}
				bw.write("<ID,"+p+">");
				bw.newLine();
				break;
			case ENT:
				bw.write("<ENT,"+lex+">");
				bw.newLine();
				break;
			case CAD:
				char[]cad=lex.toCharArray();
				String cad2="";
				for (int i = 1; i < cad.length-1; i++) {
					cad2=cad2+cad[i];
				}
				bw.write("<CAD,"+cad2+">");
				bw.newLine();
				break;
			case IGUAL:
				bw.write("<IGUAL,>");
				bw.newLine();
				break;
			case AND:
				bw.write("<AND,>");
				bw.newLine();
				break;
			case ASSIGNY:
				bw.write("<ASIGNY,>");
				bw.newLine();
				break;
			case ASSIGN:
				bw.write("<ASIGN,>");
				bw.newLine();
				break;
			case NOIGUAL:
				bw.write("<NOIGUAL,>");
				bw.newLine();
				break;
			case MENOR:
				bw.write("<MENOR,>");
				bw.newLine();
				break;
			case MAYOR:
				bw.write("<MAYOR,>");
				bw.newLine();
				break;
			case SUMA:
				bw.write("<SUMA,>");
				bw.newLine();
				break;
			case RESTA:
				bw.write("<RESTA,>");
				bw.newLine();
				break;
			case MULT:
				bw.write("<MULT,>");
				bw.newLine();
				break;
			case DIV:
				bw.write("<DIV,>");
				bw.newLine();
				break;
			case EOF:
				bw.write("<EOF,>");	
				break;
			}

		}
		int cont=1;
		int conts=2;
		for (Entry<String, TabladeSimbolos> e : op.getTs().entrySet()) {
			//System.out.println("Tabla: "+e.getKey());
			bw2.write("#"+cont+":");
			bw2.newLine();
			for (Entry<String, EntradaTS> f : e.getValue().getTabla().entrySet()) {
				//System.out.println(f.getKey());
				bw2.write("*'"+f.getKey()+"'");
				bw2.newLine();
				if (f.getValue().isFuncion()) {
					//System.out.println("..funcion..");
				}
				//System.out.println("Tipo: "+f.getValue().getTipo());
				String tipo="";
				switch (f.getValue().getTipo()) {
				case INT:
					tipo="int";
					break;
				case BOOL:
					tipo="bool";
					break;
				case CHARS:
					tipo="chars";
					break;
				case VOID:
					tipo="void";
					break;
				}
				bw2.write("+tipo:'"+tipo+"'");
				bw2.newLine();
				if (f.getValue().isFuncion()) {
					//System.out.println("Parametros: ");
					bw2.write("+parametros:"+f.getValue().getParametros().size());
					bw2.newLine();
					for (int i = 0; i < f.getValue().getParametros().size(); i++) {
						//System.out.println(" "+f.getValue().getParametros().get(i));
						String tipoparam="";
						switch (f.getValue().getParametros().get(i)) {
						case INT:
							tipoparam="int";
							break;
						case BOOL:
							tipoparam="bool";
							break;
						case CHARS:
							tipoparam="chars";
							break;
						}
						bw2.write("+tipoparam"+(i+1)+":'"+tipoparam+"'");
						bw2.newLine();
					}
					bw2.write("+idtabla:"+conts);
					bw2.newLine();
					conts++;
				}
			}
			cont++;
		}
		bw3.write("Des ");
		while(!op.getListaparse().isEmpty()){
			bw3.write(op.getListaparse().poll()+" ");
		}
		if(!errorLexico && !errorSintactico && !errorSemantico){
			System.out.println("todo va bien");
		
		}
		bw4.close();
		bw3.close();
		bw2.close();
		bw.close();

	}
}
