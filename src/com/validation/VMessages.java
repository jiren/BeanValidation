/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jiren
 */
public class VMessages {

    private boolean hasMessages;
    private List<String> messages;

    public VMessages() {
        this.hasMessages = false;
        this.messages = new ArrayList<String>();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public boolean hasMessages() {
        if (messages.size() > 0) {
            hasMessages = true;
        }
        return hasMessages;
    }

    public void addMessage(String msg) {
        this.messages.add(msg);
    }
}
