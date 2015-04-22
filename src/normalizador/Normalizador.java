package normalizador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Aplicación para calcular valores minimos, maximos, medias, y varianza
 * de valores extraidos de datos de prueba.
 * 
 * Se le pasa un archivo con todos los datos y extrae los valores correspondientes
 * a otro archivo.
 * 
 */
public class Normalizador {
    
    public static void main(String[] args) { 
        
        int longitudDatos=12;
        
        //captura datos de entrada
        String archivoEntrada=args[0]; //archivo con valores extraidos.
        String archivoSalida=args[1];
        
        //Entrada de datos desde fichero
        File archivo=new File(archivoEntrada);
        String linea;
        LinkedList listaDatos=new LinkedList();
        try{
            BufferedReader br=new BufferedReader( new FileReader (archivo));
            while((linea=br.readLine())!=null){
                
                if(!linea.trim().equals("") && !linea.contains("//")  ){ //para evitar lineas vacias 7 comentarios
                
                    System.out.println(linea);
                    String[] valores = linea.split(",");
                    double[] valoresD=new double[valores.length];
                    for(int i=0;i<valores.length;i++){
                    
                        valoresD[i]=Double.parseDouble(valores[i]);
                    }
                    listaDatos.add(valoresD);   
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
        
        double[][] datosPaEntrena=new double[listaDatos.size()][longitudDatos];
        for(int j=0;j<listaDatos.size();j++){
            datosPaEntrena[j]=(double[])listaDatos.get(j);
        }      
        
        //Procesamiento estadístico.
        Calculador micalculador=new Calculador(datosPaEntrena);   
      
        //Salida de datos a fichero.
        File archSalida=new File(archivoSalida);
      
        String textoArchivo="#Datos estadísticos \r\n";        
        textoArchivo +=   micalculador.getDatos();
      
        //escritura en fichero nuevo
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(archSalida,false)));
            writer.println(textoArchivo);
            writer.close();  
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
    
}
