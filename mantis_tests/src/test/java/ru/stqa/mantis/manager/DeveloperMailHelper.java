package ru.stqa.mantis.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeUtility;
import okhttp3.*;
import ru.stqa.mantis.manager.developermail.AddUserResponse;
import ru.stqa.mantis.manager.developermail.GetIdsResponse;
import ru.stqa.mantis.manager.developermail.GetMessageResponse;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;

import static ru.stqa.mantis.manager.JamesApiHelper.JSON;

public class DeveloperMailHelper extends HelperBase {

    OkHttpClient client;


    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public DeveloperMailUser addUser() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("https://www.developermail.com/api/v1/mailbox")
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            var text = response.body().string();
            var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);
            if (!addUserResponse.success()) {
                throw new RuntimeException(addUserResponse.error().toString());
            }
            return addUserResponse.result();//вернется информация о созданном пользователе
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(DeveloperMailUser user) {
        Request request = new Request.Builder()
                .url(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()))
                .header("X-MailboxToken", user.token())
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String receive(DeveloperMailUser user, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {

            try {
                var text1 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()), user.token());
                //делаем преобразование ответа
                var response1 = new ObjectMapper().readValue(text1, GetIdsResponse.class);
                //проаерка - пришла ли почта
                if (response1.result().size() > 0) {
                    var text2 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s/messages/%s",
                            user.name(), response1.result().get(0)), user.token());
                    if (!response1.success()) {
                        throw new RuntimeException(response1.errors().toString());
                    }
                    var response2 = new ObjectMapper().readValue(text2, GetMessageResponse.class);
                    if (!response2.success()) {
                        throw new RuntimeException(response2.errors().toString());
                    }
                    //если и второй запрос выпонился успешно, возвращаем
                    return new String(
                            //раскодируем текст письма
                            //декодер работает с байтами, поэтому переводим в байты, декодируем, переводим байты в строку
                            MimeUtility.decode(
                                    new ByteArrayInputStream(response2.result().getBytes()),
                                    "quoted-printable").readAllBytes());
                }
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            //ждем почту 1 сек., если не пришла, выполняем повторную проверку
            try {
                Thread.sleep(1000); // 1 сек.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail");
    }

    String get(String url, String token) {
        Request request = new Request.Builder()
                .url(url)
                .header("X-MailboxToken", token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
