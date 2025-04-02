package kr.co.greendae.service;


import kr.co.greendae.entity.event.Gallery;
import kr.co.greendae.repository.event.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    private GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<Gallery> getAllGalleries(){
        return galleryRepository.findAll();
    }

    public Gallery getGalleryById(int no) {
        Optional<Gallery> gallery = galleryRepository.findById(no); // Optional로 받기
        return gallery.orElse(null); // 없으면 null 반환
    }

}
