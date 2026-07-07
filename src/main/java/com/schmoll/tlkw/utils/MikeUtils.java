package com.schmoll.tlkw.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MikeUtils {
    private static final Logger log = LogManager.getLogger(MikeUtils.class);

    public static String getNowTime() {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 格式化时间为字符串（例如：2025-03-03 14:30:45）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        return currentDateTime.format(formatter);
    }

    public static Integer getDuration(String startTime,String endTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);

        // 计算时间差（自动处理正负）
        Duration duration = Duration.between(start, end);
        // 返回绝对值的秒数差
        return Math.toIntExact(Math.abs(duration.getSeconds()));
    }

    /**
     * 在指定目录中查找以 targetName 结尾的文件。
     *
     * @param directoryPath 要搜索的目录路径 (String)
     * @param targetName    目标文件名（用于匹配结尾）
     * @return 找到的第一个匹配文件的完整路径字符串，如果没找到则返回 null
     */
    public static String findFileInDirectory(String directoryPath, String targetName) {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            log.info("目录不存在或不是一个有效的目录: {}", directoryPath);
            return null;
        }

        File[] matchingFiles = directory.listFiles((dir, name) -> // 使用 Lambda 表达式简化 FilenameFilter
                name.toLowerCase().endsWith(targetName.toLowerCase())
        );

        if (matchingFiles != null && matchingFiles.length > 0) {
            // 返回第一个匹配的文件的绝对路径
            return matchingFiles[0].getAbsolutePath();
        }
        log.info("not found file in directory: {}", directoryPath);
        return null ; // 未找到
    }


    public static List<Object> parsingProductionState(String xml){
        List<Object> list=new ArrayList<>();
        JSONObject data = (JSONObject) getData(xml);
        if ( data.get("state") instanceof  JSONArray){
            JSONArray state = (JSONArray) data.get("state");
            list.addAll(state);
            return list;
        }else if (data.get("state") instanceof  JSONObject){
            JSONObject state = (JSONObject) data.get("state");
            list.add(state);
            return list;
        }else if (data.get("state") instanceof  String){
            String state = (String) data.get("state");
            list.add(state);
            return list;
        }
        return ListUtil.empty();
    }

    public static List<Object> parsingMachineMessages(String xml) {
        JSONObject data = (JSONObject) getData(xml);
        if (data==null){
            return ListUtil.empty();
        }
        Object machine = data.get("machine");

        if (machine instanceof String) {
            return ListUtil.empty();
        } else {
            if (machine instanceof JSONObject) {
                JSONObject machineData = (JSONObject) data.get("machine");
                Object error = machineData.get("error");
                if (!Objects.isNull(error)) {
                    ArrayList errorList;
                    if (error instanceof String) {
                        errorList = new ArrayList();
                        errorList.add(error);
                        return errorList;
                    }

                    if (error instanceof List) {
                        return (List) error;
                    }

                    if (error instanceof Integer) {
                        errorList = new ArrayList();
                        errorList.add(error);
                        return errorList;
                    }
                }
            }

            return ListUtil.empty();
        }
    }

    private static Object getData(String xml) {
        JSONObject jsonObject = XML.toJSONObject(xml);
        JSONObject schmollMesMessage = (JSONObject)jsonObject.get("schmoll_mes_message");
        return schmollMesMessage.get("data");
    }
    public static Boolean recipeSuccess(String xml) {
        JSONObject data = (JSONObject) getData(xml);
        if (data==null){
            return false;
        }
        JSONArray param = (JSONArray) data.get("param");
        JSONObject result = (JSONObject) param.get(0);
        Object value = result.get("content");
        return "1".equals(value.toString());
    }

    public static String addNewLines(String str, int interval) {
        StringBuilder sb = new StringBuilder(str);

        for(int i = interval; i < sb.length(); i += interval + 1) {
            sb.insert(i, '\n');
        }

        return sb.toString();
    }

    public static String parsingMissingTool(String xml ,String key){
        JSONObject data = (JSONObject)getData(xml);
        if (data==null){
            return "";
        }
        Object tool =  data.get("tool");
        if (tool instanceof  JSONArray){
            JSONObject zero = (JSONObject) ((JSONArray) tool).get(0);
            List<Map<String, Object>> param = (List) zero.get("param");
            int index = -1;
            for(int i = 0; i < param.size(); ++i) {
                Map<String, Object> map = (Map)param.get(i);
                if (map.containsValue(key)) {
                    index = i;
                }
            }

            if (index != -1) {
                Map<String, Object> map = (Map)param.get(index);
                return map.get("content") == null ? "" : map.get("content").toString();
            } else {
                return "";
            }

        }
        if (tool instanceof  JSONObject){
            JSONObject toolObject = (JSONObject) data.get("tool");
            List<Map<String, Object>> param = (List)  toolObject.get("param");
            int index = -1;
            for(int i = 0; i < param.size(); ++i) {
                Map<String, Object> map = (Map)param.get(i);
                if (map.containsValue(key)) {
                    index = i;
                }
            }

            if (index != -1) {
                Map<String, Object> map = (Map)param.get(index);
                return map.get("content") == null ? "" : map.get("content").toString();
            } else {
                return "";
            }
        }
        return "";
    }

    public static String parsingProductionData(String xml, String key) {
        JSONObject data = (JSONObject)getData(xml);
        if (data==null){
            return "";
        }
        List<Map<String, Object>> param = (List)data.get("param");
        int index = -1;

        for(int i = 0; i < param.size(); ++i) {
            Map<String, Object> map = (Map)param.get(i);
            if (map.containsValue(key)) {
                index = i;
            }
        }

        if (index != -1) {
            Map<String, Object> map = (Map)param.get(index);
            return map.get("content") == null ? "" : map.get("content").toString();
        } else {
            return "";
        }
    }

    public static double parseTimespanToMinutes(String timespanStr) {
        if (timespanStr == null || timespanStr.trim().isEmpty()) {
            return 0.0;
        }

        // 格式: "00000:00:00:00.000" -> [天, 时, 分, 秒.毫秒]
        String[] parts = timespanStr.split(":");

        if (parts.length != 4) {
            log.error("时间格式错误，预期格式为 d:hh:mm:ss.fff");
        }

        try {
            long days = Long.parseLong(parts[0]);
            long hours = Long.parseLong(parts[1]);
            long minutes = Long.parseLong(parts[2]);

            // 处理秒和毫秒部分 "00.000"
            String[] secParts = parts[3].split("\\.");
            long seconds = Long.parseLong(secParts[0]);
            long millis = 0;
            if (secParts.length > 1) {
                // 防止毫秒位数不足或过多，取前3位或直接解析
                String msStr = secParts[1];
                if (msStr.length() > 3) msStr = msStr.substring(0, 3);
                // 如果不足3位，parseLong也能处理，但为了严谨可以补零，不过直接解析通常没问题
                millis = Long.parseLong(msStr);
                // 如果原始字符串毫秒位不足3位（如 .5），需要乘以对应的倍数吗？
                // 通常 .000 格式是固定的。如果是动态的，可能需要根据长度调整。
                // 假设固定3位毫秒，直接解析即可。
            }

            // 全部换算成分钟
            double totalMinutes =
                    (days * 24 * 60) +      // 天转分
                            (hours * 60) +          // 小时转分
                            minutes +               // 分钟
                            (seconds / 60.0) +      // 秒转分
                            (millis / 60000.0);     // 毫秒转分

            return totalMinutes;

        } catch (NumberFormatException e) {
            log.error("解析数字失败: {}", timespanStr);
            return 0.0;
        }
    }

    public static Map<Integer, Integer> parseSpindleHours(String xml) throws Exception {
        Map<Integer, Integer> result = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // 防止 XXE
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        NodeList spindleList = document.getElementsByTagName("spindle");

        for (int i = 0; i < spindleList.getLength(); i++) {
            Element spindle = (Element) spindleList.item(i);
            int spindleId = Integer.parseInt(spindle.getAttribute("id"));

            NodeList params = spindle.getElementsByTagName("param");

            for (int j = 0; j < params.getLength(); j++) {
                Element param = (Element) params.item(j);

                if ("spindleHour".equals(param.getAttribute("key"))) {
                    int spindleHour = Integer.parseInt(param.getTextContent().trim());
                    result.put(spindleId, spindleHour);
                }
            }
        }

        return result;
    }

    public static Map<Integer, String> parsePanelValidation(String xml) throws Exception {
        Map<Integer, String> result = new LinkedHashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        NodeList stationList = document.getElementsByTagName("station");
        for (int i = 0; i < stationList.getLength(); i++) {
            Element station = (Element) stationList.item(i);
            int stationId = Integer.parseInt(station.getAttribute("id"));

            NodeList params = station.getElementsByTagName("param");
            for (int j = 0; j < params.getLength(); j++) {
                Element param = (Element) params.item(j);
                if ("panelID".equals(param.getAttribute("key"))) {
                    result.put(stationId, param.getTextContent().trim());
                }
            }
        }

        return result;
    }
}
