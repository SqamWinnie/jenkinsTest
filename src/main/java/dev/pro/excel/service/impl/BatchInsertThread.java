package dev.pro.excel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import dev.pro.excel.dto.PartyInfo;
import dev.pro.excel.mapper.PartyInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 11861
 */
public class BatchInsertThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(BatchInsertThread.class);

    private PartyInfoMapper partyInfoMapper;
    private List<PartyInfo> partyInfoList;
    private int pageSize;

    public BatchInsertThread(PartyInfoMapper partyInfoMapper, List<PartyInfo> partyInfoList, int pageSize) {
        this.partyInfoMapper = partyInfoMapper;
        this.partyInfoList = partyInfoList;
        this.pageSize = pageSize;
    }

    @Override
    public void run() {
        final ReentrantLock lock = new ReentrantLock();
        while (!partyInfoList.isEmpty()) {
            lock.lock();
            List<PartyInfo> list = new ArrayList<PartyInfo>(pageSize);
            list.clear();
            if (partyInfoList.size() < pageSize) {
                partyInfoMapper.batchInsert(partyInfoList);
                partyInfoList.clear();
            } else {
                list.addAll(partyInfoList.subList(0, pageSize));
                partyInfoList.subList(0, pageSize).clear();
                partyInfoMapper.batchInsert(list);
            }
            lock.unlock();
        } // while
    }
}
