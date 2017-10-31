package lexico;
import java_cup.runtime.*;
%%
%{
/*-*
* funciones y variables
*/
private void imprimir(String descripcion, String lexema) {
System.out.println(lexema + " - " + descripcion);
}
%}
/*-*
* Información sobre la clase generada
*/
%public
%class AnalizadorLexico
%type Terminales
/*-*
* Ajustes regulares
*/
SALTO=[\r|\n]
ID = [a-z|A-Z][a-z|A-Z|0-9|_]*
ENTERO = 0|[1-9][0-9]*
CADENA=\"[a-z|A-Z|0-9|_|\s]*\"
COMENTARIO=([/][/][^\r|\n]*|[/][*][^*]*[*][/]|[ |\t|\f])
%%
"if" { return IF; }
"while" {return WHILE; }
"do" {return DO; }
"function" { return FUNCTION;}
"return" { return RETURN;}
"var" { return VAR;}
"int" { return INT;}
"chars" {return CHARS;}
"bool" {return BOOL;}
"write" {return WRITE;}
"prompt" {return PROMPT;}
"(" {return PARENIZ;}
")" {return PARENDE;}
"{" {return LLAVEIZ;}
"}" {return LLAVEDE;}
"," {return COMA;}
"==" {return IGUAL;}
"=" {return ASSIGN;}
"&=" {return ASSIGNY;}
"&&" {return AND;}
"!=" {return NOIGUAL;}
"<" {return MENOR;}
">" {return MAYOR;}
"+" {return SUMA;}
"-" {return RESTA;}
"*" {return MULT;}
{SALTO} {return SALTO;}
{ID} { return ID; }
{ENTERO} { return ENT; }
{CADENA} {return CAD; }
{COMENTARIO} {return COMENTARIO;}
"/" {return DIV;}
. { return ERROR; }