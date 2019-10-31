/**
 * 
 */
package com.movie.watch.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.movie.watch.vo.FileUploadVO;

/**
 * @author kys
 *
 */
@Mapper
public interface FileUploadMapper {

	//파일 업로드하기
	public int uploadFile(FileUploadVO param);

}
