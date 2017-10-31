package lexico;

import java.io.File;

public class Generador {

	public Generador() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="C:/Users/jordy/Dropbox/Procesadores2/src/lexico/";
		String archivo=path+"lenguaje.lex";
		File file=new File(archivo);
		jflex.Main.generate(file);
	}

}
