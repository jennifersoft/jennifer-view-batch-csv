package batch.handler.service;

import batch.handler.CommonHandler;
import com.aries.extension.data.batch.ApplicationServiceData;
import com.aries.extension.data.BatchData;
import com.aries.extension.util.LogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ApplicationData extends CommonHandler {
    private static File csvFile = null;

    @Override
    public boolean preHandle(long batchTime) {
        csvFile = createFilePath(batchTime, "APPLICATION_SERVICE");

        StringBuilder head = new StringBuilder();
        head.append("\"domainId\",");
        head.append("\"domainName\",");
        head.append("\"instanceId\",");
        head.append("\"instanceName\",");
        head.append("\"standardTime\",");
        head.append("\"applicationName\",");
        head.append("\"callCount\",");
        head.append("\"responseTime\",");
        head.append("\"failureCount\",");
        head.append("\"badResponseCount\",");
        head.append("\"sqlCountPerTransaction\",");
        head.append("\"sqlTimePerTransaction\",");
        head.append("\"sqlTimeSum\",");
        head.append("\"externalCallCountPerTransaction\",");
        head.append("\"externalCallTimePerTransaction\",");
        head.append("\"externalCallTimeSum\",");
        head.append("\"fetchTimePerTransaction\",");
        head.append("\"frontEndTimePerTransaction\",");
        head.append("\"frontEndNetworkTimePerTransaction\",");
        head.append("\"cpuTimePerTransaction\",");
        head.append("\"frontEndMeasureCount\",");
        head.append("\"methodTimePerTransaction\",");
        head.append("\"maxResponseTime\",");
        head.append("\"responseTimeStandardDeviation\"\n");

        return initHandler(csvFile, head);
    }

    @Override
    public void process(BatchData[] models) {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<String> records = new ArrayList<String>();

        for(int i = 0; i < models.length; i++) {
            StringBuilder statement = new StringBuilder();
            ApplicationServiceData data = (ApplicationServiceData) models[i];
            String name = data.applicationName;

            statement.append(data.domainId + ",");
            statement.append("\"" + data.domainName + "\"" + ",");
            statement.append(data.instanceId + ",");
            statement.append("\"" + data.instanceName + "\"" + ",");
            statement.append("\"" + sdf.format(data.standardTime) + "\"" + ",");
            statement.append("\"" + ((name.length() > 500) ? name.substring(0, 500) : name) + "\"" + ",");
            statement.append(data.callCount + ",");
            statement.append(data.responseTime + ",");
            statement.append(data.failureCount + ",");
            statement.append(data.badResponseCount + ",");
            statement.append(data.sqlCountPerTransaction + ",");
            statement.append(data.sqlTimePerTransaction + ",");
            statement.append(data.sqlTimeSum + ",");
            statement.append(data.externalCallCountPerTransaction + ",");
            statement.append(data.externalCallTimePerTransaction + ",");
            statement.append(data.externalCallTimeSum + ",");
            statement.append(data.fetchTimePerTransaction + ",");
            statement.append(data.frontEndTimePerTransaction + ",");
            statement.append(data.frontEndNetworkTimePerTransaction + ",");
            statement.append(data.cpuTimePerTransaction + ",");
            statement.append(data.frontEndMeasureCount + ",");
            statement.append(data.methodTimePerTransaction + ",");
            statement.append(data.maxResponseTime + ",");
            statement.append(data.responseTimeStandardDeviation + "\n");
            records.add(statement.toString());
        }

        writeFileContent(csvFile, records, true);
        LogUtil.info(models.length + " Batch data inserted! (" + (System.currentTimeMillis() - time) + "ms)");
    }

    @Override
    public String getExtensionId() {
        return "application_service.csv";
    }
}
