package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<BoardDTO> getList(Pageable pageable) {

        //entity(Board) 페이징 처리된 목록 가져오기
        Page<Board> boards = boardRepository.findAll(pageable);

        //entity를 DTO로 변환
        return boards.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    @Override
    public BoardDTO boardDetail(Long bno) {     //bno로 글 상세 조회

        Board board = boardRepository.findById(bno).orElseThrow();

        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public void boardRegister(BoardDTO boardDTO) {

        //DTO를 entity로 변환
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);
    }

    @Override
    public void boardUpdate(BoardDTO boardDTO) {

        //DTO를 entity로 변환
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);

    }

    @Override
    public void boardDelete(Long bno) {

        boardRepository.deleteById(bno);

    }


}
