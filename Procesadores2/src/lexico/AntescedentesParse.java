package lexico;

import java.util.Stack;

public class AntescedentesParse {
	private NoTerminales ante;
	public AntescedentesParse(NoTerminales ante) {
		// TODO Auto-generated constructor stub
		this.ante=ante;
	}
	public int buscar (Terminales t){
		int parse=0;
		switch (this.ante) {
		case PP:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				parse=1;			
			}
			else if (t==Terminales.FUNCTION) {
				parse=1;
			}
			else if (t==Terminales.SALTO) {
				parse=1;
			}
			else if (t==Terminales.EOF) {
				parse=1;
			}
			else {
				return 0;
			}
			break;
		case P:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				parse=2;
				
			}
			else if (t==Terminales.FUNCTION) {
				parse=3;
				
			}
			else if (t==Terminales.SALTO) {
				parse=4;
			}
			else if (t==Terminales.EOF) {
				parse=5;
			}
			else {
				return 0;
			}
			break;
		case B:
			if (t==Terminales.VAR) {
				parse=6;
			}
			else if (t==Terminales.IF) {
				parse=7;
			}
			else if (t==Terminales.DO) {
				parse=8;
			}
			else if (t==Terminales.RETURN||t==Terminales.ID
					||t==Terminales.WRITE||t==Terminales.PROMPT) {
				parse=9;
			}
			else {
				return 0;
			}
			break;
		case C:
			if (t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
			||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT) {
				parse= 10;
				
			}
			else if (t==Terminales.LLAVEDE || t==Terminales.$) {
				parse=11;
			}
			else {
				return 0;
			}
			break;
		case T:
			if (t==Terminales.INT) {
				parse=12;
			}
			else if (t==Terminales.CHARS) {
				parse=13;
			}
			else if (t==Terminales.BOOL) {
				parse=14;
			}
			else {
				return 0;
			}
			break;
		case S:
			if (t==Terminales.RETURN) {
				parse=15;
			}
			else if (t==Terminales.ID) {
				parse=16;
			}
			else if (t==Terminales.WRITE) {
				parse=17;
			}
			else if (t==Terminales.PROMPT) {
				parse=18;
			}
			else {
				return 0;
			}
			break;
		case SP:
			if (t==Terminales.ASSIGN) {
				parse=19;
			}
			else if (t==Terminales.PARENIZ) {
				parse=20;
			}
			else if (t==Terminales.ASSIGNY) {
				parse=21;
			}
			else {
				return 0;
			}
			break;
		case X:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=22;
			}
			else if (t==Terminales.SALTO||t==Terminales.$) {
				parse=23;
			}
			else {
				return 0;
			}
			break;
		case L:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=24;
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				parse=25;
			}
			else {
				return 0;
			}
			break;
		case Q:
			if (t==Terminales.COMA) {
				parse=26;
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				parse=27;
			}
			break;
		case E:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=28;
			}
			else {
				return 0;
			}
			break;
		case EP:
			if (t==Terminales.AND) {
				parse=29;
			}
			else if (t==Terminales.COMA||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				parse=30;
			}
			else {
				return 0;
			}
			break;
		case R:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=31;
			}
			else  {
				return 0;
			}
			break;
		case RP:
			if (t==Terminales.MENOR) {
				parse=32;
			}
			else if (t==Terminales.MAYOR) {
				parse=33;
			}
			else if (t==Terminales.AND||t==Terminales.COMA||t==Terminales.PARENDE||
					t==Terminales.SALTO||t==Terminales.$) {
				parse=34;
			}else {
				return 0;
			}
			break;
		case U:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=35;
			}
			else {
				return 0;
			}
			break;
		case UP:
			if (t==Terminales.SUMA) {
				parse=36;
			}
			else if (t==Terminales.RESTA) {
				parse=37;
			}
			else if (t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				parse=38;
			}
			else {
				return 0;
			}
			break;
		case V:
			if (t==Terminales.ID||t==Terminales.ENT||t==Terminales.CAD||t==Terminales.PARENIZ) {
				parse=39;
			}
			else  {
				return 0;
			}
			break;
		case VP:
			if (t==Terminales.MULT) {
				parse=40;
			}
			else if (t==Terminales.DIV) {
				parse=41;
			}
			else if (t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				parse=42;
			}
			else {
				return 0;
			}
			break;
		case W:
			if (t==Terminales.ID) {
				parse=43;
			}
			else if (t==Terminales.ENT) {
				parse=44;
			}
			else if (t==Terminales.CAD) {
				parse=45;
			}
			else if (t==Terminales.PARENIZ) {
				parse=46;
			}
			else {
				return 0;
			}
			break;
		case WP:
			if (t==Terminales.PARENIZ) {
				parse=47;
			}
			else if (t==Terminales.MULT||t==Terminales.DIV||t==Terminales.SUMA||t==Terminales.RESTA||t==Terminales.MENOR||t==Terminales.MAYOR||t==Terminales.AND||t==Terminales.COMA
					||t==Terminales.PARENDE||t==Terminales.SALTO||t==Terminales.$) {
				parse=48;
			}
			else {
				return 0;
			}
			break;
		case F:
			if (t==Terminales.FUNCTION) {
			parse=49;
			}
			else {
				return 0;
			}
			break;
		case H:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				parse=50;
			}
			else if (t==Terminales.ID) {
				parse=51;
			}
			else {
				return 0;
			}
			break;
		case A:
			if (t==Terminales.INT||t==Terminales.CHARS||t==Terminales.BOOL) {
				parse=52;
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				parse=53;
			}
			else {
				return 0;
			}
			break;
		case K:
			if (t==Terminales.COMA) {
				parse=54;
			}
			else if (t==Terminales.PARENDE||t==Terminales.$) {
				parse=55;
			}
			else {
				return 0;
			}
			break;
		case Z:
			if (t==Terminales.SALTO) {
				parse=56;
			}
			else {
				return 0;
			}
			break;
		case ZP:
			if (t==Terminales.SALTO) {
				parse=57;
			}
			else if (t==Terminales.LLAVEIZ||t==Terminales.LLAVEDE||t==Terminales.VAR||t==Terminales.IF||t==Terminales.DO||t==Terminales.RETURN
					||t==Terminales.ID||t==Terminales.WRITE||t==Terminales.PROMPT||t==Terminales.EOF||t==Terminales.FUNCTION||t==Terminales.$) {
				parse=58;
			}
			else {
				return 0;
			}
			break;
		default:
			break;
		}
		return parse;
	}
}
