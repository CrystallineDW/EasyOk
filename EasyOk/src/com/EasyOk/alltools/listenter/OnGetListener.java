package com.EasyOk.alltools.listenter;

import java.io.File;

public interface OnGetListener{
	 
    /**
     * 下载成功之后的文件
     */
    void onGet_Success(String requestdata);

    /**
     * 下载异常信息
     */

    void onGet_Failed(Exception e);
}
