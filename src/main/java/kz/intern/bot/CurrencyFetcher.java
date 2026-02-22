package kz.intern.bot;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyFetcher {

        public static void main(String[] args) {
            // 1. Ссылка на бесплатный API курсов (базовая валюта - KZT)
            String url = "https://api.exchangerate-api.com/v4/latest/KZT";

            // 2. Создаем клиента (тот, кто отправляет запрос)
            try (HttpClient client = HttpClient.newHttpClient()) {

                // 3. Формируем сам запрос
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                // 4. Отправляем запрос и получаем ответ в виде строки
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // 5. Проверяем статус (200 - это успех) и выводим результат
                if (response.statusCode() == 200) {
                    System.out.println("Данные успешно получены:");
                    System.out.println(response.body());
                } else {
                    System.out.println("Ошибка! Статус-код: " + response.statusCode());
                }

            } catch (Exception e) {
                // Обработка ошибок (например, если нет интернета)
                System.out.println("Произошла ошибка при запросе: " + e.getMessage());
                e.printStackTrace();
            }
        }
}
