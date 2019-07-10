package com.ssm.service;

import com.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog log) throws Exception;

    List<SysLog> findAll() throws Exception;
}
