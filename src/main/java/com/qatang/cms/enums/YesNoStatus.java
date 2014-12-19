package com.qatang.cms.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhangzq on 2014/6/23.
 */
public enum YesNoStatus {
    ALL("全部"),
    YES("是"),
    NO("否");
    private static Logger logger = LoggerFactory.getLogger(YesNoStatus.class);

    private static final Map<Integer, YesNoStatus> _MAP = new HashMap<Integer, YesNoStatus>();
    private static List<YesNoStatus> _LIST = new ArrayList<YesNoStatus>();
    private static List<YesNoStatus> _ALLLIST = new ArrayList<YesNoStatus>();
    static {
        for(YesNoStatus yesNoStatus : YesNoStatus.values()){
            _MAP.put(yesNoStatus.getValue(), yesNoStatus);
            _ALLLIST.add(yesNoStatus);
            if (!yesNoStatus.equals(ALL)) {
                _LIST.add(yesNoStatus);
            }
        }

        synchronized (_LIST) {
            _LIST = Collections.unmodifiableList(_LIST);
        }
        synchronized (_ALLLIST) {
            _ALLLIST = Collections.unmodifiableList(_ALLLIST);
        }
    }

    private String name;

    private YesNoStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return this.ordinal();
    }

    public static YesNoStatus get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<YesNoStatus> list() {
        return _LIST;
    }

    public static List<YesNoStatus> listAll() {
        return _ALLLIST;
    }
}
