package com.example.test.service;


import com.example.test.service.farend.CommandModule;
import com.example.test.service.delegate.util.JsonUtils;
import com.example.test.service.dto.ClientInfo;
import com.example.test.service.dto.ReportMessage;
import com.example.test.service.emum.ExeEventEnum;
import com.perfma.xcenter.client.Configuration;
import com.perfma.xcenter.client.XCenterClient;
import com.perfma.xcenter.client.command.CommandManager;
import com.perfma.xcenter.client.filetransfer.model.UploadRequest;
import com.perfma.xcenter.client.filetransfer.model.UploadResult;
import com.perfma.xcenter.client.message.Message;
import com.perfma.xcenter.client.message.MessageManager;

import java.util.Date;
import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;



/**
 *  Xcenter代理
 *
 * @author shenyuan
 * @version v0.1 2020/08/09 12:18
 */


@Slf4j
@Component
public class XcenterProxy {

    @Reference
    private List<CommandModule> commandModuleList;

    private XCenterClient client;


    /**
     * 开始注册上报（本机）执行机
     * @param
     * @return 无
     */

    public String start() {
        Configuration configuration = new Configuration();
        configuration.setClientType("ToceanXengine");
        configuration.setServerAddress("10.10.20.199:17951");
        if (true)
            configuration.addExtension("orgCode", "testOrgcet");
        configuration.addExtension("appCode", "test-inface");
        configuration.addExtension("delegateVersion", "1.1.1");
        this.client = new XCenterClient(configuration);
        this.client.start();
        setCommandManager(this.client);
        return  this.client.getClientId();
    }

    public <T> void sendMessage(String topic, T data, ExeEventEnum eventEnum) {
        if (!this.client.isRegistered()) {
            log.error("{},data:{}]", topic, data);
            return;
        }
        MessageManager messageManager = this.client.getMessageManager();
        ClientInfo clientInfo = new ClientInfo();
        BeanUtils.copyProperties(this.client.getClientInfo(), clientInfo);
        clientInfo.setClientType("ToceanXengine");
        clientInfo.setOrgId(Long.valueOf(1L));
        ReportMessage<T> reportMessage = new ReportMessage();
        reportMessage.setData(data);
        reportMessage.setClientInfo(clientInfo);
        reportMessage.setReportTime(new Date());
        reportMessage.setEvent(eventEnum);
        Message message = new Message(topic, JsonUtils.objToJsonString(reportMessage));
        messageManager.send(message);
    }

    public String uploadFile(byte[] data) {
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setSource(data);
        UploadResult result = this.client.getFileTransferManager().upload(uploadRequest);
        return result.getFileKey();
    }
    private void setCommandManager(XCenterClient client) {
        CommandManager commandManager = client.getCommandManager();
        if (!CollectionUtils.isEmpty(this.commandModuleList))
            this.commandModuleList.forEach(commandManager::addCommand);
    }


}