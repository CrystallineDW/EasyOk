package com.EasyOk.alltools.listenter;

import java.io.File;

	public interface OnDownloadListener{
		 
        /**
         * ���سɹ�֮����ļ�
         */
        void onDownload_Success(File file);
 
        /**
         * ���ؽ���
         */
        void onDownloading(int progress);
        /**
         * �����쳣��Ϣ
         */
 
        void onDownload_Failed(Exception e);
    }
