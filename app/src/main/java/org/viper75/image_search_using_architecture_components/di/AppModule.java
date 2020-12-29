package org.viper75.image_search_using_architecture_components.di;

import org.viper75.image_search_using_architecture_components.api.service.UnsplashApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UnsplashApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public UnsplashApiService provideUnsplashApiService(Retrofit retrofit) {
        return retrofit.create(UnsplashApiService.class);
    }
}
