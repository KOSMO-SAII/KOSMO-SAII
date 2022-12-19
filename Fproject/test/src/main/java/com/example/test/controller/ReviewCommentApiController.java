package com.example.test.controller;

import com.example.test.config.LoginUser;
import com.example.test.domain.MemberDTO;
import com.example.test.domain.ReviewCommentRequestDTO;
import com.example.test.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ReviewCommentApiController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping("/reviews/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody ReviewCommentRequestDTO dto,
                                      @LoginUser MemberDTO memberDTO){
        return ResponseEntity.ok(reviewCommentService.commentSave(memberDTO.getNickname(), id, dto));
    }
}
