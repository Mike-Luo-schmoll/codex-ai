package com.schmoll.tlkw.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ClsPropertySet
{

	protected Properties properties;
	private String propertyFileName;
	public ClsPropertySet() 
	{
		properties = new Properties();
	}

	/** Constructor with configuration file */
	public ClsPropertySet(String flName) 
	{
		properties = new Properties();
		loadPropertiesFrom(flName);
		propertyFileName = flName;
	}
	/** 带上字符集编码解析文件,让中文不会乱码 **/
	public ClsPropertySet(String flName, Charset encode)
	{
		properties = new Properties();
		loadPropertiesFrom(flName,encode);
		propertyFileName = flName;
	}
	public boolean loadPropertiesFrom(String flName,Charset encode)
	{
		return loadProperties(flName,encode);
	}

	protected boolean loadProperties(String flName,Charset encode)
	{
		InputStreamReader reader = null;

		try
		{
			if (flName == null)
			{
				flName = propertyFileName;
			}

			reader = new InputStreamReader(Files.newInputStream(Paths.get(flName)), encode);
			properties.load(reader);
			propertyFileName = flName;
			reader.close();
			return true;
		}
		catch (IOException ie)
		{
			System.err.println(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS").format(new Date().getTime()) + " " + "Can't load config file: " + flName + " (" + ie + ")");

			try
			{
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (IOException ignored)
			{

			};

			return false;
		}
	}

	public Properties getProperties()
	{
		return properties;
	}

	/** set the file name */
	public void setFileName(String flName) 
	{
		propertyFileName = flName;
	}

	/** Read property values ​​from a file */
	public boolean loadPropertiesFrom(String flName) 
	{
		return loadProperties(flName);
	}

	/** Save configuration values ​​in the current file */
	public boolean saveProperties() 
	{
		return saveProperties(propertyFileName);
	}

	/** Save configuration values ​​to a new file */
	public boolean savePropertiesTo(String flName) 
	{
		return saveProperties(flName);
	}

	public boolean savePropertiesTo(String path, String flName)
	{
		return saveProperties(path+flName);
	}

	/** return the current path name */
	public String getPropertyFileName() 
	{
		return propertyFileName;
	}

	/** read access to the configuration data */
	/** ======================================================================== */
	/** Load configuration value as a string */
	public String getPropertyStr(String key, String def)
	{
		if (def != null)
		{
			return properties.getProperty(key, def);
		}
		else
		{
			return properties.getProperty(key);
		}
	}

	/** Load configuration value as Boolean */
	public Boolean getPropertyBool(String key, Boolean def) 
	{
		String str;

		if (def != null)
		{
			str = properties.getProperty(key, def.toString());
		}
		else
		{
			str = properties.getProperty(key);
		}

		return Boolean.valueOf(str);
	}

	/** Load configuration value as an integer */
	public Integer getPropertyInt(String key, Integer def) 
	{
		String str;

		if (def != null)
		{
			str = properties.getProperty(key, def.toString());
		}
		else
		{
			str = properties.getProperty(key);
		}

		if (str == null)
		{
			return null;
		}
		else 
		{
			try 
			{
				return Integer.valueOf(str.trim());
			}
			catch (NumberFormatException nfe)
			{
//				System.out.println(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS").format(new Date().getTime()) + " " + nfe);
				return null;
			}
		}
	}
	
	/** Load configuration value as long */
	public Long getPropertyLong(String key, Long def) 
	{
		String str;

		if (def != null)
		{
			str = properties.getProperty(key, def.toString());
		}
		else
		{
			str = properties.getProperty(key);
		}

		if (str == null)
		{
			return null;
		}
		else
		{
			try
			{
				return new Long(str);
			}
			catch (NumberFormatException nfe)
			{
				return null;
			}
		}
	}

	/** Load configuration value as float */
	public Float getPropertyFloat(String key, Float def) 
	{
		String str;

		if (def != null)
		{
			str = properties.getProperty(key, def.toString());
		}
		else
		{
			str = properties.getProperty(key);
		}

		if (str == null)
		{
			return null;
		}
		else
		{
			try 
			{
				return Float.valueOf(str);
			}
			catch (NumberFormatException nfe) 
			{
				return null;
			}
		}
	}

	/** write access to the configuration data */
	/** ======================================================================== */
	/** save string as configuration value */
	public boolean setPropertyStr(String key, String val) 
	{
		return properties.setProperty(key, val) != null;
	}

	/** Save Boolean as configuration value */
	public boolean setPropertyBool(String key, boolean val)
	{
		String bool;

		if (val)
		{
			bool = "true";
		}
		else
		{
			bool = "false";
		}

		return properties.setProperty(key, bool) != null;
	}

	/** Save integer as configuration value */
	public boolean setPropertyInt(String key, int val) 
	{
		return properties.setProperty(key, Integer.toString(val)) != null;
	}

	/** save float as configuration value */
	public boolean setPropertyFloat(String key, float val)
	{
		return properties.setProperty(key, Float.toString(val)) != null;
	}

	/** auxiliary methods */
	/** ======================================================================== */
	/**  Read property values ​​from a file */
	protected boolean loadProperties(String flName) 
	{
		FileInputStream sf = null;

		try 
		{
			if (flName == null)
			{
				flName = propertyFileName;
			}

			sf = new FileInputStream(flName);
			properties.load(sf);
			propertyFileName = flName;
			sf.close();
			return true;
		}
		catch (IOException ie)
		{
			System.err.println(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS").format(new Date().getTime()) + " " + "Can't load config file: " + flName + " (" + ie + ")");

			try 
			{
				if (sf != null)
				{
					sf.close();
				}
			}
			catch (IOException ioe)
			{

			};

			return false;
		}
	}

	/** auxiliary methods */
	/** ======================================================================== */
	/**  Read property values ​​from a file */
	protected boolean loadPropertiesStream(String flName) 
	{
		InputStream sf = null;

		try 
		{
			if (flName == null)
			{
				flName = propertyFileName;
			}
			//sf = new FileInputStream(flName);
//			sf = MainFrame.class.getClassLoader().getResourceAsStream(flName);
			properties.load(sf);
			propertyFileName = flName;
			sf.close();
			return true;
		}
		catch (IOException ie)
		{
			System.err.println(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS").format(new Date().getTime()) + " " + "can't load config file: " + flName + " (" + ie + ")");

			try 
			{
				if (sf != null)
				{
					sf.close();
				}
			}
			catch (IOException ioe)
			{

			};

			return false;
		}
	}
	
	/** Save configuration values in a new file */
	protected boolean saveProperties(String flName) 
	{
		FileOutputStream sf = null;

		try 
		{
			sf = new FileOutputStream(flName);
			properties.store(sf, null);
			sf.close();
			return true;
		}
		catch (IOException ie) 
		{
			System.err.println(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS").format(new Date().getTime()) + " " + "can't save config file: " + flName + " (" + ie + ")");
			try 
			{
				if (sf != null)
				{
					sf.close();
				}
			}
			catch (IOException ioe)
			{

			};

			if (sf != null)
			{
				try 
				{
					sf.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			return false;
		}
	}
}

