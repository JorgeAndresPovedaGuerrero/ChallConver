package org.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/***
 * @author Andrés Poveda
 * */

public class ConversorAppUI extends Application {


    private Conversor conversor;

    public ConversorAppUI() {
        conversor = new Conversor();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Conversor");
        String css = getClass().getResource("/styles/style.css").toExternalForm();
        conversor = new Conversor();

        Label titleLabel = new Label("Conversor");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setStyle("-fx-font-size: 20px;");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);

        ToggleGroup conversionType = new ToggleGroup();
        RadioButton monedasButton = new RadioButton("Monedas");
        monedasButton.setToggleGroup(conversionType);
        monedasButton.getStyleClass().add("radio-button");
        RadioButton temperaturaButton = new RadioButton("Temperatura");
        temperaturaButton.getStyleClass().add("radio-button");
        temperaturaButton.setToggleGroup(conversionType);


        ComboBox<String> listaOrigen = new ComboBox<>();
        ComboBox<String> listaDestino = new ComboBox<>();

        monedasButton.setOnAction(event -> {
            listaOrigen.getItems().clear();
            listaDestino.getItems().clear();
            listaOrigen.getItems().addAll(
                    "Peso Colombiano - COP",
                    "Dólar estadounidense - USD",
                    "Euro - EUR",
                    "Libra esterlina - GBP",
                    "Franco suizo - CHF",
                    "Yen japonés - JPY",
                    "Dólar hongkonés - HKD",
                    "Dólar canadiense - CAD",
                    "Yuan chino - CNY",
                    "Dólar australiano - AUD",
                    "Real brasileño - BRL",
                    "Rublo ruso - RUB",
                    "Peso mexicano - MXN"
            );
            listaDestino.getItems().addAll(
                    "Peso Colombiano - COP",
                    "Dólar estadounidense - USD",
                    "Euro - EUR",
                    "Libra esterlina - GBP",
                    "Franco suizo - CHF",
                    "Yen japonés - JPY",
                    "Dólar hongkonés - HKD",
                    "Dólar canadiense - CAD",
                    "Yuan chino - CNY",
                    "Dólar australiano - AUD",
                    "Real brasileño - BRL",
                    "Rublo ruso - RUB",
                    "Peso mexicano - MXN"
            );
        });

        temperaturaButton.setOnAction(event -> {
            listaOrigen.getItems().clear();
            listaDestino.getItems().clear();
            listaOrigen.getItems().addAll(
                    "Fahrenheit (°F)",
                    "Celsius (°C)",
                    "Kelvin (K)"
            );
            listaDestino.getItems().addAll(
                    "Fahrenheit (°F)",
                    "Celsius (°C)",
                    "Kelvin (K)"
            );
        });

        // Campo de texto
        TextField valorCampo = new TextField();
        valorCampo.setPromptText("Ingrese el valor");
        // Texto Respuesta
        Label resultadoLabel = new Label("");
        resultadoLabel.setStyle("-fx-font-size: 16px;");
        resultadoLabel.setAlignment(javafx.geometry.Pos.CENTER);

        // Botón Convertir
        Button convertirButton = new Button("Convertir");
        convertirButton.getStyleClass().add("styled-button");
        convertirButton.setOnAction(event -> {
            String tipoConversion = monedasButton.isSelected() ? "Monedas" : "Temperatura";
            String unidadOrigen = listaOrigen.getValue();
            String unidadDestino = listaDestino.getValue();
            String valorTexto = valorCampo.getText();

            try {
                double valor = Double.parseDouble(valorTexto);
                double resultado = conversor.convertir(tipoConversion, unidadOrigen, unidadDestino, valor);

                DecimalFormat formatoResult = new DecimalFormat("#.##");
                String resultadorFormateado = formatoResult.format(resultado);

                resultadoLabel.setText("Resultado: " + resultadorFormateado);
            } catch (NumberFormatException e) {

                System.err.println("Ingrese un valor válido.");
            } catch (IllegalArgumentException e) {

                System.err.println(e.getMessage());
            }
        });

        // Diseño General
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(
                titleLabel,
                new HBox(10, monedasButton, temperaturaButton),
                new Label("De:"),
                listaOrigen,
                new Label("A:"),
                listaDestino,
                valorCampo,
                convertirButton,
                resultadoLabel
        );

        Scene scene = new Scene(vbox, 400, 400);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}