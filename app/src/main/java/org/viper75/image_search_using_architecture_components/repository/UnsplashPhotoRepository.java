package org.viper75.image_search_using_architecture_components.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import org.viper75.image_search_using_architecture_components.api.service.UnsplashApiService;
import org.viper75.image_search_using_architecture_components.models.UnsplashPagingSource;
import org.viper75.image_search_using_architecture_components.models.UnsplashPhoto;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UnsplashPhotoRepository {
    @Inject
    private final UnsplashApiService unsplashApiService;
    @Inject
    private final Executor executorService;

    public LiveData<PagingData<UnsplashPhoto>> getSearchResults(String query) {

        Pager<Integer, UnsplashPhoto> pager = new Pager<>(
                new PagingConfig(20),
                () -> new UnsplashPagingSource(unsplashApiService, query, executorService)
        );

        return PagingLiveData.getLiveData(pager);
    }
}
