package com.schmoll.tlkw.swing.panel;



import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.schmoll.tlkw.TlkwApplication;
import com.schmoll.tlkw.swing.LightStatus;
import com.schmoll.tlkw.swing.LoginPage;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.utils.StaticClass;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UpJP {

    public static void upJPanel(JPanel jp){
        jp.setLayout(null);

        JLabel jl1 = MainForm.getJL("CNC:",true, 20, 30, 40, 25,false);

        MainForm.CNCJL= MainForm.getJL(MainForm.DrillIP+":" + MainForm.DrillPort,true, 60, 30, 120, 25,true);

        MainForm.CNCLight = new LightStatus();
        MainForm.CNCLight.setBounds((int) Math.round(185 * MainForm.w_s80), (int) Math.round(36 * MainForm.h_s80), (int) Math.round(10 * MainForm.w_s80), (int) Math.round(10 * MainForm.h_s80));  //185, 36, 10, 10

        JLabel jl2 = MainForm.getJL( "MES:",true, 290, 30, 40, 25,false);

        MainForm.MESJL= MainForm.getJL( MainForm.ClientIP,true, 330, 30, 330, 25,true);

        MainForm.MESLight = new LightStatus();
        MainForm.MESLight.setBounds(805, 36, 10, 10);

        JLabel jl3 = MainForm.getJL("ServerPort:",true, 660, 30, 80, 25,false);

//        MainForm.serverPortTxt= MainForm.getJL(MainForm.serverPort(),true, 740, 30, 60, 25,true);

        JButton restartBt = new JButton("重启");
        restartBt.setBounds(820, 12, 60, 25);
        restartBt.setBackground(MainForm.white);

        JButton logoutBt = MainForm.getBT("注销",820, 12, 60, 25);
        logoutBt.setVisible(false);

        // 主题选择器
        JLabel themeLabel = MainForm.getJL("主题:", true, 660, 30, 40, 25, false);
        JComboBox<String> themeComboBox = new JComboBox<>();
        themeComboBox.setBounds((int) Math.round(700 * MainForm.w_s80), (int) Math.round(28 * MainForm.h_s80), (int) Math.round(150 * MainForm.w_s80), (int) Math.round(25 * MainForm.h_s80));
        
        // 定义可用主题映射（存储 Class，每次切换时创建新实例）
        Map<String, Class<? extends LookAndFeel>> themeMap = new HashMap<>();
        
        // FlatLaf 内置主题
        themeMap.put("FlatLight", FlatLightLaf.class);
        themeMap.put("FlatDark", FlatDarkLaf.class);
        themeMap.put("FlatIntelliJ", FlatIntelliJLaf.class);
        themeMap.put("FlatDarcula", FlatDarculaLaf.class);
        
        // IntelliJ 主题（通过 flatlaf-intellij-themes 库）
        themeMap.put("Arc", FlatArcIJTheme.class);
        themeMap.put("Arc Dark", FlatArcDarkIJTheme.class);
        themeMap.put("Atom One Dark", FlatAtomOneDarkIJTheme.class);
        themeMap.put("Atom One Light", FlatAtomOneLightIJTheme.class);
        themeMap.put("Cobalt2", FlatCobalt2IJTheme.class);
        themeMap.put("Dracula", FlatDraculaIJTheme.class);
        themeMap.put("Gradianto Deep Ocean", FlatGradiantoDeepOceanIJTheme.class);
        themeMap.put("Gradianto Midnight Blue", FlatGradiantoMidnightBlueIJTheme.class);
        themeMap.put("Gradianto Nature Green", FlatGradiantoNatureGreenIJTheme.class);
        themeMap.put("Gradianto Dark Fuchsia", FlatGradiantoDarkFuchsiaIJTheme.class);
        themeMap.put("GitHub", FlatGitHubIJTheme.class);
        themeMap.put("High Contrast", FlatHighContrastIJTheme.class);
        themeMap.put("Material Dark", FlatMaterialDesignDarkIJTheme.class);
        themeMap.put("Nord", FlatNordIJTheme.class);
        themeMap.put("Solarized Dark", FlatSolarizedDarkIJTheme.class);
        themeMap.put("Solarized Light", FlatSolarizedLightIJTheme.class);
        
        themeComboBox.setModel(new DefaultComboBoxModel<>(themeMap.keySet().toArray(new String[0])));
        themeComboBox.setSelectedItem("FlatDarcula");
        
        // 主题切换事件
        themeComboBox.addActionListener(e -> {
            String selectedTheme = (String) themeComboBox.getSelectedItem();
            try {
                Class<? extends LookAndFeel> lafClass = themeMap.get(selectedTheme);
                if (lafClass != null) {
                    // 每次切换都创建新的实例
                    LookAndFeel laf = lafClass.newInstance();
                    UIManager.setLookAndFeel(laf);
                    SwingUtilities.updateComponentTreeUI(TlkwApplication.frame);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        jp.add(jl1);
        jp.add(jl2);
//        jp.add(jl3);
        jp.add(MainForm.CNCJL);
        jp.add(MainForm.CNCLight);
        jp.add(MainForm.MESJL);
//        jp.add(MESLight);
//        jp.add(MainForm.serverPortTxt);
//        jp.add(restartBt);
        jp.add(logoutBt);
        jp.add(themeLabel);
        jp.add(themeComboBox);

        restartBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(restartBt,"确定要重启吗？","重启提示",0);
                if(i==0){
//                    TlkwApplication.restart();
                    JOptionPane.showMessageDialog(null, "重启完成。");
                }
            }
        });

        logoutBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(logoutBt,"确定要注销吗？","重启提示",0);
                if(i==0){
                    ApplicationContext context = StaticClass.getApplicationContext();
                    LoginPage loginPage = context.getBean(LoginPage.class);
                    loginPage.setVisible(true);
                    TlkwApplication.frame.setVisible(false);//注销
                }
            }
        });
    }
}