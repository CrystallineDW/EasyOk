package com.EasyOk.alltools.listenter;

import java.io.File;

	public interface 下载监听器{
		 
        /**
         * 下载成功之后的文件
         */
        void 下载成功(File file);
 
        /**
         * 下载进度
         */
        void 下载中(int progress);
        /**
         * 下载异常信息
         */
 
        void 下载失败(Exception e);
    }
