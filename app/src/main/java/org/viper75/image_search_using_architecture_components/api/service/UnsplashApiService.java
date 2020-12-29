package org.viper75.image_search_using_architecture_components.api.service;

import com.google.common.util.concurrent.ListenableFuture;

import org.viper75.image_search_using_architecture_components.BuildConfig;
import org.viper75.image_search_using_architecture_components.api.responses.UnsplashResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UnsplashApiService {

    String BASE_URL = "https://api.unsplash.com/";
    String CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY;

    @Headers({"Accept-Version: v1", "Authorization: Client-ID " + CLIENT_ID})
    @GET("search/photos")
    ListenableFuture<UnsplashResponse> searchPhotos(@Query("query") String query, @Query("page") Integer page, @Query("per_page") int perPage);
}
