package com.wangziqing.nioDemo;

import java.nio.channels.SelectionKey;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * Created by ziqingwang on 2016/11/16.
 */
public class SelectorHandle {
    private Consumer<SelectionKey> acceptAction;
    private Consumer<SelectionKey> connectAction;
    private Consumer<SelectionKey> readAction;
    private Consumer<SelectionKey> writeAction;
    void doAcceptAction(SelectionKey key){
        if(Objects.nonNull(acceptAction)){
            acceptAction.accept(key);
        }
    }
    void doConnectAction(SelectionKey key){
        if(Objects.nonNull(connectAction)){
            connectAction.accept(key);
        }
    }
    void doReadAction(SelectionKey key){
        if(Objects.nonNull(readAction)){
            readAction.accept(key);
        }
    }
    void doWriteAction(SelectionKey key){
        if(Objects.nonNull(writeAction)){
            writeAction.accept(key);
        }
    }

    public Consumer<SelectionKey> getAcceptAction() {
        return acceptAction;
    }

    public void setAcceptAction(Consumer<SelectionKey> acceptAction) {
        this.acceptAction = acceptAction;
    }

    public Consumer<SelectionKey> getConnectAction() {
        return connectAction;
    }

    public void setConnectAction(Consumer<SelectionKey> connectAction) {
        this.connectAction = connectAction;
    }

    public Consumer<SelectionKey> getReadAction() {
        return readAction;
    }

    public void setReadAction(Consumer<SelectionKey> readAction) {
        this.readAction = readAction;
    }

    public Consumer<SelectionKey> getWriteAction() {
        return writeAction;
    }

    public void setWriteAction(Consumer<SelectionKey> writeAction) {
        this.writeAction = writeAction;
    }
}
