package com.hongminji.idus.config.replication;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
//        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";

        if(TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            logger.info("Connnect Slave");
            return "slave";
        }else {
            logger.info("Connnect Master");
            return "master";
        }
    }

}
