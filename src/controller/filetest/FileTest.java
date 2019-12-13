package controller.filetest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;




public class FileTest {
	
	private File filePath;
	
	
	@Test
	public void settingUnitFileTest() {
		Path pt= Paths.get(new File("").getAbsolutePath()+"/resources/setting_files/unit_setting.txt");
		org.junit.Assert.assertTrue(Files.exists(pt));
	}
	
	@Test
	public void settingLevelFileTest() {
		Path pt= Paths.get(new File("").getAbsolutePath()+"/resources/setting_files/level_setting.txt");
		org.junit.Assert.assertTrue(Files.exists(pt));
	}
	
}
