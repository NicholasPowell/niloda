package com.nilo.sam.lambda.cloudformation

class KmsResource {

    fun getResources(subnets: List<String>, securityGroups: List<String>) =
            """ 
                "TestCluster": {
                    "Type": "AWS::MSK::Cluster",
                    "Properties": {
                        "ClusterName": "ClusterWithRequiredProperties",
                        "KafkaVersion": "2.2.1",
                        "NumberOfBrokerNodes": 2,
                        "BrokerNodeGroupInfo": {
                            "SecurityGroups" : [
                                ${securityGroups.joinToString(","){"\"$it\"" }}
                            ],
                            "InstanceType": "kafka.t3.small",
                            "ClientSubnets": [
                                ${subnets.joinToString(","){"\"$it\""}}
                            ]
                        }
                    }
                }
            """.trimIndent()

}