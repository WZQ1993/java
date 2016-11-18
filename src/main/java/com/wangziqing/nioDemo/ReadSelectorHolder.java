package com.wangziqing.nioDemo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 读取轮询持有者
 * Created by ziqingwang on 2016/11/18.
 */
public class ReadSelectorHolder {
    private List<ServerSelector> serverSelectors= Lists.newArrayList();
    ReadSelectorHolder(ServerSelector serverSelector,int size){
        this.serverSelectors.add(serverSelector);
        if(size>1){
            while(size-->1){
                ServerSelector selector=new ServerSelector();
                
            }
        }
    }
}
