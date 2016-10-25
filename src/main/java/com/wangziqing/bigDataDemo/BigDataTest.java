package com.wangziqing.bigDataDemo;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.BitSet;
import java.util.Optional;

/**
 * Created by ziqingwang on 2016/10/25.
 */
public class BigDataTest {
    Logger logger = LoggerFactory.getLogger(BigDataTest.class);
    BitSet bitSet=new BitSet(30000000);
    boolean isLoadFile=false;

    private Optional<CSVReader> tryReadCsv(String path){
        try{
            FileReader fileReader=new FileReader(path);
            return Optional.of(new CSVReader(fileReader));
        }catch (IOException e){
            logger.error("load file {} error,msg: {}",path,e.getMessage());
        }
        return Optional.empty();
    }

    public void initBigSet(String path){
        CSVReader reader=tryReadCsv(path).orElse(null);
        Optional.ofNullable(reader).ifPresent(readerNotNull -> {
            try{
                String[] nextLine=readerNotNull.readNext();
                while(null!=nextLine&&nextLine.length>0){
                    bitSet.set(Integer.valueOf(nextLine[0]),true);
                    nextLine=readerNotNull.readNext();
                }
                isLoadFile=true;
            }catch (IOException e){
                logger.error("read fileLine error,msg: {}",e.getMessage());
            }
        });
    }
    public void clearBigSetfFlag(){
        isLoadFile=false;
    }
    public boolean isExist(int value){
        return isLoadFile&&bitSet.get(value);
    }
    public boolean defaultIsExist(String path,int value){
        CSVReader reader=tryReadCsv(path).orElse(null);
        return Optional.of(reader).map(readerNotNull -> {
            Boolean mapResult=false;
            try{
                String[] nextLine=readerNotNull.readNext();
                while(null!=nextLine&&nextLine.length>0){
                    if(Integer.valueOf(nextLine[0]).equals(value)){
                        mapResult=true;
                    }
                    nextLine=readerNotNull.readNext();
                }
            }catch (IOException e){
                logger.error("read fileLine error,msg: {}",e.getMessage());
            }
            return mapResult;
        }).get();
    }
}
