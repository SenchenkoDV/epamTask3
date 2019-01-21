package com.senchenko.hookah.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.TimeUnit;

public class Group implements Runnable, Smoker {
    private static Logger logger = LogManager.getLogger();
    private int groupId;
    private HookahHouse visitedHookah;
    private int countMembers;
    private Hookah orderedHookah;
    public Group(int groupId, HookahHouse visitedHookah, int countMembers) {
        this.groupId = groupId;
        this.visitedHookah = visitedHookah;
        this.countMembers = countMembers;
    }

    @Override
    public void run(){
        try {
            logger.log(Level.INFO, this + " is waiting hookah outside hookah house");
            visitedHookah.queueUpInHookahHouse().acquire(countMembers);
            logger.log(Level.INFO, this + " is waiting hookah inside hookah house");
            visitedHookah.queueForHookah().acquire();
            orderedHookah = visitedHookah.orderHookah();
            logger.log(Level.INFO, this + " has reserved" + orderedHookah);
            smoke();
            logger.log(Level.INFO, this + " has returned " + orderedHookah);
            visitedHookah.returnHookah(orderedHookah);
            visitedHookah.queueForHookah().release();
            logger.log(Level.INFO, this + " left the hookah house");
            visitedHookah.queueUpInHookahHouse().release();
        } catch (InterruptedException e) {
            logger.log(Level.DEBUG, " can't get hookah");
        }
    }

    @Override
    public void smoke(){
        try {
            logger.log(Level.INFO, this + " is smoking");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.log(Level.DEBUG,"Exception " + e);
        }
    }

    @Override
    public String toString() {
        return "Group " + groupId;
    }
}