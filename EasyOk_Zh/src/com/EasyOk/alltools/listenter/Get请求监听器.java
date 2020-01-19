package com.EasyOk.alltools.listenter;

import java.io.File;

public interface Get请求监听器{
	 
    /**
     * 下载成功之后的文件
     */
    void Get成功(String requestdata);

    /**
     * 下载异常信息
     */

    void Get失败(Exception e);
}
