package com.muteng.dgjs.service;

import com.muteng.dgjs.DTO.Auditlog;

public interface AuditlogService {
    //插入用户记录
    void addlog(Auditlog auditlog);

    //插入详细用户动作记录
    void adddetailedlog(Auditlog auditlog,String keyword);
}
