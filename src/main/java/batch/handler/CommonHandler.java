package batch.handler;

import batch.base.IDataLegacy;
import batch.util.FileUtility;
import com.aries.extension.handler.BatchHandler;
import com.aries.extension.util.LogUtil;
import com.aries.extension.util.PropertyUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonHandler implements BatchHandler, IDataLegacy {
    private static final String DEFAULT_DIRECTORY = "../batch";

    protected boolean initHandler(File file, StringBuilder head) {
        String dirName = PropertyUtil.getValue(getExtensionId(), "dir_name", DEFAULT_DIRECTORY);

        File dir = new File(dirName);
        if(!dir.exists()) {
            dir.mkdir();
        }

        if(file.exists()) {
            file.delete();
        }

        List<String> records = new ArrayList<String>();
        records.add(head.toString());
        writeFileContent(file, records, false);

        LogUtil.info("File \"" + file.getName() + "\" is created!");
        return true;
    }

    protected void writeFileContent(File file, List<String> records, boolean append) {
        try {
            FileUtility.writeBuffered(file, records, 1024, append);
        } catch (IOException e) {
            LogUtil.error(e.getMessage());
        }
    }

    protected File createFilePath(long batchTime, String optFileName) {
        String dirName = PropertyUtil.getValue(getExtensionId(), "dir_name", DEFAULT_DIRECTORY);
        String fileName = PropertyUtil.getValue(getExtensionId(), "file_name", optFileName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return new File(dirName + File.separator + fileName + "_" + sdf.format(batchTime) + ".csv");
    }
}
