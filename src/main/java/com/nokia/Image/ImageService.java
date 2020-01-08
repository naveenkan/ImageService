package com.nokia.Image;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.nokia.ImageAlbum.ImageAlbumRepository;
import com.nokia.kafka.KafkaSender;

@Service
@Transactional
public class ImageService {
      @Autowired
	  private ImageRepository imageRepository;
      @Autowired
      private ImageAlbumRepository imageAlbumRepository;
      @Autowired
      private KafkaSender kafkaSender;
      
	public Image createImage(int imageAlbumId,MultipartFile file, String name) throws IOException {
		Image image=new Image();
		image.setName(name);
		image.setFile1(file.getBytes());
		return imageAlbumRepository.findById(imageAlbumId).map(album -> {
        	image.setImageAlbum(album);
             Image savedimg=imageRepository.save(image);
            kafkaSender.send("mytopic", "Image is created"+image);
            return savedimg;
        }).orElseThrow(() -> new ResourceNotFoundException("imageAlbumId " + imageAlbumId + " not found"));
		
	}

	public ResponseEntity<?> deleteImage(int imageId,int imageAlbumId) {
		return imageRepository.findByIdAndImageAlbumId(imageId, imageAlbumId).map(img -> {
			imageRepository.delete(img);
			kafkaSender.send("mytopic", "Image with Id"+imageId+"is deleted");
			return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
    public ResponseEntity<?> deleteAllImages(@PathVariable int imageAlbumId){
    	return imageRepository.findByImageAlbumId(imageAlbumId).map(imgs->{
    		imageRepository.deleteAll(imgs);
    		kafkaSender.send("mytopic", "All Images in Image Album with Id"+imageAlbumId+"is deleted");
    		return ResponseEntity.ok().build();
           }
    	).orElseThrow(() -> new ResourceNotFoundException("Not found"));
   
    }
	public List<Image> getAllImage(int albumid) {
		return imageRepository.findByImageAlbumId(albumid).map(
				images->{return images;}).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		
	}

	public ResponseEntity<Image> getImage(int imageId,int imageAlbumId) {
		return imageRepository.findByIdAndImageAlbumId(imageId,imageAlbumId).map(img -> {
            return new ResponseEntity<Image>(img, HttpStatus.FOUND);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
		
	}

