package com.example.test.controller;


import com.example.test.domain.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    //업로드된 파일 저장 결로 설정
    @Value("${com.example.upload.path") //애플리케이션 설정 변수
    private String uploadPath;

    //MultipartFile 타입을 이용한 파일 사용
    //: Ajax를 이용한 파일 업로드 처리 -> 업로드 결과에 대한 화면 작성 X
    //따라서 업로드 결과는 JSON 형태로 제공

    @PostMapping("/uploadAjax")
    //ResponseEntity는 HttpHeader와 HttpBody를 포함하는 HttpEntity를 상속받아 구현한 클래스
    //즉, 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스로 HttpStatus, HttpHeaders, HttpBody를 포함
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles) {
            //1. 확장자 검사
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                //이미지가 아닐경우 HttpRequest에 포함하는 HttpStatus 403을 포함해 반환
            }

            //실제 파일 이름이 전체 경로가 들오기 때문에 원본 이름 처리와 실제 파일 이름 처리
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("filename: " + fileName);

            //2. 동일한 폴더에 많은 파일 방지
            //(1) 날짜 폴더 생성
            String folderPath = makeFolder();

            //(2) UUID 클래스를 이용한 고유한 파일 이름 생성
            String uuid = UUID.randomUUID().toString();

            //(3) UUID를 이용해 저장 파일 이름 중간에 "_"를 이용해 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath); //실제 이미지 저장부
                //이미지 저장 후, DTOList에 추가한다.
                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
        //성공할 경우엔 HttpStatus OK를 담아 resultDTOList를 반환한다.
    }

    //(3) 폴더 생성 메서드 작성
    private String makeFolder(){
        String str= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        //경로에 폴더가 없을 경우 폴더 생성
        File uploadPathFolder =new File(uploadPath, folderPath);

        if(uploadPathFolder.exists()==false){
            uploadPathFolder.mkdirs();
        }
        return folderPath;

    }

    //URL로 이미지 전송을 위한 메서드
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){ //URL 인코딩된 파일 이름을 파라미터로 받아서 해당 파일을 byte[]로 만들어 브라우저로 전송
        ResponseEntity<byte[]> result = null;

        try{
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("fileName : "+ srcFileName);
            File file = new File(uploadPath+File.separator+srcFileName);

            log.info("file : "+ file);

            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            //: 마임 타입이란 클라이언트에게 전송된 문서의 다양성을 알려주기 위한 메커니즘 -> 올바른 마임타입 전송하도록 설정필요
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //: 확장자를 통해 마임타입을 판단하는데, 확장자가 없으면 null을 반환한다.

            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
            //ResponseEntity에 바이트 배열로 만든 파일, 헤더, 상태코드 전달
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
