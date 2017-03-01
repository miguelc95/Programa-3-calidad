import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.io.File;
import java.text.DecimalFormat;
import java.math.RoundingMode;


/**
 *
 * @author miguelcuellar
 */
//&p-Parejas
  class Parejas{
   public double x = 0.0;
   public double y = 0.0;
//&i
  public  Parejas(){
    x=0;
    y=0;
  }
  //&i
  public Parejas(double equis, double ye){
    x=equis;
    y=ye;
  }
//&i
   public void setX(double equis){
     x = equis;
   }
//&i
   public void setY(double ye){
     y = ye;
   }
   //&i
   public double getX(){
     return x;
   }
   //&i
   public double getY(){
     return y;
   }
 }

//&p-DatosArchivo
 class DatosArchivo{
   //&b=17
   public String sNombre = " ";
   int xk = 0;
   ArrayList<Parejas> ArrParejas = new ArrayList<Parejas>();
   int N = 0;
   double r = 0.0;
   double r2 = 0.0;
   double b0 = 0.0;
   double b1 = 0.0;
   double yk = 0.0;
//&d=3

//&i
   public DatosArchivo(){
     sNombre = " ";
//&d=2
   }
//&i
   public DatosArchivo(String n){
     sNombre = n;
//&d=2
   }
//&i
   public void setNombre(String n){
     sNombre = n;
   }
//&i
   public String getNombre(){
     return sNombre;
   }
//&i
   public void Imprimir(){
     //&d=8
     DecimalFormat Redondeo = new DecimalFormat("#.#####");
     Redondeo.setRoundingMode(RoundingMode.HALF_UP);
     System.out.println("N  = "+N);
     System.out.println("xk  =  "+ xk);
     System.out.println("r  =  "+ Redondeo.format(r));
     System.out.println("r2  =  "+ Redondeo.format(r2));
     System.out.println("b0  =  "+ Redondeo.format(b0));
     System.out.println("b1  =  "+ Redondeo.format(b1));
     System.out.println("yk  =  "+ Redondeo.format(yk));

   }

   public boolean leer(){
     //&d=26
     Parejas pareja = new Parejas();
     int icont = 0;
     double acumXi=0.0;
     double acumYi=0.0;
     double acumXiYi=0.0;
     double acumXiSquared=0.0;
     double acumYiSquared=0.0;
     try {
          BufferedReader in = new BufferedReader(new FileReader(sNombre));
          String linea;
          while((linea = in.readLine()) != null) {
            if (icont==0) {
              xk=Integer.parseInt(linea.trim());
            }else{
              String[] parteSeparada = linea.split("\\,");
              pareja.setX(Double.parseDouble(parteSeparada[0]));
              pareja.setY(Double.parseDouble(parteSeparada[1]));
              acumXi+=Double.parseDouble(parteSeparada[0]);
              acumYi+=Double.parseDouble(parteSeparada[1]);
              acumXiYi+=Double.parseDouble(parteSeparada[0])*Double.parseDouble(parteSeparada[1]);
              acumXiSquared+=Double.parseDouble(parteSeparada[0])*Double.parseDouble(parteSeparada[0]);
              acumYiSquared+=Double.parseDouble(parteSeparada[1])*Double.parseDouble(parteSeparada[1]);
              N++;
            }
            icont++;
          }
          r = (N*acumXiYi - acumXi*acumYi)/(Math.sqrt((N*acumXiSquared-acumXi*acumXi)*(N*acumYiSquared-acumYi*acumYi)));
          r2=r*r;
          b1 = (acumXiYi - (N*(acumXi/N)*(acumYi/N)))/(acumXiSquared-(N*((acumXi/N)*(acumXi/N))));
          b0 = acumYi/N - b1*(acumXi/N);
          yk = b0+b1*xk;
          System.out.println("acumXi: "+acumXi);
          System.out.println("acumYi: "+acumYi);
          System.out.println("acumXiSquared: "+acumXiSquared);
          System.out.println("acumYiSquared: "+acumYiSquared);
          System.out.println("acumXiYi: "+acumXiYi);

          return true;
        }
        catch(IOException e){
          return false;
        }
   }
 }
 //&p-Programa3
public class Programa3{
  //&b=18
    public static void main(String[] args) {
      ArrayList<DatosArchivo> Archivos = new ArrayList<DatosArchivo>();
//&d=1
      System.out.println("Ingrese el nombre del archivo"); //&m
      Scanner nomArchivos = new Scanner(System.in);
      String  inputNombres = nomArchivos.nextLine();
      String[] nombres = inputNombres.split("\\s+"); //&m

      for (int ix = 0; ix<nombres.length; ix++) {
        DatosArchivo arch = new DatosArchivo();
        arch.setNombre(nombres[ix]);
        Archivos.add(arch);
      }


  for(int iy = 0; iy<Archivos.size(); iy++) {
//&d=7
  if(Archivos.get(iy).leer()){
    Archivos.get(iy).Imprimir();
  }else{
    System.out.println("El archivo con nombre: "+Archivos.get(iy).getNombre()+" no existe.");
  }
    }
  }
}
