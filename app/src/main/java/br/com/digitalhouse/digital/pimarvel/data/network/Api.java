package br.com.digitalhouse.digital.pimarvel.data.network;

import br.com.digitalhouse.digital.pimarvel.comic.model.ComicsResponse;
import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import br.com.digitalhouse.digital.pimarvel.serie.model.SeriesResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("comics?")
    Single<ComicsResponse> getComics(@Query("format") String format,
                                     @Query("formatType") String formatType,
                                     @Query("noVariants") boolean noVariants,
                                     @Query("orderBy") String orderBy,
                                     @Query("limit") String limit,
                                     @Query("ts") String ts,
                                     @Query("hash") String hash,
                                     @Query("apikey") String apikey);

    @GET("series?")
    Single<SeriesResponse> getSeries(@Query("seriesType") String seriesType,
                                     @Query("contains") String contains,
                                     @Query("orderBy") String orderBy,
                                     @Query("limit") String limit,
                                     @Query("ts") String ts,
                                     @Query("hash") String hash,
                                     @Query("apikey") String apikey);

    @GET("events?")
    Single<EventsResponse> getEvents(@Query("orderBy") String orderBy,
                                     @Query("limit") String limit,
                                     @Query("ts") String ts,
                                     @Query("hash") String hash,
                                     @Query("apikey") String apikey);
}
