package batch.handler.metrics;

import batch.handler.CommonHandler;
import com.aries.extension.data.batch.MetricsDataAsBusiness;
import com.aries.extension.data.BatchData;
import com.aries.extension.util.LogUtil;

import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessData extends CommonHandler {
    private static File csvFile = null;

    @Override
    public boolean preHandle(long batchTime) {
        csvFile = createFilePath(batchTime, "METRICS_AS_BUSINESS");

        StringBuilder head = new StringBuilder();
        head.append("\"domainId\",");
        head.append("\"domainName\",");
        head.append("\"businessId\",");
        head.append("\"businessName\",");
        head.append("\"standardTime\",");
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
        head.append("\"userRequestInterval\",");
        head.append("\"concurrentUser\",");
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
            MetricsDataAsBusiness data = (MetricsDataAsBusiness) models[i];
            
            statement.append(data.domainId + ",");
            statement.append("\"" + data.domainName + "\"" + ",");
            statement.append(data.businessId + ",");
            statement.append("\"" + data.businessName + "\"" + ",");
            statement.append("\"" + sdf.format(data.standardTime) + "\"" + ",");
            statement.append(data.serviceCount+ ",");
            statement.append(data.serviceTime+ ",");
            statement.append(data.serviceErrorCount+ ",");
            statement.append(data.serviceSlowCount+ ",");
            statement.append(data.serviceMethodTime+ ",");
            statement.append(data.serviceSqlTime+ ",");
            statement.append(data.serviceExternalCallTime+ ",");
            statement.append(data.serviceFetchTime+ ",");
            statement.append(data.serviceRate+ ",");
            statement.append(data.serviceCpu+ ",");
            statement.append(data.activeService+ ",");
            statement.append(data.userRequestInterval+ ",");
            statement.append(data.concurrentUser+ ",");
            statement.append(data.eventNormalCount+ ",");
            statement.append(data.eventWarningCount+ ",");
            statement.append(data.eventFatalCount+ ",");
            statement.append(data.eventCount+ ",");
            statement.append(data.errorCount+ ",");
            statement.append(data.sqlCount+ ",");
            statement.append(data.sqlTime+ ",");
            statement.append(data.externalCallCount+ ",");
            statement.append(data.externalCallTime+ ",");
            statement.append(data.fetchCount+ ",");
            statement.append(data.fetchTime+ ",");
            statement.append(data.frontendMeasureCount+ ",");
            statement.append(data.averageFrontendTime+ ",");
            statement.append(data.averageFrontendNetworkTime+ ",");
            statement.append(data.maxTps + "\n");
            records.add(statement.toString());
        }

        writeFileContent(csvFile, records, true);
        LogUtil.info(models.length + " Batch data inserted! (" + (System.currentTimeMillis() - time) + "ms)");
    }

    @Override
    public String getExtensionId() {
        return "metrics_as_business.csv";
    }
}
