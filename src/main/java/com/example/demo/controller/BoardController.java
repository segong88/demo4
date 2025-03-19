package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardlist")
    public String boardList(@PageableDefault(size = 10, sort = "bno", direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {

        //DTO로 받아서 뷰에 뿌리기(model)
        Page<BoardDTO> boardDTOS = boardService.getList(pageable);
        model.addAttribute("boardDTOS", boardDTOS);

        return "boardlist";
    }

    @GetMapping("/detail/{bno}")
    public String boardDetail(@PathVariable("bno") Long bno, Model model){

        BoardDTO boardDTO = boardService.boardDetail(bno);
        model.addAttribute("boardDTO", boardDTO);

        return "boarddetail";
    }

    @GetMapping("/boardregister")
    public String boardRegister(){

        return "boardregister";
    }

    @PostMapping("/boardregister")
    public String boardRegister(BoardDTO boardDTO){
        //서비스에서 DTO->entity->DTO 변환된 걸 그대로 갖고와서 다시 새로운 DTO에 담아.
        boardDTO = boardService.boardRegister(boardDTO);

        //그리고 등록 누르면 그 글(새로운 DTO의 bno 찾아서) 보여줌.
        Long bno = boardDTO.getBno();

        return "redirect:/detail/" + bno;
    }

    @GetMapping("/boardupdate/{bno}")
    public String boardUpdate(@PathVariable("bno") BoardDTO boardDTO, Model model){
        //todo 수정삭제 고치기 ~~

        BoardDTO boardDTO1 = boardService.boardDetail(boardDTO.getBno());
        model.addAttribute("boardDTO1", boardDTO1);

        return "/boardupdate";
    }

    @PostMapping("/boardupdate")
    public String boardUpdate(BoardDTO boardDTO){

        boardService.boardDetail(boardDTO.getBno());

        return "redirect:/detail/" + boardDTO.getBno();
    }








}
