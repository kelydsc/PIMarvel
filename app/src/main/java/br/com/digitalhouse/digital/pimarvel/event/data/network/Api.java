package br.com.digitalhouse.digital.pimarvel.event.data.network;

import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("comics")
    Single<EventsResponse> getEvents (
            @Query("format") String format,
            @Query("formatType") String formatType,
            @Query("noVariants") boolean noVariants,
            @Query("orderBy") String orderBy,
            @Query("limit") String limit,
            @Query("ts") String ts,
            @Query("hash") String hash,
            @Query("apikey") String apikey);
}
