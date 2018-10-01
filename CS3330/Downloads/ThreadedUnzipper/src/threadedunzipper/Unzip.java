/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadedunzipper;

import java.io.File;
import java.io.FilenameFilter;
import javafx.application.Platform;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author dale
 */
public class Unzip extends Thread {
    private File sourceDirectory;
    private File destinationDirectory;
    private File[] files;   
    public Boolean stop = false;
    private Notification notification;
    
    public Unzip(File sourceDirectory, File destinationDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.destinationDirectory = destinationDirectory;
        
        files = sourceDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String filename) {
                return filename.endsWith(".zip");
            }
        });
    }
    
    public int getNumFiles() {
        if (files == null) {
            return 0;
        }
        return files.length;
    }
    
    public File[] getFiles() {
        return this.files;
    }
    
    @Override
    public void run() {
        String destinationDirectoryPath = destinationDirectory.getPath();
        int numComplete = 0;
        for (File file : files) {
            if (stop) { 
                doNotify(calculatePercent(numComplete, files.length), UnzipState.INTERRUPTED, "Unzip operation interrupted.");
                break;
            }

            doNotify(calculatePercent(numComplete, files.length), UnzipState.RUNNING, "Extracting " + file.getPath() + " to " + destinationDirectoryPath);

            try {
                ZipFile zipFile = new ZipFile(file.getPath());
                zipFile.extractAll(destinationDirectoryPath);
                numComplete++;
            } catch (ZipException e) {
                doNotify(calculatePercent(numComplete, files.length), UnzipState.EXCEPTION, "Exception occurred on " + file.getPath() + ": " + e);
            } 
        }
        doNotify(calculatePercent(numComplete, files.length), UnzipState.ENDED, "Unzip ended.");
    }
    
    public void end() {
        stop = true;
    }
    
    private double calculatePercent(int count, int total) {
        if (total == 0) return 0;
        return (double)count / (double)total;
    }
    
    // this method allows a notification handler to be registered to receive notifications
    public void setOnNotification(Notification notification) {
        this.notification = notification;
    }
    
    private void doNotify(double percentComplete, UnzipState state, String message) {
        // this provides the notification through the registered notification handler
        if (notification != null) {
            Platform.runLater(() -> {
                notification.handle(percentComplete, state, message);
            });
        }
    }
}
