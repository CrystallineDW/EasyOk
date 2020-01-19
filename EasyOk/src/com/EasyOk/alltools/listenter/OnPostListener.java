package com.EasyOk.alltools.listenter;

public interface OnPostListener{
	 
    /**
     * 下载成功之后的文件
     */
    void onPost_Success(String requestdata);

    /**
     * 下载异常信息
     */

    void onPost_Failed(Exception e);
}