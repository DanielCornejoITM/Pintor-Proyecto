public class Ensamble{
  String S1principal="";//Inserta TODOS los codigos Hexadecimales del código, al imprimir se generan las lineas automaticas.
int contS1=0;//Contador de bytes
int sumaBytes=0;
int Direccion=0;
int noBytes=0;
String inicio="";
String S0="S0";
String primerej="";
boolean first=false;

void ORG(String inicio,String Et){
      String start="0000";
      switch(inicio.length()){
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



  void ADDI(int size, int mode, int registro,int data){
      int a = 3 <<9;
      a |=size<<6;
      a |= mode <<3;
      a |= registro;

      String com=Integer.toString(a, 16);
         com="0"+com;
          sumaBytes+=longitud(com);//201+10
      S1principal+=com;

       System.out.println("ensamblado ADDI:"+ com);
  }


  void ORI(int size, int mode, int registro,int data){
      int a = size<<6;
      a |= mode <<3;
      a |= registro;

      String com=Integer.toString(a, 16);

      if(size==0&&mode==0){
          com="000"+com;
      }

      if(size==0 && mode != 0){
          com="00"+com;
      }

      if(size==1 || size==2){
          com="00"+com;
      }

         sumaBytes+=longitud(com);//201+10
      S1principal+=com;

       System.out.println("ensamblado ORI:"+ com);
  }

  void BCHG(int registro, int mode, int registro2, int ext, int version){
      if(version == 1){
          int a = registro<<9;
          a |= 1<<8;
          a |= 1<<6;
          a |= mode<<3;
          a |= registro2;
          String com=Integer.toString(a, 16);
          com="0"+com;
          sumaBytes+=longitud(com);//201+10
      S1principal+=com;


              System.out.println("ensamblado BCHG:"+ com);
      }

      if(version == 2){
          int a = 1<<11;
          a |= 1<<6;
          a |= mode<<3;
          a |= registro2;
          String com=Integer.toString(a, 16);
          com="0"+com;
          sumaBytes+=longitud(com);//201+10
      S1principal+=com;

              System.out.println("ensamblado BCHG:"+ com);
      }

  }

  void BTST(int ext, int registro, int registro2, int mode, int version,int data){
      if(version==1){
          int a = registro<<9;
          a|=1<<8;
          a|= mode<<3;
          a|= registro2;
          String com=Integer.toString(a, 16);
          com="0"+com;
         sumaBytes+=longitud(com);//201+10
      S1principal+=com;
         /* S1principal+=com;
          contS1+=2;//Número de bytes de la instrucción
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
          if (contS1==252) {
              Imprimir2S1();
          }*/


          System.out.println("ensamblado BTST:"+ com);
      }

      if(version==2){
          int a = 1<<11;
          a|= mode<<3;
          a|= registro2;
          String com=Integer.toString(a, 16);
          com="0"+com;
          sumaBytes+=longitud(com);//201+10
      S1principal+=com;
          /*S1principal+=com;
          contS1+=2;//Número de bytes de la instrucción
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
          if (contS1==252) {
              Imprimir2S1();
          }*/


          System.out.println("ensamblado BTST:"+ com);
      }

  }



  void JSR (int ext, int registro, int mode){
      int a = 1<<14;
      a|=7<<9;
      a|=1<<7;
      a|= mode<<3;
      a|= registro;
      String com=Integer.toString(a, 16);
       sumaBytes+=longitud(com);//201+10
      S1principal+=com;
     /* S1principal+=com;
      contS1+=2;//Número de bytes de la instrucción
      sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

      System.out.println("ensamblado JSR:"+ com);
  }



  void ANDITOSR (int ext,int data){
      int a = 1<<9;
      a|=31<<2;
      String com=Integer.toString(a, 16);
      com="0"+com;
       sumaBytes+=longitud(com);//201+10
      S1principal+=com;
        /*  S1principal+=com;
      contS1+=2;//Número de bytes de la instrucción
      sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }
*/
      System.out.println("ensamblado Andi to sr:"+ com);
  }

  void LSL(int registro, int registro2, int ext, int ir, int size, int version, int mode,int data){
      if(version == 1){
          int a = 7<<13;
          a |= registro<<9;
          a |= 1<<8;
          a |= size<<6;
          a |= ir<<5;
          a |= 1<<3;
          a |= registro2;

          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
         /* S1principal+=com;


          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSL:"+com);

      }

      if(version == 2){
          int a = 7<<13;
          a |= ext<<9;
          a |= 1<<8;
          a |= size<<6;
          a |= ir<<5;
          a |= 1<<3;
          a |= registro2;

          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
          /*S1principal+=com;
          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSL:"+com);

      }

      if(version == 3){
          int a = 7<<13;
          a |= 1<<9;
          a |= 1<<8;
          a |= 3<<6;
          a |= mode<<3;
          a |= registro;
          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
         /* S1principal+=com;
          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSL:"+com);

      }
  }

  void LSR(int registro, int registro2, int ext, int ir, int size, int version, int mode,int data){
      if(version == 1){
          int a = 7<<13;
          a |= registro<<9;
          a |= size<<6;
          a |= ir<<5;
          a |= 1<<3;
          a |= registro2;

          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
        /*  S1principal+=com;
          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSR:"+com);

      }

      if(version == 2){
          int a = 7<<13;
          a |= ext<<9;
          a |= size<<6;
          a |= ir<<5;
          a |= 1<<3;
          a |= registro2;

          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
          /*S1principal+=com;
          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSR:"+com);

      }

      if(version == 3){
          int a = 7<<13;
          a |= 1<<9;
          a |= 3<<6;
          a |= mode<<3;
          a |= registro;
          String com=Integer.toString(a, 16);
           sumaBytes+=longitud(com);//201+10
      S1principal+=com;
         /* S1principal+=com;
          contS1+=2;
          sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

          System.out.println("ensamblado LSR:"+com);

      }
  }

  void MOVETOSR (int registro,int ext, int mode){
      int a = 1<<14;
      a|=3<<9;
      a|=3<<6;
      a|=mode<<3; /*Primero es dato , segundo posicion*/
      a|=registro;
      String com=Integer.toString(a, 16);
       sumaBytes+=longitud(com);//201+10
      S1principal+=com;
      /*S1principal+=com;
      contS1+=2;//Número de bytes de la instrucción
      sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

      System.out.println("ensamblado move to sr:"+com);
  }


  void MOVEFROMSR (int registro,int ext, int mode){
      int a = 1<<14;
      a|=3<<6;
      a|=mode<<3; /*Primero es dato , segundo posicion*/
      a|=registro;
      String com=Integer.toString(a, 16);
       sumaBytes+=longitud(com);//201+10
      S1principal+=com;
      /*S1principal+=com;
      contS1+=2;//Número de bytes de la instrucción
      sumaBytes+=hex2decimal(com.substring(0, 2))+hex2decimal(com.substring(2, 4));//201+10
      if (contS1==252) {
          Imprimir2S1();
      }*/

      System.out.println("ensamblado move from sr:"+com);
  }

void BSET (int registro1,int registro2,int ext, int mode,int version,int data){

      if(version==1){
      int a =0<<12;
      a|=registro1<<9;
      a|=1<<8;
      a|=1<<7;
      a|=1<<6;
      a|=mode<<3;
      a|=registro2;
      String com=Integer.toString(a, 16);
         com="0"+com;
    sumaBytes+=longitud(com);//201+10
      S1principal+=com;
          System.out.println("ensamblado:"+com);
      }

      if(version==2){
      int a =0<<12;
      a|=1<<11;
      a|=1<<7;
      a|=1<<6;
      a|=mode<<3;
      a|=registro1;
      String com=Integer.toString(a, 16);
          com="0"+com;
    sumaBytes+=longitud(com);//201+10
      S1principal+=com;

System.out.println("ensamblado:"+com);
      }


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
          //Direccion+=255;
      }else{
          inicio=primerej;
          first=true;
      }
      //System.out.println(" Direccion: "+inicio+" noBytes: "+noBytes+" Contador de bytes:"+contS1);
      switch(inicio.length()){
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
      System.out.println(" "+inicio.toUpperCase()+" "+S1principal.toUpperCase()+" "+Complemento(contS1+3+hex2decimal(inicio.substring(0,2))+hex2decimal(inicio.substring(2,4))+sumaBytes));
      Direccion=Direccion+noBytes;
      noBytes=contS1;
      contS1=0;
      sumaBytes=0;
      S1principal="";
  }
void ImprimirS9(){
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
  for (int a=0;a<ensamblado.length();a+=2) {
      par+=hex2decimal(ensamblado.substring(a,a+2));
    canti++;
  }
  int verificar= contS1+canti;

  if(verificar>252){
      Imprimir2S1();
      contS1+=canti;//Número de bytes de la instrucción
  }
  else{contS1=verificar;}//Número de bytes de la instrucción
  return par;
}
}
