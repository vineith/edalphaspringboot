package com.edalpha.controller;

import com.edalpha.model.TranscriptModel;
import com.edalpha.service.PDFReader;
import com.edalpha.service.TranscriptFactory;
import com.edalpha.service.TranscriptUS;
import com.edalpha.service.TranscriptType;
import com.edalpha.storage.StorageFileNotFoundException;
import com.edalpha.storage.StorageService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class FileUploadController {

    private final StorageService storageService;
    @Inject
    PDFReader pdfReader;
    @Inject
    TranscriptUS transcriptUS;
    @Inject
    TranscriptFactory transcriptFactory;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        System.out.println(">1");
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
        System.out.println(model);
        System.out.println(">2");
        //return "uploadForm";
        return "transcript";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes ,      Model model) {
        String text ="";
        TranscriptModel transcript = null;

        try{
            text = pdfReader.read(file.getInputStream());
            System.out.println(text);
            transcript =  transcriptFactory.getTranscript(TranscriptType.TRANSCRIPT_US,text);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch(TikaException ex){
            ex.printStackTrace();
        }

        //storageService.store(file);
        //model.addAttribute("textAttribute",text);
        model.addAttribute("transcript",transcript);
//        model.addAttribute("birthDate",transcript.getBirthDate());
//        model.addAttribute("degreeDate",transcript.getBirthDate());
//        model.addAttribute("major",transcript.getMajor());
       // System.out.println(transcript.toString());

        return "result";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/gpa-details")
    public String gpaDetails(Model model) throws IOException {

        System.out.println("gpa details1");
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
        System.out.println(model);
        System.out.println(">2");
        return "gpa-details";
    }

}