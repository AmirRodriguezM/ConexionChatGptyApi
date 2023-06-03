package Logica;

import Presentancion.Formulariosegundario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class ConsultaChatGPT extends Formulariosegundario {
    public ConsultaChatGPT(){

    }
    public String getConsultaPreguntaGPT(String promt) {
        String apiUrl = "https://api.openai.com/v1/completions";
        String openaiApiKey = "";

        String text = null;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + openaiApiKey);
            connection.setDoOutput(true);

            String jsonInputString = "{\"model\":\"text-davinci-003\",\"prompt\":\"" + promt + "\",\"max_tokens\":70,\"temperature\":1}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonInputString.getBytes());
                outputStream.flush();
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.toString());

            JsonNode choices = jsonResponse.get("choices");
            int i=1;

            if (i == 1 && choices.size() > 0) {
                JsonNode choice = choices.get(0);
                text = choice.get("text").asText();

            } else {
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return text;
    }
}