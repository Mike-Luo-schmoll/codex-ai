package com.schmoll.tlkw.customer.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.schmoll.tlkw.customer.CustomerClient;
import com.schmoll.tlkw.entity.*;
import com.schmoll.tlkw.service.*;
import com.schmoll.tlkw.service.impl.PcDataAlarmServiceImpl;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.utils.ClsPropertySet;
import com.schmoll.tlkw.utils.MikeUtils;
import com.schmoll.tlkw.utils.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class CustomerClientImpl implements CustomerClient {
    public static final transient SimpleDateFormat SLFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final transient SimpleDateFormat SLFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static String lastParam = "";
    public static String lastError = "";
    public static String operatorSign = ""; //当前用户
    public static String diameter = "";  //刀直径
    public static String spindleSpeed = "";  //刀转速
    public static String infeed = "";  //刀进到速
    public static String retreat = "";  //刀退刀速
    public static String totalBlocks = "";  //孔数
    public static Boolean timeOut = true;
    public static Integer count = 0;
    @Autowired
    public PcDataAlarmService alarmService;
    @Autowired
    public PcDataScanService scanService;
    @Autowired
    public PcDataCountService dataCountService;
    @Autowired
    public EapMaterialDataService materialDataService;
    @Autowired
    PcSignalStatusService signalStatusService;
    public static String[] status = new String[5];
    public static String drillProductionDataEnd = "";
    public static List<Object> lastErrorList=new ArrayList<>();

    @Override
    public void send(String StringIn) {
        if (StringIn.contains("SMDATEND")){
            StringIn=StringIn.replace("SMDATEND","");
        }


        if (StringIn.contains("drill.PanelValidation")){
            try {
                Map<Integer, String> panelValidationMap = MikeUtils.parsePanelValidation(StringIn);
                for (Map.Entry<Integer, String> entry : panelValidationMap.entrySet()) {
                    PcDataScan dataScan = new PcDataScan();
                    dataScan.setPnlidorsetid(entry.getValue());
                    dataScan.setDrctype("DRILLID");
                    dataScan.setCreateuser("轴号：" + entry.getKey());
                    dataScan.setCreatetime(new Date());
                    scanService.save(dataScan);
                }

                log.info("PanelValidation: {}", panelValidationMap);
            } catch (Exception e) {
                log.error("PanelValidation Error:{}", e.getMessage());
            }
        }
        if (StringIn.contains("drill.JobCall")){
            if (MikeUtils.recipeSuccess(StringIn)){
                count=0;
                PcDataCount dataCount=new PcDataCount();
                dataCount.setCount(count);
                dataCount.setUpdatetime(new Date());
                dataCountService.update(dataCount,new LambdaQueryWrapper<PcDataCount>().eq(PcDataCount::getId,StaticClass.pcDataCountId));
                List<Long> collect = materialDataService.list().stream().map(EapMaterialData::getId).collect(Collectors.toList());
                if (materialDataService.removeBatchByIds(collect)) {
                    log.info("设备加载完毕资料，设备删除EAP_MATERIAL_DATA表资料成功");
                }else {
                    log.info("设备删除配方失败!");
                }
            }
        }
        
        if (StringIn.contains("drill.ProductionData")){
            //优化数据
            String event = MikeUtils.parsingProductionData(StringIn, "event");
            String eventInfo = MikeUtils.parsingProductionData(StringIn, "eventInfo");
            String jobName = MikeUtils.parsingProductionData(StringIn, "jobName");
            String prgName = MikeUtils.parsingProductionData(StringIn, "prgName");
            String prgDrive = MikeUtils.parsingProductionData(StringIn, "prgDrive");
            String cutName = MikeUtils.parsingProductionData(StringIn, "cutName");
            String cutDrive = MikeUtils.parsingProductionData(StringIn, "cutDrive");
            String blockCounter = MikeUtils.parsingProductionData(StringIn, "blockCounter");
            String totalBlocks = MikeUtils.parsingProductionData(StringIn, "totalBlocks");
            String progress = MikeUtils.parsingProductionData(StringIn, "progress");
            String startTime = MikeUtils.parsingProductionData(StringIn, "startTime");
            String runTime = MikeUtils.parsingProductionData(StringIn, "runTime");
            String endTime = MikeUtils.parsingProductionData(StringIn, "endTime");
            String AV = MikeUtils.parsingProductionData(StringIn, "AV");
            String Inch = MikeUtils.parsingProductionData(StringIn, "Inch");
            String XH = MikeUtils.parsingProductionData(StringIn, "XH");
            String YH = MikeUtils.parsingProductionData(StringIn, "YH");
            String X0 = MikeUtils.parsingProductionData(StringIn, "X0");
            String Y0 = MikeUtils.parsingProductionData(StringIn, "Y0");
            String VX = MikeUtils.parsingProductionData(StringIn, "VX");
            String VY = MikeUtils.parsingProductionData(StringIn, "VY");
            String ZZ = MikeUtils.parsingProductionData(StringIn, "ZZ");
            String ZUT = MikeUtils.parsingProductionData(StringIn, "ZUT");
            String HH = MikeUtils.parsingProductionData(StringIn, "HH");
            String ZOT = MikeUtils.parsingProductionData(StringIn, "ZOT");
            String ZLL = MikeUtils.parsingProductionData(StringIn, "ZLL");
            String ZOB = MikeUtils.parsingProductionData(StringIn, "ZOB");
            String SDX = MikeUtils.parsingProductionData(StringIn, "SDX");
            String SDY = MikeUtils.parsingProductionData(StringIn, "SDY");
            String SPOX = MikeUtils.parsingProductionData(StringIn, "SPOX");
            String SPOY = MikeUtils.parsingProductionData(StringIn, "SPOY");
            String WB = MikeUtils.parsingProductionData(StringIn, "WB");
            String LODI = MikeUtils.parsingProductionData(StringIn, "LODI");
            String TOTO = MikeUtils.parsingProductionData(StringIn, "TOTO");
            String SUTOAdd = MikeUtils.parsingProductionData(StringIn, "SUTO+");
            String SUTODel = MikeUtils.parsingProductionData(StringIn, "SUTO-");
            String BORX = MikeUtils.parsingProductionData(StringIn, "BORX");
            String BORY = MikeUtils.parsingProductionData(StringIn, "BORY");
            String ZPO = MikeUtils.parsingProductionData(StringIn, "ZPO");
            String Z1O = MikeUtils.parsingProductionData(StringIn, "Z1O");
            String Z2O = MikeUtils.parsingProductionData(StringIn, "Z2O");
            String Z3O = MikeUtils.parsingProductionData(StringIn, "Z3O");
            String Z4O = MikeUtils.parsingProductionData(StringIn, "Z4O");
            String Z5O = MikeUtils.parsingProductionData(StringIn, "Z5O");
            String Z6O = MikeUtils.parsingProductionData(StringIn, "Z6O");
            String ZOff1 = MikeUtils.parsingProductionData(StringIn, "ZOff1");
            String ZOff2 = MikeUtils.parsingProductionData(StringIn, "ZOff2");
            String ZOff3 = MikeUtils.parsingProductionData(StringIn, "ZOff3");
            String ZOff4 = MikeUtils.parsingProductionData(StringIn, "ZOff4");
            String ZOff5 = MikeUtils.parsingProductionData(StringIn, "ZOff5");
            String ZOff6 = MikeUtils.parsingProductionData(StringIn, "ZOff6");
            String ZCOX = MikeUtils.parsingProductionData(StringIn, "ZCOX");
            String ZCOY = MikeUtils.parsingProductionData(StringIn, "ZCOY");

        }
        if (StringIn.contains("drill.MachineInformation")){
            try {
                handleMachineInformation(StringIn);
            } catch (Exception e) {
                log.error("handleMachineInformation===>{}",e.getMessage());
            }
        }

        if (StringIn.contains("drill.MissingTool")){
            String tool = MikeUtils.parsingMissingTool(StringIn, "tool");
            String diameter = MikeUtils.parsingMissingTool(StringIn, "diameter");
            String index = MikeUtils.parsingMissingTool(StringIn, "index");
            String toolType = MikeUtils.parsingMissingTool(StringIn, "toolType");
            String missing = MikeUtils.parsingMissingTool(StringIn, "missing");

        }
        if (StringIn.contains("drill.ToolBreak")){
            String info = MikeUtils.parsingMissingTool(StringIn, "info");
            String positionX = MikeUtils.parsingMissingTool(StringIn, "positionX");
            String positionY = MikeUtils.parsingMissingTool(StringIn, "positionY");
            String spindle = MikeUtils.parsingMissingTool(StringIn, "spindle");
            String logTool = MikeUtils.parsingMissingTool(StringIn, "logTool");
            String physTool = MikeUtils.parsingMissingTool(StringIn, "physTool");
            String diameter = MikeUtils.parsingMissingTool(StringIn, "diameter");
            String index = MikeUtils.parsingMissingTool(StringIn, "index");
            String hitNumber = MikeUtils.parsingMissingTool(StringIn, "hitNumber");
            String block = MikeUtils.parsingMissingTool(StringIn, "block");
            String stepRepeat = MikeUtils.parsingMissingTool(StringIn, "stepRepeat");
            String step = MikeUtils.parsingMissingTool(StringIn, "step");
            String actualHit = MikeUtils.parsingMissingTool(StringIn, "actualHit");
            
        }
        if (StringIn.contains("drill.PartProgramStatus")){
            String prepTime = MikeUtils.parsingProductionData(StringIn, "prepTime");
            count++;
            PcDataCount pcDataCount = new PcDataCount();
            pcDataCount.setCount(count);
            pcDataCount.setUpdatetime(new Date());
            dataCountService.update(pcDataCount,new QueryWrapper<PcDataCount>().eq("id",StaticClass.pcDataCountId));
        }
        if (StringIn.contains("drill.ProductionData") && StringIn.contains("End")){
            signalStatusService.save(new PcSignalStatus(null,"PRODUCT","生产结束","0",new Date()));
            signalStatusService.save(new PcSignalStatus(null,"WAITMATERIAL","设备待料","1",new Date()));
        }

        if (StringIn.contains("drill.ProductionData") && StringIn.contains("Start")){
            signalStatusService.save(new PcSignalStatus(null,"PRODUCT","生产开始","1",new Date()));
            signalStatusService.save(new PcSignalStatus(null,"WAITMATERIAL","设备复位","0",new Date()));

            
            if (!StrUtil.isEmpty(StaticClass.lastEndTime)){
                String startTime = MikeUtils.parsingProductionData(StringIn, "startTime");
                OffsetDateTime strTime = OffsetDateTime.parse(startTime);
                OffsetDateTime endTime = OffsetDateTime.parse(StaticClass.lastEndTime);
                double diffInSeconds = ChronoUnit.SECONDS.between(endTime, strTime);
                StaticClass.timeOfRefuelings+=diffInSeconds;
            }
        }

        if (StringIn.contains("drill.ProductionData") && StringIn.contains("End")) {
            String endTime = MikeUtils.parsingProductionData(StringIn, "endTime");
            String runTime = MikeUtils.parsingProductionData(StringIn, "runTime");
            //计算设备运行时间
            double runTimeDouble = MikeUtils.parseTimespanToMinutes(runTime);
            StaticClass.runTimeForDay+=runTimeDouble;
            StaticClass.lastEndTime=endTime;
            //计算换料次数
            StaticClass.numberOfRefuelings++;
            //任务结束出料

        }

        
        if (StringIn.contains("<cmd_id>common.ProductionState</cmd_id>")) {
            if (StringIn.contains("Run")) {
                status[0] = "1";//生产状态
            }
            if (StringIn.contains("Wait")) {
                status[1] = "2";//待机状态
            }
            if (StringIn.contains("Error")) {
                PcSignalStatus pcSignalStatus=new PcSignalStatus(null,"TOTALALARM","设备总故障状态","1",new Date());
                signalStatusService.save(pcSignalStatus);
                status[2] = "4"; //故障状态
            }else {
                PcSignalStatus lastAlarm = signalStatusService.getOne(new LambdaQueryWrapper<PcSignalStatus>()
                        .eq(PcSignalStatus::getSignalcode, "TOTALALARM")
                        .orderByDesc(PcSignalStatus::getCreatetime)
                        .last("limit 1"));
                if (lastAlarm!=null){
                    if ("1".equals(lastAlarm.getSignalvalue())){
                        //归零
                        PcSignalStatus pcSignalStatus=new PcSignalStatus(null,"TOTALALARM","设备总故障状态","0",new Date());
                        signalStatusService.save(pcSignalStatus);
                    }
                }
            }
            if (StringIn.contains("Service")) {
                status[3] = "8"; //保养状态
            }
            if (StringIn.contains("Stop")) {
                PcSignalStatus pcSignalStatus=new PcSignalStatus(null,"EMERGENCYSTOP","紧急停止","1",new Date());
                signalStatusService.save(pcSignalStatus);
                status[4] = "16"; //暂停状态
            }else {
                PcSignalStatus lastStop = signalStatusService.getOne(new LambdaQueryWrapper<PcSignalStatus>()
                        .eq(PcSignalStatus::getSignalcode, "EMERGENCYSTOP")
                        .orderByDesc(PcSignalStatus::getCreatetime)
                        .last("limit 1"));
                if (lastStop!=null){
                    if ("1".equals(lastStop.getSignalvalue())){
                        //归零
                        PcSignalStatus pcSignalStatus=new PcSignalStatus(null,"EMERGENCYSTOP","紧急停止","0",new Date());
                        signalStatusService.save(pcSignalStatus);
                    }
                }
            }
        }

        if (StringIn.contains("<cmd_id>common.MachineMessages</cmd_id>")) {
            try {
                List<Object> objects = MikeUtils.parsingMachineMessages(StringIn);
                String propertyAllError=System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "messageCode.pro";
                ClsPropertySet allError= new ClsPropertySet(propertyAllError, StandardCharsets.UTF_8);
                if (lastErrorList.isEmpty()){
                    lastErrorList.addAll(objects);
                    for (Object startError : objects) {
                        String allErrorStr = allError.getPropertyStr(startError.toString(), "");
                        PcDataAlarm dataAlarm=new PcDataAlarm();
                        dataAlarm.setAlarmcode(startError.toString());
                        dataAlarm.setAlarmdes(allErrorStr);
                        dataAlarm.setAlarmvalue("1");
                        dataAlarm.setCreatetime(new Date());
                        alarmService.save(dataAlarm);
                    }
                }else {
                    List<Object> intersection = objects.stream()
                            .filter(lastErrorList::contains)
                            .collect(Collectors.toList());
                    if (intersection.isEmpty()){
                        //与上一次无交集，直接发信息
                        for (Object startError : objects) {
                            String allErrorStr = allError.getPropertyStr(startError.toString(), "");
                            PcDataAlarm dataAlarm=new PcDataAlarm();
                            dataAlarm.setAlarmcode(startError.toString());
                            dataAlarm.setAlarmdes(allErrorStr);
                            dataAlarm.setAlarmvalue("1");
                            alarmService.save(dataAlarm);
                        }
                        for (Object endError : lastErrorList) {
                            String allErrorStr = allError.getPropertyStr(endError.toString(), "");
                            PcDataAlarm dataAlarm=new PcDataAlarm();
                            dataAlarm.setAlarmcode(endError.toString());
                            dataAlarm.setAlarmdes(allErrorStr);
                            dataAlarm.setAlarmvalue("0");
                            alarmService.save(dataAlarm);
                        }
                        lastErrorList.clear();
                        lastErrorList.addAll(objects);
                    }else {
                        //求两个集合的对称差集
                        List<Object> symmetricDiff= Stream.concat(
                                objects.stream().filter(e -> !lastErrorList.contains(e)),
                                lastErrorList.stream().filter(e ->!objects.contains(e))
                        ).collect(Collectors.toList());
                        //和原数组进行对比，比对需要发送解除的
                        List<Object> sendTwo = symmetricDiff.stream()
                                .filter(lastErrorList::contains)
                                .collect(Collectors.toList());
                        List<Object> sendOne = symmetricDiff.stream()
                                .filter(objects::contains)
                                .collect(Collectors.toList());
                        lastErrorList.removeAll(sendTwo);
                        lastErrorList.addAll(sendOne);
                        for (Object startError : sendOne) {
                            String allErrorStr = allError.getPropertyStr(startError.toString(), "");
                            PcDataAlarm dataAlarm=new PcDataAlarm();
                            dataAlarm.setAlarmcode(startError.toString());
                            dataAlarm.setAlarmdes(allErrorStr);
                            dataAlarm.setAlarmvalue("1");
                            alarmService.save(dataAlarm);
                        }
                        for (Object endError : sendTwo) {
                            String allErrorStr = allError.getPropertyStr(endError.toString(), "");
                            PcDataAlarm dataAlarm=new PcDataAlarm();
                            dataAlarm.setAlarmcode(endError.toString());
                            dataAlarm.setAlarmdes(allErrorStr);
                            dataAlarm.setAlarmvalue("0");
                            alarmService.save(dataAlarm);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("MachineMessages Error:{}",e.getMessage());
            }
        }

        if (StringIn.contains("<cmd_id>common.Supplies</cmd_id>")) {
            gxSave(StringIn);
        }
    }
    public void handleMachineInformation(String xml) throws Exception {
        String machineId = "S80";
        Date collectTime = new Date();

        ShiftInfo shiftInfo = getShiftInfo(
                LocalDateTime.ofInstant(
                        collectTime.toInstant(),
                        ZoneId.systemDefault()
                )
        );
        Map<Integer, Integer> spindleHours = MikeUtils.parseSpindleHours(xml);
        ApplicationContext context = StaticClass.getApplicationContext();
        MachineSpindleShiftUsageService bean = context.getBean(MachineSpindleShiftUsageService.class);
        for (Map.Entry<Integer, Integer> entry : spindleHours.entrySet()) {
            Integer spindleId = entry.getKey();
            Integer currentHour = entry.getValue();

            MachineSpindleShiftUsage usage = bean.lambdaQuery()
                    .eq(MachineSpindleShiftUsage::getMachineId, machineId)
                    .eq(MachineSpindleShiftUsage::getShiftStartTime, shiftInfo.getShiftStart())
                    .eq(MachineSpindleShiftUsage::getSpindleId, spindleId)
                    .last("limit 1")
                    .one();

            if (usage == null) {
                usage = new MachineSpindleShiftUsage();
                usage.setMachineId(machineId);
                usage.setShiftStartTime(shiftInfo.getShiftStart());
                usage.setShiftEndTime(shiftInfo.getShiftEnd());
                usage.setShiftType(shiftInfo.getShiftType());
                usage.setSpindleId(spindleId);
                usage.setStartSpindleHour(currentHour);
                usage.setCurrentSpindleHour(currentHour);
                usage.setShiftUsedHour(0);
                usage.setLastCollectTime(collectTime);

                bean.save(usage);
            } else {
                int usedHour = currentHour - usage.getStartSpindleHour();

                if (usedHour < 0) {
                    usedHour = 0;
                    usage.setStartSpindleHour(currentHour);
                }

                usage.setCurrentSpindleHour(currentHour);
                usage.setShiftUsedHour(usedHour);
                usage.setLastCollectTime(collectTime);

                bean.updateById(usage);
            }
        }
    }
    public static ShiftInfo getShiftInfo(LocalDateTime time) {
        LocalDate date = time.toLocalDate();

        LocalDateTime dayStart = date.atTime(8, 0);
        LocalDateTime nightStart = date.atTime(20, 0);

        ShiftInfo info = new ShiftInfo();

        if (!time.isBefore(dayStart) && time.isBefore(nightStart)) {
            info.setShiftStart(toDate(dayStart));
            info.setShiftEnd(toDate(nightStart));
            info.setShiftType("DAY");
        } else if (!time.isBefore(nightStart)) {
            info.setShiftStart(toDate(nightStart));
            info.setShiftEnd(toDate(date.plusDays(1).atTime(8, 0)));
            info.setShiftType("NIGHT");
        } else {
            info.setShiftStart(toDate(date.minusDays(1).atTime(20, 0)));
            info.setShiftEnd(toDate(dayStart));
            info.setShiftType("NIGHT");
        }

        return info;
    }
    private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    @Override
    public void setServerLight(int status) {
        if (MainForm.CNCLight == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        MainForm.CNCLight.setLight(status, 0, 0);

    }

    public static Map<String, String> commonSupplies = new HashMap<>();
    public static void gxSave(String str){
        JSONObject jsonObject = XML.toJSONObject(str);
        JSONObject schmollMesMessage = (JSONObject) jsonObject.get("schmoll_mes_message");
        Object data1 = schmollMesMessage.get("data");
        JSONObject data = (JSONObject) data1;
        cn.hutool.json.JSONArray param = (cn.hutool.json.JSONArray) data.get("pressure");
        for (int k = 0; k < param.size(); k++) {
            String key = (String) param.getJSONObject(k).get("id");
            Object content = param.getJSONObject(k).get("content");
            if (key.equals("Exhaust")) {
                StaticClass.dataMap.put("macCode_380", content.toString());
            }
            if (key.equals("Vacuum1")) {
                StaticClass.dataMap.put("macCode_381", content.toString());
            }

        }
    }
}
