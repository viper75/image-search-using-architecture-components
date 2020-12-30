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
public class UnsplashPhotoUrls implements Parcelable {
    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;

    protected UnsplashPhotoUrls(Parcel in) {
        raw = in.readString();
        full = in.readString();
        regular = in.readString();
        small = in.readString();
        thumb = in.readString();
    }

    public static final Creator<UnsplashPhotoUrls> CREATOR = new Creator<UnsplashPhotoUrls>() {
        @Override
        public UnsplashPhotoUrls createFromParcel(Parcel in) {
            return new UnsplashPhotoUrls(in);
        }

        @Override
        public UnsplashPhotoUrls[] newArray(int size) {
            return new UnsplashPhotoUrls[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(raw);
        dest.writeString(full);
        dest.writeString(regular);
        dest.writeString(small);
        dest.writeString(thumb);
    }
}
