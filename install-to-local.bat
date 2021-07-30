@echo off
@rem publish to local repo
call mvn clean package

call mvn install:install-file -Dfile="flow-protocol-api-nft/target/flow-protocol-api-nft-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-api-nft/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-protocol-api-nft-order/target/flow-protocol-api-nft-order-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-api-nft-order/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-protocol-api-order/target/flow-protocol-api-order-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-api-order/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-protocol-model-common/target/flow-protocol-model-common-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-model-common/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-protocol-model-nft/target/flow-protocol-model-nft-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-model-nft/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-protocol-model-order/target/flow-protocol-model-order-2.0.0-SNAPSHOT.jar" -DpomXml="flow-protocol-model-order/pom.xml" -Dversion="2.0.0-SNAPSHOT"

call mvn install:install-file -Dfile="flow-api/target/flow-api-2.0.0-SNAPSHOT.jar" -DpomXml="flow-api/pom.xml" -Dversion="2.0.0-SNAPSHOT"
