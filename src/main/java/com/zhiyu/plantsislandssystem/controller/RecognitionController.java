package com.zhiyu.plantsislandssystem.controller;

import com.zhiyu.plantsislandssystem.common.Result;
import com.zhiyu.plantsislandssystem.service.RecognitionService;
import com.zhiyu.plantsislandssystem.vo.RecognitionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 识别控制器
 * 提供植物识别相关的REST API接口
 */
@RestController
@RequestMapping("/recognition")
@RequiredArgsConstructor
public class RecognitionController {

    // 注入识别服务
    private final RecognitionService recognitionService;

    /**
     * 植物识别接口
     * @param file 上传的图像文件
     * @return 识别结果封装对象
     */
    @PostMapping("/recognize")
    public Result<RecognitionVO> recognize(@RequestParam("file") MultipartFile file) {
        // 调用服务层进行植物识别
        RecognitionVO vo = recognitionService.recognize(file);
        // 返回成功响应
        return Result.success(vo);
    }
}
