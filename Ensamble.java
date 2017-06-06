public class Ensamble {

String S1principal="";    //Inserta TODOS los codigos Hexadecimales del código, al imprimir se generan las lineas automaticas.
int contS1=0;    //Contador de bytes
int sumaBytes=0;
int Direccion=0;
int noBytes=0;
String inicio="";
String S0="S0";
String primerej="";
boolean first=false;
boolean banderacode=false;
int cont=0;


void bandera(){
        banderacode=true;
        Imprimir2S1();
}
void DWLB(int opcion,int datatipo, int valor){
        String Cadena="";
        int modulo=0;
        int top=0;
        /*System.out.println("Adios "+opcion+"  "+datatipo+"  "+valor);*/
        switch(opcion) {

        case 1:  for (int i=0; i<valor; i++ ) {
                        Cadena+="00";
        }
                break; //BYTE
        case 2: for (int i=0; i<valor; i++ ) {
                        Cadena+="0000";
        }
                break; //WORD
        case 3: for (int i=0; i<valor; i++ ) {
                        Cadena+="00000000";
        }
                break; //LONG
        case  4:     //Data
                String com=Integer.toString(valor, 16);

                switch(datatipo) {
                case 1: /*if(com.length()==1){ //Byte
                           Cadena+="0"+com;
                           }
                           else if(com.length()==2){ Cadena+=com;}*/
                        modulo=com.length()%2;
                        if(modulo!=0) {
                                top = 2 - modulo;
                                for(int x=0; x<top; x++) {
                                        Cadena+="0";
                                }
                                Cadena+=com;
                        }
                        else{Cadena+=com; }
                        break;

                case 2:  //Word
                         /* if(com.length()==4){Cadena+=com;}
                            else{
                            for(int x=com.length();x<4;x++){
                              Cadena+="0";
                            }
                            }*/

                        modulo=com.length()%4;
                        if(modulo!=0) {
                                top = 4 - modulo;
                                for(int x=0; x<top; x++) {
                                        Cadena+="0";
                                } Cadena+=com;
                        }
                        else{Cadena+=com; }
                        break;

                case 3: //Long
                        /* if(com.length()==8){Cadena+=com;}
                           else{
                           for(int x=com.length();x<8;x++){
                             Cadena+="0";
                           }
                           }
                           Cadena+=com;
                         */

                        modulo=com.length()%8;
                        if(modulo!=0) {
                                top = 8 - modulo;
                                for(int x=0; x<top; x++) {
                                        Cadena+="0";
                                } Cadena+=com;
                        }
                        else{Cadena+=com; }
                        break;


                }
        }
        //System.out.println("Suuuuu"+Cadena);
        sumaBytes+=longitud(Cadena);    //201+10
        S1principal+=Cadena;

}
void ORG(String inicio,String Et){
        String start="0000";
        switch(inicio.length()) {
        case 1:
                start="000"+inicio;
                break;
        case 2:
                start="00"+inicio;
                break;
        case 3:
                start="0"+inicio;
                break;
        case 4:
                start=inicio;
                break;
        default:
                System.out.println("Valor inválido para inicio del programa.");
                break;

        }
        primerej=start;
        int par=hex2decimal(start.substring(0,2))+hex2decimal(start.substring(2,4));
        Direccion=par;
        String hex="";
        for (int i = 0; i < Et.length(); i++) {
                int a=(int)Et.charAt(i);
                par+=a;
                hex+=Integer.toString(a,16).toUpperCase();
        }
        //System.out.println("eti "+par);
        int follow=Et.length();
        noBytes=0;
        String asd="";
        if (follow<16) {
                asd="0"+Integer.toHexString(follow+3).toUpperCase();
                //par+=hex2decimal(asd);
        }else{
                asd=Integer.toHexString(follow+3).toUpperCase();
                //par+=hex2decimal(asd);
        }

        S0+=asd+" "+start+" "+hex+" "+Complemento(par);
        System.out.println(S0);


}
String addCerosExt(String ext, int mode, int registro){
        int top;
        if (mode == 5 || (mode == 7 && (registro == 0 || registro == 2))) { // Word 16 bits
                top = 4 - ext.length();
                for(int i=0; i < top; i++)
                        ext = '0'+ext;
        }
        else if(mode == 6 || (mode == 7 && registro == 3)) { // Byte 8 bits
                top = 4 - ext.length();
                for(int i=0; i < top; i++)
                        ext = '0'+ext;
        }
        else if( mode == 7 && registro == 1) { //Long 32 bits
                top = 8 - ext.length();
                for(int i=0; i < top; i++)
                        ext = '0'+ext;
        }
        else if( mode == 7 && registro == 1) { //Long 32 bits
                top = 8 - ext.length();
                for(int i=0; i < top; i++)
                        ext = '0'+ext;
        }
        else if(mode == 7 && registro == 4) {
                ext = agregarCeros(ext,0);
        }
        return ext;
}
String agregarCeros(String data, int band){
        int lngData = data.length();
        int top;
        if(lngData <= 2 && band == 0) {
                top = 4 - lngData;
                for(int i=0; i < top; i++)
                        data = '0'+data;
        }
        else if(lngData <= 4) {
                top = 4 - lngData;
                for(int i=0; i < top; i++)
                        data = '0'+data;
        }
        else if(lngData <= 8 && band == 0) {
                top = 8 - lngData;
                for(int i=0; i < top; i++)
                        data = '0'+data;
        }
        return data;
}
void Imprimir2S1(){

        System.out.print("S1");
        if (contS1+3<16) {
                System.out.print("0"+Integer.toHexString(contS1+3).toUpperCase());

        }else{
                System.out.print(Integer.toHexString(contS1+3).toUpperCase());

        }
        //System.out.println(" Direccion: "+inicio+" noBytes: "+noBytes+" Contador de bytes:"+contS1);

        if (first) {
                inicio=Integer.toHexString(hex2decimal(inicio)+noBytes);
                if(banderacode) {
                        primerej=inicio;
                }
                //Direccion+=255;
        }else{
                inicio=primerej;
                first=true;
                if(banderacode) {

                }
        }
        //System.out.println(" Direccion: "+inicio+" noBytes: "+noBytes+" Contador de bytes:"+contS1);
        switch(inicio.length()) {
        case 1:
                inicio="000"+inicio;
                break;
        case 2:
                inicio="00"+inicio;
                break;
        case 3:
                inicio="0"+inicio;
                break;
        case 4:
                inicio=inicio;
                break;
        default:
                System.out.println("Valor inválido para inicio del programa.");
                break;

        }
        //System.out.println("Numeros:"+contS1+" "+Direccion+" "+noBytes+" "+sumaBytes);
        System.out.println(inicio.toUpperCase()+S1principal.toUpperCase()+Complemento(contS1+3+hex2decimal(inicio.substring(0,2))+hex2decimal(inicio.substring(2,4))+sumaBytes));
        Direccion=Direccion+noBytes;
        noBytes=contS1;
        contS1=0;
        sumaBytes=0;
        S1principal="";
}
void ImprimirS9(){
        int top = 4-primerej.length();
        for(int i=0; i<top; i++)
                primerej="0"+primerej;
        int pbaja=hex2decimal(primerej.substring(0,2)),palta=hex2decimal(primerej.substring(2,4));
        //System.out.println("baja= "+pbaja+" alta= "+palta);
        System.out.println("S903"+primerej+Complemento(3+pbaja+palta));
}
String Complemento(int op){
        //System.out.print("\nParidad="+op);
        String numero=String.valueOf(op);
        int operador=Integer.parseInt(numero);
        operador=~operador;
        numero=Integer.toHexString(operador).toUpperCase();
        String fin=numero.substring(numero.length()-2, numero.length());
        return fin;
}
public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int d = digits.indexOf(c);
                val = 16*val + d;
        }
        return val;
}
int longitud(String ensamblado){

        int par=0;
        int canti=0;
        for (int a=0; a<ensamblado.length(); a+=2) {
                par+=hex2decimal(ensamblado.substring(a,a+2));
                canti++;
        }
        int verificar= contS1+canti;

        if(verificar>252) {
                Imprimir2S1();
                contS1+=canti; //Número de bytes de la instrucción
        }
        else{contS1+=verificar; } //Número de bytes de la instrucción}

        return par;


}

///// Instrucciones

void BCHG2(String P1,String P2, int type,int modo,String bitnumber,String ext){
        int NV1=0;
        if ( (ext == null) || (ext.equals("")) ) NV1=0;
        else NV1=Integer.parseInt(ext.substring(1,ext.length()),16);
        int NV2=Integer.parseInt(bitnumber.substring(1,bitnumber.length()),16);
        int RAD2=Integer.parseInt(P2.substring(1,P2.length()));
        String extH=Integer.toHexString(NV1).toUpperCase();
        String dataH=Integer.toHexString(NV2).toUpperCase();
        dataH = agregarCeros(dataH, 1);
        extH = addCerosExt(extH,modo,RAD2);

        if(type==1) {
                int RAD1=Integer.parseInt(P1.substring(1,P1.length()));
                int a = RAD1<<9;
                a |= 5<<6;
                a |= modo<<3;
                a |= RAD2;
                String com=Integer.toString(a, 16);
                com="0"+com;
                if(modo == 5 || modo == 6 || modo == 7) com+=extH;
                //System.out.println("ensamblado BCHG:"+ com);
                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
        }
        if(type==2) {
                int a = 1<<11;
                a |= 1<<6;
                a |= modo<<3;
                a |= RAD2;
                String com=Integer.toString(a, 16);
                com="0"+com+dataH;
                if(modo == 5 || modo == 6 || modo == 7) com+=extH;
                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
                //System.out.println("ensamblado BCHG:"+ com);
        }

}
void EOR(int RAD1, int RAD2, int EOPMODO,int mode,int ext){
        String extH=Integer.toHexString(ext).toUpperCase();
        extH = addCerosExt(extH,mode,RAD2);
        int a=11<<12;
        a|= RAD1<<9;
        a|=EOPMODO<<6;
        a|=mode<<3;
        a|=RAD2<<0;
        String com = Integer.toString(a,16);
        if(mode == 5 || mode == 6 || mode == 7)
                com+=extH;
        sumaBytes+=longitud(com);
        S1principal+=com;
        //System.out.println("ensamblado EOR:"+com);
}
void SBCD(int RAD1, int RAD2,int rm){
        int a=8<<12;
        a|=RAD2<<9;
        a|=16<<4;
        a|=rm<<3;
        a|=RAD1<<0;
        String com = Integer.toString(a,16);
        sumaBytes+=longitud(com);
        S1principal+=com;
        //System.out.println("ensamblado SBCD:"+com);

}
void LSL(int registro, int registro2, int ext, int ir, int size, int version, int mode,int data){
        String extH=Integer.toHexString(ext).toUpperCase();
        extH = addCerosExt(extH,mode,registro);
        if(version == 1) {
                int a = 7<<13;
                a |= registro<<9;
                a |= 1<<8;
                a |= size<<6;
                a |= ir<<5;
                a |= 1<<3;
                a |= registro2;
                String com=Integer.toString(a, 16);
                String t=0+com;
                com=t;
                //System.out.println("Impresion de LSL: "+com);
                sumaBytes+=longitud(com); //201+10
                S1principal+=com;

        }

        if(version == 2) {
                int a = 7<<13;
                a |= data<<9;
                a |= 1<<8;
                a |= size<<6;
                a |= ir<<5;
                a |= 1<<3;
                a |= registro2;

                String com=Integer.toString(a, 16);
                String t=0+com;
                com=t;

                //System.out.println("Impresion de LSL: "+com);
                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
        }

        if(version == 3) {
                int a = 7<<13;
                a |= 1<<9;
                a |= 1<<8;
                a |= 3<<6;
                a |= mode<<3;
                a |= registro;
                String com=Integer.toString(a, 16);

                if(mode == 5 || mode == 6 || mode == 7)
                        com+=extH;
                //System.out.println("Impresion de LSL: "+com);
                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
        }
}
void LSR(int registro, int registro2, int ext, int ir, int size, int version, int mode,int data){

        String extH=Integer.toHexString(ext).toUpperCase();
        extH = addCerosExt(extH,mode,registro);


        if(version == 1) {
                int a = 7<<13;
                a |= registro<<9;
                a |= size<<6;
                a |= ir<<5;
                a |= 1<<3;
                a |= registro2;

                String com=Integer.toString(a, 16);
                //System.out.println("Impresion de LSR: ");
                String t=0+com;
                com=t;

                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
        }

        if(version == 2) {
                int a = 7<<13;
                a |= data<<9;
                a |= size<<6;
                a |= ir<<5;
                a |= 1<<3;
                a |= registro2;

                String com=Integer.toString(a, 16);
                String t=0+com;
                com=t;


                //System.out.println("Impresion de LSR: "+com);

                sumaBytes+=longitud(com); //201+10
                S1principal+=com;
        }

        if(version == 3) {
                int a = 7<<13;
                a |= 1<<9;
                a |= 3<<6;
                a |= mode<<3;
                a |= registro;
                String com=Integer.toString(a, 16);

                if(mode == 5 || mode == 6 || mode == 7)
                        com+=extH;
                //System.out.println("Impresion de LSR: "+com);


                sumaBytes+=longitud(com); //201+10

                S1principal+=com;
        }


}

void JMP(String RAD1,int mode, int ext){

        int registro1=0;
        if (mode==7) {
                registro1=Integer.parseInt(RAD1.substring(1),2);
        }
        else{
                registro1=Integer.parseInt(RAD1.substring(1,RAD1.length()));
        }
        String extH=Integer.toHexString(ext).toUpperCase();
        int a=315<<6;
        a|=mode<<3;
        a|=registro1<<0;
        String com=Integer.toString(a,16);
        if (mode==5||mode==6|| mode==7)
                com+=extH;
        sumaBytes+=longitud(com);
        S1principal+=com;

}

}
