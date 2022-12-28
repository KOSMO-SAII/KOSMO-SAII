package com.example.test.controller;

import com.example.test.domain.ReviewCommentDTO;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewCommentApiController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping("/comments/{id}")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody ReviewCommentDTO dto){

        System.out.println("comments dto = "+dto.toString());
        return ResponseEntity.ok(reviewCommentService.commentSave(dto));
    }

}
