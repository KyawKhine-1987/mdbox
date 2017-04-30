package com.witts.mdbox.util;

import android.content.res.Resources;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";
	
	public static interface DateFormat {
		public final static String DATE_FORMAT_yyyyMMdd="yyyyMMdd";
        public final static String DATE_FORMAT_yyyyMMdd2="yyyy/MM/dd";
		public final static String DATE_FORMAT_HHmmss="HHmmss";
		public final static String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss_s="yyyy-MM-dd HH:mm:ss.s";
		public final static String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss="yyyyMMdd HHmmss";
		public final static String DATE_FORMAT_yyyy_MM_dd="yyyy-MM-dd";
		public final static String DATE_FORMAT_dd_MM_yyyy="dd/MM/yyyy";
		public final static String DATE_FORMAT_dd_MMM_yyyy="dd MMM yyyy";
		public final static String DATE_FORMAT_dd_MM_yyyy_HH_mm_ss="dd/MM/yyyy HH:mm:ss";
		public final static String DATE_FORMAT_dd_MMM_yyyy_HH_mm="dd MMM yyyy, hh:mm aa";
        public final static String DATE_PICKER_DIALOG_TITLE_FORMAT = "E, dd MMM yyyy";
        public final static String DATE_FORMAT_HUMAN_READABLE = "dd E yyyy";
		public final static String DATE_FORMAT_TIME = "HH:mm:ss";
	}
	public static boolean isEmpty(String str){
		if(str!=null){
			return "".equals(str.trim());
		}
		return true;
	}

	public static String convertDateToString(Date date, String format){
		if(date!=null){
			SimpleDateFormat dateFormate=new SimpleDateFormat(format);
			return dateFormate.format(date);
		}else{
			return null;
		}
	}
	
	public static String formatDateString(String date, String fromFormat, String toFormat){
		SimpleDateFormat fromFormate=new SimpleDateFormat(fromFormat);
		SimpleDateFormat toFormate=new SimpleDateFormat(toFormat);
		String value="";
		try {
			Date d=fromFormate.parse(date);
			value= toFormate.format(d);
		} catch (ParseException e) {
			
		}		
		return value;
	}

    public static String formatDate(Date date, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static String formatDate(Date date, String formatStr, Locale locale){
        SimpleDateFormat format = new SimpleDateFormat(formatStr, locale);
        return format.format(date);

    }

    public static Date parseDate(String date, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static boolean isProfileVerified(String verifiedInd){
		if("Y".equalsIgnoreCase(verifiedInd))
			return true;
		else
			return false;		
	}

    public static SpannableString createIndentedText(String text, int marginFirstLine, int marginNextLines) {
        SpannableString result=new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginNextLines),0,text.length(),0);
        return result;
    }

    public static boolean isEmail(String str){
        return str.matches(EMAIL_PATTERN);
    }

    public static boolean isNumber(String str){
        return str.matches(NUMBER_PATTERN);
    }

	public static int pxToDp(Resources r, int px) {
		DisplayMetrics displayMetrics = r.getDisplayMetrics();
		return Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	public static int dpToPx(Resources r, int dp) {
		DisplayMetrics displayMetrics = r.getDisplayMetrics();
		return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	/** Returns the consumer friendly device name */
	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		}
		return capitalize(manufacturer) + " " + model;
	}

	private static String capitalize(String str) {
		if (TextUtils.isEmpty(str)) {
			return str;
		}
		char[] arr = str.toCharArray();
		boolean capitalizeNext = true;

		StringBuilder phrase = new StringBuilder();
		for (char c : arr) {
			if (capitalizeNext && Character.isLetter(c)) {
				phrase.append(Character.toUpperCase(c));
				capitalizeNext = false;
				continue;
			} else if (Character.isWhitespace(c)) {
				capitalizeNext = true;
			}
			phrase.append(c);
		}

		return phrase.toString();
	}

	public static String parseInputOutputDate(String time, String inputPattern, String outputPattern) {
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
		SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

		Date date = null;
		String str = null;

		try {
			date = inputFormat.parse(time);
			str = outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static float convertDpToPixel(Resources r, float dp) {
		DisplayMetrics metrics = r.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}
}
