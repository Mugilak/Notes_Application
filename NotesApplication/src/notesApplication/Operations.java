package notesApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class Operations {

	public boolean create(String filePath) {
		boolean check = false;
		try {
			File file = new File(filePath);

			check = file.createNewFile();
			if (check) {
				System.out.println("Note created : " + file.getName());
				return check;
			}

		} catch (IOException e) {
			System.out.println("An error occurred...please try Again");
			e.printStackTrace();
		}
		return check;
	}

	public void doRead(String filePath, String fileName) throws FileNotFoundException {
		int eachChar;
		filePath = (filePath+"\\"+fileName+".txt");
		try {
			FileReader reader = new FileReader(filePath);
			while ((eachChar = reader.read()) != -1) {
				System.out.print((char) eachChar);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred...please try Again");
			e.printStackTrace();
		}
	}

	public void doWrite(String filePath,String write) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(write);
			writer.close();
			System.out.println("Successfully wrote in to the new Note created !\n");
		} catch (IOException e) {
			System.out.println("An error occurred...please try Again");
			e.printStackTrace();
		}
	}

	public void deleteFile(String filePath , String fileName) {
		if (isFileExist(filePath, fileName) == true) {
			filePath =filePath + "\\" + fileName + ".txt";
			File file = new File(filePath);
			System.out.println("After deletion .. you are not able to recover the note in the folder\n");
			if (file.delete())
				System.out.println("Successfully deleted !");
			else
				System.out.println("cannot able to delete ... try again");
		} else
			System.out.println(fileName + " not found");
	}

	public boolean isFileExist(String FilePath, String fileName) {
		File file = new File(FilePath);
		String[] fileList = file.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return true;
			}
		});
		fileName = fileName.strip();
		if (fileList != null) {
			for (String s : fileList) {
				String nameOfFile = s.replace(".txt", " ");
				nameOfFile = nameOfFile.trim();
				if (nameOfFile.equals(fileName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String[] viewAll(String FilePath) {
		File file = new File(FilePath);
		String[] fileList = file.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return true;
			}
		});
		return fileList;
	}
	
	public void viewInfo(String filePath) {
		File file = new File(filePath);
		BasicFileAttributes attr;
		try {
			attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			System.out.println("creationTime: " + attr.creationTime());
			System.out.println("lastAccessTime: " + attr.lastAccessTime());
			System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
		} catch (IOException e) {
			System.out.println("error occured.. please try again");
			e.printStackTrace();
		}
	}

//	public static void deleteFolder(String filePath) {
//		
//		if (isFolderExist(filePath, folderName) == true) {
//			filePath = Note.getFolderBasePath() + "\\" + folderName;
//			File file = new File(filePath);
//			System.out.println("After deletion .. you are not able to recover the notes in the folder\n");
//			if (file.delete())
//				System.out.println("Successfully deleted !");
//			else
//				System.out.println("cannot able to delete ... try again");
//		} else
//			System.out.println(folderName + " not found");
//	}

//	private static boolean isFolderExist(String FilePath, String folderName) {
//		File file = new File(FilePath);
//		String[] folderList = file.list(new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				return true;
//			}
//		});
//
//		folderName = (folderName + ".txt").strip();
//		if (folderList != null) {
//			for (String nameOfFolder : folderList) {
//				if (nameOfFolder.equals(folderName)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

}
