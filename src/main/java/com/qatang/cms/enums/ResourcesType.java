package com.qatang.cms.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhangzq on 14-12-16.
 */
public enum ResourcesType {
    ALL(-1, "全部"),
    MENU(0, "菜单"),
    FUNCTION(1, "功能"),
    RESOURCES(2, "资源");
    private static Logger logger = LoggerFactory.getLogger(ResourcesType.class);

    private static final Map<Integer, ResourcesType> _MAP = new HashMap<Integer, ResourcesType>();
    private static List<ResourcesType> _LIST = new ArrayList<ResourcesType>();
    private static List<ResourcesType> _ALLLIST = new ArrayList<ResourcesType>();
    static {
        for(ResourcesType resourcesType : ResourcesType.values()){
            _MAP.put(resourcesType.getValue(), resourcesType);
            _ALLLIST.add(resourcesType);
            if (!resourcesType.equals(ALL)) {
                _LIST.add(resourcesType);
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
    private int value;

    private ResourcesType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public static ResourcesType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<ResourcesType> list() {
        return _LIST;
    }

    public static List<ResourcesType> listAll() {
        return _ALLLIST;
    }
}
