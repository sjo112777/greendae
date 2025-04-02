package kr.co.greendae.controller;

import kr.co.greendae.entity.event.Gallery;
import kr.co.greendae.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/campus")
public class CampusController {

    private final GalleryService galleryService; // final로 변경

    // ✅ 생성자를 통한 의존성 주입 (권장 방식)
    public CampusController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    // 갤러리 페이지
    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<Gallery> galleryList = galleryService.getAllGalleries();
        log.info("galleryList size: " + galleryList.size());

        model.addAttribute("galleryList", galleryList);
        return "/campus/gallery";
    }

    // 갤러리 상세 페이지
    @GetMapping("/galleryview")
    public String galleryview(@RequestParam("no") int no, Model model) {
        Gallery gallery = galleryService.getGalleryById(no);
        model.addAttribute("gallery", gallery);
        return "/campus/galleryview";
    }
}