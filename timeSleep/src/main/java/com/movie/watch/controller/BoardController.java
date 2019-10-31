/**
 * 
 */
package com.movie.watch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.watch.service.BoardService;
import com.movie.watch.utill.UploadFileUtils;
import com.movie.watch.vo.BoardVO;
import com.movie.watch.vo.FileUploadVO;

import lombok.AllArgsConstructor;

/**
 * @author kys
 *
 */

@Controller
@AllArgsConstructor
@RequestMapping(value = "/board/**")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private BoardService service;

	// 파일 업로드 베이스 돌아오기
	@RequestMapping(value = "/board_file", method = RequestMethod.POST)
	public String board_filePOST() {
		return "/floatSection/boardRegister";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO boardVO, Model model, RedirectAttributes rttr) {

		// 파일 업로드 vo리스트에관한 로그 찍는 부분
		if (boardVO.getFileUploadVO() != null) {
			boardVO.getFileUploadVO().forEach(value -> logger.info("파일VO 값들 : " + value));
		}

		int signal = service.insertBoard(boardVO);

		logger.info("넘어온 값 없나?? 없냐는 말이다 : " + boardVO);

		if (signal < 1) {
			return "redirect:/board/board_file?signal=fail";
		} else {
			return "redirect:/move/board";
		}

	}

	// 파일 업로드
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public @ResponseBody List<FileUploadVO> board_file_uploadPOST(MultipartHttpServletRequest multipartRequest,
			ServletRequest request) throws IOException, Exception {
		// 리스트 객체 선언
		FileUploadVO vo = null;
		List<FileUploadVO> voList = new ArrayList<FileUploadVO>();
		// --
		logger.info("upload");
		Iterator<String> itr = multipartRequest.getFileNames();

		String str = new String();

		while (itr.hasNext()) {
			MultipartFile mpf = multipartRequest.getFile(itr.next());

			// 객체 구조화
			vo = new FileUploadVO();

			String originalFilename = mpf.getOriginalFilename();
			System.out.println("originalFilename : " + originalFilename);
			String uploadPath = request.getServletContext().getRealPath("/resources");
			System.out.println("realPath : " + uploadPath);
			// set
			vo.setFileName(originalFilename);

			str = UploadFileUtils.uploadFile(uploadPath, originalFilename, mpf.getBytes(), "/image");

			str = str.substring(str.indexOf("image/") - 1);

			vo.setUploadPath(str);

			// 리스트 추가
			voList.add(vo);
		}
		// logger.info(str);
		logger.info("result :===>" + voList);
		return voList;

	}

	// 상세보기
	@GetMapping(value = "/read", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public List<Map<String, ?>> read(Model model,BoardVO vo) {
		logger.info("넘오온 값 : " + vo.getBno());
		return service.read(vo.getBno());

	}

}
