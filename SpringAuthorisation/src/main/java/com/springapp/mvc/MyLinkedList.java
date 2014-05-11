package com.springapp.mvc;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Александр on 04.05.14.
 */
public class MyLinkedList extends LinkedList<AboutUser> {
    @Override
    public String toString (){
        String out = "";
        Iterator i = this.iterator();
        while(i.hasNext()){
            AboutUser a = (AboutUser) i.next();
            out += a.toString();
        }
        return out;
    }
}
