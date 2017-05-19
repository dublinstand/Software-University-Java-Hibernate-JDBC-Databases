package com.softuni._hibernatecodefirst.terminal;


import com.softuni._hibernatecodefirst.entities.User;
import com.softuni._hibernatecodefirst.entities.WizardDeposit;
import com.softuni._hibernatecodefirst.services.UserService;
import com.softuni._hibernatecodefirst.services.WizardDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private  WizardDepositService wizardDepositService;

    @Autowired
    private UserService userService;

    public Terminal() {
    }

    @Override
    public void run(String... strings) throws Exception {
        WizardDeposit wd = new WizardDeposit();
        wd.setFirstName("Albus");
        wd.setLastName("Dumbledore");
        wd.setAge(150);
        wd.setMagicWandCreator("Antoich Peverell");
        wd.setMagicWandSize(15);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 11, 11);
        Date depositStart = calendar.getTime();
        wd.setDepositStartDate(depositStart);
        calendar.set(2020, 10, 20);
        Date depositEnd = calendar.getTime();
        wd.setDepositExpirationDate(depositEnd);
        wd.setDepositAmount(2000.24);
        wd.setDepositExpired(false);

        wizardDepositService.persist(wd);

        //second task, create a user in the users table
        User user = new User();
        user.setUsername("bulgIYI");
        user.setEmail("bulg@abv.bg");
        user.setPassword("Bb#111gfs88");
        user.setRegisteredOn(new Date());
        user.setLastTimeLoggedIn(new Date());
        user.setDeleted(false);

        //we upload the picture from a file
        File picture = new File("resources/Less_1MB.jpg");
        //put the size and create the byte array to enter the characters
        byte[] pictureBytes = new byte[(int) picture.length()];
        //put it in the file input stream
        FileInputStream fis = new FileInputStream(picture);
        //read the file and fill it in the pictureByte (the byte array)
        fis.read(pictureBytes);
        fis.close();
        //put it in the user
        user.setProfilePicture(pictureBytes);

        //we create a second user
        User user2 = new User();
        user2.setUsername("qwrfg");
        user2.setEmail("asd@abv.bg");
        user2.setPassword("Bb#111gfs88");
        user2.setRegisteredOn(new Date());
        user2.setLastTimeLoggedIn(new Date());
        user2.setDeleted(false);

        userService.createUser(user);
        userService.createUser(user2);

        List<User> users = userService.findUsersByEmail("bulg@abv.bg");
        for (User user1 : users) {
                System.out.println(user1.getEmail());
        }

        List<User> usersByEmailProviders = userService.findUserByEmailProvider("abv.bg");
        for (User usersByEmailProvider : usersByEmailProviders) {
            System.out.println(usersByEmailProvider.getEmail());
        }

        int count = userService.countByProfilePictureGreaterThan(new byte[5]);
        System.out.println(count);
    }
}
