package ar.edu.et7;

public class Calculos {

    // Método para calcular el valor de cada cuota en el sistema alemán
    public float[] calcularCuotasAleman(float monto, float tna, int cuotas) {
        // Inicializa el array para almacenar el valor de cada cuota
        float[] cuotasMensuales = new float[cuotas];
        
        // Tasa de interés mensual
        float tasaMensual = tna / 12 / 100;
        
        // Amortización fija por cuota
        float amortizacionFija = monto / cuotas;
        
        // Saldo pendiente inicial
        float saldoPendiente = monto;
        
        // Calcular cada cuota
        for (int i = 0; i < cuotas; i++) {
            // Interés del mes actual
            float interes = saldoPendiente * tasaMensual;
            
            // Valor de la cuota actual
            float cuota = amortizacionFija + interes;
            
            // Almacenar el valor de la cuota
            cuotasMensuales[i] = cuota;
            
            // Actualizar el saldo pendiente
            saldoPendiente -= amortizacionFija;
        }
        
        // Retorna el array con el valor de cada cuota
        return cuotasMensuales;
    }
    
    // Ejemplo de uso
    public static void main(String[] args) {
        Calculos calc = new Calculos();
        float monto = 10000f;  // Monto del préstamo
        float tna = 12f;       // Tasa Nominal Anual en porcentaje
        int cuotas = 12;       // Número de cuotas
        
        // Calcular cuotas
        float[] cuotasMensuales = calc.calcularCuotasAleman(monto, tna, cuotas);
        
        // Imprimir resultados
        for (int i = 0; i < cuotasMensuales.length; i++) {
            System.out.printf("Cuota %d: %.2f%n", i + 1, cuotasMensuales[i]);
        }
    }
}
