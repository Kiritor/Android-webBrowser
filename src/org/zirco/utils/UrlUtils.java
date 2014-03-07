/*
 * Zirco Browser for Android
 * 
 * Copyright (C) 2010 J. Devauchelle and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package org.zirco.utils;

import java.util.Iterator;

import org.zirco.controllers.Controller;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * 
 * @author LCore
 * @date  2014-03-07
 *  
 *  
 *  
 *    URL工具类
 */
public class UrlUtils {
    
	/**
	 * 验证字符串是否为URL
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		return url.equals(Constants.URL_ABOUT_BLANK) ||
			url.equals(Constants.URL_ABOUT_START) ||
			url.contains(".");
	}
	
	/**
	 * 得到当前需要链接的URL
	 * @param context
	 * @param searchTerms
	 * @return
	 */
	public static String getSearchUrl(Context context, String searchTerms) {
		String currentSearchUrl = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.PREFERENCES_GENERAL_SEARCH_URL, Constants.URL_SEARCH_GOOGLE);
		return String.format(currentSearchUrl, searchTerms);
	}
	
	/**
	 * 重新包装URL地址(加上http头)
	 * @param url
	 * @return string
	 */
	public static String checkUrl(String url) {
		if ((url != null) &&
    			(url.length() > 0)) {
    	
    		if ((!url.startsWith("http://")) &&
    				(!url.startsWith("https://")) &&
    				(!url.startsWith("file://")) &&
    				(!url.startsWith(Constants.URL_ABOUT_BLANK)) &&
    				(!url.startsWith(Constants.URL_ABOUT_START))) {
    			
    			url = "http://" + url;
    			
    		}
		}
		
		return url;
	}
	
	/**
	 * 检查设备view url list是否存在给定的URL
	 * @param context
	 * @param url
	 * @return  if exist return true else false
	 */
	public static boolean checkInMobileViewUrlList(Context context, String url) {
		
		if (url != null) {
			boolean inList = false;
			Iterator<String> iter = Controller.getInstance().getMobileViewUrlList(context).iterator();			
			while ((iter.hasNext()) &&
					(!inList)) {
				if (url.contains(iter.next())) {
					inList = true;
				}
			}
			return inList;
		} else {
			return false;
		}
	}
	
}
