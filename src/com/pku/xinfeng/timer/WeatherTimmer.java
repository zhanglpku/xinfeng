package com.pku.xinfeng.timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pku.xinfeng.model.Pm;
import com.pku.xinfeng.model.Temperature;
import com.pku.xinfeng.service.WeatherService;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.ResourceProperties;

@Component
@Lazy(false) 
public class WeatherTimmer{
	static final String username = ResourceProperties.getPropertyValue(
			"config.persistence.config", "jdbc_username");
	static final String password = ResourceProperties.getPropertyValue(
			"config.persistence.config", "jdbc_password");
	static final String basename = ResourceProperties.getPropertyValue(
			"config.persistence.config", "jdbc_name");
	
	@Autowired
	private WeatherService weatherService;
	
	// 0 0 08,11,18 * * ? 每天上午8点，下午11点，18点
	// 0/5 * * * * ? 间隔5秒执行
	// 0 0 0/1 * * ?
	// 0 0/65 * * * ?每65分钟执行
	// [秒] [分] [小时] [日] [月] [周] [年]
	//	1. 秒（0~59）
	//	2. 分钟（0~59）
	//	3. 小时（0~23）
//	@Scheduled(cron = "0/20 * * * * ? ")
	@Scheduled(fixedRate = 1000 * 60 * 60)  //fixedRate 是执行频率单位秒,1000=1秒,5000=每5秒执行一次
	public void updatePmTimer() {
		System.out.println("-------updateWeatherTimer--自动更新-%%%----------");
		System.out.println(DateUtil.formatDateTime(new Date()));
		
		// 1查询pm接口,2全部删除,3保存数据
		List<Pm> list = weatherService.getPmFromInterface();
		weatherService.insertPmBatch(list);
		
		System.out.println("-------updateWeatherTimer--end-----------");
	}
	
	//每天8点、11点更新
	@Scheduled(cron = "0 0 08,11,18 * * ?")
	public void updateTempertureTimer() {
		System.out.println("-------updateTempertureTimer---begin--------");
		System.out.println(DateUtil.formatDateTime(new Date()));
		// 1从pm表中查询出所有城市,2查询天气接口,3查询所有温度等信息,4全部删除,5保存数据，为空的字段取上次的值
		List<Temperature> list = weatherService.getTempFromInterface();
		weatherService.insertTempBatch(list);
		System.out.println("-------updateTempertureTimer--end-----------");
	}
	
	//0 59 23 ? * FRI 表示每个星期五23：59点 
	@Scheduled(cron = "0 59 23 ? * FRI")
	public void backLinux() {
		System.out.println("**********backLinux*******begin***************");
		InputStream in = null;
		try {
			Runtime rt = Runtime.getRuntime();
			String dumstr = "mysqldump --user=" + username + " --password=" + password 
					+ " --opt " + basename + " > /usr/local/backup_xinfeng/xinfeng"
					+ DateUtil.formatDateTime2(new Date()) + ".sql";
			
			Process pro = rt.exec(new String[]{"/bin/sh","-c",dumstr});  
	        pro.waitFor();  
	        in = pro.getInputStream();  
	        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
	        String line = null;  
	        while((line = read.readLine())!=null){  
	            System.out.println(line);  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("**********backLinux*******end*****************");
	}
}
