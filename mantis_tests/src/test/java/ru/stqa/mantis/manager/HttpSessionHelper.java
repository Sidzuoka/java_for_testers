package ru.stqa.mantis.manager;

import okhttp3.*;
import java.io.IOException;
import java.net.CookieManager;


public class HttpSessionHelper  extends HelperBase {

    OkHttpClient client; //OkHttpClient client = new OkHttpClient();

    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
        //м-д .build() строит http-клиент
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build(); //формирует значение нужного типа и помещает ее в переменную formBody

        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl"))) //local.properties web.baseUrl=http://localhost/mantisbt-2.25.8/
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(String.format(manager.property("web.baseUrl"))) //local.properties web.baseUrl=http://localhost/mantisbt-2.25.8/
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            //сохраняем ответ для его дальнейшего анализа
            String body = response.body().string(); //возвращает объект, а не строку, т.к. тело запроса может содержать не только строку
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
