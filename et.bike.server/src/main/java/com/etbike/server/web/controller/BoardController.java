package com.etbike.server.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etbike.server.domain.model.Board;
import com.etbike.server.domain.model.BoardCategory;
import com.etbike.server.domain.model.Reply;
import com.etbike.server.domain.model.ShareBoard;
import com.etbike.server.domain.model.UploadedFile;
import com.etbike.server.persistence.BoardRepository;
import com.etbike.server.persistence.BoardSpecifications;
import com.etbike.server.persistence.FileRepository;
import com.etbike.server.persistence.FileSpecifications;
import com.etbike.server.service.BoardService;

@Controller
public class BoardController {

	@Autowired private BoardService boardService;
	@Autowired private FileRepository fileRepository;
	@Autowired private BoardRepository boardRepository;
	
	@RequestMapping(value="/addBoard", method=RequestMethod.POST)
	public String addBoard(@Valid Board model, ModelMap map) {
			boardService.saveBoard(model);
			
			List<Board> boards = boardRepository.findAll(BoardSpecifications.isWriterName(model.getWriter()));
			Board board = boards.get(boards.size() - 1);
			List<UploadedFile> bikeImages =fileRepository.findAll(FileSpecifications.isfileName(board.getBikeImagePath()));
			if(!bikeImages.isEmpty()){
				UploadedFile selectedImege = bikeImages.get(bikeImages.size() - 1);
				board.setBikeImagePath(selectedImege.getFileDownloadUrl());
				board.setBikeImagePathThumb("http://125.209.193.11:8080/etbike/thumb/"+ selectedImege.getId()+"/200");	
			}
			List<UploadedFile>  myImages =fileRepository.findAll(FileSpecifications.isfileName(board.getMyImagePath()));
			if(!myImages.isEmpty())
				board.setMyImagePath(myImages.get(myImages.size() - 1).getFileDownloadUrl());
			
			boardService.saveBoard(board);
			
			map.put("result", "success");

			return "jsonView";
		}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public void board(ModelMap map) {
		map.put("boardCategories", BoardCategory.values());
	}

	@RequestMapping(value = "/boards/{category}/{page}", method = RequestMethod.GET)
	public String list(@PathVariable String category, @PathVariable Integer page, String q, ModelMap map) {
		map.put("category", category);
		map.put("page", boardService.getBoards(category, page, q));
		return "jsonView";
	}

	@RequestMapping(value = "/boards/view/{id}", method = RequestMethod.GET)
	public String readById(@PathVariable Long id, ModelMap map) {
		map.put("board", boardService.readById(id));
		return "jsonView";
	}


	@RequestMapping(value = "/boards", method = RequestMethod.PUT)
	public String add(Board model, ModelMap map) {
		boardService.saveBoard(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/boards", method = RequestMethod.DELETE)
	public String delete(Long id) {
		boardService.deleteBoard(id); 
		return "jsonView";
	}
	


	@RequestMapping(value = "/replies/{boardId}/{page}", method = RequestMethod.GET)
	public String replies(@PathVariable Long boardId, @PathVariable Integer page, ModelMap map) {
		map.put("page", boardService.getReplies(boardId, page));
		return "jsonView";
	}

	@RequestMapping(value = "/replies", method = RequestMethod.PUT)
	public String addReply(Reply model, ModelMap map) {
		boardService.saveReply(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/replies", method = RequestMethod.DELETE)
	public String deleteReply(Long id) {
		boardService.deleteReply(id); 
		return "jsonView";
	}
	
}
