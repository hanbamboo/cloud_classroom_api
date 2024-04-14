package com.ruoyi.checkIn.domain;


import lombok.Data;

import java.util.Date;

@Data
public class CheckinInsertVo {

    private String checkinId;

    private Long courseId;

    private Long studentId;

    private Long teacherId;

    private Long method;

    private String device;

}
