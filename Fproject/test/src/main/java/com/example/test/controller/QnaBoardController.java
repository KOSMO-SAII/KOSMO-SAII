package com.example.test.controller;

import com.example.test.entity.QnABoard;
import com.example.test.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/QnABoard")
@RequiredArgsConstructor
public class QnaBoardController {

    private final QnaBoardService qnaBoardService;

    @GetMapping("/write")
    public String write() {
        return "qna_board/write";
    }


    @PostMapping("/writedo")
    public String writedo(QnABoard qnABoard, Model model) {

        qnaBoardService.write(qnABoard);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/QnABoard/list");

        return "qna_board/message";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       String searchKeyword
    ) {
        Page<QnABoard> list = null;

        if (searchKeyword == null) {
            list = qnaBoardService.list(pageable);
        } else {
            list = qnaBoardService.searchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "qna_board/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("board", qnaBoardService.view(id));
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
    public String update(@PathVariable("id") Long id, QnABoard qnABoard) {

        QnABoard boardTemp = qnaBoardService.view(id);
        boardTemp.setTitle(qnABoard.getTitle());
        boardTemp.setContent(qnABoard.getContent());

        qnaBoardService.write(boardTemp);

        return "redirect:/QnABoard/list";
    }
}
