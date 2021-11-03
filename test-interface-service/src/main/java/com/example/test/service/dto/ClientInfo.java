package com.example.test.service.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * xcenter 客户端信息
 *
 * @author sy
 * @version
 */
@Setter
@Getter
@ToString
public class ClientInfo {

        private String clientId;

        private String clientType;

        private String nodeName;

        private Long orgId;

        private Long envId;

        private Long appId;

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public void setOrgId(Long orgId) {
            this.orgId = orgId;
        }

        public void setEnvId(Long envId) {
            this.envId = envId;
        }

        public void setAppId(Long appId) {
            this.appId = appId;
        }

}
