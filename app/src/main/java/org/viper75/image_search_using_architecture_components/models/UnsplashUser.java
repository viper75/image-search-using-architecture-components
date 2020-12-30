package org.viper75.image_search_using_architecture_components.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class UnsplashUser implements Parcelable {
    private String username;
    private String name;
    private String attributionUrl;

    public UnsplashUser(String username, String name) {
        this.username = username;
        this.name = name;
        this.attributionUrl = "https://unsplash.com/" + username + "?utm_source=ImageSearchApp&utm_medium=referral";
    }

    protected UnsplashUser(Parcel in) {
        username = in.readString();
        name = in.readString();
        attributionUrl = in.readString();
    }

    public static final Creator<UnsplashUser> CREATOR = new Creator<UnsplashUser>() {
        @Override
        public UnsplashUser createFromParcel(Parcel in) {
            return new UnsplashUser(in);
        }

        @Override
        public UnsplashUser[] newArray(int size) {
            return new UnsplashUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(attributionUrl);
    }
}
