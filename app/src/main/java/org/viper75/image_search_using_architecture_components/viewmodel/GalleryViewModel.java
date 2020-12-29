package org.viper75.image_search_using_architecture_components.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import org.viper75.image_search_using_architecture_components.models.UnsplashPhoto;
import org.viper75.image_search_using_architecture_components.repository.UnsplashPhotoRepository;

import kotlinx.coroutines.CoroutineScope;

public class GalleryViewModel extends ViewModel {
    private final String DEFAULT_SEARCH_QUERY = "cats";
    private final UnsplashPhotoRepository unsplashPhotoRepository;

    private MutableLiveData<String> currentQuery = new MutableLiveData<>();
    private LiveData<PagingData<UnsplashPhoto>> photos;

    @ViewModelInject
    public GalleryViewModel(UnsplashPhotoRepository unsplashPhotoRepository) {
        this.unsplashPhotoRepository = unsplashPhotoRepository;
        searchPhoto(DEFAULT_SEARCH_QUERY);
    }

    public void searchPhoto(String query) {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        photos = PagingLiveData.cachedIn(unsplashPhotoRepository.getSearchResults(query), viewModelScope);
    }
}
