package com.schmoll.tlkw.customer;



public interface CustomerClient {



    /** Send message to Mes **/
    public void send(String StringIn);

    /** 更改服务端状态灯颜色 **/
    public void setServerLight(int status);
}

