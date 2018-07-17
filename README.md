## Overview
It is an extension function to backup JENNIFER data to CSV File.


## Getting started

The first step is to register the adapter: 
1. In JENNIFER Client, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the adapter tab is selected then click the **[+Add]** button
3. Select the data classification.
4. Enter an ID according to the data classification. The ID types are discussed in detail below.
5. Enter the path to the adapter JAR file ``jennifer-view-batch-csv.jar`` or upload the JAR file from you local machine.
6. Enter the class name according to the data classification. Class types are discussed in detail below.
 
<img src="https://raw.githubusercontent.com/jennifersoft/jennifer-extension-manuals/master/res/img/view_server_batch/1.png">


### ID and class name mapping table for data classification ##

The following table shows the required options for this adapter

| Classification | ID | Class names |
| ------------- |:-------------:|:-------------:|
| METRICS_AS_INSTANCE | metrics_as_instance.csv | batch.handler.metrics.InstanceData |
| METRICS_AS_BUSINESS | metrics_as_business.csv | batch.handler.metrics.BusinessData |
| METRICS_AS_DOMAIN | metrics_as_domain.csv | batch.handler.metrics.DomainData |
| APPLICATION_SERVICE_DAILY | application_service.csv | batch.handler.service.ApplicationData |
| APPLICATION_SERVICE_HOURLY | application_service.csv | batch.handler.service.ApplicationData |


### Options for database connections ##

The following table shows the required options for this batch

| Key           | Required      | Default |
| ------------- |:-------------:|:-------------:|
| dir_name | NO | `../batch` |
| file_name | NO | |

<img src="https://github.com/jennifersoft/jennifer-extension-manuals/blob/master/res/img/view_server_batch/2.png">


### Top toolbar description ##

| Name | Description |
| ------------- |:-------------:|
| Manual Build | Daily backups can be done manually. |
| Auto build settings | It is an automatic build related setting. |
| Show build logs | You can view the manual and automatic build logs. |


### Supported version ##
 
| Adapter version           | Jennifer server version |
| ------------- |:-------------:|
| 0.0.1       | Greater than or equal to version 5.4.0 |
