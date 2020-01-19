package com.EasyOk.alltools.listenter;

public interface Post监听器{
	 
    /**
     * 下载成功之后的文件
     */
    void Post成功(String requestdata);

    /**
     * 下载异常信息
     */

    void Post失败(Exception e);
}