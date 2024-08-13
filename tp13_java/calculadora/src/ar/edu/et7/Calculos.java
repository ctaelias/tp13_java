package ar.edu.et7;

public class Calculos {

    // Método para sumar tres números enteros
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }
    
    // Método para calcular las cuotas alemanas
    public float[] calcularCuotasAlemanas(float monto, float tna, int cuotas) {
        float[] cuotasAlemanas = new float[cuotas];
        float amortizacionFija = monto / cuotas;
        float saldoPendiente = monto;
        float tasaMensual = tna / 100 / 12;
        
        for (int i = 0; i < cuotas; i++) {
            float interes = saldoPendiente * tasaMensual;
            float cuotaTotal = amortizacionFija + interes;
            cuotasAlemanas[i] = cuotaTotal;
            saldoPendiente -= amortizacionFija;
        }
        
        return cuotasAlemanas;
    }
}

