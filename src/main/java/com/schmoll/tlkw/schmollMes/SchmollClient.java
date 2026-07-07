package com.schmoll.tlkw.schmollMes;

import org.w3c.dom.Document;

import java.io.IOException;


/** CONNECTION TO MES
 * @author SAP**/
public interface SchmollClient {





    public void sendToMes(String StringIn);

        /** Send Message to S50 **/
    public String send(String StringIn);


    /** 更改客户端状态灯颜色 **/
    public void setClientLight(int status);
}
