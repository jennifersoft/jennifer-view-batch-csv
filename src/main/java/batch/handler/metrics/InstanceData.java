package batch.handler.metrics;

import batch.handler.CommonHandler;
import com.aries.extension.data.BatchData;
import com.aries.extension.data.batch.MetricsDataAsInstance;
import com.aries.extension.util.LogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InstanceData extends CommonHandler {
    private static File csvFile = null;

    @Override
    public boolean preHandle(long batchTime) {
        csvFile = createFilePath(batchTime, "METRICS_AS_INSTANCE");

        StringBuilder head = new StringBuilder();
        head.append("\"domainId\",");
        head.append("\"domainName\",");
        head.append("\"instanceId\",");
        head.append("\"instanceName\",");
        head.append("\"standardTime\",");
        head.append("\"gcTime\",");
        head.append("\"gcCount\",");
        head.append("\"fileCount\",");
        head.append("\"finalizePending\",");
        head.append("\"heapCommitted\",");
        head.append("\"heapUsed\",");
        head.append("\"heapUsage\",");
        head.append("\"nonHeapUsed\",");
        head.append("\"systemCpu\",");
        head.append("\"systemMemory\",");
        head.append("\"systemMemoryRate\",");
        head.append("\"processCpu\",");
        head.append("\"processMemory\",");
        head.append("\"threadDaemon\",");
        head.append("\"threadCurrent\",");
        head.append("\"threadStarted\",");
        head.append("\"collectionCount\",");
        head.append("\"socketCount\",");
        head.append("\"serviceCount\",");
        head.append("\"serviceTime\",");
        head.append("\"serviceErrorCount\",");
        head.append("\"serviceSlowCount\",");
        head.append("\"serviceMethodTime\",");
        head.append("\"serviceSqlTime\",");
        head.append("\"serviceExternalCallTime\",");
        head.append("\"serviceFetchTime\",");
        head.append("\"serviceRate\",");
        head.append("\"serviceCpu\",");
        head.append("\"activeService\",");
        head.append("\"activeUser\",");
        head.append("\"userRequestInterval\",");
        head.append("\"concurrentUser\",");
        head.append("\"visitHour\",");
        head.append("\"visitDay\",");
        head.append("\"eventNormalCount\",");
        head.append("\"eventWarningCount\",");
        head.append("\"eventFatalCount\",");
        head.append("\"eventCount\",");
        head.append("\"errorCount\",");
        head.append("\"sqlCount\",");
        head.append("\"sqlTime\",");
        head.append("\"externalCallCount\",");
        head.append("\"externalCallTime\",");
        head.append("\"fetchCount\",");
        head.append("\"fetchTime\",");
        head.append("\"frontendMeasureCount\",");
        head.append("\"averageFrontendTime\",");
        head.append("\"averageFrontendNetworkTime\",");
        head.append("\"aliveAgent\",");
        head.append("\"averageDbPoolActiveCount\",");
        head.append("\"averageDbPoolIdleCount\",");
        head.append("\"maxDbPoolActiveCount\",");
        head.append("\"maxTps\"\n");

        return initHandler(csvFile, head);
    }

    @Override
    public void process(BatchData[] models) {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<String> records = new ArrayList<String>();

        for(int i = 0; i < models.length; i++) {
            StringBuilder statement = new StringBuilder();
            MetricsDataAsInstance data = (MetricsDataAsInstance) models[i];
            
            statement.append(data.domainId + ",");
            statement.append("\"" + data.domainName + "\"" + ",");
            statement.append(data.instanceId + ",");
            statement.append("\"" + data.instanceName + "\"" + ",");
            statement.append("\"" + sdf.format(data.standardTime) + "\"" + ",");
            statement.append(data.gcTime + ",");
            statement.append(data.gcCount + ",");
            statement.append(data.fileCount + ",");
            statement.append(data.finalizePending + ",");
            statement.append(data.heapCommitted + ",");
            statement.append(data.heapUsed + ",");
            statement.append(data.heapUsage + ",");
            statement.append(data.nonHeapUsed + ",");
            statement.append(data.systemCpu + ",");
            statement.append(data.systemMemory + ",");
            statement.append(data.systemMemoryRate + ",");
            statement.append(data.processCpu + ",");
            statement.append(data.processMemory + ",");
            statement.append(data.threadDaemon + ",");
            statement.append(data.threadCurrent + ",");
            statement.append(data.threadStarted + ",");
            statement.append(data.collectionCount + ",");
            statement.append(data.socketCount + ",");
            statement.append(data.serviceCount + ",");
            statement.append(data.serviceTime + ",");
            statement.append(data.serviceErrorCount + ",");
            statement.append(data.serviceSlowCount + ",");
            statement.append(data.serviceMethodTime + ",");
            statement.append(data.serviceSqlTime + ",");
            statement.append(data.serviceExternalCallTime + ",");
            statement.append(data.serviceFetchTime + ",");
            statement.append(data.serviceRate + ",");
            statement.append(data.serviceCpu + ",");
            statement.append(data.activeService + ",");
            statement.append(data.activeUser + ",");
            statement.append(data.userRequestInterval + ",");
            statement.append(data.concurrentUser + ",");
            statement.append(data.visitHour + ",");
            statement.append(data.visitDay + ",");
            statement.append(data.eventNormalCount + ",");
            statement.append(data.eventWarningCount + ",");
            statement.append(data.eventFatalCount + ",");
            statement.append(data.eventCount + ",");
            statement.append(data.errorCount + ",");
            statement.append(data.sqlCount + ",");
            statement.append(data.sqlTime + ",");
            statement.append(data.externalCallCount + ",");
            statement.append(data.externalCallTime + ",");
            statement.append(data.fetchCount + ",");
            statement.append(data.fetchTime + ",");
            statement.append(data.frontendMeasureCount + ",");
            statement.append(data.averageFrontendTime + ",");
            statement.append(data.averageFrontendNetworkTime + ",");
            statement.append(data.aliveAgent + ",");
            statement.append(data.averageDbPoolActiveCount + ",");
            statement.append(data.averageDbPoolIdleCount + ",");
            statement.append(data.maxDbPoolActiveCount + ",");
            statement.append(data.maxTps + "\n");
            records.add(statement.toString());
        }

        writeFileContent(csvFile, records, true);
        LogUtil.info(models.length + " Batch data inserted! (" + (System.currentTimeMillis() - time) + "ms)");
    }

    @Override
    public String getExtensionId() {
        return "metrics_as_instance.csv";
    }
}
