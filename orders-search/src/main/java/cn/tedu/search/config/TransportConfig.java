package cn.tedu.search.config;

import java.net.InetAddress;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="es")
public class TransportConfig {
	private List<String> nodes;
	
	@Bean
	public TransportClient initTclient(){
		TransportClient client=new 
		PreBuiltTransportClient(Settings.EMPTY);//配置默认
		for (String node : nodes) {
			try{
				//解析ip和端口
				String host=node.split(":")[0];
				int port=Integer.parseInt(node.split(":")[1]);
				InetAddress address =
						InetAddress.getByName(host);
				//代码端口9300
				client.addTransportAddress
				(new InetSocketTransportAddress(address, port));
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return client;
	}

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	
	
}
