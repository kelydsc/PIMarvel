package br.com.digitalhouse.digital.pimarvel.data.network;

import br.com.digitalhouse.digital.pimarvel.model.comic.ComicResponse;
import br.com.digitalhouse.digital.pimarvel.model.event.EventResponse;
import br.com.digitalhouse.digital.pimarvel.model.serie.SerieResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("comics?")
    Single<ComicResponse> getComics(@Query("format") String format,
                                    @Query("formatType") String formatType,
                                    @Query("noVariants") boolean noVariants,
                                    @Query("orderBy") String orderBy,
                                    @Query("limit") String limit,
                                    @Query("ts") String ts,
                                    @Query("hash") String hash,
                                    @Query("apikey") String apikey);

    @GET("events?")
    Single<EventResponse> getEvents(@Query("orderBy") String orderBy,
                                    @Query("limit") String limit,
                                    @Query("ts") String ts,
                                    @Query("hash") String hash,
                                    @Query("apikey") String apikey);

    @GET("series?")
    Single<SerieResponse> getSeries(@Query("seriesType") String seriesType,
                                    @Query("contains") String contains,
                                    @Query("orderBy") String orderBy,
                                    @Query("limit") String limit,
                                    @Query("ts") String ts,
                                    @Query("hash") String hash,
                                    @Query("apikey") String apikey);


}
