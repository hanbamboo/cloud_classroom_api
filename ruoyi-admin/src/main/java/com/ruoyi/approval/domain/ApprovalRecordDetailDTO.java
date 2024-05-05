package com.ruoyi.approval.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApprovalRecordDetailDTO {

    private String leaveId;
    private Long approverId;
    private String approverName;
    private Long status;
    private Long type;
    private String reason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private List<ApprovalRecordDTO> details;

}
