package com.crm.controller;

import com.crm.common.result.Result;
import com.crm.service.CommonService;
import com.crm.vo.FileUrlVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: DELL
 * @Date: 2025/10/26
 * @Version: 1.0
 */
@Tag(name = "通用模块")
@RestController
@RequestMapping("common")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @PostMapping("upload/file")
    @Operation(summary = "文件上传")
    public Result<FileUrlVO> upload(@RequestBody MultipartFile file) {
        return Result.ok(commonService.upload(file));
    }
}
