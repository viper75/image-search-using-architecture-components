package org.viper75.image_search_using_architecture_components.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.viper75.image_search_using_architecture_components.R;
import org.viper75.image_search_using_architecture_components.databinding.UnsplashPhotoItemBinding;
import org.viper75.image_search_using_architecture_components.models.UnsplashPhoto;

public class UnsplashPhotoAdapter extends PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.UnsplashPhotoItemViewHolder> {

    static class UnsplashPhotoItemViewHolder extends RecyclerView.ViewHolder {

        private final UnsplashPhotoItemBinding binding;

        public UnsplashPhotoItemViewHolder(@NonNull UnsplashPhotoItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(UnsplashPhoto photo) {
            Glide.with(binding.getRoot())
                    .load(photo.getUrls().getRegular())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(binding.unsplashPhotoImageView);

            binding.unsplashUsernameTextView.setText(photo.getUser().getUsername());
        }
    }

    private static final DiffUtil.ItemCallback<UnsplashPhoto> UNSPLASH_PHOTO_COMPARATOR = new DiffUtil.ItemCallback<UnsplashPhoto>() {
        @Override
        public boolean areItemsTheSame(@NonNull UnsplashPhoto oldItem, @NonNull UnsplashPhoto newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UnsplashPhoto oldItem, @NonNull UnsplashPhoto newItem) {
            return oldItem.equals(newItem);
        }
    };

    public UnsplashPhotoAdapter() {
        super(UNSPLASH_PHOTO_COMPARATOR);
    }

    @NonNull
    @Override
    public UnsplashPhotoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnsplashPhotoItemBinding binding = UnsplashPhotoItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);


        return new UnsplashPhotoItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsplashPhotoItemViewHolder holder, int position) {
        UnsplashPhoto currentItem = getItem(position);

        if (currentItem != null) {
            holder.bind(currentItem);
        }
    }
}
