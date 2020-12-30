package org.viper75.image_search_using_architecture_components.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.viper75.image_search_using_architecture_components.adapters.UnsplashPhotoAdapter;
import org.viper75.image_search_using_architecture_components.databinding.GalleryFragmentLayoutBinding;
import org.viper75.image_search_using_architecture_components.viewmodel.GalleryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GalleryFragment extends Fragment {

    private GalleryViewModel viewModel;
    private GalleryFragmentLayoutBinding binding = null;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = GalleryFragmentLayoutBinding.inflate(inflater, container, false);

        UnsplashPhotoAdapter adapter = new UnsplashPhotoAdapter();
        binding.unsplashPhotosRecyclerView.setAdapter(adapter);
        binding.unsplashPhotosRecyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        viewModel.getPhotos().observe(requireActivity(), data -> {
            adapter.submitData(getViewLifecycleOwner().getLifecycle(), data);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}