package com.ruoyi.approval.domain;

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
    private Long forwardId;
    private String forwardName;
    private Long studentId;
    private String studentName;
    private Long status;
    private Long type;
    private String reason;
    private Date startTime;
    private Date endTime;

}
