/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package momento;

import static demo.MinMaxCategoryPlotDemo1.createChart;
import java.awt.Dimension;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Alba Proyecto
 */
public class Momento {
    
    private static Frame_momento fr;
    private static Frame_cortante fc;

    public Momento() {        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double sigma_a = 1.67;
        double sigma_b = 3.71;
        double altura_muro = 3.5;
        double P = (sigma_b - sigma_a) / altura_muro;
        double h1 = 3.0;
        double h2 = 0.5;
        double fs = 1.0;
        double fe = 1.6;
        double xs = altura_muro * 0.6;
        double pae = 5.07280459892461;
        double pa = 3.549561538258196;
        double fi_mr = 6.489;
        //double pa = 3.572916667;
        //****variacion_pae y Ps del excel son lo mismo
        double variacion_pae = pae - pa;
        double factor = 100.0;
        int aux = (int) factor;
        double posicion_bastones=17.2838;
        double fijo = altura_muro / factor;
        double[] long2 = new double[aux];
        double[] long1 = new double[aux];
        //**** sigma_a es lo mismo que w1 en el excel 
        double[] w2 = new double[aux];
        double[] m1 = new double[aux];
        double[] m2 = new double[aux];
        double[] mrb = new double[aux];
        double[] ms = new double[aux];
        double[] sum = new double[aux];
        double[] v1 = new double[aux];
        double[] v2 = new double[aux];
        double[] vrb = new double[aux];
        double[] vs = new double[aux];
        double[] sum_mcv = new double[aux];
//        double[] rb = new double[aux];
//        double[] g1 = new double[aux];
//        double[] g2 = new double[aux];
//        double[] g3 = new double[aux];
        for (int i = 0; i < long2.length; i++) {
            long2[i] = altura_muro - (fijo * i);
            long1[i] = (fijo * i);
            w2[i] = (P * long1[i]) + sigma_a;
            m1[i] = (sigma_a * long1[i]) * (long1[i] * 0.5);
            m2[i] = ((w2[i] - sigma_a) * long1[i]) / 2 * (1.0 / 3.0) * long1[i];
            mrb[i] = ((long1[i] - altura_muro) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
//            g1[i]=(sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe);
//            g2[i]=((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe);
//            g3[i]=(xs * variacion_pae * fs) ;
//            rb[i]=(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2);
            ms[i] = ((long1[i] > (altura_muro - xs)) ? variacion_pae * (long1[i] - (altura_muro - xs)) : 0);
            sum[i] = ((m1[i] + m2[i]) * fe) + (ms[i] * fs) + mrb[i];
            v1[i] = long1[i] * sigma_a;
            v2[i] = (((w2[i] - sigma_a) * long1[i]) / 2);
            vrb[i] = ((long1[i] - h1) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
            vs[i] = (long1[i] > (altura_muro - xs)) ? variacion_pae : 0;
            sum_mcv[i] = ((v1[i] + v2[i]) * fe) + (vs[i] * fs) + vrb[i];
        }
        for (int j = 0; j < long2.length; j++) {
            System.out.println((j + 1) + "                      " + long1[j] + "                      " + long2[j] + "                      " + sigma_a + "                      " + w2[j] + "                      " + m1[j] + "                      " + m2[j] + "                      " + mrb[j] + "                              " + ms[j] + "                           " + sum[j]);
            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        }
        System.out.println("");
        System.out.println("");
        for (int j = 0; j < v1.length; j++) {
            System.out.println((j + 1) + "                      " + v1[j] + "                      " + v2[j] + "                      " + vrb[j] + "                      " + vs[j] + "                      " + sum_mcv[j]);
            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        }
        fr=new Frame_momento(sum,long2,altura_muro,fi_mr);
        fr.setVisible(true);
        fc=new Frame_cortante(long2,sum_mcv,posicion_bastones);
        fc.setVisible(true);
    }

}
