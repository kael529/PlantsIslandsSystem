package com.zhiyu.plantsislandssystem.service.impl;

import com.zhiyu.plantsislandssystem.common.BusinessException;
import com.zhiyu.plantsislandssystem.common.ResultCode;
import com.zhiyu.plantsislandssystem.service.RecognitionService;
import com.zhiyu.plantsislandssystem.vo.RecognitionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecognitionServiceImpl implements RecognitionService {

    private final RestTemplate restTemplate;

    @Value("${fastapi.url}")
    private String fastApiUrl;

    //文件最大10MB
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Override
    public RecognitionVO recognize(MultipartFile file) {
        //-----文件校验------
        //1.空文件校验
        if (file.isEmpty() || file == null) {
            throw new BusinessException(ResultCode.ERROR,"请上传图片!");
        }
        //2.文件大小校验
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BusinessException(ResultCode.ERROR,"图片大小不能超过10MB!");
        }
        //3.文件类型校验
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new BusinessException(ResultCode.ERROR,"只能上传图片文件!");
        }

        //-----调植物识别-----
        try {
            //1.构建请求体
            MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
            body.add("file",new ByteArrayResource(file.getBytes()){
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            //2.设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);

            //3.调用FastAPI
            ResponseEntity<Map> response = restTemplate.postForEntity(fastApiUrl,requestEntity, Map.class);
            if (response.getBody() == null) {
                throw new BusinessException(ResultCode.ERROR,"识别服务无响应!");
            }

            //4.解析返回结果
            Map<String,Object> result = response.getBody();
            RecognitionVO vo = new RecognitionVO();
            vo.setClassEn((String) result.get("class_en"));
            vo.setClassCn((String) result.get("class_cn"));
            vo.setConfidence(((Number) result.get("confidence")).doubleValue());
            //解析Top3
            List<Map<String,Object>> top3Raw = (List<Map<String, Object>>) result.get("top3");
            if (top3Raw != null) {
                List<RecognitionVO.Top3Item> top3 = top3Raw.stream().map(item -> {
                    RecognitionVO.Top3Item t = new RecognitionVO.Top3Item();
                    t.setClassEn((String) item.get("class_en"));
                    t.setClassCn((String) item.get("class_cn"));
                    t.setConfidence(((Number) item.get("confidence")).doubleValue());
                    return t;
                }).collect(Collectors.toList());
                vo.setTop3(top3);
            }
            return vo;
        } catch (ResourceAccessException e) {
            log.error("FastAPI服务不可达!",e);
            throw new BusinessException(ResultCode.ERROR,"识别服务暂时不可用!");
        } catch (HttpClientErrorException e) {
            log.error("FastAPI返回错误: {}",e.getResponseBodyAsString(),e);
            throw new BusinessException(ResultCode.ERROR,"图片格式不正确!");
        } catch (Exception e) {
            log.error("调用识别服务失败!",e);
            throw new BusinessException(ResultCode.ERROR,"识别失败,请稍后重试!");
        }
    }
}
