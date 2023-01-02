package com.example.test.controller;

import com.example.test.entity.QnABoard;
import com.example.test.entity.QnABoardReply;
import com.example.test.service.QnAReplyService;
import com.example.test.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/QnABoard")
@RequiredArgsConstructor
public class QnaBoardController {

    private final QnaBoardService qnaBoardService;

    private final QnAReplyService qnAReplyService;

    @GetMapping("/write")
    public String write() {
        return "qna_board/write";
    }


    @PostMapping("/writedo")
    public String writedo(QnABoard qnABoard, Model model, Principal principal) {

        qnaBoardService.write(qnABoard,principal);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/QnABoard/list");

        return "qna_board/message";
    }

    @GetMapping("/list")
    public String list(Model model,
                       Optional<Integer> page,
                       String searchKeyword
    ) {
        Page<QnABoard> list = null;
        PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,10);

        if (searchKeyword == null) {
            list = qnaBoardService.list(pageRequest);
        } else {
            list = qnaBoardService.searchList(searchKeyword, pageRequest);
        }


        model.addAttribute("list", list);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchKeyword",searchKeyword);

        return "qna_board/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("board", qnaBoardService.view(id));
        List<QnABoardReply> qnABoardReplyList=null;
        qnABoardReplyList = qnAReplyService.view(id);
        model.addAttribute("replyList", qnABoardReplyList);
        return "qna_board/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        qnaBoardService.deleteById(id);

        return "redirect:/QnABoard/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("board", qnaBoardService.view(id));

        return "qna_board/modify";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, QnABoard qnABoard,Principal principal) {

        QnABoard boardTemp = qnaBoardService.view(id);
        boardTemp.setTitle(qnABoard.getTitle());
        boardTemp.setContent(qnABoard.getContent());

        qnaBoardService.write(boardTemp,principal);

        return "redirect:/QnABoard/list";
    }
}
