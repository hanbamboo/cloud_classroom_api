package com.ruoyi.checkIn.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CheckinHistoryVo {
    private String checkinId;
    private Long courseId;
    private String courseName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private int method;
    private String source;
    private int courseCountTotal;
    private int courseCountPeople;
    private int courseCountOut;
    private double checkinRateNormal;
    private double checkinRateOut;
    private String remark;
}
