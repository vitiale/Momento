/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package momento;

/**
 *
 * @author Alba Proyecto
 */
public class Momento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double sigma_a=0.51;
        double sigma_b=2.57;
        double altura_muro=2.9;
        double P=(sigma_b-sigma_a)/altura_muro;
        double h1 = 1.90;
        double h2 = 1.0;
        double fs = 1.0;
        double fe = 1.6;
        double xs = altura_muro*0.6;
        double pae=2.995229073;
        double pa=2.995229073;
        //****variacion_pae y Ps del excel son lo mismo
        double variacion_pae=pae-pa;
        double factor = 100.0;
        int aux=(int)factor;
        double fijo=altura_muro/factor;
        double[] long2=new double[aux];
        double[] long1=new double[aux];
        //**** sigma_a es lo mismo que w1 en el excel 
        double[] w2=new double[aux];
        double[] m1=new double[aux];
        double[] m2=new double[aux];
        double[] mrb=new double[aux];
        double[] ms=new double[aux];
        double[] sum=new double[aux];
        double[] v1=new double[aux];
        double[] v2=new double[aux];
        double[] vrb=new double[aux];
        double[] vs=new double[aux];
        double[] sum_mcv=new double[aux];
        for(int i=0; i<long2.length; i++){
            long2[i]=altura_muro-(fijo*i);
            long1[i]=(fijo*i);
            w2[i]=(P*long1[i])+sigma_a;
            m1[i]=(sigma_a*long1[i]) * (long1[i]*0.5);
            m2[i]=( (w2[i]-sigma_a) *long1[i] )/2 * (1.0/3.0) * long1[i];
            mrb[i]= ((long1[i]-altura_muro)>0)? ( (sigma_a*Math.pow(altura_muro, 2)*0.5*fe) + ((sigma_b-sigma_a)*Math.pow(altura_muro, 2)) * ( (1.0/6.0) *fe) + (xs*variacion_pae*fs) ) / h2 :0;
            ms[i]= ( (long1[i]>(altura_muro-xs))?variacion_pae*(long1[i]-(altura_muro-xs)):0 ) ;
            sum[i]= ( (m1[i]+m2[i]) * fe ) + (ms[i]*fs) + mrb[i];
            v1[i]= long1[i]*sigma_a;
            v2[i]= (((w2[i]-sigma_a)*long1[i])/2);
            vrb[i]=  ((long1[i]-h1)>0)? ( (sigma_a*Math.pow(altura_muro, 2)*0.5*fe) + ((sigma_b-sigma_a)*Math.pow(altura_muro, 2)) * ( (1.0/6.0) *fe) + (xs*variacion_pae*fs) ) / h2 :0;
            //vs[i]= long1>(altura_muro-xs)
        }
        for(int j=0; j<long2.length; j++){
            System.out.println((j+1) +"                      "+long1[j] +"                      "+long2[j]+"                      "+sigma_a+"                      "+ w2[j]+"                      "+ m1[j]+"                      "+ m2[j]+"                      "+ mrb[j]+"                              "+ ms[j]+"                           "+ sum[j]);
            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        }
    }
    
}
