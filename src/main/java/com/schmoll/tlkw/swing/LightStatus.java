/**
 * 状态灯
 * @author Dean
 */
package com.schmoll.tlkw.swing;


import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

@Slf4j
public class LightStatus extends JPanel {
    Color color = Color.red;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 初始化状态灯
        Graphics2D g2 = (Graphics2D) g;
        Shape circle = new Ellipse2D.Double(0, 0, 10, 10);
        g.setColor(color);
        g2.fill(circle);
    }

    /** 1显示绿灯，其他显示红灯 **/
    public void setLight(int status, int x, int y) {
        try {
            Graphics graphics = this.getGraphics();
            if (status == 1) {
                graphics.setColor(new Color(73, 200, 84));
                this.color = new Color(73, 200, 84);
            } else {
                graphics.setColor(Color.RED);
                this.color = Color.RED;
            }
            graphics.drawOval(x, y, 10, 10);
            graphics.fillOval(x, y, 11, 11);
        }catch (Exception e){
            log.error("setLightStatus error: {}", e.getMessage());
        }
    }
}
