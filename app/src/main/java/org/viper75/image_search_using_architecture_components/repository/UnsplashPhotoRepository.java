package org.viper75.image_search_using_architecture_components.repository;

import org.viper75.image_search_using_architecture_components.api.service.UnsplashApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UnsplashPhotoRepository {
    @Inject
    private final UnsplashApiService unsplashApiService;
}
