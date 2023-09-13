package org.example;
import java.util.HashMap;
import java.util.Map;

public class Conversor {
    private Map<String, Map<String, Double>> conversionRates;


    public Conversor() {
        conversionRates = new HashMap<>();

        Map<String, Double> monedaConversion = new HashMap<>();
        monedaConversion.put("Peso Colombiano - COP", 1.0);
        monedaConversion.put("Dólar estadounidense - USD", 0.00027);
        monedaConversion.put("Euro - EUR", 0.00023);
        monedaConversion.put("Libra esterlina - GBP", 0.00021);
        monedaConversion.put("Franco suizo - CHF", 0.00025);
        monedaConversion.put("Yen japonés - JPY", 0.029);
        monedaConversion.put("Dólar hongkonés - HKD", 0.002);
        monedaConversion.put("Dólar canadiense - CAD", 0.00034);
        monedaConversion.put("Yuan chino - CNY", 0.0017);
        monedaConversion.put("Dólar australiano - AUD", 0.00037);
        monedaConversion.put("Real brasileño - BRL", 0.0014);
        monedaConversion.put("Rublo ruso - RUB", 0.020);
        monedaConversion.put("Peso mexicano - MXN", 0.0052);

        conversionRates.put("Monedas", monedaConversion);


        Map<String, Double> temperaturaConversion = new HashMap<>();
        temperaturaConversion.put("Fahrenheit (°F)", 1.0);
        temperaturaConversion.put("Celsius (°C)", 1.8);
        temperaturaConversion.put("Kelvin (K)", 0.5556);

        conversionRates.put("Temperatura", temperaturaConversion);
    }

    public double convertir(String tipo, String unidadOrigen, String unidadDestino, double valor) {
        if ("Monedas".equals(tipo)) {
            Map<String, Double> conversionMap = conversionRates.get(tipo);
            if (conversionMap != null) {
                double valorEnPesos = valor / conversionMap.get(unidadOrigen);
                return valorEnPesos * conversionMap.get(unidadDestino);
            } else {
                throw new IllegalArgumentException("Tipo de conversión de monedas no válido.");
            }
        } else if ("Temperatura".equals(tipo)) {
            if ("Fahrenheit (°F)".equals(unidadOrigen) && "Celsius (°C)".equals(unidadDestino)) {
                return (valor - 32) * 5 / 9;
            } else if ("Celsius (°C)".equals(unidadOrigen) && "Fahrenheit (°F)".equals(unidadDestino)) {
                return (valor * 9 / 5) + 32;
            } else if ("Celsius (°C)".equals(unidadOrigen) && "Kelvin (K)".equals(unidadDestino)) {
                return valor + 273.15;
            } else if ("Kelvin (K)".equals(unidadOrigen) && "Celsius (°C)".equals(unidadDestino)) {
                return valor - 273.15;
            } else if ("Fahrenheit (°F)".equals(unidadOrigen) && "Kelvin (K)".equals(unidadDestino)) {
                return (valor + 459.67) * 5 / 9;
            } else if ("Kelvin (K)".equals(unidadOrigen) && "Fahrenheit (°F)".equals(unidadDestino)) {
                return (valor * 9 / 5) - 459.67;
            } else if (unidadOrigen.equals(unidadDestino)) {
                return valor;
            } else {
                throw new IllegalArgumentException("Tipo de conversión de temperatura no válido.");
            }
        } else {
            throw new IllegalArgumentException("Tipo de conversión no válido.");
        }
    }
}