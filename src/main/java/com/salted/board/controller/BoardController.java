package com.salted.board.controller;

import com.salted.board.entity.BoardEntity;
import com.salted.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    // service
    @Autowired
    private BoardService boardService;
    // DAO - repository
    // DTO - Entity

    @GetMapping("/board")
    public String board(Model model) {
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC)Pageable pageable) {
        Page<BoardEntity> list = boardService.selectAll(pageable);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    @GetMapping("/board/detail/{bno}")
    public String boardDetail(Model model, @PathVariable Long bno) {
        BoardEntity boardEntity = boardService.selectOne(bno);
        model.addAttribute("board", boardEntity);
        return "board/detail";
    }

    @GetMapping("/board/write")
    public String boardWrite() {
        return "board/write";
    }

    @PostMapping("/board/write")
    public String boardWritePost(@ModelAttribute BoardEntity boardEntity) {
        boardService.write(boardEntity);
        return "redirect:/board";
    }

    @GetMapping("/board/modify/{bno}")
    public String boardModify(Model model, @PathVariable Long bno) {
        BoardEntity boardEntity = boardService.selectOne(bno);
        model.addAttribute("board", boardEntity);
        return "board/modify";
    }

    @PostMapping("/board/modify")
    public String boardModifyPost(@ModelAttribute BoardEntity boardEntity,
                                  @RequestParam("bno") Long bno) {
        BoardEntity oriBoardEntity = boardService.selectOne(boardEntity.getBno());
        if (boardEntity.getBno() == oriBoardEntity.getBno()) {
            boardService.modify(boardEntity);
        }
        return "redirect:/board/detail/"+bno;
    }

    @GetMapping("/board/delete/{bno}")
    public String boardDelete(@PathVariable Long bno) {
        boardService.delete(bno);
        return "redirect:/board";
    }

}
