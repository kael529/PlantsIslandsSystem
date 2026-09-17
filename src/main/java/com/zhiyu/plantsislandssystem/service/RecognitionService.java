package com.zhiyu.plantsislandssystem.service;

import com.zhiyu.plantsislandssystem.vo.RecognitionVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 植物识别服务接口
 * 该接口定义了植物识别服务的核心方法,用于处理上传的图片文件并返回识别结果
 */
public interface RecognitionService {
    /**
     * 植物识别方法
     *
     * @param file 用户上传的图片文件,使用MultipartFile类型接收
     * @return RecognitionVO 包含植物识别结果的值对象,包含识别到的英文名、中文名、置信度
     */
    RecognitionVO recognize(MultipartFile file);
}
