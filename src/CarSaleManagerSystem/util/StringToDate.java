package CarSaleManagerSystem.util;

import com.sun.istack.internal.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hasee on 2016/10/4.
 */
public class StringToDate
{
	static public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

	@Nullable
	static public Date get(String format)
	{
		try
		{
			Date date = simpleDateFormat.parse(format);
			return date;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
