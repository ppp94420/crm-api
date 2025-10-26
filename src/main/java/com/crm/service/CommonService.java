package com.crm.service;

import com.crm.vo.FileUrlVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: DELL
 * @Date: 2025/10/26
 * @Version: 1.0
 */
public interface CommonService {
    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    FileUrlVO upload(MultipartFile multipartFile);
}
