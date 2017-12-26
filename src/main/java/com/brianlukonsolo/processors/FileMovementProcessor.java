package com.brianlukonsolo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_FILE_TYPE;
import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_STORAGE_FOLDER_ROOT;
import static com.brianlukonsolo.constants.CodeConstants.StringRelatedConstants.DOUBLE_NEWLINE;

public class FileMovementProcessor implements Processor{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileMovementProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        //Get the storage filepath root
        String filePathToStorageRoot = exchange.getIn().getHeader(HEADER_STORAGE_FOLDER_ROOT, String.class);
        filePathToStorageRoot = addTrailingSlashIfNotAdded(filePathToStorageRoot);

        //Check if the folder exists. Create it if it doesn't.
        createFolderIfDoesNotExist((filePathToStorageRoot));

        //Check if the folder with the same name as the extension exists. Create it if it doesn't.
        String fileExtension = exchange.getIn().getHeader(HEADER_FILE_TYPE, String.class);
        createFolderIfDoesNotExist(filePathToStorageRoot + fileExtension);

    }

    private void createFolderIfDoesNotExist(String folderPath){
        File theDir = new File(folderPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            LOGGER.info(DOUBLE_NEWLINE + "Creating directory: " + theDir.getName());
            boolean result = false;
            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                LOGGER.info(DOUBLE_NEWLINE + "Could not create directory due to insufficient permissions!" + DOUBLE_NEWLINE);
            }
            if(result) {
                LOGGER.info(DOUBLE_NEWLINE + "Directory created" + DOUBLE_NEWLINE);
            }
        }


    }

    private String addTrailingSlashIfNotAdded(String folderPath){
        String path = folderPath;
        if(path.charAt(folderPath.length()-1) != '\\'){
            path = path + "\\";
        }

        return path;


    }
}
