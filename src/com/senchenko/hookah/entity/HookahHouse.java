package com.senchenko.hookah.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class HookahHouse {
    private static Logger logger = LogManager.getLogger();
    private LinkedList<Hookah> freeHookahs;
    private Semaphore freeSpaceHookahHouse;
    private Semaphore hookahs;

    public HookahHouse(LinkedList<Hookah> freeHookahs, Semaphore hookahs, Semaphore freeSpaceHookahHouse) {
        this.freeHookahs = freeHookahs;
        this.hookahs = hookahs;
        this.freeSpaceHookahHouse = freeSpaceHookahHouse;
    }

    public Semaphore queueUpInHookahHouse() {
        return freeSpaceHookahHouse;
    }

    public Semaphore queueForHookah() {
        return hookahs;
    }

    public Hookah orderHookah() {
        Hookah orderedHookah;
        orderedHookah = freeHookahs.pollFirst();
        logger.log(Level.INFO, orderedHookah + " has been reserved");
        return orderedHookah;
    }

    public boolean returnHookah(Hookah hookah) {
        logger.log(Level.INFO, " has been returned " + hookah);
        return freeHookahs.add(hookah);
    }
}
