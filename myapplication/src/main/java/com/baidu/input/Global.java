package com.baidu.input;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.baidu.input_demo.R;
import com.baidu.util.SysIO;

public final class Global {

 // 输入法必须的文件
    public final static byte IPTFILE_HZ = 0;
    public final static byte IPTFILE_UZ = 1;
    public final static byte IPTFILE_CELL = 2;
    public final static byte IPTFILE_FT = 3;
    public final static byte IPTFILE_BH = 4;
    public final static byte IPTFILE_UE = 5;
    public final static byte IPTFILE_SYMBIN = 6;
    public final static byte IPTFILE_SYMLBIN = 7;
    public final static byte IPTFILE_CP = 8;
    /** 联系人文件 */
    public final static byte IPTFILE_CATE = 9;
    public final static byte IPTFILE_DEF = 10;
    /** 五笔86模式 */
    public final static byte IPTFILE_WB86 = 11;
    public final static byte IPTFILE_EN = 12;
    public final static byte IPTFILE_GRAM = 13;
    /** 输入时会触发多媒体搜索表情的关键字 */
    public final static byte IPTFILE_KEYWORD = 14;
    /** 根据ttf过滤的文件 */
    public final static byte IPTFILE_TTFFILTER = 15;
    /** 手写自造次 */
    public final static byte IPTFILE_USERWORD_HW = 16;
    /** 火星文对应关系 */
    public final static byte IPTFILE_MARSWORD = 17;
    /** 其它特殊的对应关系 */
    public final static byte IPTFILE_OTHERWORD = 18;
    // added for zhuyin-cangjie
    public final static byte IPTFILE_CANGJIE = 19;
    public final static byte IPTFILE_ZHUYIN_HZ = 20;
    public final static byte IPTFILE_ZHUYIN_CZ = 21;
    /** 拼音纠正文件 */
    public final static byte IPTFILE_CH_COR = 22;
    /** 盲打拼音模板 */
    public final static byte IPTFILE_KP = 23;
    /** 手写汉字模板文件 */
    public final static byte IPTFILE_WT = 24;
    /** 歇后语词库 */
    public final static byte IPTFILE_XHY = 25;
    /** 个性短语的plus文件 */
    public final static byte IPTFILE_PHRASE_PLUS = 26;
    /** 快速输入的补丁文件 */
    public final static byte IPTFILE_FASTINPUT = 27;
    /** 五笔98模式 */
    public final static byte IPTFILE_WB98 = 28;


    public final static byte SYSFILE_INPUTCORE = 0;
    /** 设置项存储文件 */
    public final static byte SYSFILE_SETTING = 1;
    public final static byte SYSFILE_SP26 = 2;
    public final static byte SYSFILE_SP10 = 3;
    /** 符号联想需要的文件 */
    public final static byte SYSFILE_SYMCC = 4;
    /** cand条功能区数据文件 */
    public final static byte SYSFILE_FLAUNCH = 5;
    
    /** 内核最多处理输入码的个数*/
    public final static byte MAX_KEY = 63;

    public static String sysFilePath;
    /** 所有词库文件 */
    public static String[] iptFiles;
    /** 内核文件 */
    public static String[] sysFiles;
    /** 是否有三维词库 */
    private static boolean hasGram;
    /** 是否有自定义文件 */
    private static boolean hasVarFile;
    
    public static String[] filepath;
    /** 是否有拇指双拼的文件 */
    public static boolean hasSp10File;
    public static String sp26FilePath;
    public static String sp10FilePath;
    
    public static ImeCore core;

    public static String cuid;


    // //////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 载入必要的资源文件
     * 
     * @param con
     * @param iFlag
     */
    public final static void loadResources(Context con) {
        
        sysFilePath = con.getFilesDir().getPath() + '/';
        
        Resources res = con.getResources();
        if (iptFiles == null) {
            iptFiles = res.getStringArray(R.array.IPTFILES);
        }
        if (sysFiles == null) {
            sysFiles = res.getStringArray(R.array.SYSFILES);
        }

        res = null;
    }


    /**
     * 组装指定后缀的文件名
     * 
     * @param name
     * @param ext
     * @return
     */
    public final static String buildFileName(String name) {

        String str = name + ".cz";
        return str;
    }

    
    /**
     * 复制ipt里面的文件到默认目录下
     * 
     * @param con
     * @param isipt
     * @param filenames
     */
    public final static void installFile(Context con) {
        installSysFiles(con, sysFiles);
        installIptFiles(con, iptFiles);
    }

    private static final void installIptFiles(Context con, String[] filenames) {
        // 联系人词库防错
        File f = new File(Global.sysFilePath + filenames[Global.IPTFILE_CATE]);
        if (f.exists() && f.length() <= 0) {
            f.delete();
        }
        // 复制词库文件到/data/data/。。。/files下
        int[] filesize = copyToDataFiles(con, filenames);
        // 后续处理
        hasVarFile = filesize[Global.IPTFILE_DEF] > 0;
        // if (hasVarFile)
        // {
        // StrGroup.sSimpleShortKeyMaps[3] = StrGroup.sSimpleShortKeyMaps[7];
        // StrGroup.sShortKeyMaps[3] = StrGroup.sShortKeyMaps[7];// 自定义 //modified for zhuyin-cangjie
        // }
        // else
        // {
        // StrGroup.sSimpleShortKeyMaps[3] = StrGroup.sSimpleShortKeyMaps[12];
        // StrGroup.sShortKeyMaps[3] = StrGroup.sShortKeyMaps[12];// 五笔 //modified for zhuyin-cangjie
        // }
        if (filesize[Global.IPTFILE_GRAM] == 0) {
            hasGram = false;
        } else {
            hasGram = true;
        }

        // 配置filepath用于PlInit的参数
        filepath = new String[filenames.length - 1];
        for (int i = 0; i < filenames.length - 1; i++) {
            switch (i) {
                case Global.IPTFILE_DEF:
                    filepath[i] = hasVarFile ? Global.sysFilePath + filenames[i] : "";
                    break;
                case Global.IPTFILE_WB86:
                    SharedPreferences settings =
                            con.getSharedPreferences(con.getPackageName() + "_preferences", Context.MODE_PRIVATE);
                    int wbMode = 0;
                    if (settings != null) {
                        wbMode = settings.getInt("wbmode", 0);
                    }
                    filepath[i] =
                            (wbMode == 0) ? Global.sysFilePath + filenames[i] : Global.sysFilePath
                                    + filenames[Global.IPTFILE_WB98];
                    break;
                case Global.IPTFILE_GRAM:
                    filepath[i] = hasGram ? Global.sysFilePath + filenames[i] : "";
                    break;
                default:
                    filepath[i] = Global.sysFilePath + filenames[i];
                    break;
            }
        }
    }

    private static final void installSysFiles(Context con, String[] filenames) {
        // 复制到/data/data/。。。/files下
        int[] filesize = copyToDataFiles(con, filenames);
        // 自带的双拼方案,后续处理
        hasSp10File = filesize[Global.SYSFILE_SP10] > 0;
        sp26FilePath = Global.sysFilePath + filenames[Global.SYSFILE_SP26];
        sp10FilePath = Global.sysFilePath + filenames[Global.SYSFILE_SP10];
    }

    private static final int[] copyToDataFiles(Context con, String[] filenames) {
        File f;
        InputStream is = null;
        byte[] data;
        FileOutputStream fos = null;
        int count = filenames.length, size;
        String[] path = new String[count];
        int[] filesize = new int[count];

        for (int i = 0; i < count; i++) {
            path[i] = Global.sysFilePath + filenames[i];
            f = new File(path[i]);

            if (f.exists()) {
                filesize[i] = (int) f.length();

                if (filesize[i] > 0) {
                    continue;
                } else {
                    switch (i) {
                        case Global.IPTFILE_CATE:
                            f.delete();
                            break;
                        default:
                            break;
                    }
                }
            }

            try {

                is = con.getAssets().open("ipt/" + filenames[i]);

                if (is != null) {
                    size = is.available();
                    filesize[i] = size;
                    data = new byte[size];
                    is.read(data);
                    is.close();

                    fos = con.openFileOutput(filenames[i], 0);
                    fos.write(data);
                    fos.close();
                    data = null;
                    Log.d(ImeCore.TAG, "copy = " + filenames[i]);

                } else {
                    filesize[i] = 0;
                }
            } catch (Exception e) {
                // if (Macro.IS_DEBUG)
                {
                    // e.printStackTrace();
                }
                filesize[i] = 0;
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception ex) {
                    // if (Macro.IS_DEBUG)
                    {
                        ex.printStackTrace();
                    }
                } finally {
                    is = null;
                    fos = null;
                }
            }
        }
        return filesize;
    }
    
    public final static String[] read(Context con, String filename) {
        byte[] buffer = SysIO.load(con, "zh/" + filename);
        if (buffer == null) {
            return null;
        }
        String[] strs = SysIO.parseTxt(buffer);
        buffer = null;
        return strs;
    }



}
