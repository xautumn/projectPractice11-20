package com.baidu.input;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.android.bba.common.util.CommonParam;
import com.baidu.input.pub.CoreString;
import com.baidu.input_demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ImeCore extends PlumCore {

    public static final String TAG = "IME_DEMO";

    private final static String INPUTCORE_FILE = "inputcore_20170221";

    /** cz词库的总大小 */
    private final static int CZBIN_LENS = 5682396;
    /** cz词库的个数 */
    private final static int CZBIN_CNT = 8;

    /** 个性短语的plus文件 */
    public static final byte INSTALL_FILE_PHRASE_PLUS = 0;
    /** 快速输入的补丁文件 */
    public static final byte INSTALL_FILE_FASTINPUT = 1;
    /** 直达号的补丁文件 */
    public static final byte INSTALL_FILE_ZHIDAHAO = 2;
    /** 快速输入的补丁文件 */
    public static final byte INSTALL_FILE_MULTIMEDIA = 3;
    /** 扩展表情的不定文件 */
    public static final byte INSTALL_FILE_EMOJI_EXTEND = 4;
    /** 二次元数据文件，NIJIGEN为二次元日语的发音写法 */
    public static final byte INSTALL_FILE_NIJIGEN = 5;
    /** 表情的补丁文件 */
    public static final byte INSTALL_FILE_EMOJI = 6;
    /** 颜文字联想文件 */
    public static final byte INSTALL_FILE_YAN = 7;
    /** 颜文字联想文件 */
    public static final byte INSTALL_FILE_YAN_IDM = 8;
    /** 分类词库：高频口语 */
    public static final byte INSTALL_FILE_CELL0 = 9;
    /** 分类词库：成语俗语 */
    public static final byte INSTALL_FILE_CELL1 = 10;
    /** 分类词库：手机软件 */
    public static final byte INSTALL_FILE_CELL2 = 11;
    /** 邮件文件 */
    public static final byte INSTALL_FILE_EMAIL = 12;

    public static final byte INSTALL_FILE_KEYWORD_START = 1;
    public static final byte INSTALL_FILE_KEYWORD_END = 5;
    public static final byte INSTALL_FILE_KEYWORD_EMOTICON_START = 6;
    public static final byte INSTALL_FILE_KEYWORD_EMOTICON_END = 7;
    public static final byte INSTALL_FILE_IDM_START = 8;
    public static final byte INSTALL_FILE_IDM_END = 8;
    public static final byte INSTALL_FILE_CELL_START = 9;
    public static final byte INSTALL_FILE_CELL_END = 11;
    public static final byte INSTALL_FILE_SYLIAN_START = 12;
    public static final byte INSTALL_FILE_SYLIAN_END = 12;

    public byte[] param4Core = new byte[48];

    public static String pkgName;

    static {
        System.load(Global.sysFilePath + INPUTCORE_FILE);
    }

    public final void init(Context con) {
        PackageInfo pi = null;
        try {
            pkgName = con.getPackageName();
            pi = con.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
        }
        synchronized (Global.core) {
            try {

                // 初始化内核
                boolean isValid = loadCiKu(con, CIKU_TYPE_CZ, CZBIN_LENS, CZBIN_CNT);

                Global.cuid = CommonParam.getCUID(con);
                String model = android.os.Build.MODEL;
                PlInit(Global.filepath, pi, isValid, con, Global.cuid, model);
                installWritableCiKuAsync(con);


            } catch (UnsatisfiedLinkError error) {

            }
        }
    }

    /**
     * 处理载入内置的安装文件
     * 
     * @param con context
     */
    private final void installWritableCiKuAsync(final Context con) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] installFiles = con.getResources().getStringArray(R.array.INSTALLFILES);
                    if (installFiles != null) {
                        copyToDataFiles(con, installFiles);
                        // 删除安装文件
                        for (String file : installFiles) {
                            con.deleteFile(file);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        }).start();

    }


    /**
     * 删除覆盖安装之后的某些临时词库（目前只有5.4版本新加的 羊年祝福）
     */
    public final void close() {
        synchronized (Global.core) {
            PlFlush();
            PlClose();
        }
    }

    private final boolean loadCiKu(Context con, int cikuType, int cikuSize, int cikuCount) {
        // 初始化内核
        boolean isValid = false;
        if (PlCiKuAlloc(cikuSize, cikuType)) /** 1. 申请Cz的数据内存段 */
        {
            /** 2. 拷贝Cz的数据内容 */
            InputStream is = null;
            byte[] data = new byte[8192]; // 8K
            int off = 0;
            boolean loadError = false;

            try {
                int czIdx = 0;
                String fileName = Global.buildFileName("ipt/" + czIdx);
                is = con.getAssets().open(fileName);
                czIdx += 1;
                while (is != null) {
                    if (is.available() > 0) {
                        int size = is.read(data);
                        if (size > 0) {
                            if (!PlCiKuAppend(data, off, size, cikuType)) {
                                loadError = true;
                                break;
                            }
                            off += size;
                        }
                    } else {
                        is.close();
                        is = null;
                        if (czIdx < cikuCount) {
                            fileName = Global.buildFileName("ipt/" + czIdx);
                            is = con.getAssets().open(fileName);
                            czIdx += 1;
                        }
                    }
                }
            } catch (Exception e) {
                loadError = true;
                // if (Macro.IS_DEBUG)
                {
                    e.printStackTrace();
                }
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        // if (Macro.IS_DEBUG)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                is = null;
                data = null;
            }

            /** 3. 验证Cz段内容是否正确 */
            isValid = !loadError && PlCiKuCheck(off, cikuType);
            // long i2 = System.currentTimeMillis();
            // System.err.println("copy cost=" + (i2 - i1) + "#" +
            // isValidCz);
        }
        return isValid;
    }


    public final int PlGetStrByCandType(CoreString cs, int id) {

        // 如果是五笔输入方式并且设置项开启“五笔编码提示”，则传入candType的类型为GETTYPE_CAND_WB_TIP
        return PlGetStrByCandType(cs, id, false);

    }

    /**
     * 如果是五笔输入方式，根据@param isForceOpenHint判断是否是否开启五笔编码提示; 如果不是五笔，则忽略isForceOpenHint参数，获取查询到的候选字或候选词
     * 
     * @param cs 此处 将使用Java的反射机制，返回查询到的字或词
     * @param id 候选字或候选词的位置，一般 从0开始到PlCount
     * @param isForceOpenWbHint 是否强行开启五笔编码提示进行查询
     * @return 候选字或候选词的类型
     */
    public final int PlGetStrByCandType(CoreString cs, int id, boolean isForceOpenWbHint) {

        // 检查是否无五笔输入方式,否则不认为是开启五笔编码提示
        if (isForceOpenWbHint) {
            isForceOpenWbHint = false;// (Global.imeserv.inputStat.inputType == InputStatMac.TYPE_WB);
        }

        return (isForceOpenWbHint) ? PlGetStr(cs, id, ImeCore.GETTYPE_CAND_WB_TIP) : PlGetStr(cs, id,
                ImeCore.GETTYPE_CAND);

    }

    private static String getFilesPath(String oldUzBinName) {
        return "/data/data/" + pkgName + "/files/" + oldUzBinName;
    }

    public static final int[] copyToDataFiles(Context con, String[] filenames) {
        File f;
        byte[] data;
        FileOutputStream fos = null;
        int count = filenames.length, size;
        String[] path = new String[count];
        int[] filesize = new int[count];

        for (int i = 0; i < count; i++) {
            if (filenames[i].equals("iptwt_20151202.bin")) {
                continue;
            }
            path[i] = getFilesPath(filenames[i]);
            f = new File(path[i]);

            if (f.exists()) {
                filesize[i] = (int) f.length();

                if (filesize[i] > 0) {
                    continue;
                }
            }

            try {
                data = load(con, "ipt/" + filenames[i]);
                if (null != data) {
                    filesize[i] = data.length;
                    fos = con.openFileOutput(filenames[i], 0);
                    fos.write(data);
                } else {
                    filesize[i] = 0;
                }
            } catch (Exception e) {

                filesize[i] = 0;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {

                } finally {
                    fos = null;
                }
            }
        }
        return filesize;
    }

    /**
     * 閿熸枻鎷峰簲閿熺煫纰夋嫹assets鐩綍閿熸枻鎷烽敓楗衡増闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹鍊奸敓鏂ゆ嫹閿熺殕锟�
     * 
     * @param con
     * @param filename
     * @return
     */
    public final static byte[] load(Context con, String filename)
    {
        try
        {
            InputStream is = con.getAssets().open(filename);
            if (is == null)
            {
                return null;
            }
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            is = null;
            // System.out.println("loadAssets :: " + filename + " # " +
            // buffer.length);
            return buffer;
        } catch (Exception e)
        {
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
