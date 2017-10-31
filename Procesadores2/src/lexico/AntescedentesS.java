package lexico;

import java.nio.file.AccessMode;
import java.util.Stack;

public class AntescedentesS {
	private NoTerminales ante;
	//private Stack<ConsecuenteS> cons;
	
	public AntescedentesS(NoTerminales ante) {
		// TODO Auto-generated constructor stub
		this.ante=ante;
	}
	public Stack<ConsecuenteS> buscar (Terminales t){
		Stack<ConsecuenteS> cons=new Stack<ConsecuenteS>();
		switch (this.ante) {
		case PP:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new ConsecuenteS(AccionSem.PP1));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.PP2));				
			}
			else if (t==Terminales.FUNCTION) {
				cons.push(new ConsecuenteS(AccionSem.PP1));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.PP2));
			}
			else if (t==Terminales.SALTO) {
				cons.push(new ConsecuenteS(AccionSem.PP1));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.PP2));

			}
			else if (t==Terminales.EOF) {
				cons.push(new ConsecuenteS(AccionSem.PP1));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.PP2));
			}
			else {
				return null;
			}
			break;
		case P:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new ConsecuenteS(NoTerminales.B));
				cons.push(new ConsecuenteS(AccionSem.P11));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.P12));
				
			}
			else if (t==Terminales.FUNCTION) {
				cons.push(new ConsecuenteS(NoTerminales.F));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.P2));
				
			}
			else if (t==Terminales.SALTO) {
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.P));
				cons.push(new ConsecuenteS(AccionSem.P3));
			}
			else if (t==Terminales.EOF) {
				cons.push(new ConsecuenteS(Terminales.EOF));
				cons.push(new ConsecuenteS(AccionSem.P4));
			}
			else {
				return null;
			}
			break;
		case B:
			if (t==Terminales.VAR) {
				cons.push(new ConsecuenteS(Terminales.VAR));
				cons.push(new ConsecuenteS(NoTerminales.T)); 
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.B1));
			}
			else if (t==Terminales.IF) {
				cons.push(new ConsecuenteS(Terminales.IF));
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(AccionSem.B21 ));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(NoTerminales.S));
				cons.push(new ConsecuenteS(AccionSem.B22 ));
			}
			else if (t==Terminales.DO) {
				cons.push(new ConsecuenteS(Terminales.DO));
				cons.push(new ConsecuenteS(Terminales.LLAVEIZ));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.C));
				cons.push(new ConsecuenteS(Terminales.LLAVEDE));
				cons.push(new ConsecuenteS(Terminales.WHILE));
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(AccionSem.B31));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.B32));
			}
			else if (t==Terminales.RETURN||t==Terminales.ID
					||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new ConsecuenteS(NoTerminales.S));
				cons.push(new ConsecuenteS(AccionSem.B41));
			}
			else {
				return null;
			}
			break;
		case C:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				cons.push(new ConsecuenteS(NoTerminales.B));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.C));
				cons.push(new ConsecuenteS(AccionSem.C11));
				
			}
			else if (t==Terminales.LLAVEDE || t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case T:
			if (t==Terminales.INT) {
				cons.push(new ConsecuenteS(Terminales.INT));
				cons.push(new ConsecuenteS(AccionSem.T1));
			}
			else if (t==Terminales.CHARS) {
				cons.push(new ConsecuenteS(Terminales.CHARS));
				cons.push(new ConsecuenteS(AccionSem.T2));
			}
			else if (t==Terminales.BOOL) {
				cons.push(new ConsecuenteS(Terminales.BOOL));
				cons.push(new ConsecuenteS(AccionSem.T3));
			}
			else {
				return null;
			}
			break;
		case S:
			if (t==Terminales.RETURN) {
				cons.push(new ConsecuenteS(Terminales.RETURN));
				cons.push(new ConsecuenteS(NoTerminales.X));
				cons.push(new ConsecuenteS(AccionSem.S11));
			}
			else if (t==Terminales.ID) {
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.S21));
				cons.push(new ConsecuenteS(NoTerminales.SP));
				cons.push(new ConsecuenteS(AccionSem.S22));
			}
			else if (t==Terminales.WRITE) {
				cons.push(new ConsecuenteS(Terminales.WRITE));
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.S31));
			}
			else if (t==Terminales.PROMPT) {
				cons.push(new ConsecuenteS(Terminales.PROMPT));
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.S41));
			}
			else {
				return null;
			}
			break;
		case SP:
			if (t==Terminales.ASSIGN) {
				cons.push(new ConsecuenteS(Terminales.ASSIGN));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(AccionSem.SP1));
			}
			else if (t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.L));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.SP2));
			}
			else if (t==Terminales.ASSIGNY) {
				cons.push(new ConsecuenteS(Terminales.ASSIGNY));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(AccionSem.SP3));
			}
			else {
				return null;
			}
			break;
		case X:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(AccionSem.X1));
			}
			else if (t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
				cons.push(new ConsecuenteS(AccionSem.X2));
			}else {
				return null;
			}
			break;
		case L:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(NoTerminales.Q));
				cons.push(new ConsecuenteS(AccionSem.L1));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case Q:
			if (t==Terminales.COMA) {
				cons.push(new ConsecuenteS(Terminales.COMA));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(NoTerminales.Q));
				cons.push(new ConsecuenteS(AccionSem.Q1));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case E:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.R));
				cons.push(new ConsecuenteS(NoTerminales.EP));
				cons.push(new ConsecuenteS(AccionSem.E1));
			}
			else {
				return null;
			}
			break;
		case EP:
			if (t==Terminales.AND) {
				cons.push(new ConsecuenteS(Terminales.AND));
				cons.push(new ConsecuenteS(NoTerminales.R));
				cons.push(new ConsecuenteS(NoTerminales.EP));
				cons.push(new ConsecuenteS(AccionSem.EP1));
			}
			else if (t==Terminales.COMA||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case R:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.U));
				cons.push(new ConsecuenteS(NoTerminales.RP));
				cons.push(new ConsecuenteS(AccionSem.R1));
			}
			else  {
				return null;
			}
			break;
		case RP:
			if (t==Terminales.MENOR) {
				cons.push(new ConsecuenteS(Terminales.MENOR));
				cons.push(new ConsecuenteS(NoTerminales.U));
				//cons.push(new ConsecuenteS(NoTerminales.RP));
				cons.push(new ConsecuenteS(AccionSem.RP1));
			}
			else if (t==Terminales.MAYOR) {
				cons.push(new ConsecuenteS(Terminales.MAYOR));
				cons.push(new ConsecuenteS(NoTerminales.U));
				//cons.push(new ConsecuenteS(NoTerminales.RP));
				cons.push(new ConsecuenteS(AccionSem.RP1));
			}
			else if (t==Terminales.AND||t==Terminales.COMA||t==Terminales.PARENDE||
					t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case U:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.V));
				cons.push(new ConsecuenteS(NoTerminales.UP));
				cons.push(new ConsecuenteS(AccionSem.U1));
			}
			else {
				return null;
			}
			break;
		case UP:
			if (t==Terminales.SUMA) {
				cons.push(new ConsecuenteS(Terminales.SUMA));
				cons.push(new ConsecuenteS(NoTerminales.V));
				cons.push(new ConsecuenteS(NoTerminales.UP));
				cons.push(new ConsecuenteS(AccionSem.UP1));
			}
			else if (t==Terminales.RESTA) {
				cons.push(new ConsecuenteS(Terminales.RESTA));
				cons.push(new ConsecuenteS(NoTerminales.V));
				cons.push(new ConsecuenteS(NoTerminales.UP));
				cons.push(new ConsecuenteS(AccionSem.UP1));
			}
			else if (t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case V:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(NoTerminales.W));
				cons.push(new ConsecuenteS(NoTerminales.VP));
				cons.push(new ConsecuenteS(AccionSem.V1));
			}
			else  {
				return null;
			}
			break;
		case VP:
			if (t==Terminales.MULT) {
				cons.push(new ConsecuenteS(Terminales.MULT));
				cons.push(new ConsecuenteS(NoTerminales.W));
				cons.push(new ConsecuenteS(NoTerminales.VP));
				cons.push(new ConsecuenteS(AccionSem.VP1));
			}
			else if (t==Terminales.DIV) {
				cons.push(new ConsecuenteS(Terminales.DIV));
				cons.push(new ConsecuenteS(NoTerminales.W));
				cons.push(new ConsecuenteS(NoTerminales.VP));
				cons.push(new ConsecuenteS(AccionSem.VP1));
			}
			else if (t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case W:
			if (t==Terminales.ID) {
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.W11));
				cons.push(new ConsecuenteS(NoTerminales.WP));
				cons.push(new ConsecuenteS(AccionSem.W12));
			}
			else if (t==Terminales.ENT) {
				cons.push(new ConsecuenteS(Terminales.ENT));
				cons.push(new ConsecuenteS(AccionSem.W21));
			}
			else if (t==Terminales.CAD) {
				cons.push(new ConsecuenteS(Terminales.CAD));
				cons.push(new ConsecuenteS(AccionSem.W31));
			}
			else if (t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.E));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.W41));
			}
			else {
				return null;
			}
			break;
		case WP:
			if (t==Terminales.PARENIZ) {
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.L));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(AccionSem.WP11));
			}
			else if (t==Terminales.MULT||t==Terminales.DIV||t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
				cons.push(new ConsecuenteS(AccionSem.WP21));
			}
			else {
				return null;
			}
			break;
		case F:
			if (t==Terminales.FUNCTION) {
				cons.push(new ConsecuenteS(Terminales.FUNCTION));
				cons.push(new ConsecuenteS(NoTerminales.H));
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.F11));
				cons.push(new ConsecuenteS(Terminales.PARENIZ));
				cons.push(new ConsecuenteS(NoTerminales.A));
				cons.push(new ConsecuenteS(AccionSem.F12));
				cons.push(new ConsecuenteS(Terminales.PARENDE));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(Terminales.LLAVEIZ));
				cons.push(new ConsecuenteS(NoTerminales.Z));
				cons.push(new ConsecuenteS(NoTerminales.C));
				cons.push(new ConsecuenteS(Terminales.LLAVEDE));
				cons.push(new ConsecuenteS(AccionSem.F13));
			}
			else {
				return null;
			}
			break;
		case H:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				cons.push(new ConsecuenteS(NoTerminales.T));
				cons.push(new ConsecuenteS(AccionSem.H11));
			}
			else if (t==Terminales.ID) {
				cons.push(new ConsecuenteS(Terminales.lambda));
				cons.push(new ConsecuenteS(AccionSem.H12));
			}
			else {
				return null;
			}
			break;
		case A:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				cons.push(new ConsecuenteS(NoTerminales.T));
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.A11));
				cons.push(new ConsecuenteS(NoTerminales.K));
				cons.push(new ConsecuenteS(AccionSem.A12));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case K:
			if (t==Terminales.COMA) {
				cons.push(new ConsecuenteS(Terminales.COMA));
				cons.push(new ConsecuenteS(NoTerminales.T));
				cons.push(new ConsecuenteS(Terminales.ID));
				cons.push(new ConsecuenteS(AccionSem.K11));
				cons.push(new ConsecuenteS(NoTerminales.K));
				cons.push(new ConsecuenteS(AccionSem.K12));
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
			}
			else {
				return null;
			}
			break;
		case Z:
			if (t==Terminales.SALTO) {
				cons.push(new ConsecuenteS(Terminales.SALTO));
				cons.push(new ConsecuenteS(NoTerminales.ZP));
				cons.push(new ConsecuenteS(AccionSem.Z1));
			}
			else {
				return null;
			}
			break;
		case ZP:
			if (t==Terminales.SALTO) {
				cons.push(new ConsecuenteS(Terminales.SALTO));
				cons.push(new ConsecuenteS(NoTerminales.ZP));
				cons.push(new ConsecuenteS(AccionSem.ZP1));
			}
			else if (t==Terminales.LLAVEIZ||t==Terminales.LLAVEDE||t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
					||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT||t==Terminales.EOF||t==Terminales.FUNCTION||t==Terminales.$) {
				cons.push(new ConsecuenteS(Terminales.lambda));
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
