package com.softuni._hibernatecodefirst.terminal;


import com.softuni._hibernatecodefirst.entities.*;
import com.softuni._hibernatecodefirst.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private  WizardDepositService wizardDepositService;



    @Autowired
    private UserService userService;



    @Autowired
    private CommentService commentService;

    @Autowired
    private DiagnoseService diagnoseService;

    @Autowired
    private MedicamentService medicamentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitationService visitationService;

    public Terminal() {
    }

    @Override
    public void run(String... strings) throws Exception {

        //First Task - Wizards
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

        //Second task, create a user in the users table
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


        //Third Task - Hospital (Comment, Diagnose, Medicament, Patient, Visitation)
        Patient patient = new Patient();
        patient.setFirstName("Johny");
        patient.setLastName("Bravo");
        patient.setEmail("email@abv.bg");
        patient.setHasMedicalInsurance(true);
        patient.setDateOfBirth(new Date());
        this.patientService.create(patient);

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setPatient(patient);
        this.visitationService.create(visitation);

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Cold");
        diagnose.setPatient(patient);
        this.diagnoseService.create(diagnose);


        Comment comment = new Comment();
        comment.setVisitation(visitation);
        comment.setText("Healthy Comment");
        comment.setDiagnose(diagnose);
        this.commentService.create(comment);

        Medicament medicament = new Medicament();
        medicament.setName("Aspirin");
        medicament.setPatients(new HashSet<>());
        medicament.getPatients().add(patient);
        this.medicamentService.create(medicament);

        //we can pass the id in the console and get the patient from the database
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!(line = bufferedReader.readLine()).equals("Stop")){
            long id = Long.parseLong(line);
            Patient p = this.patientService.retrieve(id);
            System.out.println(p.toString());
        }
    }
}
