package com.schmoll.tlkw.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schmoll.tlkw.entity.PcDataAlarm;
import com.schmoll.tlkw.mapper.PcDataAlarmMapper;
import com.schmoll.tlkw.service.PcDataAlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
* @author Mike.Luo
* @description 针对表【pc_data_alarm】的数据库操作Service实现
* @createDate 2026-05-27 10:58:53
*/
@Service
public class PcDataAlarmServiceImpl extends ServiceImpl<PcDataAlarmMapper, PcDataAlarm>
    implements PcDataAlarmService {

}




