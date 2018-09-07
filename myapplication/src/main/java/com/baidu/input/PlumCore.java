package com.baidu.input;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;

import com.baidu.input.pub.CoreString;


public abstract class PlumCore {
    // 内核输入定义
    public final static byte CORE_NONE = 0;
    public final static byte CORE_PY = 1;
    public final static byte CORE_EN = 2;
    public final static byte CORE_BH = 3;
    public final static byte CORE_WB = 4;
    public final static byte CORE_DEF = 5;
    public final static byte CORE_SYM = 6;
    public final static byte CORE_HW = 9;
    public final static byte CORE_ZHUYIN = 10;// modified for zhuyin-cangjie
    public final static byte CORE_CANGJIE = 11;// modified for zhuyin-cangjie
    public final static byte CORE_PY_EDIT = 12;

    public final static int SET_CONFIG = 1;
    public final static int GET_CONFIG = 2;
    // 模糊音个数为32个，暂未用到的地方，以0填充即可
    public final static int SET_MOHU = 3;
    public final static int GET_MOHU = 4;
    public final static int SET_SHUANGPIN = 5;
    public final static int GET_SHUANGPIN = 6;
    public final static int SET_HW_CONFIG = 7;
    public final static int GET_HW_CONFIG = 8;

    public final static int USERWORDS_EDIT_ADJUST = 0;
    public final static int USERWORDS_EDIT_DELETE = 1;

    public final static int GETTYPE_NULL = -1;
    public final static int GETTYPE_CAND = 0;// //普通候选词///////////////
    public final static int GETTYPE_LIST = 1;// //拼音列表(或 笔画筛选 等)/////
    public final static int GETTYPE_HOTSYM = 2; // /<常用符号(目前不支持)
    public final static int GETTYPE_CAND_ORG = 3; // /<普通候选词原始形态(无繁简转换或英文大小写转换)
    public final static int GETTYPE_CAND_WB_TIP = 4; // <中文候选词(带五笔编码提示)
    public final static int GETTYPE_PY_LIST = 5; // /<拼音列表
    public final static int GETTYPE_BH_LIST = 6; // 笔画筛选列表
    public final static int GETTYPE_USWORD = 100; // //自造词.//////////////////

    public final static int SEARCHKEY_EN = 20;
    /** 查询所有中文词 */
    public static final int SEARCHKEY_ALL_CH = 30;

    public final static byte CELLVER_POP = 0;
    public final static byte CELLVER_SYS = 1;

    public final static byte PHRASE_EDIT = 1;
    public final static byte PHRASE_DEL = 2;
    public final static byte PHRASE_ADD = 3;
    public final static byte PHRASE_REMOVE = 4;

    public final static byte MATCHINFO_WORDCNT = 0;
    public final static byte MATCHINFO_CODECNT = 1;
    public final static byte MATCHINFO_ZI_ID = 2;

    /** 需要调频的词压栈(idx 表示要进行压栈候选词索引) */
    public final static int CMD_ADJUST_PUSH = 1;
    /** 需要调频的词弹出(退格的时候才用, idx 表示弹出的词的个数,idx=0表示弹出所有词(清空栈)) */
    public final static int CMD_ADJUST_POP = 2;
    /** 对栈内元素执行调调频操作 (此时:设定idx = 0就可以了) */
    public final static int CMD_ADJUST_COMMIT = 3;
    /** 删除词(idx 表示要进行删除的候选词索引)，支持中英文，英文注意大小写 */
    public final static int CMD_ADJUST_DELETE = 4;
    /** 上屏后,对已经压栈的词(但还没有commit),以字为单位做退格操作,idx 表示退格的字的个数 */
    public final static int CMD_ADJUST_BACKSPACE = 5;
    /** 清除逐词输入优化状态(面板弹起,输入标点,等句子中断行为 */
    public final static int CMD_PREDICT_CLEAN = 6;
    /** 查询当前逐词前一个词的长度 */
    public final static int CMD_PREDICT_PREWORD_LEN = 7;
    /** 查询当前调频栈的PUSH次数 */
    public final static int CMD_STACK_ITEM_CNT = 8;
    /** 查询当前栈里面,某个第idx个元素的长度.(没有这个元素就返回0) */
    public final static int CMD_STACK_ITEM_LEN = 9;
    /** 判断是否可以删除.(返回值>0 时,才表示可以删除) */
    public final static int CMD_IS_DELABLE = 20;
    /** 判断是否存在系统词.(返回值>0 时,才表示存在系统词, = 0和<0 分别表示不存在和出现错误.) */
    public final static int CMD_IS_SYSWORD = 21;
    /** 一次完整的查询结束，输入码已经被无法复原的清除掉的时候，使用该命令做一次清理,无法复原的意思是，候选词已经上屏可以调用联想查询的时候，或者左划清除所有输入 */
    public final static int CMD_INPUT_CLEAN_UP = 10;
    /** 清除list的选中状态 */
    public final static int CMD_LIST_CLEAN = 30;
    /** 选中当前拼音list中的第idx个拼音压栈 */
    public final static int CMD_PY_LIST_PUSH = 31;
    /** 将当前栈顶的拼音弹出 */
    public final static int CMD_PY_LIST_POP = 32;
    /** 选中当前笔画list中的第idx个笔画压栈 */
    public final static int CMD_BH_LIST_PUSH = 33;
    /** 将当前栈顶的笔画弹出 */
    public final static int CMD_BH_LIST_POP = 34;
    /** 返回当前拼音list缓存长度 */
    public final static int CMD_PY_LIST_CACHE_LEN = 35;
    /** 选择当前的list查询模式，中文或者英文，idx = 1时代表英文，idx = 0时代表中文 */
    public final static int CMD_LIST_FIND_MODE = 36;
    /** 获取相应联系人个数 */
    public final static int CMD_CONTACT_CNT = 40;
    /** 执行联想查询. */
    public final static int CMD_FIND_LIAN = 41;
    /** 使用上次的条件重新查询一次 */
    public final static int CMD_FIND_REFRESH = 42;
    /** 清除手写状态 */
    public final static int CMD_HW_CLEAN = 43;
    /** 删除某个词对应联系人的信息 */
    public final static int CMD_CONTACT_DEL = 44;
    /** 删除某个词对应联系人的信息并恢复默认词频 */
    public final static int CMD_CONTACT_RESTORE_FREQ = 45;
    /** 删除某个词对应联系人以及对应的自造词 */
    public final static int CMD_CONTACT_DEL_ALL = 46;
    /** 清除整句出表情的状态 */
    public final static int CMD_SENTENCE_LIAN_CLEAN = 50;
    /**
     * 清除英文list的选中状态 请勿使用该接口，应当使用CMD_INPUT_CLEAN_UP
     */
    public final static int CMD_EN_LIST_CLEAN = 60;
    /** 选中当前英文list中的第idx个英文压栈,当选中的是“数字”时，返回1，其他返回0； */
    public final static int CMD_EN_LIST_PUSH = 61;
    /** 将当前栈顶的英文弹出 // 暂不实现 */
    public final static int CMD_EN_LIST_POP = 62;
    /** 返回当前英文list缓存长度 */
    public final static int CMD_EN_LIST_CACHE_LEN = 63;
    /** 手写回退 */
    public static final int CMD_HW_BACKSPACE = 64;
    // /////////////////////////////////输入码操作标记INPUT_FLAG////////////////////////////
    // //////////////////////////////用于ipt_query_set_encase的参数////////////////////////////
    /** 正常状态 */
    public final static int INPUT_FLAG_NORMAL = 0x00;
    /** 强制大写，或是shift键被按下 */
    public final static int INPUT_FLAG_UPPER_CASE = 0x01;
    /** 强制小写 */
    public final static int INPUT_FLAG_LOWER_CASE = 0x02;
    /** 上划精确输入 */
    public final static int INPUT_FLAG_PRECISE = 0x04;

    // ////////////////////////////////手写查询范围HW_FIND_RANGE/////////////////////////
    // ////////////////////////////////用于手写查询范围设置
    /** 常用字 */
    public final static int HW_FIND_RANGE_CH_COMMON = (0x01);
    /** 生僻字 */
    public final static int HW_FIND_RANGE_CH_RARE = (0x02);
    /** 偏旁部首 */
    public final static int HW_FIND_RANGE_CH_RADICAL = (0x04);
    /** 数字 */
    public final static int HW_FIND_RANGE_NUM = (0x08);
    /** 英文小写 */
    public final static int HW_FIND_RANGE_EN_LOWER = (0x10);
    /** 英文大写 */
    public final static int HW_FIND_RANGE_EN_UPPER = (0x20);
    /** 常用标点 */
    public final static int HW_FIND_RANGE_PUN_COMMON = (0x40);
    /** 扩展标点 */
    public final static int HW_FIND_RANGE_PUN_EXT = (0x80);
    /** 常用符号 */
    public final static int HW_FIND_RANGE_SYM_COMMON = (0x0100);
    /** 扩展符号 */
    public final static int HW_FIND_RANGE_SYM_EXT = (0x0200);
    /** 特殊符号1 */
    public final static int HW_FIND_RANGE_SYM_RARE_G1 = (0x0400);
    /** 特殊符号2 */
    public final static int HW_FIND_RANGE_SYM_RARE_G2 = (0x0800);
    /** 特殊符号3 */
    public final static int HW_FIND_RANGE_SYM_RARE_G3 = (0x1000);
    /** 不包括偏旁部首的汉字 */
    public final static int HW_FIND_RANGE_CH_NM = (HW_FIND_RANGE_CH_COMMON | HW_FIND_RANGE_CH_RARE);
    /** 英文 */
    public final static int HW_FIND_RANGE_EN = (HW_FIND_RANGE_EN_LOWER | HW_FIND_RANGE_EN_UPPER);
    /** 标点 */
    public final static int HW_FIND_RANGE_PUN = (HW_FIND_RANGE_PUN_COMMON | HW_FIND_RANGE_PUN_EXT);
    /** 符号 */
    public final static int HW_FIND_RANGE_SYM = (HW_FIND_RANGE_SYM_COMMON | HW_FIND_RANGE_SYM_EXT
            | HW_FIND_RANGE_SYM_RARE_G1 | HW_FIND_RANGE_SYM_RARE_G2 | HW_FIND_RANGE_SYM_RARE_G3);
    /** 所有汉字 */
    public final static int HW_FIND_RANGE_CH_ALL = (HW_FIND_RANGE_CH_NM | HW_FIND_RANGE_CH_RADICAL);
    /** 生僻字+偏旁部首 */
    public final static int HW_FIND_RANGE_CH_RARE_RADICAL = (HW_FIND_RANGE_CH_RARE | HW_FIND_RANGE_CH_RADICAL);
    /** 英文+数字 */
    public final static int HW_FIND_RANGE_EN_NUM = (HW_FIND_RANGE_EN | HW_FIND_RANGE_NUM);
    /** 标点+符号 */
    public final static int HW_FIND_RANGE_PUN_SYM = (HW_FIND_RANGE_PUN | HW_FIND_RANGE_SYM);

    /** 虚框范围 */
    public final static int HW_FIND_RANGE_DASH = (HW_FIND_RANGE_EN_NUM | HW_FIND_RANGE_PUN_SYM);
    /** 所有范围 */
    public final static int HW_FIND_RANGE_ALL = (HW_FIND_RANGE_CH_ALL | HW_FIND_RANGE_DASH);

    // ////////////////////////////////
    // /手写输入选项(手写输入时使用)
    /** 单字输入 */
    public final static int HW_INPUT_TYPE_HZ = 1;
    /** 叠写输入 */
    public final static int HW_INPUT_TYPE_REDUP = 2;
    /** 连写输入 */
    public final static int HW_INPUT_TYPE_NM = 4;
    /** 英文数字输入 */
    public final static int HW_INPUT_TYPE_EN_NUM = 8;

    /*********************************/
    /*********************************/
    /* 内核初始化、配置、释放相关的接口 */
    /*********************************/
    /*********************************/

    // /////////////////////////////////输入码错误标记////////////////////////////
    /** 输错 */
    public final static byte PY_IEC_FLAG_TE = 0x1;
    /** 少输 */
    public final static byte PY_IEC_FLAG_LE = 0x2;
    /** 多输 */
    public final static byte PY_IEC_FLAG_ME = 0x4;
    /** 交换输入 */
    public final static byte PY_IEC_FLAG_SE = 0x8;
    /** 少输，在最前 */
    public final static byte PY_IEC_FLAG_POS = 0x10;
    /** 少输，在最前 */
    public final static byte PY_IEC_FLAG_LE_PRE = PY_IEC_FLAG_LE | PY_IEC_FLAG_POS;

    public final static int CIKU_TYPE_CZ = 0;
    public final static int CIKU_TYPE_GRAM = 1;
    
    
    /** 场景类型定义，没有场景 **/
    public static final int NO_CONTEXT_ID = 0;
    /** 场景类型定义，电商场景 **/
    public static final int CONTEXT_ID_MERCH = 0x1;
    /** 场景类型定义，应用市场场景 **/
    public static final int CONTEXT_ID_APP = 0x2;
    /** 场景类型定义，地理位置场景 **/
    public static final int CONTEXT_ID_LBS = 0x4;
    /** 场景类型定义，多媒体场景 **/
    public static final int CONTEXT_ID_MEDIA = 0x8;
    /** 场景类型定义，点评团购场景 **/
    public static final int CONTEXT_ID_GROUPON = 0x10;
    /** 场景类型定义，浏览器场景 **/
    public static final int CONTEXT_ID_BROWSER = 0x20;
    /** 场景类型定义，聊天软件场景 **/
    public static final int CONTEXT_ID_SNS = 0x40;
    /** 场景类型定义，音乐类场景 **/
    public static final int CONTEXT_ID_MUSIC = 0x80;
    /** 场景类型定义，B站类场景 **/
    public static final int CONTEXT_ID_BILI = 0x100;
    /** 场景类型定义，贴吧论坛场景 **/
    public static final int CONTEXT_ID_TIEBA = 0x200;
    /** 场景类型定义，交友类场景 **/
    public static final int CONTEXT_ID_SOCIAL = 0x400;
    /** 场景类型定义，王者荣耀场景 **/
    public static final int CONTEXT_ID_KING_GLORY = 0x800;

    /** 当list中选中“英文”时传给内核的值,PlKpAppendPoint穿的pos参数常量,pos只能为0以及LIST_ENGLISH_SELECT的值 */
    public static final int LIST_ENGLISH_SELECT = 127;

    /** 云输入触发词 */
    public static final int EX_EX_CAND_FLAG_WHITE = 0x01;
    /** 云输入触发词，只能够触发联想，没有该标记位代表有候选 */
    public static final int EX_EX_CAND_FLAG_WHITE_LIAN = 0x02;
    /** 云输入触发词，纯文本类型，没有该标记位代表非纯文本类型 */
    public static final int EX_EX_CAND_FLAG_WHITE_TEXT = 0x4;
    /** 云缓存词，且与本地重复 */
    public static final int EX_EX_CAND_FALG_CLOUD_DUPLICATE = 0x1000;
    
    /** 支持CPU的neom指令,{@link #PlSetCpuMsg(String)} */
    public static final int CPU_SUPPORT_NEOM = 0;

    /**
     * 申请词库的native地址空间
     *
     * @param size     数据段的字节数
     * @param cikuType 词库类型
     * @return 成功返回true，失败返回false
     */
    protected native boolean PlCiKuAlloc(int size, int cikuType);

    /**
     * 增量载入词库
     *
     * @param data     内容数据段
     * @param off      当前数据在实际词库内容的偏移量
     * @param size     当前数据段的有效字节数
     * @param cikuType 词库类型
     * @return 成功返回true，失败返回false
     */
    protected native boolean PlCiKuAppend(byte[] data, int off, int size, int cikuType);

    /**
     * 验证预载入的Cz词库是否正确
     *
     * @param copySize 拷贝的大小
     * @param cikuType 词库类型
     * @return 正确返回true，错误返回false
     */
    protected native boolean PlCiKuCheck(int copySize, int cikuType);

    /**
     * 初始化 载入词库和字库
     * 
     * @param fileNames
     *            文件名
     * @param pi
     *            包信息
     * @param isCzValid
     *            预载入的Cz词库是否正确
     * @param context
     *            context
     * @param cuid
     *            cuid
     * @param model
     *            手机型号
     * @return 成功返回true，失败返回false
     */
    protected native boolean PlInit(String[] fileNames, PackageInfo pi, boolean isCzValid, Context context, String cuid, String model);

    /**
     * 刷新内核，重载手写库
     * 
     * @param fileName 内核词库数据文件, 指定内核需要刷新的数据文件的位置，若文件不存在不刷新；如果为null表示只卸载
     * @return 0代表刷新成功; 返回值<0代表刷新失败或没有刷新
     */
    public native int PlHandWritingRefresh(String fileName);
    
    /**
     * 刷新内核，重载二元库gram.bin
     *
     * @param fileName 内核词库数据文件, 指定内核需要刷新的数据文件的位置，若文件不存在不刷新；如果为null表示只卸载
     * @return 0代表刷新成功; 返回值<0代表刷新失败或没有刷新
     */
    public native int PlGramRefresh(String fileName);
    /**
     * 激活
     * 
     * @param activeNow
     *            是否立即激活，否则延期激活
     * @return 
     */
    public native void PlActivate(boolean activeNow);

    /**
     * 刷新内核，重载某个内核文件（目前只支持重载二元库gram.bin）
     *
     * @return 0代表刷新成功; 返回值<0代表刷新失败或没有刷新
     */
    public native int PlDefGramRefresh();

    /**
     * 卸载词库和字库
     * 
     * @return 成功返回true，失败返回false
     */
    public native boolean PlClose();


    /**
     * 将更改保存到文件
     * 
     * @return
     */
    public native void PlFlush();

    /**
     * 获得内核版本号
     * 
     * @return 内核词库版本号
     */
    public native int PlGetCoreVersion();
    
    
    /**
     * 
     * 获得SDK的版本号
     * 
     * @return 例如："1.0.0.2"
     */
    public native String PlGetSDKVersion();

    /**
     * 设置输入状态
     * 
     * @param params 输入状态拼接顺序：（48 byte） 字频；词频；常用字/生僻字；只显示字，默认； 整句；中英混输(0:关闭,1:开启,2:开启,但屏蔽单个字母)；简繁转换；笔画 下 字形优先；
     *            开启五笔拼音；开启双拼；不显示双拼原始输入码；开启简拼二元整句； 英文排序，默认为BYFREQ=0，BYLEN=1，BYABC=2；英文大小写，默认为NORMAL=0， FIRST=1，
     *            ALL=2；reserved；reserved； 智能纠错；个性短语(0=关闭, 1-9 = 个性短语的默认出现位置)；是否开启自动实时保存；符号联想开关(暂未开通该功能)；
     *            逐词输入优化;中英整句混输;reserved;reserved 6个四个字节整数； 模糊音拼接顺序：（16 byte） ch;sh;zh;k; f;n;ang;eng;
     *            ing;iang;uang;reserved; 四个字节整数 双拼方案：（64 byte） 声母[24];韵母[33];reserved[3]； 四个字节整数；
     * 
     * @param type 1：设置输入状态 2：读得输入状态 3：设置模糊音 4：读取模糊音 5：设置双拼方案 6：读取双拼方案
     * @return 成功返回true， 失败返回false
     */
    public native boolean PlConfig(byte[] params, int type);


    /**
     * 按照输入的搜索词库和字库
     * 
     * @param input 输入
     * @param pos 0 -- 默认   64 -- 最末    127 -- 英文
     * @param type 选项 （1:拼音, 2：英文, 3：笔画, 4：五笔,5：自定义, 6：符号, 7:个性短语 , 8:联想 ）
     * @param contextId 场景类别Id
     * @return 错误代码
     */
    public native int PlFind(byte[] input, int pos, int type, int contextId);

    /**
     * 词语联想
     * 
     * @param zids 需要联想的内容:每个字的ID组成的数组 或者unicode码组，以‘\0’结尾
     * @param len 需要联想的内容的长度：字长度 或者0
     * @return 错误代码
     */
    public native int PlFindLian(char[] zids, int len);

    /**
     * 通过zid对手写联想查询
     * 
     * @param zids 需要联想的内容:每个字的ID组成的数组 或者unicode码组，以‘\0’结尾
     * @param len 需要联想的内容的长度：字长度 或者0
     * @return 0代表查找成功; 返回值<0代表查找失败
     */
    public native int PlFindLianbyHW(char[] zids, int len);

    /**
     * 清楚查询（Find和FindLian）的缓存
     */
    public native void PlClean();

    /**
     * 获得当次查询的候选字或列表或常用符号的个数
     * 
     * @param type 0：普通候选词 1：拼音列表（或笔画筛选列表） 2：常用符号
     * @return 个数
     */
    public native int PlCount(int type);

    /**
     * 获取查询到的候选字或候选词
     * 
     * @param cs 此处 将使用Java的反射机制，返回查询到的字或词
     * @param id 候选字或候选词的位置，一般 从0开始到PlCount
     * @param type 选项 （1:普通候选词, 2：拼音列表（或笔画筛选列表）, 3：常用符号）
     * @return 候选字或候选词的类型 （）
     */
    public native int PlGetStr(CoreString cs, int id, int type);
    
    /**
     * 获取候选词扩展类型
     * 
     * @param id 候选字或候选词的位置，一般 从0开始到PlCount
     * @return 候选字或候选词的扩展类型 （0x1为云输入白名单）
     */
    public native int PlGetExFlag(int id);

    /**
     * 获取选中中文候选字字词的信息
     * 
     * @param id 候选列表中的索引
     * @return 返回选中中文候选字字词的信息（0:返回字的长度, 1:返回匹配的输入码的长度,2:每个字的编码）
     */
    public native int[] PlMatchInfo(int id);

    /**
     * 得到输入显示
     * 
     * @param id 选中的候选词的索引
     * @param info 选中的候选词的索引的长度
     * @return 返回输入显示
     */
    public native String PlGetShow(int id, byte[] info);

    /**
     * CMD_ADJUST_DELETE = 4,////删除词(idx 表示要进行删除的候选词索引)，支持中英文，英文注意大小写 CMD_IS_DELABLE = 20,////判断是否可以删除.(返回值>0 时,才表示可以删除)
     * CMD_IS_SYSWORD = 21,////判断是否存在系统词.(返回值>0 时,才表示存在系统词, = 0和<0 分别表示不存在和出现错误.) CMD_CONTACT_CNT = 40, ///获取相应联系人个数.
     * CMD_FIND_LIAN = 41,////执行联想查询.
     * 
     * @param id 候选词的索引
     * @param cmdType 动作的类型
     * @return CMD_ADJUST_DELETE：//返回1表示存在//返回0表示不存在//返回负数,表示出错. CMD_IS_DELABLE：返回1表示可以删除，返回0表示无法删除 CMD_IS_SYSWORD：返回值>0
     *         时,才表示存在系统词, = 0和<0 分别表示不存在和出现错误. CMD_CONTACT_CNT：返回联系人个数 CMD_FIND_LIAN：返回错误代码
     * 
     */
    public native int PlQueryCmd(int id, int cmdType);

    /*********************************/
    /*********************************/
    /* 用户词导入导出相关接口 */
    /*********************************/
    /*********************************/



    /**
     * 获得词库的版本
     *
     * @param type 0:流行词 1：系统词
     * @return inner_ver
     */
    public native int PlCellGetVer(int type);

    /**
     * 获得系统词库的SID
     *
     * @return
     */
    public native int PlCellGetSysVER();
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // 手写相关
    /**
     * 手写添加一笔,只能一笔不支持多笔
     * 
     * @param point_data — 手写数据以-1,0结尾
     * @param input_type — 手写类型（具体内容请参照HW_INPUT_TYPE）
     * @return 返回值<0表示失败,0表示成功
     */
    public native int PlHandWritingRecognizeAppand(short[] point_data, int input_type);

    /**
     * 获取手写版本号
     * 
     * @return 获取手写版本号
     */
    public native int PlHandWritingVersion();

    /**
     * 获取手写设置项
     * 
     * @return 手写设置项 {识别范围，每个字的最大匹配笔画数}
     */
    public native int[] PlHandWritingGetConfig();

    /**
     * 设置手写设置项
     * 
     * @param config 手写设置项 {识别范围，每个字的最大匹配笔画数}
     * @return 返回值<0表示失败,0表示成功
     */
    public native int PlHandWritingSetConfig(int[] config);

    /**
     * 手写数据压缩
     * 
     * @param candId 候选字idx
     * @return 手写压缩数据
     */
    public native byte[] PlHandWritingEncodePoints(int candId);



    /**
     * 设置部首过滤
     *
     * @param point_data 部首数据,单笔用(-1,0)隔离,结尾用(-1,-1)隔离
     */
    public native int PlHandWritingSetBsFilter(short[] points);
}
