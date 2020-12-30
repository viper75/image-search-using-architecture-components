package org.viper75.image_search_using_architecture_components.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UnsplashPhoto implements Parcelable {
    private String id;
    private String description;
    private UnsplashUser user;
    private UnsplashPhotoUrls urls;

    protected UnsplashPhoto(Parcel in) {
        id = in.readString();
        description = in.readString();
        user = in.readParcelable(UnsplashUser.class.getClassLoader());
        urls = in.readParcelable(UnsplashPhotoUrls.class.getClassLoader());
    }

    public static final Creator<UnsplashPhoto> CREATOR = new Creator<UnsplashPhoto>() {
        @Override
        public UnsplashPhoto createFromParcel(Parcel in) {
            return new UnsplashPhoto(in);
        }

        @Override
        public UnsplashPhoto[] newArray(int size) {
            return new UnsplashPhoto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(urls, flags);
    }
}
