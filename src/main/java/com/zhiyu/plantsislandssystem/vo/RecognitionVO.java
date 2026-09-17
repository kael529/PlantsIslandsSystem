package com.zhiyu.plantsislandssystem.vo;

import lombok.Data;
import java.util.List;

@Data
public class RecognitionVO {
    private String classEn;
    private String classCn;
    private Double confidence;
    private List<Top3Item> top3;

    @Data
    public static class Top3Item{
        private String classEn;
        private String classCn;
        private Double confidence;
    }
}
