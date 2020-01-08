package com.nokia.Image;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nokia.ImageAlbum.ImageAlbum;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String name;
	@Lob
	private byte[] file1;

	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	private ImageAlbum imageAlbum;
	
	public byte[] getFile1() {
		return file1;
	}

	public void setFile1(byte[] file1) {
		this.file1 = file1;
	}

	

	public ImageAlbum getImageAlbum() {
		return imageAlbum;
	}

	public void setImageAlbum(ImageAlbum imageAlbum) {
		this.imageAlbum = imageAlbum;
	}

	public Image(String name,ImageAlbum imageAlbum,byte[] file1) {
		super();
		this.name = name;
		this.imageAlbum=imageAlbum;
		this.file1=file1;
	}

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", file1=" + Arrays.toString(file1) + ", imageAlbum=" + imageAlbum
				+ "]";
	}

}
