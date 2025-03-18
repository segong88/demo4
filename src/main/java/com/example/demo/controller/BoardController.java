package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardlist")
    public String boardList(@PageableDefault(size = 10, sort = "bno", direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {

        //DTO로 받아서 뷰에 뿌리기(model)
        Page<BoardDTO> boardDTOS = boardService.getList(pageable);
        model.addAttribute("boardDTOS", boardDTOS);

        return "/boardlist";
    }



}
