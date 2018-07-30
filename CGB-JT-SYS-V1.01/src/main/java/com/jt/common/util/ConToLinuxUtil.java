package com.jt.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 连接并执行linux命令的工具类
 * @author BHT
 *
 */
public class ConToLinuxUtil {
	private static JSch jsch;
	private static Session session;
 
	/**
	 * 连接到指定的IP
	 * 
	 * @throws JSchException
	 */
	public static void connect(String user, String passwd, String host) throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, 22);
		session.setPassword(passwd);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();		
	}
 
	/**
	 * 执行相关的命令
	 * @throws JSchException 
	 */
	public static List<String> execCmd(String command, String user, String passwd, String host) throws JSchException {
		connect(user, passwd, host);
		BufferedReader reader = null;
		Channel channel = null;
		List<String> list = new ArrayList<>();
		try {
				// 对channel初始化，在使用之前必须执行channel.connect()
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);
				
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);
				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					list.add(new String(buf.getBytes("gbk"), "UTF-8"));
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
		return list;
	}
	public static void main(String[] args) throws JSchException, ParseException {

		String date = "2012-11-21";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date value = sdf.parse(date);
		System.out.println(value);
		//ConToLinuxUtil conToLinuxUtil = new ConToLinuxUtil();
		// tar cvpf /home/admin/test/add.tar b.txt c.txt
		// "cd test"+";"+"touch delete.txt"+";"
		//System.out.println(conToLinuxUtil.execCmd("echo -e nihao'\n'djshf >delete.txt", "admin", "admin", "192.168.47.129"));
	}
}