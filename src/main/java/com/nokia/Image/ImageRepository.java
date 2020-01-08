package com.nokia.Image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nokia.ImageAlbum.ImageAlbum;


public interface ImageRepository extends JpaRepository<Image, Integer> {
Optional<Image> findByIdAndImageAlbumId(int imageId, int ImageAlbumId);
Optional<List<Image>> findByImageAlbumId(int imageAlbumId);

}
