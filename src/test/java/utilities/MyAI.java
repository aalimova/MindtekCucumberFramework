package utilities;

import io.restassured.response.Response;

import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class MyAI {

    public static void main(String[] args) {
        String question;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Type your question:");
             question = scanner.nextLine();

            Response response = given().baseUri("https://api.openai.com/v1")
                    .and().header("Content-Type", "application/json")
                    .and().header("Authorization", "Bearer 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")
                    .and().body("{\n" +
                            "     \"model\": \"gpt-3.5-turbo\",\n" +
                            "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
                            "     \"temperature\": 0.7\n" +
                            "   }")
                    .when().post("/chat/completions");

            System.out.println(response.body().jsonPath().getString("choices[0].message.content"));
        }
        while (!question.equalsIgnoreCase("stop"));
    }

}
