// @author Dawid Tarasienko
package poetradeonlinestatus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class SaveURL
{
	private String directoryPath, filePath;
	
	
	public SaveURL()
	{
		String userName = System.getProperty("user.name");
		directoryPath = "C:/Users/" + userName + "/Documents";
		Path path = new File(directoryPath).toPath();
		if(!Files.isDirectory(path)) // If system isn't a Windows.
		{
			directoryPath = "";
		}
		filePath = directoryPath + "/PoE Trade Online Status - Saved URL.txt";
	}

	public void saveFile(String url)
	{
		try
		{
			Path path = new File(directoryPath).toPath();
			if(Files.isDirectory(path))
			{
				File file = new File(filePath);
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(url);
				writer.close();
			}
		}
		catch(Exception e)
		{ }
	}

	public void deleteFile()
	{
		try
		{
			Path path = new File(filePath).toPath();
			Files.delete(path);
		}
		catch(Exception e)
		{ }
	}

	public String loadFile()
	{
		try
		{
			File file = new File(directoryPath);
			if(file.exists())
			{
				Scanner in = new Scanner(new FileReader(filePath));
				if(in.hasNext())
				{
					String url = in.next();
					in.close();
					return url;
				}
				else
				{
					in.close();
				}
			}
		}
		catch(Exception e)
		{ }
		return null;
	}
}
