package lexico;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Antescedentes {
	private NoTerminales ante;
	//private Stack<Consecuente> cons;
	
	public Antescedentes(NoTerminales ante) {
		// TODO Auto-generated constructor stub
		this.ante=ante;
	}
	public Stack<Consecuente> buscar (Terminales t){
		Stack<Consecuente> cons=new Stack<Consecuente>();
		switch (this.ante) {
		case P:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new Consecuente(NoTerminales.B));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.P));
			}
			else if (t==Terminales.FUNCTION) {
				cons.push(new Consecuente(NoTerminales.F));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.P));
			}
			else if (t==Terminales.SALTO) {
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.P));
			}
			else if (t==Terminales.EOF) {
				cons.push(new Consecuente(Terminales.EOF));
			}
			else {
				return null;
			}
			break;
		case B:
			if (t==Terminales.VAR) {
				cons.push(new Consecuente(Terminales.VAR));
				cons.push(new Consecuente(NoTerminales.T));
				cons.push(new Consecuente(Terminales.ID));
			}
			else if (t==Terminales.IF) {
				cons.push(new Consecuente(Terminales.IF));
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(Terminales.PARENDE));
				cons.push(new Consecuente(NoTerminales.S));
			}
			else if (t==Terminales.DO) {
				cons.push(new Consecuente(Terminales.DO));
				cons.push(new Consecuente(Terminales.LLAVEIZ));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.C));
				cons.push(new Consecuente(Terminales.LLAVEDE));
				cons.push(new Consecuente(Terminales.WHILE));
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else if (t==Terminales.RETURN||t==Terminales.ID
					||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new Consecuente(NoTerminales.S));
			}
			else {
				return null;
			}
			break;
		case C:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new Consecuente(NoTerminales.B));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.C));
			}
			else if (t==Terminales.LLAVEDE || t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case T:
			if (t==Terminales.INT) {
				cons.push(new Consecuente(Terminales.INT));
			}
			else if (t==Terminales.CHARS) {
				cons.push(new Consecuente(Terminales.CHARS));
			}
			else if (t==Terminales.BOOL) {
				cons.push(new Consecuente(Terminales.BOOL));
			}
			else {
				return null;
			}
			break;
		case S:
			if (t==Terminales.RETURN) {
				cons.push(new Consecuente(Terminales.RETURN));
				cons.push(new Consecuente(NoTerminales.X));
			}
			else if (t==Terminales.ID) {
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(NoTerminales.SP));
			}
			else if (t==Terminales.WRITE) {
				cons.push(new Consecuente(Terminales.WRITE));
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else if (t==Terminales.PROMPT) {
				cons.push(new Consecuente(Terminales.PROMPT));
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else {
				return null;
			}
			break;
		case SP:
			if (t==Terminales.ASSIGN) {
				cons.push(new Consecuente(Terminales.ASSIGN));
				cons.push(new Consecuente(NoTerminales.E));
			}
			else if (t==Terminales.PARENIZ) {
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.L));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else if (t==Terminales.ASSIGNY) {
				cons.push(new Consecuente(Terminales.ASSIGNY));
				cons.push(new Consecuente(NoTerminales.E));
			}
			else {
				return null;
			}
			break;
		case X:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.E));
			}
			else if (t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			break;
		case L:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(NoTerminales.Q));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case Q:
			if (t==Terminales.COMA) {
				cons.push(new Consecuente(Terminales.COMA));
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(NoTerminales.Q));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			break;
		case E:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.R));
				cons.push(new Consecuente(NoTerminales.EP));
			}
			else {
				return null;
			}
			break;
		case EP:
			if (t==Terminales.AND) {
				cons.push(new Consecuente(Terminales.AND));
				cons.push(new Consecuente(NoTerminales.R));
				cons.push(new Consecuente(NoTerminales.EP));
			}
			else if (t==Terminales.COMA||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case R:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.U));
				cons.push(new Consecuente(NoTerminales.RP));
			}
			else  {
				return null;
			}
			break;
		case RP:
			if (t==Terminales.MENOR) {
				cons.push(new Consecuente(Terminales.MENOR));
				cons.push(new Consecuente(NoTerminales.U));
				cons.push(new Consecuente(NoTerminales.RP));
			}
			else if (t==Terminales.MAYOR) {
				cons.push(new Consecuente(Terminales.MAYOR));
				cons.push(new Consecuente(NoTerminales.U));
				cons.push(new Consecuente(NoTerminales.RP));
			}
			else if (t==Terminales.AND||t==Terminales.COMA||t==Terminales.PARENDE||
					t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			break;
		case U:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.V));
				cons.push(new Consecuente(NoTerminales.UP));
			}
			else {
				return null;
			}
			break;
		case UP:
			if (t==Terminales.SUMA) {
				cons.push(new Consecuente(Terminales.SUMA));
				cons.push(new Consecuente(NoTerminales.V));
				cons.push(new Consecuente(NoTerminales.UP));
			}
			else if (t==Terminales.RESTA) {
				cons.push(new Consecuente(Terminales.RESTA));
				cons.push(new Consecuente(NoTerminales.V));
				cons.push(new Consecuente(NoTerminales.UP));
			}
			else if (t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case V:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new Consecuente(NoTerminales.W));
				cons.push(new Consecuente(NoTerminales.VP));
			}
			else  {
				return null;
			}
			break;
		case VP:
			if (t==Terminales.MULT) {
				cons.push(new Consecuente(Terminales.MULT));
				cons.push(new Consecuente(NoTerminales.W));
				cons.push(new Consecuente(NoTerminales.VP));
			}
			else if (t==Terminales.DIV) {
				cons.push(new Consecuente(Terminales.DIV));
				cons.push(new Consecuente(NoTerminales.W));
				cons.push(new Consecuente(NoTerminales.VP));
			}
			else if (t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case W:
			if (t==Terminales.ID) {
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(NoTerminales.WP));
			}
			else if (t==Terminales.ENT) {
				cons.push(new Consecuente(Terminales.ENT));
			}
			else if (t==Terminales.CAD) {
				cons.push(new Consecuente(Terminales.CAD));
			}
			else if (t==Terminales.PARENIZ) {
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.E));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else {
				return null;
			}
			break;
		case WP:
			if (t==Terminales.PARENIZ) {
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.L));
				cons.push(new Consecuente(Terminales.PARENDE));
			}
			else if (t==Terminales.MULT||t==Terminales.DIV||t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case F:
			if (t==Terminales.FUNCTION) {
				cons.push(new Consecuente(Terminales.FUNCTION));
				cons.push(new Consecuente(NoTerminales.H));
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(Terminales.PARENIZ));
				cons.push(new Consecuente(NoTerminales.A));
				cons.push(new Consecuente(Terminales.PARENDE));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(Terminales.LLAVEIZ));
				cons.push(new Consecuente(NoTerminales.Z));
				cons.push(new Consecuente(NoTerminales.C));
				cons.push(new Consecuente(Terminales.LLAVEDE));
			}
			else {
				return null;
			}
			break;
		case H:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				cons.push(new Consecuente(NoTerminales.T));
			}
			else if (t==Terminales.ID) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case A:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				cons.push(new Consecuente(NoTerminales.T));
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(NoTerminales.K));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case K:
			if (t==Terminales.COMA) {
				cons.push(new Consecuente(Terminales.COMA));
				cons.push(new Consecuente(NoTerminales.T));
				cons.push(new Consecuente(Terminales.ID));
				cons.push(new Consecuente(NoTerminales.K));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case Z:
			if (t==Terminales.SALTO) {
				cons.push(new Consecuente(Terminales.SALTO));
				cons.push(new Consecuente(NoTerminales.ZP));
			}
			else {
				return null;
			}
			break;
		case ZP:
			if (t==Terminales.SALTO) {
				cons.push(new Consecuente(Terminales.SALTO));
				cons.push(new Consecuente(NoTerminales.ZP));
			}
			else if (t==Terminales.LLAVEIZ||t==Terminales.LLAVEDE||t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
					||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT||t==Terminales.EOF||t==Terminales.FUNCTION||t==Terminales.$) {
				cons.push(new Consecuente(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		default:
			break;
		}
		return cons;
	}

}
