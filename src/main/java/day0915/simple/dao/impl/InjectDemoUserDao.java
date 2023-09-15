package day0915.simple.dao.impl;

import day0915.simple.dao.UserDao;

import java.util.*;

public class InjectDemoUserDao implements UserDao {
    private int straightValue;

    private int[] array;

    public int getStraightValue() {
        return straightValue;
    }

    public void setStraightValue(int straightValue) {
        this.straightValue = straightValue;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private List<String> list;

    private Set<String> set;

    private Map<String,String> map;

    private Properties properties;


    @Override
    public void showLogin() {
        System.out.println("straightValue =" + straightValue);
        System.out.println("array =" + Arrays.toString(array));
        System.out.println("list =" + list);
        System.out.println("map =" + map);
        System.out.println("properties = " + properties);
    }

    @Override
    public void login() {

    }
}
