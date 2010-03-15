/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.annotation;

import com.validation.VMessages;
import com.validation.BeanValidator;
import com.validation.annotation.Email;
import com.validation.annotation.MinLength;
import com.validation.annotation.NotEmpty;
import com.validation.annotation.NotNull;
import com.validation.annotation.Range;
import java.util.Iterator;

/**
 *
 * @author Jiren
 */
public class MyBean {

    @MinLength(value = 5, message = "Min Length of name field is 5.")
    @NotEmpty(message = "This field can not be blank")
    private String name;

    @Email(message="Pelese Enter valid email in format abc@xyz.com")
    @MinLength(value = 5, message = "Min Length of name field is 5.")
    private String email;

    @Range(start=1, end= 10, message= "Out Of range")
    private Integer length;

    @NotNull(message="valid is empty")
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        MyBean myBean = new MyBean();

        myBean.setName("name1");
        myBean.setEmail("abc@abc.com");
        myBean.setLength(1);
        //myBean.setEmail(null);
       
        //Validation
        VMessages vm = BeanValidator.validate(myBean);

        //Print Validation Message
        for (Iterator it = vm.getMessages().iterator(); it.hasNext();) {
            System.out.println(it.next());
        }



    }
}
