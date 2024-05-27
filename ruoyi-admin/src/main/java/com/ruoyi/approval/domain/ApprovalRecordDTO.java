package com.ruoyi.approval.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ApprovalRecordDTO {
    private Long approvalId;
    private String leaveId;
    private Long approverId;
    private String approverName;
    private Long superiorId;
    private String superiorName;
    private String img;
    private Long forwardId;
    private String forwardName;
    private Long studentId;
    private String studentName;
    private String comment;
    private Long status;
    private Long finalStatus;
    private Long type;
    private String reason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

}
