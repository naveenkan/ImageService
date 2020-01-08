package com.nokia.ImageAlbum;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nokia.Image.ResourceNotFoundException;

@Service
@Transactional
public class ImageAlbumSevice {
	@Autowired
    private ImageAlbumRepository imageAlbumRepository;
	public ImageAlbum createImageAlbum(ImageAlbum imageAlbum) {
		return imageAlbumRepository.save(imageAlbum);
	}
     public ResponseEntity<ImageAlbum> getImageAlbum(int id){
    	return imageAlbumRepository.findById(id).map(album -> {
            return ResponseEntity.ok(album);
    	}).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + id + " not found"));

     }
	public ResponseEntity<?> deleteImageAlbum(int id) {
		return imageAlbumRepository.findById(id).map(album -> {
			imageAlbumRepository.delete(album);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + id + " not found"));
    }
	public List<ImageAlbum> getAllImageAlbums() {
		return imageAlbumRepository.findAll();
	}
		
	}

