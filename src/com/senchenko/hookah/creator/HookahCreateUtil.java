package com.senchenko.hookah.creator;

import com.senchenko.hookah.entity.Group;
import com.senchenko.hookah.entity.Hookah;
import com.senchenko.hookah.entity.HookahHouse;
import com.senchenko.hookah.entity.Person;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class HookahCreateUtil {
    private static final int ID_HOOKAHS = 0;
    private static final int ID_MAX_PEOPLE_IN_HOOKAH_HOUSE = 1;
    private static final int ID_NUMBER_SINGLE_CUSTOMERS = 2;
    private static final int ID_NUMBER_GROUPS = 3;
    private static final int ID_NUMBER_PEOPLE_IN_GROUP = 4;

    public static LinkedList<Hookah> createHookahs(int hookahCount){
        LinkedList<Hookah> hookahList = new LinkedList<>();
        for (int i = 1; i <= hookahCount; i++) {
            hookahList.add(new Hookah(i));
        }
        return hookahList;
    }

    public static HookahHouse createHookahHouse(List<Integer> dateForHouse){
        return new HookahHouse(createHookahs(dateForHouse.get(ID_HOOKAHS)),
                new Semaphore(dateForHouse.get(ID_HOOKAHS), true),
                new Semaphore(dateForHouse.get(ID_MAX_PEOPLE_IN_HOOKAH_HOUSE), true));
    }

    public static List<Runnable> createCustomersList(List<Integer> dataForCreateCustomers, HookahHouse hookahHouse){
        List<Runnable> listCustomers = new ArrayList<>();
        for (int i = 1; i <= dataForCreateCustomers.get(ID_NUMBER_SINGLE_CUSTOMERS); i++) {
            listCustomers.add(new Person(i, hookahHouse));
        }
        for (int i = 1; i <= dataForCreateCustomers.get(ID_NUMBER_GROUPS); i++) {
            listCustomers.add(new Group(i, hookahHouse, dataForCreateCustomers.get(ID_NUMBER_PEOPLE_IN_GROUP)));
        }
        return listCustomers;
    }
}
