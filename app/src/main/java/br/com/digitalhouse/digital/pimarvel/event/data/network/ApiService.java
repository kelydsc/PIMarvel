package br.com.digitalhouse.digital.pimarvel.event.data.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    // Url da api
    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/";
    public static final String PUBLIC_KEY = "d38f9e6c5e71a38f79ecba2cab98fdaa";
    public static final String PRIVATE_KEY = "26191c8d15d6b17b0389e8fbf43f210316c43523";

    // Instancia que criaremos do retrofit
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {

        if (retrofit == null) {

            // configurações da conexão
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            // Inicializamos o retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    // Retornamos a instancia da API criada com o retrofit
    public static Api getApiService() {
        return getRetrofit().create(Api.class);
    }
}