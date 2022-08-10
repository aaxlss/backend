package com.backend.Backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TrackingWebService {

    
    final String userDirectory = Paths.get("")
            .toAbsolutePath()
            .toString();

    // @GetMapping(path = "create-file")
    // public ResponseEntity createFile() {

    // final String tmpPath = "\\tmp";
    // // Getting project path
    // String userDirectory = Paths.get("")
    // .toAbsolutePath()
    // .toString();

    // // validate if Directory exist
    // Path pathDirectory = Paths.get(userDirectory + tmpPath);

    // System.out.println("tmp exist:" + Files.exists(pathDirectory));
    // // if directory doesn't exist, it will be created
    // if (!Files.exists(pathDirectory)) {
    // new File(userDirectory + tmpPath).mkdirs();
    // }
    // try {
    // // Creating the file ok if the directory exist
    // File file = new File(userDirectory + "\\tmp\\ok.txt");
    // if (file.createNewFile()) {
    // System.out.println("File created: " + file.getName());
    // } else {
    // // this will be executed if the file already exist
    // System.out.println("File exists: " + file.getName());
    // return ResponseEntity.badRequest().build();
    // }
    // } catch (IOException e) {
    // System.out.println("An error occurred.");
    // e.printStackTrace();
    // }
    // return ResponseEntity.ok().build();

    // }

    @GetMapping(path = "/ping")
    public ResponseEntity ping() {

        String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory);

        File f = new File(userDirectory + "\\tmp\\ok.txt");
        if (f.exists()) {

            // Show if the file exists
            System.out.println("Exists");
            return ResponseEntity.ok().body("ok");
        } else {

            // Show if the file does not exists
            System.out.println("Does not Exists");
            return ResponseEntity.status(503).build();

        }

    }

    @RequestMapping(path = "/img", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getGifFile(String param) throws IOException {
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        File file = new File(userDirectory + "\\src\\img\\1x1.gif");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
