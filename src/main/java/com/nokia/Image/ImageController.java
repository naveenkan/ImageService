package com.nokia.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nokia.ImageAlbum.ImageAlbum;
import com.nokia.ImageAlbum.ImageAlbumRepository;
import com.nokia.ImageAlbum.ImageAlbumSevice;

@RestController
public class ImageController {
	@Autowired
	private ImageService imageService;
	

    @PostMapping("/imageAlbum/{imageAlbumId}/image")
    public ResponseEntity<Image> createImage(@PathVariable int imageAlbumId,@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws IOException{
    	System.out.println(imageAlbumId+" "+name+" "+file.getOriginalFilename()+" "+file.getBytes());
    	Image image=imageService.createImage(imageAlbumId,file,name);
		return new ResponseEntity<Image>(image, HttpStatus.CREATED);
    }
    @DeleteMapping("/imageAlbum/{imageAlbumId}/image/{imageId}")
    public ResponseEntity<?> DeleteImage(@PathVariable int imageId,@PathVariable int imageAlbumId){
    	return imageService.deleteImage(imageId,imageAlbumId);
    }
    @DeleteMapping("/imageAlbum/{imageAlbumId}/images")
    public ResponseEntity<?> DeleteAllImages(@PathVariable int imageAlbumId){
    	return imageService.deleteAllImages(imageAlbumId);
    }
    @GetMapping("/imageAlbum/{imageAlbumId}/image")
    public ResponseEntity<List<Image>> getAllImage(@PathVariable int imageAlbumId){
    	List<Image>images=imageService.getAllImage(imageAlbumId);
    	return new ResponseEntity<List<Image>>(images, HttpStatus.FOUND);
    }
    @GetMapping("/imageAlbum/{imageAlbumId}/image/{imageId}")
    public ResponseEntity<Image> getImage(@PathVariable int imageId,@PathVariable int imageAlbumId){
    	return imageService.getImage(imageId,imageAlbumId);
 
    }

    }
