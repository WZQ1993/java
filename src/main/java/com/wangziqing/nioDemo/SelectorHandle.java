package com.wangziqing.nioDemo;

import java.nio.channels.SelectionKey;

/**
 *
 * Created by ziqingwang on 2016/11/16.
 */
public interface SelectorHandle {
    void doAcceptAction(SelectionKey key);
    void doConnectAction(SelectionKey key);
    void doReadAction(SelectionKey key);
    void doWriteAction(SelectionKey key);
}
