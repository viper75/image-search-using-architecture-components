package org.viper75.image_search_using_architecture_components.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingSource;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.viper75.image_search_using_architecture_components.api.responses.UnsplashResponse;
import org.viper75.image_search_using_architecture_components.api.service.UnsplashApiService;

import java.io.IOException;
import java.util.concurrent.Executor;

import kotlin.coroutines.Continuation;
import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

@RequiredArgsConstructor
public class UnsplashPagingSource extends ListenableFuturePagingSource<Integer, UnsplashPhoto> {

    private static final int UNSPLASH_STARTING_PAGE_INDEX = 1;

    private final UnsplashApiService unsplashApiService;
    private final String query;
    private final Executor backgroundExecutor;

    private int currentPageNumber;

    @NotNull
    @Override
    public ListenableFuture<LoadResult<Integer, UnsplashPhoto>> loadFuture(@NotNull LoadParams<Integer> loadParams) {
        currentPageNumber = (loadParams.getKey() == null)? UNSPLASH_STARTING_PAGE_INDEX : loadParams.getKey();

        ListenableFuture<LoadResult<Integer, UnsplashPhoto>> pageFuture = Futures.transform(
                unsplashApiService.searchPhotos(query, currentPageNumber, loadParams.getLoadSize()),
                this::toLoadResult, backgroundExecutor);

        ListenableFuture<LoadResult<Integer, UnsplashPhoto>> partialLoadResultFuture = Futures.catching(
                pageFuture, HttpException.class,
                LoadResult.Error::new, backgroundExecutor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, backgroundExecutor);
    }

    private LoadResult<Integer, UnsplashPhoto> toLoadResult(@NonNull UnsplashResponse response) {
        return new LoadResult.Page<>(
                response.getResults(),
                Math.max(UNSPLASH_STARTING_PAGE_INDEX, currentPageNumber - 1), // Only paging forward.
                currentPageNumber + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
}
