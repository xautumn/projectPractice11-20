package com.baidu.input;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.baidu.input.pub.CoreString;


public abstract class PlumCore {
    // �ں����붨��
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
    // ģ��������Ϊ32������δ�õ��ĵط�����0��伴��
    public final static int SET_MOHU = 3;
    public final static int GET_MOHU = 4;
    public final static int SET_SHUANGPIN = 5;
    public final static int GET_SHUANGPIN = 6;
    public final static int SET_HW_CONFIG = 7;
    public final static int GET_HW_CONFIG = 8;

    public final static int USERWORDS_EDIT_ADJUST = 0;
    public final static int USERWORDS_EDIT_DELETE = 1;

    public final static int GETTYPE_NULL = -1;
    public final static int GETTYPE_CAND = 0;// //��ͨ��ѡ��///////////////
    public final static int GETTYPE_LIST = 1;// //ƴ���б�(�� �ʻ�ɸѡ ��)/////
    public final static int GETTYPE_HOTSYM = 2; // /<���÷���(Ŀǰ��֧��)
    public final static int GETTYPE_CAND_ORG = 3; // /<��ͨ��ѡ��ԭʼ��̬(�޷���ת����Ӣ�Ĵ�Сдת��)
    public final static int GETTYPE_CAND_WB_TIP = 4; // <���ĺ�ѡ��(����ʱ�����ʾ)
    public final static int GETTYPE_PY_LIST = 5; // /<ƴ���б�
    public final static int GETTYPE_BH_LIST = 6; // �ʻ�ɸѡ�б�
    public final static int GETTYPE_USWORD = 100; // //�����.//////////////////

    public final static int SEARCHKEY_EN = 20;
    /** ��ѯ�������Ĵ� */
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

    /** ��Ҫ��Ƶ�Ĵ�ѹջ(idx ��ʾҪ����ѹջ��ѡ������) */
    public final static int CMD_ADJUST_PUSH = 1;
    /** ��Ҫ��Ƶ�Ĵʵ���(�˸��ʱ�����, idx ��ʾ�����Ĵʵĸ���,idx=0��ʾ�������д�(���ջ)) */
    public final static int CMD_ADJUST_POP = 2;
    /** ��ջ��Ԫ��ִ�е���Ƶ���� (��ʱ:�趨idx = 0�Ϳ�����) */
    public final static int CMD_ADJUST_COMMIT = 3;
    /** ɾ����(idx ��ʾҪ����ɾ���ĺ�ѡ������)��֧����Ӣ�ģ�Ӣ��ע���Сд */
    public final static int CMD_ADJUST_DELETE = 4;
    /** ������,���Ѿ�ѹջ�Ĵ�(����û��commit),����Ϊ��λ���˸����,idx ��ʾ�˸���ֵĸ��� */
    public final static int CMD_ADJUST_BACKSPACE = 5;
    /** �����������Ż�״̬(��嵯��,������,�Ⱦ����ж���Ϊ */
    public final static int CMD_PREDICT_CLEAN = 6;
    /** ��ѯ��ǰ���ǰһ���ʵĳ��� */
    public final static int CMD_PREDICT_PREWORD_LEN = 7;
    /** ��ѯ��ǰ��Ƶջ��PUSH���� */
    public final static int CMD_STACK_ITEM_CNT = 8;
    /** ��ѯ��ǰջ����,ĳ����idx��Ԫ�صĳ���.(û�����Ԫ�ؾͷ���0) */
    public final static int CMD_STACK_ITEM_LEN = 9;
    /** �ж��Ƿ����ɾ��.(����ֵ>0 ʱ,�ű�ʾ����ɾ��) */
    public final static int CMD_IS_DELABLE = 20;
    /** �ж��Ƿ����ϵͳ��.(����ֵ>0 ʱ,�ű�ʾ����ϵͳ��, = 0��<0 �ֱ��ʾ�����ںͳ��ִ���.) */
    public final static int CMD_IS_SYSWORD = 21;
    /** һ�������Ĳ�ѯ�������������Ѿ����޷���ԭ���������ʱ��ʹ�ø�������һ������,�޷���ԭ����˼�ǣ���ѡ���Ѿ��������Ե��������ѯ��ʱ�򣬻���������������� */
    public final static int CMD_INPUT_CLEAN_UP = 10;
    /** ���list��ѡ��״̬ */
    public final static int CMD_LIST_CLEAN = 30;
    /** ѡ�е�ǰƴ��list�еĵ�idx��ƴ��ѹջ */
    public final static int CMD_PY_LIST_PUSH = 31;
    /** ����ǰջ����ƴ������ */
    public final static int CMD_PY_LIST_POP = 32;
    /** ѡ�е�ǰ�ʻ�list�еĵ�idx���ʻ�ѹջ */
    public final static int CMD_BH_LIST_PUSH = 33;
    /** ����ǰջ���ıʻ����� */
    public final static int CMD_BH_LIST_POP = 34;
    /** ���ص�ǰƴ��list���泤�� */
    public final static int CMD_PY_LIST_CACHE_LEN = 35;
    /** ѡ��ǰ��list��ѯģʽ�����Ļ���Ӣ�ģ�idx = 1ʱ����Ӣ�ģ�idx = 0ʱ�������� */
    public final static int CMD_LIST_FIND_MODE = 36;
    /** ��ȡ��Ӧ��ϵ�˸��� */
    public final static int CMD_CONTACT_CNT = 40;
    /** ִ�������ѯ. */
    public final static int CMD_FIND_LIAN = 41;
    /** ʹ���ϴε��������²�ѯһ�� */
    public final static int CMD_FIND_REFRESH = 42;
    /** �����д״̬ */
    public final static int CMD_HW_CLEAN = 43;
    /** ɾ��ĳ���ʶ�Ӧ��ϵ�˵���Ϣ */
    public final static int CMD_CONTACT_DEL = 44;
    /** ɾ��ĳ���ʶ�Ӧ��ϵ�˵���Ϣ���ָ�Ĭ�ϴ�Ƶ */
    public final static int CMD_CONTACT_RESTORE_FREQ = 45;
    /** ɾ��ĳ���ʶ�Ӧ��ϵ���Լ���Ӧ������� */
    public final static int CMD_CONTACT_DEL_ALL = 46;
    /** �������������״̬ */
    public final static int CMD_SENTENCE_LIAN_CLEAN = 50;
    /**
     * ���Ӣ��list��ѡ��״̬ ����ʹ�øýӿڣ�Ӧ��ʹ��CMD_INPUT_CLEAN_UP
     */
    public final static int CMD_EN_LIST_CLEAN = 60;
    /** ѡ�е�ǰӢ��list�еĵ�idx��Ӣ��ѹջ,��ѡ�е��ǡ����֡�ʱ������1����������0�� */
    public final static int CMD_EN_LIST_PUSH = 61;
    /** ����ǰջ����Ӣ�ĵ��� // �ݲ�ʵ�� */
    public final static int CMD_EN_LIST_POP = 62;
    /** ���ص�ǰӢ��list���泤�� */
    public final static int CMD_EN_LIST_CACHE_LEN = 63;
    /** ��д���� */
    public static final int CMD_HW_BACKSPACE = 64;
    // /////////////////////////////////������������INPUT_FLAG////////////////////////////
    // //////////////////////////////����ipt_query_set_encase�Ĳ���////////////////////////////
    /** ����״̬ */
    public final static int INPUT_FLAG_NORMAL = 0x00;
    /** ǿ�ƴ�д������shift�������� */
    public final static int INPUT_FLAG_UPPER_CASE = 0x01;
    /** ǿ��Сд */
    public final static int INPUT_FLAG_LOWER_CASE = 0x02;
    /** �ϻ���ȷ���� */
    public final static int INPUT_FLAG_PRECISE = 0x04;

    // ////////////////////////////////��д��ѯ��ΧHW_FIND_RANGE/////////////////////////
    // ////////////////////////////////������д��ѯ��Χ����
    /** ������ */
    public final static int HW_FIND_RANGE_CH_COMMON = (0x01);
    /** ��Ƨ�� */
    public final static int HW_FIND_RANGE_CH_RARE = (0x02);
    /** ƫ�Բ��� */
    public final static int HW_FIND_RANGE_CH_RADICAL = (0x04);
    /** ���� */
    public final static int HW_FIND_RANGE_NUM = (0x08);
    /** Ӣ��Сд */
    public final static int HW_FIND_RANGE_EN_LOWER = (0x10);
    /** Ӣ�Ĵ�д */
    public final static int HW_FIND_RANGE_EN_UPPER = (0x20);
    /** ���ñ�� */
    public final static int HW_FIND_RANGE_PUN_COMMON = (0x40);
    /** ��չ��� */
    public final static int HW_FIND_RANGE_PUN_EXT = (0x80);
    /** ���÷��� */
    public final static int HW_FIND_RANGE_SYM_COMMON = (0x0100);
    /** ��չ���� */
    public final static int HW_FIND_RANGE_SYM_EXT = (0x0200);
    /** �������1 */
    public final static int HW_FIND_RANGE_SYM_RARE_G1 = (0x0400);
    /** �������2 */
    public final static int HW_FIND_RANGE_SYM_RARE_G2 = (0x0800);
    /** �������3 */
    public final static int HW_FIND_RANGE_SYM_RARE_G3 = (0x1000);
    /** ������ƫ�Բ��׵ĺ��� */
    public final static int HW_FIND_RANGE_CH_NM = (HW_FIND_RANGE_CH_COMMON | HW_FIND_RANGE_CH_RARE);
    /** Ӣ�� */
    public final static int HW_FIND_RANGE_EN = (HW_FIND_RANGE_EN_LOWER | HW_FIND_RANGE_EN_UPPER);
    /** ��� */
    public final static int HW_FIND_RANGE_PUN = (HW_FIND_RANGE_PUN_COMMON | HW_FIND_RANGE_PUN_EXT);
    /** ���� */
    public final static int HW_FIND_RANGE_SYM = (HW_FIND_RANGE_SYM_COMMON | HW_FIND_RANGE_SYM_EXT
            | HW_FIND_RANGE_SYM_RARE_G1 | HW_FIND_RANGE_SYM_RARE_G2 | HW_FIND_RANGE_SYM_RARE_G3);
    /** ���к��� */
    public final static int HW_FIND_RANGE_CH_ALL = (HW_FIND_RANGE_CH_NM | HW_FIND_RANGE_CH_RADICAL);
    /** ��Ƨ��+ƫ�Բ��� */
    public final static int HW_FIND_RANGE_CH_RARE_RADICAL = (HW_FIND_RANGE_CH_RARE | HW_FIND_RANGE_CH_RADICAL);
    /** Ӣ��+���� */
    public final static int HW_FIND_RANGE_EN_NUM = (HW_FIND_RANGE_EN | HW_FIND_RANGE_NUM);
    /** ���+���� */
    public final static int HW_FIND_RANGE_PUN_SYM = (HW_FIND_RANGE_PUN | HW_FIND_RANGE_SYM);

    /** ���Χ */
    public final static int HW_FIND_RANGE_DASH = (HW_FIND_RANGE_EN_NUM | HW_FIND_RANGE_PUN_SYM);
    /** ���з�Χ */
    public final static int HW_FIND_RANGE_ALL = (HW_FIND_RANGE_CH_ALL | HW_FIND_RANGE_DASH);

    // ////////////////////////////////
    // /��д����ѡ��(��д����ʱʹ��)
    /** �������� */
    public final static int HW_INPUT_TYPE_HZ = 1;
    /** ��д���� */
    public final static int HW_INPUT_TYPE_REDUP = 2;
    /** ��д���� */
    public final static int HW_INPUT_TYPE_NM = 4;
    /** Ӣ���������� */
    public final static int HW_INPUT_TYPE_EN_NUM = 8;

    /*********************************/
    /*********************************/
    /* �ں˳�ʼ�������á��ͷ���صĽӿ� */
    /*********************************/
    /*********************************/

    // /////////////////////////////////�����������////////////////////////////
    /** ��� */
    public final static byte PY_IEC_FLAG_TE = 0x1;
    /** ���� */
    public final static byte PY_IEC_FLAG_LE = 0x2;
    /** ���� */
    public final static byte PY_IEC_FLAG_ME = 0x4;
    /** �������� */
    public final static byte PY_IEC_FLAG_SE = 0x8;
    /** ���䣬����ǰ */
    public final static byte PY_IEC_FLAG_POS = 0x10;
    /** ���䣬����ǰ */
    public final static byte PY_IEC_FLAG_LE_PRE = PY_IEC_FLAG_LE | PY_IEC_FLAG_POS;

    public final static int CIKU_TYPE_CZ = 0;
    public final static int CIKU_TYPE_GRAM = 1;
    
    
    /** �������Ͷ��壬û�г��� **/
    public static final int NO_CONTEXT_ID = 0;
    /** �������Ͷ��壬���̳��� **/
    public static final int CONTEXT_ID_MERCH = 0x1;
    /** �������Ͷ��壬Ӧ���г����� **/
    public static final int CONTEXT_ID_APP = 0x2;
    /** �������Ͷ��壬����λ�ó��� **/
    public static final int CONTEXT_ID_LBS = 0x4;
    /** �������Ͷ��壬��ý�峡�� **/
    public static final int CONTEXT_ID_MEDIA = 0x8;
    /** �������Ͷ��壬�����Ź����� **/
    public static final int CONTEXT_ID_GROUPON = 0x10;
    /** �������Ͷ��壬��������� **/
    public static final int CONTEXT_ID_BROWSER = 0x20;
    /** �������Ͷ��壬����������� **/
    public static final int CONTEXT_ID_SNS = 0x40;
    /** �������Ͷ��壬�����ೡ�� **/
    public static final int CONTEXT_ID_MUSIC = 0x80;
    /** �������Ͷ��壬Bվ�ೡ�� **/
    public static final int CONTEXT_ID_BILI = 0x100;
    /** �������Ͷ��壬������̳���� **/
    public static final int CONTEXT_ID_TIEBA = 0x200;
    /** �������Ͷ��壬�����ೡ�� **/
    public static final int CONTEXT_ID_SOCIAL = 0x400;
    /** �������Ͷ��壬������ҫ���� **/
    public static final int CONTEXT_ID_KING_GLORY = 0x800;

    /** ��list��ѡ�С�Ӣ�ġ�ʱ�����ں˵�ֵ,PlKpAppendPoint����pos��������,posֻ��Ϊ0�Լ�LIST_ENGLISH_SELECT��ֵ */
    public static final int LIST_ENGLISH_SELECT = 127;

    /** �����봥���� */
    public static final int EX_EX_CAND_FLAG_WHITE = 0x01;
    /** �����봥���ʣ�ֻ�ܹ��������룬û�иñ��λ�����к�ѡ */
    public static final int EX_EX_CAND_FLAG_WHITE_LIAN = 0x02;
    /** �����봥���ʣ����ı����ͣ�û�иñ��λ����Ǵ��ı����� */
    public static final int EX_EX_CAND_FLAG_WHITE_TEXT = 0x4;
    /** �ƻ���ʣ����뱾���ظ� */
    public static final int EX_EX_CAND_FALG_CLOUD_DUPLICATE = 0x1000;

    public static final int CPU_SUPPORT_NEOM = 0;

    /**
     * ����ʿ��native��ַ�ռ�
     *
     * @param size     ���ݶε��ֽ���
     * @param cikuType �ʿ�����
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    protected native boolean PlCiKuAlloc(int size, int cikuType);

    /**
     * ��������ʿ�
     *
     * @param data     �������ݶ�
     * @param off      ��ǰ������ʵ�ʴʿ����ݵ�ƫ����
     * @param size     ��ǰ���ݶε���Ч�ֽ���
     * @param cikuType �ʿ�����
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    protected native boolean PlCiKuAppend(byte[] data, int off, int size, int cikuType);

    /**
     * ��֤Ԥ�����Cz�ʿ��Ƿ���ȷ
     *
     * @param copySize �����Ĵ�С
     * @param cikuType �ʿ�����
     * @return ��ȷ����true�����󷵻�false
     */
    protected native boolean PlCiKuCheck(int copySize, int cikuType);

    /**
     * ��ʼ�� ����ʿ���ֿ�
     * 
     * @param fileNames
     *            �ļ���
     * @param pi
     *            ����Ϣ
     * @param isCzValid
     *            Ԥ�����Cz�ʿ��Ƿ���ȷ
     * @param context
     *            context
     * @param cuid
     *            cuid
     * @param model
     *            �ֻ��ͺ�
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    protected native boolean PlInit(String[] fileNames, PackageInfo pi, boolean isCzValid, Context context, String cuid, String model);

    /**
     * ˢ���ںˣ�������д��
     * 
     * @param fileName �ں˴ʿ������ļ�, ָ���ں���Ҫˢ�µ������ļ���λ�ã����ļ������ڲ�ˢ�£����Ϊnull��ʾֻж��
     * @return 0����ˢ�³ɹ�; ����ֵ<0����ˢ��ʧ�ܻ�û��ˢ��
     */
    public native int PlHandWritingRefresh(String fileName);
    
    /**
     * ˢ���ںˣ����ض�Ԫ��gram.bin
     *
     * @param fileName �ں˴ʿ������ļ�, ָ���ں���Ҫˢ�µ������ļ���λ�ã����ļ������ڲ�ˢ�£����Ϊnull��ʾֻж��
     * @return 0����ˢ�³ɹ�; ����ֵ<0����ˢ��ʧ�ܻ�û��ˢ��
     */
    public native int PlGramRefresh(String fileName);
    /**
     * ����
     * 
     * @param activeNow
     *            �Ƿ���������������ڼ���
     * @return 
     */
    public native void PlActivate(boolean activeNow);

    /**
     * ˢ���ںˣ�����ĳ���ں��ļ���Ŀǰֻ֧�����ض�Ԫ��gram.bin��
     *
     * @return 0����ˢ�³ɹ�; ����ֵ<0����ˢ��ʧ�ܻ�û��ˢ��
     */
    public native int PlDefGramRefresh();

    /**
     * ж�شʿ���ֿ�
     * 
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    public native boolean PlClose();


    /**
     * �����ı��浽�ļ�
     * 
     * @return
     */
    public native void PlFlush();

    /**
     * ����ں˰汾��
     * 
     * @return �ں˴ʿ�汾��
     */
    public native int PlGetCoreVersion();
    
    
    /**
     * 
     * ���SDK�İ汾��
     * 
     * @return ���磺"1.0.0.2"
     */
    public native String PlGetSDKVersion();

    /**
     * ��������״̬
     * 
     * @param params ����״̬ƴ��˳�򣺣�48 byte�� ��Ƶ����Ƶ��������/��Ƨ�֣�ֻ��ʾ�֣�Ĭ�ϣ� ���䣻��Ӣ����(0:�ر�,1:����,2:����,�����ε�����ĸ)����ת�����ʻ� �� �������ȣ�
     *            �������ƴ��������˫ƴ������ʾ˫ƴԭʼ�����룻������ƴ��Ԫ���䣻 Ӣ������Ĭ��ΪBYFREQ=0��BYLEN=1��BYABC=2��Ӣ�Ĵ�Сд��Ĭ��ΪNORMAL=0�� FIRST=1��
     *            ALL=2��reserved��reserved�� ���ܾ������Զ���(0=�ر�, 1-9 = ���Զ����Ĭ�ϳ���λ��)���Ƿ����Զ�ʵʱ���棻�������뿪��(��δ��ͨ�ù���)��
     *            ��������Ż�;��Ӣ�������;reserved;reserved 6���ĸ��ֽ������� ģ����ƴ��˳�򣺣�16 byte�� ch;sh;zh;k; f;n;ang;eng;
     *            ing;iang;uang;reserved; �ĸ��ֽ����� ˫ƴ��������64 byte�� ��ĸ[24];��ĸ[33];reserved[3]�� �ĸ��ֽ�������
     * 
     * @param type 1����������״̬ 2����������״̬ 3������ģ���� 4����ȡģ���� 5������˫ƴ���� 6����ȡ˫ƴ����
     * @return �ɹ�����true�� ʧ�ܷ���false
     */
    public native boolean PlConfig(byte[] params, int type);


    /**
     * ��������������ʿ���ֿ�
     * 
     * @param input ����
     * @param pos 0 -- Ĭ��   64 -- ��ĩ    127 -- Ӣ��
     * @param type ѡ�� ��1:ƴ��, 2��Ӣ��, 3���ʻ�, 4�����,5���Զ���, 6������, 7:���Զ��� , 8:���� ��
     * @param contextId �������Id
     * @return �������
     */
    public native int PlFind(byte[] input, int pos, int type, int contextId);

    /**
     * ��������
     * 
     * @param zids ��Ҫ���������:ÿ���ֵ�ID��ɵ����� ����unicode���飬�ԡ�\0����β
     * @param len ��Ҫ��������ݵĳ��ȣ��ֳ��� ����0
     * @return �������
     */
    public native int PlFindLian(char[] zids, int len);

    /**
     * ͨ��zid����д�����ѯ
     * 
     * @param zids ��Ҫ���������:ÿ���ֵ�ID��ɵ����� ����unicode���飬�ԡ�\0����β
     * @param len ��Ҫ��������ݵĳ��ȣ��ֳ��� ����0
     * @return 0������ҳɹ�; ����ֵ<0�������ʧ��
     */
    public native int PlFindLianbyHW(char[] zids, int len);

    /**
     * �����ѯ��Find��FindLian���Ļ���
     */
    public native void PlClean();

    /**
     * ��õ��β�ѯ�ĺ�ѡ�ֻ��б���÷��ŵĸ���
     * 
     * @param type 0����ͨ��ѡ�� 1��ƴ���б���ʻ�ɸѡ�б� 2�����÷���
     * @return ����
     */
    public native int PlCount(int type);

    /**
     * ��ȡ��ѯ���ĺ�ѡ�ֻ��ѡ��
     * 
     * @param cs �˴� ��ʹ��Java�ķ�����ƣ����ز�ѯ�����ֻ��
     * @param id ��ѡ�ֻ��ѡ�ʵ�λ�ã�һ�� ��0��ʼ��PlCount
     * @param type ѡ�� ��1:��ͨ��ѡ��, 2��ƴ���б���ʻ�ɸѡ�б�, 3�����÷��ţ�
     * @return ��ѡ�ֻ��ѡ�ʵ����� ����
     */
    public native int PlGetStr(CoreString cs, int id, int type);
    
    /**
     * ��ȡ��ѡ����չ����
     * 
     * @param id ��ѡ�ֻ��ѡ�ʵ�λ�ã�һ�� ��0��ʼ��PlCount
     * @return ��ѡ�ֻ��ѡ�ʵ���չ���� ��0x1Ϊ�������������
     */
    public native int PlGetExFlag(int id);

    /**
     * ��ȡѡ�����ĺ�ѡ���ִʵ���Ϣ
     * 
     * @param id ��ѡ�б��е�����
     * @return ����ѡ�����ĺ�ѡ���ִʵ���Ϣ��0:�����ֵĳ���, 1:����ƥ���������ĳ���,2:ÿ���ֵı��룩
     */
    public native int[] PlMatchInfo(int id);

    /**
     * �õ�������ʾ
     * 
     * @param id ѡ�еĺ�ѡ�ʵ�����
     * @param info ѡ�еĺ�ѡ�ʵ������ĳ���
     * @return ����������ʾ
     */
    public native String PlGetShow(int id, byte[] info);

    /**
     * CMD_ADJUST_DELETE = 4,////ɾ����(idx ��ʾҪ����ɾ���ĺ�ѡ������)��֧����Ӣ�ģ�Ӣ��ע���Сд CMD_IS_DELABLE = 20,////�ж��Ƿ����ɾ��.(����ֵ>0 ʱ,�ű�ʾ����ɾ��)
     * CMD_IS_SYSWORD = 21,////�ж��Ƿ����ϵͳ��.(����ֵ>0 ʱ,�ű�ʾ����ϵͳ��, = 0��<0 �ֱ��ʾ�����ںͳ��ִ���.) CMD_CONTACT_CNT = 40, ///��ȡ��Ӧ��ϵ�˸���.
     * CMD_FIND_LIAN = 41,////ִ�������ѯ.
     * 
     * @param id ��ѡ�ʵ�����
     * @param cmdType ����������
     * @return CMD_ADJUST_DELETE��//����1��ʾ����//����0��ʾ������//���ظ���,��ʾ����. CMD_IS_DELABLE������1��ʾ����ɾ��������0��ʾ�޷�ɾ�� CMD_IS_SYSWORD������ֵ>0
     *         ʱ,�ű�ʾ����ϵͳ��, = 0��<0 �ֱ��ʾ�����ںͳ��ִ���. CMD_CONTACT_CNT��������ϵ�˸��� CMD_FIND_LIAN�����ش������
     * 
     */
    public native int PlQueryCmd(int id, int cmdType);

    /*********************************/
    /*********************************/
    /* �û��ʵ��뵼����ؽӿ� */
    /*********************************/
    /*********************************/



    /**
     * ��ôʿ�İ汾
     *
     * @param type 0:���д� 1��ϵͳ��
     * @return inner_ver
     */
    public native int PlCellGetVer(int type);

    /**
     * ���ϵͳ�ʿ��SID
     *
     * @return
     */
    public native int PlCellGetSysVER();
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // ��д���
    /**
     * ��д���һ��,ֻ��һ�ʲ�֧�ֶ��
     * 
     * @param point_data �� ��д������-1,0��β
     * @param input_type �� ��д���ͣ��������������HW_INPUT_TYPE��
     * @return ����ֵ<0��ʾʧ��,0��ʾ�ɹ�
     */
    public native int PlHandWritingRecognizeAppand(short[] point_data, int input_type);

    /**
     * ��ȡ��д�汾��
     * 
     * @return ��ȡ��д�汾��
     */
    public native int PlHandWritingVersion();

    /**
     * ��ȡ��д������
     * 
     * @return ��д������ {ʶ��Χ��ÿ���ֵ����ƥ��ʻ���}
     */
    public native int[] PlHandWritingGetConfig();

    /**
     * ������д������
     * 
     * @param config ��д������ {ʶ��Χ��ÿ���ֵ����ƥ��ʻ���}
     * @return ����ֵ<0��ʾʧ��,0��ʾ�ɹ�
     */
    public native int PlHandWritingSetConfig(int[] config);

    /**
     * ��д����ѹ��
     * 
     * @param candId ��ѡ��idx
     * @return ��дѹ������
     */
    public native byte[] PlHandWritingEncodePoints(int candId);



    /**
     * ���ò��׹���
     *
     */
    public native int PlHandWritingSetBsFilter(short[] points);
}
