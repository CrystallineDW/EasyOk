package com.EasyOk.alltools.listenter;

import java.io.File;

public interface OnGetListener{
	 
    /**
     * ���سɹ�֮����ļ�
     */
    void onGet_Success(String requestdata);

    /**
     * �����쳣��Ϣ
     */

    void onGet_Failed(Exception e);
}
