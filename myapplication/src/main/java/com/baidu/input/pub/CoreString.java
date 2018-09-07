package com.baidu.input.pub;

//import com.baidu.input.PIConsts;

/**
 * 
 * @2013-10-21
 */
public final class CoreString
{
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////候选词属性定义////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////内核通用类型属性定义//////////////////////////////////////////
    /** 中文候选词属性定义 */
    private final static int EX_CAND_FLAG_CH = 0x1;
    /** 英文候选词属性定义 */
    private final static int EX_CAND_FLAG_EN = 0x2;
    /** 长句候选项属性定义 */
    private final static int EX_CAND_FLAG_CJ = 0x4;
    /** 保留 */
    private final static int EX_CAND_FLAG_RESERVED_1 = 0x8;
    /** 用户词属性定义（不包含细胞词, 即可以删除的词） */
    private final static int EX_CAND_FLAG_US = 0x10;
    /** 细胞词属性定义 */
    private final static int EX_CAND_FLAG_CELL = 0x20;
    /** 中文笔画或五笔输入词属性定义 */
    private final static int EX_CAND_FLAG_BW = 0x40;
    /** 中文联想词属性定义 */
    private final static int EX_CAND_FLAG_LX = 0x80;
    /** 输入码候选词属性定义 */
    private final static int EX_CAND_FLAG_EN_INPUT = 0x100;
    /** 长句候选项属性定义(带有英文单词结果) */
    private final static int EX_CAND_FLAG_CJ_CNEN = 0x200;
    /** 逐词输入词属性定义 */
    private final static int EX_CAND_FLAG_PRED = 0x400;
    /** 最高逐词输入词属性定义 */
    private final static int EX_CAND_FLAG_TOP_PRED = 0x800;
    /** 三维词库词属性定义(执行联想查询时或者长句带三维词库结果) */
    private final static int EX_CAND_FLAG_GRAM = 0x1000;
    /** 是否纠错候选标记 */
    private final static int EX_CAND_FLAG_IEC = 0x2000;
    /** 联系人词 */
    private final static int EX_CAND_FLAG_CONTACT = 0x4000;
    /** 上划输入造的特殊词 */
    private final static int EX_CAND_FLAG_EN_PRECISE = 0x8000;
    /** 个性短语属性定义 */
    private final static int EX_CAND_FLAG_PHRASE = 0x10000;
    /** 中英混合词定义 */
    private final static int EX_CAND_FLAG_MIXWORD = 0x20000;
    /** 卖萌词定义 */
    private final static int EX_CAND_FLAG_OTHERWORD = 0x40000;
    /** 拆字组合属性定义 */
    private final static int EX_CAND_FLAG_CHAIZI = 0x80000;

    // ///////////////////////////////////特殊类型属性定义////////////////////////////////////////////////////////
    /** 搜索词属性定义-- 中文、英文、长句、短语类通用 output内容为命中的搜索词 */
    private final static int EX_CAND_FLAG_SEARCH = 0x100000;
    /** 多媒体词属性定义 -- 中文、英文、长句、短语类通用 output格式为(1,2位指示media的类型(1定位，2图片，3录音，4随写，5涂鸦)， 3-结束 提示 */
    private final static int EX_CAND_FLAG_MEDIA = 0x200000;
    /** 排在第一个的纠错词  */
    private final static int EX_CAND_FLAG_TOP_IEC = 0x800000;
    // //////为了保证以后可以扩展，低24位每一个bit都是独立的含义，可以直接与操作判断，高八位用于做附加的属性定义，用等号做判断在做高八位判断时，记得对低24位清零////////////////////
    // ///////////////////////////////////特殊类型属性定义，当低24位都是0的时候，使用等号做判断(==)/////////////////////////////////////////////////////
    /** 符号属性定义 */
    private final static int EX_CAND_FLAG_SYM = (2 << 24);
    /** 旧版自定义输入法属性定义 */
    private final static int EX_CAND_FLAG_OLDDEF = (3 << 24);
    /** 符号联想属性定义 */
    private final static int EX_CAND_FLAG_SYM_LIAN = (4 << 24);
    /** 网址和邮箱符号属性定义 */
    private final static int EX_CAND_FLAG_FORM = (5 << 24);
    /** 固定首位词属性定义 */
    private final static int EX_CAND_FLAG_FIRST = (6 << 24);
    /** 表情联想属性定义 */
    private final static int EX_CAND_FLAG_EMOJI_LIAN = (7 << 24);
    /** 表情属性定义 */
    private final static int EX_CAND_FLAG_EMOJI = (8 << 24);
    /** 仓颉输入法属性定义 */
    private final static int EX_CAND_FLAG_CANGJIE = (9 << 24);
    /** 注音输入法属性定义 */
    private final static int EX_CAND_FLAG_ZY = (10 << 24);
    /** 歇后语词属性定义 */
    private final static int EX_CAND_FLAG_XIEHOUYU = (13<<24);
    /** 快速输入属性定义 */
    private final static int EX_CAND_FLAG_FAST_INPUT = (14 << 24);

    /** 颜文字联想属性定义 */
    private static final int EX_CAND_FLAG_YAN_LIAN = (20 << 24);
    /** 颜文字属性定义 */
    private static final int EX_CAND_FLAG_EMOTICON = (22 << 24);

    // ///////////////////////////////////多媒体类型子属性定义，当该类型是EX_CAND_FLAG_MEDIA的时候，使用等号做判断(==)///////////////////////////////////
    public final static int EX_CAND_FLAG_POS = (2 << 24); // /<定一下位吧
    public final static int EX_CAND_FLAG_PIC = (3 << 24); // /<秀一张图片吧
    public final static int EX_CAND_FLAG_SOUND = (4 << 24); // /<录一段声音吧
    public final static int EX_CAND_FLAG_WRITE = (5 << 24); // /<用笔记书写心情吧
    public final static int EX_CAND_FLAG_PEN = (6 << 24); // /<涂几笔吧

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////上层自定义类型属性定义//////////////////////////////////////////
    /** "联系人信息"词条 */
    public final static byte INDEX_CONTACT = -10;
    // /** 中英混输词 elseCode */
    // public final static byte INDEX_COMBO = -11;

    public final static byte INDEX_ZOMBIE = -12;
    /** 语音改错候选词 */
    public final static byte INDEX_VOICE_CORRECT = -13;
    // /** 多媒体联想结果 */
    // public final static byte INDEX_MULTIMEDIA = -14;
    /** 自定义的英文词 */
    public final static byte INDEX_ENCUST = -15;
    /** 自定义符号 */
    public final static byte INDEX_SYMCUST = -16;
    /** 云图标 */
    public final static byte INDEX_CLOUD_ICON = -17;
    /** 云输入的词 */
    public final static byte INDEX_CLOUD_WORD = -18;

    public final static byte INDEX_ASSIST_WORD = -19;
    /** input条中显示的候选词 */
    public final static byte INDEX_SUBMIT_WORD_WITH_INPUT = -20;
    /** 鲸鱼表情 */
    public final static byte INDEX_WHALE = -21;
    /** 颜文字 */
    public final static byte INDEX_CHARGROUP = -22;
    // /**安装的表情包的表情输入*/
    // public final static byte INDEX_EMOJI_PKG = -23;
    // /**默认的emoji表情输入*/
    // public final static byte INDEX_EMOJI_DEF = -24;
    /** 表情符 */
    public final static byte INDEX_CHARLINE = -25;
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    public final static short UIFLAG_CONTACT = 0x01;
    public final static short UIFLAG_PINYIN = 0x02;
    /** 默认的emoji表情输入 */
    public final static short UIFLAG_EMOJI_DEF = 0x04;
    /** 安装的表情包的表情输入 */
    public final static short UIFLAG_EMOJI_PKG = 0x08;
    /** 快速输入-符号 */
    public final static short UIFLAG_FAST_INPUT_SYMBOL = 0x10;
    /** 快速输入-日期，年月日 */
    public final static short UIFLAG_FAST_INPUT_DAY = 0x20;
    /** 快速输入-时间 */
    public final static short UIFLAG_FAST_INPUT_TIME = 0x40;
    /** 默认的颜文字输入 */
    public static final short UIFLAG_YAN_DEF = 0x400;
    // //////////////////////////////////////////////////////////////////////////////////////////////////

    public final static byte PINYIN_TEXT = 0;
    public final static byte PINYIN_LENS = 10;
    public final static byte PINYIN_TONE = 11;
    public final static byte PINYIN_YUNMU = 12;
    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 该词的内容
     * 
     * 另外的特殊情况会保存额外信息: 1.当coreString为INDEX_EMOJI_PKG类型时，存uid
     */
    public String value;

    /** 该词的序列号: >=0，内核中的序列号; <0，上层自定义的候选字 */
    public int index;

    /** 该词的内核属性 */
    private int flag;
    /** 该词的上层属性 */
    private int flag4UI;
    /** 生僻词注音 */
    private byte[] pinyin;
    /** 模糊音、多音词注音 */
    public PinyinHolder[] chcor;

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    public CoreString()
    {
        value = null;
        flag = 0;
        index = -1;
        flag4UI = 0;
        pinyin = null;
        chcor = null;
    }

    /**
     * 内核回调函数，供内核使用，不能删除
     * 
     * @param v
     *            候选字的内容
     * @param f
     *            候选字的flag
     */
    public void set(String v, int f)
    {
        value = v;
        flag = f;
        index = -1;
        flag4UI = 0;
        pinyin = null;
        chcor = null;
    }

    /**
     * 候选字是否有效
     * 
     * @return
     */
    public final boolean isAvailable()
    {
        if (value == null || value.length() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 是否占用一个候选字位
     * 
     * @return
     */
    public final boolean isOccupted()
    {
        if (value == null || value.length() == 0)
        {
            if (index >= 0)
            {// 联系人或云输入
                return false;
            }
        }
        return true;
    }

    public final void copy(CoreString str)
    {
        if (str != null)
        {
            // System.out.println("copy test :: "+str.isAvailable());
            value = str.value;
            index = str.index;
            flag = str.flag;
            flag4UI = str.flag4UI;
            pinyin = str.pinyin;
            chcor = str.chcor;
        }
    }

    /**
     * 是否可以删除或恢复这个词
     * 
     * @return
     */
    public final boolean canDel()
    {
        int result = 0;
//        if (Macro.IS_CORE_ON)
//        {
//            synchronized (Global.core)
//            {
//                result = Global.core.PlQueryCmd(index, ImeCore.CMD_IS_DELABLE);
//            }
//        }
        return (result > 0);
    }

    /**
     * 
     * 只在Global.core.PlGetStr(cs, i, ImeCore.GETTYPE_LIST)的特殊情况下使用 为Global.keepFlags赋值
     * 
     * @return
     */
    public boolean isListItemLock()
    {
        return flag == 1;
    }

    /**
     * 是否是可以被限制的类型
     * 
     * @return
     */
    public final boolean canLimit()
    {
        return (flag & EX_CAND_FLAG_CH) != 0 || isEmoji() || (index == INDEX_CLOUD_ICON) || (index == INDEX_SYMCUST) || isSym() || isSymLX();
    }

    /**
     * 候选字是否是英文单词
     * 
     * @return
     */
    public final boolean isEn()
    {
        return (flag & EX_CAND_FLAG_EN) != 0 || index == INDEX_ENCUST;
    }

    /**
     * 是否是联想的结果
     * 
     * @return
     */
    public final boolean isLX()
    {
        return (flag & EX_CAND_FLAG_LX) != 0 || isSymLX();
    }

    /**
     * 是否是搜索关键词或者是搜索入口
     * 
     * @return
     */
    public final boolean isSearch()
    {
        return ((flag & EX_CAND_FLAG_SEARCH) != 0);
    }

    /**
     * 是否是搜索入口
     * 
     * @return
     */
    public final boolean isMultimedia()
    {
        return ((flag & EX_CAND_FLAG_MEDIA) != 0);
    }

//    /**
//     * 如果是搜索入口，获得多媒体入口的类型，与内核定义的多媒体类型做映射 mediaType对应1:位置，2：图片 3：录音 4：涂鸦 5：随写 实际多媒体定义的类型对应1:位置，2：图片 3：录音 4：二维码 5：涂鸦 6：随写
//     * 
//     * @return
//     */
//    public final byte getMultimediaType()
//    {
//        byte type = PIConsts.MMTYPE_NONE;
//        if (isMultimedia())
//        {
//            int multiType = (int) (flag & 0xFF000000);
//            switch (multiType)
//            {
//                case EX_CAND_FLAG_POS:
//                    type = PIConsts.MMTYPE_GEO_LOCA;
//                    break;
//                case EX_CAND_FLAG_PIC:
//                    type = PIConsts.MMTYPE_PICTURE;
//                    break;
//                case EX_CAND_FLAG_SOUND:
//                    type = PIConsts.MMTYPE_RECORD;
//                    break;
//                case EX_CAND_FLAG_WRITE:
//                    type = PIConsts.MMTYPE_WRITE;
//                    break;
//                case EX_CAND_FLAG_PEN:
//                    type = PIConsts.MMTYPE_PAINT;
//                    break;
//                default:
//                    break;
//            }
//        }
//        return type;
//    }

    /**
     * 是否是最优逐词
     * 
     * @return
     */
    public final boolean isTopPred()
    {
        return (flag & EX_CAND_FLAG_TOP_PRED) != 0;
    }

    /**
     * 
     * 是否是细胞词库
     * 
     * @return 是否是细胞词库
     */
    public final boolean isCell()
    {
        return (flag & EX_CAND_FLAG_CELL) != 0;
    }
    
    /**
     * 是否是最优纠错
     */
    public final boolean isTopKp()
    {
        return index == 0 && (flag & EX_CAND_FLAG_TOP_IEC) != 0;
    }
    
    /**
     * 是否是最优多音词
     */
    public final boolean isTopChChor()
    {
        return index == 0 && (chcor != null && chcor.length > 0);
    }

    /**
     * 是否是联系人
     */
    public final boolean isContact()
    {
        return (flag & EX_CAND_FLAG_CONTACT) != 0;
    }

    /**
     * 是否是二元整句或二元中英整句
     * 
     * @return
     */
    public final boolean isGramCJ()
    {
        return (flag & EX_CAND_FLAG_CJ) != 0 && (flag & EX_CAND_FLAG_GRAM) != 0;
    }

    /**
     * 是否是笔画、五笔或者自定义的候选词
     * 
     * @return
     */
    public final boolean isBW_OldDef()
    {
        return (flag & EX_CAND_FLAG_BW) != 0 || isSpecialCoreStringType(EX_CAND_FLAG_OLDDEF);
    }

    /**
     * 是否是符号联想
     * 
     * @return
     */
    public final boolean isSym()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_SYM);
    }

    /**
     * 是否是符号联想
     * 
     * @return
     */
    public final boolean isSymLX()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_SYM_LIAN);
    }

    /**
     * 是否是个性短语
     * 
     * @return
     */
    public final boolean isPhrase()
    {
        return (flag & EX_CAND_FLAG_PHRASE) != 0;
    }

    /**
     * 是否是颜文字
     *
     * @return
     */
    public final boolean isYan() {
        return isSpecialCoreStringType(EX_CAND_FLAG_EMOTICON) || hasFlag(UIFLAG_YAN_DEF);
    }

    /**
     * 是否联想颜文字
     *
     * @author chengyuan02
     * @return
     */
    public final boolean isYanLX() {
        return isSpecialCoreStringType(EX_CAND_FLAG_YAN_LIAN);
    }

    /**
     * 是否是emoji表情
     * 
     * @return
     */
    public final boolean isEmoji()
    {
        return hasFlag(UIFLAG_EMOJI_DEF) || hasFlag(UIFLAG_EMOJI_PKG);
    }

    public final void setFlag(short flag)
    {
        this.flag4UI |= flag;
    }

    public final void removeFlag(short flag)
    {
        flag4UI = flag4UI & (~flag);
    }

    public final boolean hasFlag(short flag)
    {
        return ((flag4UI & flag) != 0);
    }

    public final void setPinyin(byte[] data)
    {
        if (data != null && data.length > 0)
        {
            this.setFlag(UIFLAG_PINYIN);
            pinyin = new byte[14];
            byte i;
            for (i = 0; i < 6; i++)
            {
                if (data[i] >= '0' && data[i] <= '9')
                {
                    break;
                }
                else
                {
                    pinyin[i + 2] = data[i];
                }
            }
            pinyin[0] = '(';
            pinyin[1] = 0x20;
            pinyin[i + 2] = 0x20;
            pinyin[i + 3] = ')';
            // System.out.println("PINYIN 1 :: " +(char)pinyin[0]+" # "+(char)pinyin[1]+" # "+(char)pinyin[2]+" # "+(char)pinyin[3]+" # "+(char)pinyin[4]+" # "+(char)pinyin[5]+" # "+(char)pinyin[6]);

            pinyin[PINYIN_LENS] = (byte) (i + 4);
            if (i + 2 <= data.length)
            {

                pinyin[PINYIN_TONE] = (byte) (data[i] & 0xF);
                i++;
                pinyin[PINYIN_YUNMU] = (byte) ((data[i] & 0xF) + 2);
            }
            // System.out.println("PINYIN 2 :: " + new String(pinyin, 0, pinyin[PINYIN_LENS]) +" # "+pinyin[PINYIN_LENS]+ " # " + pinyin[PINYIN_TONE] + " # " + pinyin[PINYIN_YUNMU]);

        }
    }

    public final byte[] getPinyin()
    {
        return pinyin;
    }
    
    /**
     * isCsFastInput 是否属于快速输入
     * 
     * @author zhongyangyibing
     * @return
     */
    public final boolean isCsFastInput()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_FAST_INPUT);
        // return false;
    }

    public final boolean isCsEmoji()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_EMOJI);
    }
    
    public final boolean isCsXHY()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_XIEHOUYU);
    }

    public final boolean isCsEmojiLX()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_EMOJI_LIAN);
    }

    private final boolean isSpecialCoreStringType(int type)
    {
        int lowFlag = flag & 0x00FFFFFF;
        return (type == flag) && (lowFlag == 0);
    }
    
    public class PinyinHolder
    {
        /** 汉字 */
        public String hz;
        /** 拼音 */
        public String py;
        /** 音节所在的位置 */
        public int idx;
        /** 音节的声调 */
        public int tone;

        PinyinHolder()
        {
            hz = null;
            py = null;
            idx = -1;
            tone = -1;
        }
        // public String toString()
        // {
        // return "@KpMohuStrItem:" + hz + "#" + py + "#" + idx + "#" + tone;
        // }
    }
    
    public String toString()
    {
        return "val=" + value + ", index=" + index + ", flag=" + Integer.toHexString(flag);
    }
}
