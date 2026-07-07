package com.schmoll.tlkw.utils;

import com.schmoll.tlkw.entity.EapMaterialData;
import com.schmoll.tlkw.entity.EapSignalStatus;
import com.schmoll.tlkw.service.EapMaterialDataService;
import com.schmoll.tlkw.service.EapSignalStatusService;
import com.schmoll.tlkw.swing.panel.G3JP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Vector;

@Component
public class TaskManager {
    private static final String DR1_DRILL = "DR1_DRILL";
    private static final String DR1_DR_PARAMETER = "DR1_DR_PARAMETER";

    @Autowired
    EapSignalStatusService eapSignalStatusService;
    @Autowired
    EapMaterialDataService eapMaterialDataService;

    public void checkEapSignal(){
        List<EapSignalStatus> list = eapSignalStatusService.list();
        //无信号，直接跳过
        if (list.isEmpty()){
            return;
        }
        for (EapSignalStatus eapSignalStatus : list){
            if (eapSignalStatus.getSignalcode().equals("MATERIALERROR")){
                //展示报错信息
                SwingUtilities.invokeLater(()->{
                    G3JP.mesMessageBody.setText(eapSignalStatus.getSignalvalue());
                });
                //展示之后删除表格信息
                eapSignalStatusService.removeById(eapSignalStatus.getId());
            }else if (eapSignalStatus.getSignalcode().equals("MATERIALDOWN")){
                //下发成功去查资料下发表
                List<EapMaterialData> recipe = eapMaterialDataService.list();
                if (recipe.isEmpty()){
                   return;
                }
                String recipeName="";
                for (EapMaterialData eapMaterialData : recipe){
                    if (DR1_DRILL.equals(eapMaterialData.getItemcode())){
                        if (!checkAndCopyMaterialFile(eapMaterialData)) {
                            return;
                        }
                        recipeName=eapMaterialData.getItemvalue();
                    }
                    if (DR1_DR_PARAMETER.equals(eapMaterialData.getItemcode())){
                        if (!checkAndCopyMaterialFile(eapMaterialData)) {
                            return;
                        }
                    }
                }
                //成功复制到本地，加载到项目
                Vector<Vector<Object>> recipeList = new Vector<>();
                Vector<Object> vector=new Vector<>();
                vector.add(recipeName);
                recipeList.add(vector);
                SwingUtilities.invokeLater(()->{
                    G3JP.setMyTable(recipeList);
                });
            }
        }
    }

    private Boolean checkAndCopyMaterialFile(EapMaterialData eapMaterialData) {
        String sourcePath = eapMaterialData.getItemvalue();
        String itemcode = eapMaterialData.getItemcode();
        if (sourcePath == null || sourcePath.trim().isEmpty()) {
            showMessage(itemcode + " \u6587\u4ef6\u8def\u5f84\u4e3a\u7a7a");
            return false;
        }

        try {
            Path source = buildSourcePath(sourcePath.trim());
            if (!Files.isRegularFile(source)) {
                showMessage(itemcode + " \u6587\u4ef6\u4e0d\u5b58\u5728\uff1a" + sourcePath);
                return false;
            }

            Path target = Paths.get(System.getProperty("user.dir")).resolve(source.getFileName());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            if (itemcode.equals("DR1_DR_PARAMETER")){
                String crossPlatformStr = target.toString().replace("\\", "/");
                SwingUtilities.invokeLater(()->{
                    G3JP.cut.setText(target.toString());
                });
                ClsPropertySet set_cutNameDataPath = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "cutNameDataPath.pro");
                set_cutNameDataPath.setPropertyStr("cutNamePath", crossPlatformStr);
                set_cutNameDataPath.saveProperties(); //保存到 cutNameDataPath.pro 文件
            }
        } catch (InvalidPathException e) {
            showMessage(itemcode + " \u6587\u4ef6\u8def\u5f84\u683c\u5f0f\u9519\u8bef\uff1a" + sourcePath);
        } catch (IOException e) {
            showMessage(itemcode + " \u6587\u4ef6\u590d\u5236\u5931\u8d25\uff1a" + e.getMessage());
        }
        return true;
    }

    private Path buildSourcePath(String sourcePath) {
        if (!isWindows() && sourcePath.startsWith("\\\\")) {
            return Paths.get(sourcePath.replace('\\', '/'));
        }
        return Paths.get(sourcePath);
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private void showMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }
}
