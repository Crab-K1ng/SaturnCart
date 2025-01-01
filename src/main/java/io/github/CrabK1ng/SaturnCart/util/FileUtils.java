package io.github.CrabK1ng.SaturnCart.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectSet;
import finalforeach.cosmicreach.io.SaveLocation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.function.BiConsumer;

public class FileUtils {

    public static void forEachAsset(String worldFolderName, String extension, BiConsumer<String, String> assetConsumer) {
        forEachAsset(worldFolderName, extension, assetConsumer, false);
    }

    public static void forEachAsset(String worldFolderName, String extension, BiConsumer<String, String> assetConsumer, boolean includeDirectories) {
        ObjectSet<String> objectset = new ObjectSet<>();

        String worldSaveFolder = SaveLocation.getWorldSaveFolderLocation(worldFolderName).replace("./", "");
        String TrackFolder = worldSaveFolder + "/tracks";
        String basePath = Gdx.files.absolute(TrackFolder).path().replace("\\", "/");

        // Recursively iterate through all files and directories
        searchFilesRecursive(Gdx.files.absolute(basePath), extension, includeDirectories, (path, fileHandle) -> {
            String relativePath = fileHandle.path().replace("\\", "/");
            if (relativePath.startsWith("/")) {
                relativePath = relativePath.substring(1); // Remove leading slash
            }
            objectset.add(relativePath);
        });

        for (String file : objectset) {
            assetConsumer.accept(file.toString(), file);
        }
    }

    private static void searchFilesRecursive(FileHandle directory, String extension, boolean includeDirectories, BiConsumer<String, FileHandle> fileProcessor) {
        for (FileHandle fileHandle : directory.list()) {
            if (fileHandle.isDirectory()) {
                if (includeDirectories) {
                    fileProcessor.accept(fileHandle.path(), fileHandle);
                }
                searchFilesRecursive(fileHandle, extension, includeDirectories, fileProcessor);
            } else if (fileHandle.path().endsWith(extension)) {
                fileProcessor.accept(fileHandle.path(), fileHandle);
            }
        }
    }

    public static InputStream loadFile(String path) throws FileNotFoundException {
        File file1 = new File(path);
        FileInputStream fileinputstream = new FileInputStream(file1);
        return fileinputstream;
    }
}
