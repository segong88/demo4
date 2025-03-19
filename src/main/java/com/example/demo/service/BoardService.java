package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    public Page<BoardDTO> getList(Pageable pageable);

    public BoardDTO boardDetail(Long bno);

    public BoardDTO boardRegister(BoardDTO boardDTO);

    public BoardDTO boardUpdate(BoardDTO boardDTO);

    public void boardDelete(Long bno);
}
