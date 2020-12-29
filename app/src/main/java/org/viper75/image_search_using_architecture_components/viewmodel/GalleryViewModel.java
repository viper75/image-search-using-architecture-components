package org.viper75.image_search_using_architecture_components.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import org.viper75.image_search_using_architecture_components.repository.UnsplashPhotoRepository;

public class GalleryViewModel extends ViewModel {
    private final UnsplashPhotoRepository unsplashPhotoRepository;

    @ViewModelInject
    public GalleryViewModel(UnsplashPhotoRepository unsplashPhotoRepository) {
        this.unsplashPhotoRepository = unsplashPhotoRepository;
    }
}
