package com.baidu.input.pub;

//import com.baidu.input.PIConsts;

/**
 * 
 * @2013-10-21
 */
public final class CoreString
{
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////��ѡ�����Զ���////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////�ں�ͨ���������Զ���//////////////////////////////////////////
    /** ���ĺ�ѡ�����Զ��� */
    private final static int EX_CAND_FLAG_CH = 0x1;
    /** Ӣ�ĺ�ѡ�����Զ��� */
    private final static int EX_CAND_FLAG_EN = 0x2;
    /** �����ѡ�����Զ��� */
    private final static int EX_CAND_FLAG_CJ = 0x4;
    /** ���� */
    private final static int EX_CAND_FLAG_RESERVED_1 = 0x8;
    /** �û������Զ��壨������ϸ����, ������ɾ���Ĵʣ� */
    private final static int EX_CAND_FLAG_US = 0x10;
    /** ϸ�������Զ��� */
    private final static int EX_CAND_FLAG_CELL = 0x20;
    /** ���ıʻ��������������Զ��� */
    private final static int EX_CAND_FLAG_BW = 0x40;
    /** ������������Զ��� */
    private final static int EX_CAND_FLAG_LX = 0x80;
    /** �������ѡ�����Զ��� */
    private final static int EX_CAND_FLAG_EN_INPUT = 0x100;
    /** �����ѡ�����Զ���(����Ӣ�ĵ��ʽ��) */
    private final static int EX_CAND_FLAG_CJ_CNEN = 0x200;
    /** �����������Զ��� */
    private final static int EX_CAND_FLAG_PRED = 0x400;
    /** ��������������Զ��� */
    private final static int EX_CAND_FLAG_TOP_PRED = 0x800;
    /** ��ά�ʿ�����Զ���(ִ�������ѯʱ���߳������ά�ʿ���) */
    private final static int EX_CAND_FLAG_GRAM = 0x1000;
    /** �Ƿ�����ѡ��� */
    private final static int EX_CAND_FLAG_IEC = 0x2000;
    /** ��ϵ�˴� */
    private final static int EX_CAND_FLAG_CONTACT = 0x4000;
    /** �ϻ������������� */
    private final static int EX_CAND_FLAG_EN_PRECISE = 0x8000;
    /** ���Զ������Զ��� */
    private final static int EX_CAND_FLAG_PHRASE = 0x10000;
    /** ��Ӣ��ϴʶ��� */
    private final static int EX_CAND_FLAG_MIXWORD = 0x20000;
    /** ���ȴʶ��� */
    private final static int EX_CAND_FLAG_OTHERWORD = 0x40000;
    /** ����������Զ��� */
    private final static int EX_CAND_FLAG_CHAIZI = 0x80000;

    // ///////////////////////////////////�����������Զ���////////////////////////////////////////////////////////
    /** ���������Զ���-- ���ġ�Ӣ�ġ����䡢������ͨ�� output����Ϊ���е������� */
    private final static int EX_CAND_FLAG_SEARCH = 0x100000;
    /** ��ý������Զ��� -- ���ġ�Ӣ�ġ����䡢������ͨ�� output��ʽΪ(1,2λָʾmedia������(1��λ��2ͼƬ��3¼����4��д��5Ϳѻ)�� 3-���� ��ʾ */
    private final static int EX_CAND_FLAG_MEDIA = 0x200000;
    /** ���ڵ�һ���ľ����  */
    private final static int EX_CAND_FLAG_TOP_IEC = 0x800000;
    // //////Ϊ�˱�֤�Ժ������չ����24λÿһ��bit���Ƕ����ĺ��壬����ֱ��������жϣ��߰�λ���������ӵ����Զ��壬�õȺ����ж������߰�λ�ж�ʱ���ǵöԵ�24λ����////////////////////
    // ///////////////////////////////////�����������Զ��壬����24λ����0��ʱ��ʹ�õȺ����ж�(==)/////////////////////////////////////////////////////
    /** �������Զ��� */
    private final static int EX_CAND_FLAG_SYM = (2 << 24);
    /** �ɰ��Զ������뷨���Զ��� */
    private final static int EX_CAND_FLAG_OLDDEF = (3 << 24);
    /** �����������Զ��� */
    private final static int EX_CAND_FLAG_SYM_LIAN = (4 << 24);
    /** ��ַ������������Զ��� */
    private final static int EX_CAND_FLAG_FORM = (5 << 24);
    /** �̶���λ�����Զ��� */
    private final static int EX_CAND_FLAG_FIRST = (6 << 24);
    /** �����������Զ��� */
    private final static int EX_CAND_FLAG_EMOJI_LIAN = (7 << 24);
    /** �������Զ��� */
    private final static int EX_CAND_FLAG_EMOJI = (8 << 24);
    /** ������뷨���Զ��� */
    private final static int EX_CAND_FLAG_CANGJIE = (9 << 24);
    /** ע�����뷨���Զ��� */
    private final static int EX_CAND_FLAG_ZY = (10 << 24);
    /** Ъ��������Զ��� */
    private final static int EX_CAND_FLAG_XIEHOUYU = (13<<24);
    /** �����������Զ��� */
    private final static int EX_CAND_FLAG_FAST_INPUT = (14 << 24);

    /** �������������Զ��� */
    private static final int EX_CAND_FLAG_YAN_LIAN = (20 << 24);
    /** ���������Զ��� */
    private static final int EX_CAND_FLAG_EMOTICON = (22 << 24);

    // ///////////////////////////////////��ý�����������Զ��壬����������EX_CAND_FLAG_MEDIA��ʱ��ʹ�õȺ����ж�(==)///////////////////////////////////
    public final static int EX_CAND_FLAG_POS = (2 << 24); // /<��һ��λ��
    public final static int EX_CAND_FLAG_PIC = (3 << 24); // /<��һ��ͼƬ��
    public final static int EX_CAND_FLAG_SOUND = (4 << 24); // /<¼һ��������
    public final static int EX_CAND_FLAG_WRITE = (5 << 24); // /<�ñʼ���д�����
    public final static int EX_CAND_FLAG_PEN = (6 << 24); // /<Ϳ���ʰ�

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////�ϲ��Զ����������Զ���//////////////////////////////////////////
    /** "��ϵ����Ϣ"���� */
    public final static byte INDEX_CONTACT = -10;
    // /** ��Ӣ����� elseCode */
    // public final static byte INDEX_COMBO = -11;

    public final static byte INDEX_ZOMBIE = -12;
    /** �����Ĵ��ѡ�� */
    public final static byte INDEX_VOICE_CORRECT = -13;
    // /** ��ý�������� */
    // public final static byte INDEX_MULTIMEDIA = -14;
    /** �Զ����Ӣ�Ĵ� */
    public final static byte INDEX_ENCUST = -15;
    /** �Զ������ */
    public final static byte INDEX_SYMCUST = -16;
    /** ��ͼ�� */
    public final static byte INDEX_CLOUD_ICON = -17;
    /** ������Ĵ� */
    public final static byte INDEX_CLOUD_WORD = -18;

    public final static byte INDEX_ASSIST_WORD = -19;
    /** input������ʾ�ĺ�ѡ�� */
    public final static byte INDEX_SUBMIT_WORD_WITH_INPUT = -20;
    /** ������� */
    public final static byte INDEX_WHALE = -21;
    /** ������ */
    public final static byte INDEX_CHARGROUP = -22;
    // /**��װ�ı�����ı�������*/
    // public final static byte INDEX_EMOJI_PKG = -23;
    // /**Ĭ�ϵ�emoji��������*/
    // public final static byte INDEX_EMOJI_DEF = -24;
    /** ����� */
    public final static byte INDEX_CHARLINE = -25;
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    public final static short UIFLAG_CONTACT = 0x01;
    public final static short UIFLAG_PINYIN = 0x02;
    /** Ĭ�ϵ�emoji�������� */
    public final static short UIFLAG_EMOJI_DEF = 0x04;
    /** ��װ�ı�����ı������� */
    public final static short UIFLAG_EMOJI_PKG = 0x08;
    /** ��������-���� */
    public final static short UIFLAG_FAST_INPUT_SYMBOL = 0x10;
    /** ��������-���ڣ������� */
    public final static short UIFLAG_FAST_INPUT_DAY = 0x20;
    /** ��������-ʱ�� */
    public final static short UIFLAG_FAST_INPUT_TIME = 0x40;
    /** Ĭ�ϵ����������� */
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
     * �ôʵ�����
     * 
     * �������������ᱣ�������Ϣ: 1.��coreStringΪINDEX_EMOJI_PKG����ʱ����uid
     */
    public String value;

    /** �ôʵ����к�: >=0���ں��е����к�; <0���ϲ��Զ���ĺ�ѡ�� */
    public int index;

    /** �ôʵ��ں����� */
    private int flag;
    /** �ôʵ��ϲ����� */
    private int flag4UI;
    /** ��Ƨ��ע�� */
    private byte[] pinyin;
    /** ģ������������ע�� */
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
     * �ں˻ص����������ں�ʹ�ã�����ɾ��
     * 
     * @param v
     *            ��ѡ�ֵ�����
     * @param f
     *            ��ѡ�ֵ�flag
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
     * ��ѡ���Ƿ���Ч
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
     * �Ƿ�ռ��һ����ѡ��λ
     * 
     * @return
     */
    public final boolean isOccupted()
    {
        if (value == null || value.length() == 0)
        {
            if (index >= 0)
            {// ��ϵ�˻�������
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
     * �Ƿ����ɾ����ָ������
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
     * ֻ��Global.core.PlGetStr(cs, i, ImeCore.GETTYPE_LIST)�����������ʹ�� ΪGlobal.keepFlags��ֵ
     * 
     * @return
     */
    public boolean isListItemLock()
    {
        return flag == 1;
    }

    /**
     * �Ƿ��ǿ��Ա����Ƶ�����
     * 
     * @return
     */
    public final boolean canLimit()
    {
        return (flag & EX_CAND_FLAG_CH) != 0 || isEmoji() || (index == INDEX_CLOUD_ICON) || (index == INDEX_SYMCUST) || isSym() || isSymLX();
    }

    /**
     * ��ѡ���Ƿ���Ӣ�ĵ���
     * 
     * @return
     */
    public final boolean isEn()
    {
        return (flag & EX_CAND_FLAG_EN) != 0 || index == INDEX_ENCUST;
    }

    /**
     * �Ƿ�������Ľ��
     * 
     * @return
     */
    public final boolean isLX()
    {
        return (flag & EX_CAND_FLAG_LX) != 0 || isSymLX();
    }

    /**
     * �Ƿ��������ؼ��ʻ������������
     * 
     * @return
     */
    public final boolean isSearch()
    {
        return ((flag & EX_CAND_FLAG_SEARCH) != 0);
    }

    /**
     * �Ƿ����������
     * 
     * @return
     */
    public final boolean isMultimedia()
    {
        return ((flag & EX_CAND_FLAG_MEDIA) != 0);
    }

//    /**
//     * �����������ڣ���ö�ý����ڵ����ͣ����ں˶���Ķ�ý��������ӳ�� mediaType��Ӧ1:λ�ã�2��ͼƬ 3��¼�� 4��Ϳѻ 5����д ʵ�ʶ�ý�嶨������Ͷ�Ӧ1:λ�ã�2��ͼƬ 3��¼�� 4����ά�� 5��Ϳѻ 6����д
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
     * �Ƿ����������
     * 
     * @return
     */
    public final boolean isTopPred()
    {
        return (flag & EX_CAND_FLAG_TOP_PRED) != 0;
    }

    /**
     * 
     * �Ƿ���ϸ���ʿ�
     * 
     * @return �Ƿ���ϸ���ʿ�
     */
    public final boolean isCell()
    {
        return (flag & EX_CAND_FLAG_CELL) != 0;
    }
    
    /**
     * �Ƿ������ž���
     */
    public final boolean isTopKp()
    {
        return index == 0 && (flag & EX_CAND_FLAG_TOP_IEC) != 0;
    }
    
    /**
     * �Ƿ������Ŷ�����
     */
    public final boolean isTopChChor()
    {
        return index == 0 && (chcor != null && chcor.length > 0);
    }

    /**
     * �Ƿ�����ϵ��
     */
    public final boolean isContact()
    {
        return (flag & EX_CAND_FLAG_CONTACT) != 0;
    }

    /**
     * �Ƿ��Ƕ�Ԫ������Ԫ��Ӣ����
     * 
     * @return
     */
    public final boolean isGramCJ()
    {
        return (flag & EX_CAND_FLAG_CJ) != 0 && (flag & EX_CAND_FLAG_GRAM) != 0;
    }

    /**
     * �Ƿ��Ǳʻ�����ʻ����Զ���ĺ�ѡ��
     * 
     * @return
     */
    public final boolean isBW_OldDef()
    {
        return (flag & EX_CAND_FLAG_BW) != 0 || isSpecialCoreStringType(EX_CAND_FLAG_OLDDEF);
    }

    /**
     * �Ƿ��Ƿ�������
     * 
     * @return
     */
    public final boolean isSym()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_SYM);
    }

    /**
     * �Ƿ��Ƿ�������
     * 
     * @return
     */
    public final boolean isSymLX()
    {
        return isSpecialCoreStringType(EX_CAND_FLAG_SYM_LIAN);
    }

    /**
     * �Ƿ��Ǹ��Զ���
     * 
     * @return
     */
    public final boolean isPhrase()
    {
        return (flag & EX_CAND_FLAG_PHRASE) != 0;
    }

    /**
     * �Ƿ���������
     *
     * @return
     */
    public final boolean isYan() {
        return isSpecialCoreStringType(EX_CAND_FLAG_EMOTICON) || hasFlag(UIFLAG_YAN_DEF);
    }

    /**
     * �Ƿ�����������
     *
     * @author chengyuan02
     * @return
     */
    public final boolean isYanLX() {
        return isSpecialCoreStringType(EX_CAND_FLAG_YAN_LIAN);
    }

    /**
     * �Ƿ���emoji����
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
     * isCsFastInput �Ƿ����ڿ�������
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
        /** ���� */
        public String hz;
        /** ƴ�� */
        public String py;
        /** �������ڵ�λ�� */
        public int idx;
        /** ���ڵ����� */
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
