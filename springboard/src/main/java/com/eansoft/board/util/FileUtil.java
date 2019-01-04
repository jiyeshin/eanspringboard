package com.eansoft.board.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eansoft.board.service.BoardVO;

@Component("fileUtil")
public class FileUtil {

	public List<Map<String, Object>> parseInsertFileInfo(BoardVO vo, HttpServletRequest request) throws Exception {

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
				
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;		

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		String boardIdx = Integer.toString(vo.getCode());

		// System.out.println(request.getRealPath("/img"));
		String uploadPath = request.getRealPath("/resources/img");
		
		File file = new File(uploadPath);
		
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			//System.out.println("multipartFile"+multipartFile.getOriginalFilename());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();				
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				storedFileName = CommonUtils.getRandomString() + "." + originalFileExtension;

				String imagepath = uploadPath + "\\" + storedFileName;

				file = new File(imagepath);
				multipartFile.transferTo(file);		
				
				listMap = new HashMap<String, Object>();
				
;				listMap.put("imagepath", imagepath);
				listMap.put("boardcode", boardIdx);
				listMap.put("originalfilename", originalFileName);
				listMap.put("storedfilename", storedFileName);
				listMap.put("filesize", multipartFile.getSize());
				list.add(listMap);
			}
			//boolean result = writeFile(multipartFile, storedFileName);
		}
		return list;
	}

	

}
