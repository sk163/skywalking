package com.a.eye.skywalking.collector.worker.application.persistence;

import com.a.eye.skywalking.collector.actor.AbstractLocalAsyncWorkerProvider;
import com.a.eye.skywalking.collector.actor.ClusterWorkerContext;
import com.a.eye.skywalking.collector.actor.selector.RollingSelector;
import com.a.eye.skywalking.collector.actor.selector.WorkerSelector;
import com.a.eye.skywalking.collector.worker.RecordPersistenceMember;
import com.a.eye.skywalking.collector.worker.WorkerConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author pengys5
 */
public class DAGNodePersistence extends RecordPersistenceMember {

    private Logger logger = LogManager.getFormatterLogger(DAGNodePersistence.class);

    public DAGNodePersistence(com.a.eye.skywalking.collector.actor.Role role, ClusterWorkerContext clusterContext) throws Exception {
        super(role, clusterContext);
    }

    @Override
    public String esIndex() {
        return "application";
    }

    @Override
    public String esType() {
        return "dag_node";
    }

    public static class Factory extends AbstractLocalAsyncWorkerProvider<DAGNodePersistence> {
        public static Factory INSTANCE = new Factory();

        @Override
        public Role role() {
            return Role.INSTANCE;
        }

        @Override
        public Class workerClass() {
            return DAGNodePersistence.class;
        }

        @Override
        public int queueSize() {
            return WorkerConfig.Queue.Persistence.DAGNodePersistence.Size;
        }
    }

    public static class Role extends com.a.eye.skywalking.collector.actor.Role {
        public static Role INSTANCE = new Role();

        @Override
        public String name() {
            return DAGNodePersistence.class.getSimpleName();
        }

        @Override
        public WorkerSelector workerSelector() {
            return new RollingSelector();
        }
    }
}