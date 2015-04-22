package normalizador;

/**
 * A partir de unos datos obtiene valores estadisticos.
 * 
 * @author SAMUAN
 */
public class Calculador {

    private double[][] datos;
    
    private double[] minimo;
    private double[] maximo;
    private double[] media;
    private double[] desviacion;
    private double[][] normalizado;
    
    private double[] suma;
    private double[] cuadrado;
    
    Calculador(double[][] datosPaEntrena) {
        this.datos=datosPaEntrena;
        
        //iniciar vectores.
        minimo=new double[8];
        maximo=new double[8];
        media=new double[8];
        desviacion=new double[8];
        suma=new double[8];
        cuadrado=new double[8];
        normalizado=new double[datos.length][12];
        
        for(int i=0;i<8;i++){
            minimo[i]=1000;
            maximo[i]=-1000;
            suma[i]=0;
            cuadrado[i]=0;
        }
        
        calcular();
        
        System.out.println("minimo");
        imprimirVector(minimo);
        System.out.println("maximo");
        imprimirVector(maximo);
        System.out.println("suma");
        imprimirVector(suma);
        System.out.println("cuadrado");
          imprimirVector(cuadrado);
          System.out.println("media");
            imprimirVector(media);
            System.out.println("desviacion");
              imprimirVector(desviacion);
              
              
    }   
    
    private void calcular(){
        for(int fila=0;fila<datos.length;fila++){
            for(int carac=0;carac<8;carac++){
                //minimo
                if( datos[fila][carac] < minimo[carac] ) minimo[carac]=datos[fila][carac];
                
                //maximo
                if( datos[fila][carac] > maximo[carac] ) maximo[carac]=datos[fila][carac];
                
                //suma para media
                suma[carac]=suma[carac]+datos[fila][carac];
                
                //cuadrado para desviacion
                cuadrado[carac]=cuadrado[carac]+Math.pow(datos[fila][carac], 2);
                
            }
        }
        //media
        for(int carac=0;carac<8;carac++){
            media[carac]=suma[carac] / datos.length;
            
        }
        for(int carac=0;carac<8;carac++){
           desviacion[carac]=( cuadrado[carac]/datos.length )-Math.pow(media[carac],2);  
           desviacion[carac]=Math.sqrt(desviacion[carac]);
        }
        
        //NORMALIZACION
        for(int fila=0;fila<datos.length;fila++){
            for(int carac=0;carac<8;carac++){
                normalizado[fila][carac] = (datos[fila][carac]-media[carac])/desviacion[carac];
            }
            for(int indice=8;indice<12;indice++){
                normalizado[fila][indice]=datos[fila][indice];
            }
        }
   
    }
   
    
    public String getDatos(){
        String cadena="\r\n Minimos \r\n \r\n";
        cadena += imprimirVector2(minimo);
        cadena += "\r\n \r\n Maximos \r\n \r\n";
        cadena += imprimirVector2(maximo);
        cadena += "\r\n  \r\n Medias  \r\n \r\n";
        cadena += imprimirVector2(media);
        cadena += "\r\n  \r\n Desviación  \r\n \r\n";
        cadena += imprimirVector2(desviacion);    
        
        cadena += "\r\n  \r\n NORMALIZADOS \r\n \r\n";
        cadena += imprimirMatriz(normalizado);
        
        
        return cadena;
    }
    
    
    
    private void imprimirVector(double[] vector){
        for(int i=0;i<vector.length;i++){
            System.out.println( vector[i] );
        }
    }
    
    private String imprimirVector2(double[] vector){
        String cadena="";
        for(int i=0;i<vector.length;i++){
            cadena += vector[i];
            if(i<vector.length-1) cadena += ", ";           
        }        
        return cadena;
    }   
    
    private String imprimirMatriz(double[][] matriz){
        String cadena="";
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                cadena += matriz[i][j];
                if(j<matriz[0].length-1) cadena += ", ";
            }
            cadena += " \r\n ";
        }
        return cadena;
    }
    
    
}
