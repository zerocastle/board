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

	//���� ���ε��ϱ�
	public int uploadFile(FileUploadVO param);

}
