package com.EasyOk.alltools.listenter;

public interface OnPostListener{
	 
    /**
     * ���سɹ�֮����ļ�
     */
    void onPost_Success(String requestdata);

    /**
     * �����쳣��Ϣ
     */

    void onPost_Failed(Exception e);
}