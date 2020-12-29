package org.viper75.image_search_using_architecture_components.api.responses;

import org.viper75.image_search_using_architecture_components.models.UnsplashPhoto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UnsplashResponse {
    private List<UnsplashPhoto> results;
}
