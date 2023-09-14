package com.itfeng.frame.elk.repository;

import com.itfeng.frame.elk.common.ReqRspLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: lf
 * @creat: 2022/8/31 16:21
 * @describe:
 */
public interface RspLogRepository extends ElasticsearchRepository<ReqRspLog,String> {
}
