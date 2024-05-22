package com.salted.board.service;

import com.salted.board.entity.BoardEntity;
import com.salted.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardEntity> selectAll(){
        List<BoardEntity> list = boardRepository.findAll();
        return list;
    }

    public BoardEntity selectOne(Long bno){
        BoardEntity boardEntity = boardRepository.findById(bno).get();
        return boardEntity;
    }

    public void write(BoardEntity boardEntity){
        boardRepository.save(boardEntity);
    }

    public void modify(BoardEntity boardEntity){
        boardRepository.save(boardEntity);
    }

    public void delete(Long bno){
        boardRepository.deleteById(bno);
    }

}
