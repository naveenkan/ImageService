package com.nokia.ImageAlbum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageAlbumController {
	@Autowired
private ImageAlbumSevice imageAlbumSevice;

      @PostMapping("/imageAlbum/{name}")
      public ResponseEntity<ImageAlbum> createImageAlbum(@PathVariable String name){
    	  ImageAlbum imageAlbum = new ImageAlbum(name);
    	  ImageAlbum album=imageAlbumSevice.createImageAlbum(imageAlbum);
    	  return new ResponseEntity<ImageAlbum>(album, HttpStatus.CREATED);
      }
      @DeleteMapping("/imageAlbum/{id}")
      public ResponseEntity<?> deleteImageAlbum(@PathVariable int id){
    	  return imageAlbumSevice.deleteImageAlbum(id);
      }
      @GetMapping("/imageAlbum/{id}")
      public ResponseEntity<ImageAlbum> getImageAlbum(@PathVariable int id){
    	  return imageAlbumSevice.getImageAlbum(id);
		
      }
      @GetMapping("/imageAlbum")
      public List<ImageAlbum> getAllImageAlbums(){
    	  return imageAlbumSevice.getAllImageAlbums();
		
      }
}
