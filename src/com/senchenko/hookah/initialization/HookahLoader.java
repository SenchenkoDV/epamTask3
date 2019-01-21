package com.senchenko.hookah.initialization;

import com.senchenko.hookah.creator.HookahCreateUtil;
import com.senchenko.hookah.entity.HookahHouse;
import com.senchenko.hookah.exception.HookahException;
import com.senchenko.hookah.parser.HookahDataParser;
import com.senchenko.hookah.reader.DataHookahReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class HookahLoader {
    private static Logger logger = LogManager.getLogger();
    private static HookahHouse hookahHouse = null;
    private static List<Integer> dateForHookahHouse;
    private static List<Runnable> customersList;

    public static void main(String[] args) {
        try {
            dateForHookahHouse = new HookahDataParser()
                    .parseDataForHookahHouse(DataHookahReader.readDataForHookahHouse("path"));
            hookahHouse = HookahCreateUtil.createHookahHouse(dateForHookahHouse);
        } catch (HookahException e) {
            logger.log(Level.DEBUG, "error creating a hookah house");
        }
        customersList = HookahCreateUtil.createCustomersList(dateForHookahHouse, hookahHouse);
        for (Runnable smoker: customersList){
            new Thread(smoker).start();
        }
    }
}
