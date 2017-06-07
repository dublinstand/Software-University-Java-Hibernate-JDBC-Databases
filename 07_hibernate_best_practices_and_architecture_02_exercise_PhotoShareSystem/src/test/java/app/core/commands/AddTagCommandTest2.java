package app.core.commands;

import app.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AddTagCommandTest2 {

    //this means in our addTagCommand we will inject services, from Mockito
    @InjectMocks
    private AddTagCommand addTagCommand;

    //this is mocked service, so it won't save in the database, from Mockito
    @Mock
    private TagService tagService;

    //this is our data
    private String[] data;

    //in our AddTagCommand we need to create a setter for the Autowired TagService
    //@Before mens that this method will be run before our execute test
    @Before
    public void setUp(){
        //we set our data
        this.data = new String[]{"AddTag", "#verynicetag"};

        //addTagCommand is created by @InjectMocks, we pass the Service that we will test
        this.addTagCommand.setTagService(this.tagService);

        //we need to set the data in addTagCommand
        this.addTagCommand.setData(this.data);
    }

    @Test
    public void executeReturnsCorrectStringOutput() throws Exception {
        //this is our expected result
        String expectedOutput = this.data[1] + " was added successfully to database";

        //no data will be added to the database
        //the data in addTagCommand is created from our setUp, which uses @Before so it is run before the test
        String returnedOutput = this.addTagCommand.execute();
        assertEquals(returnedOutput, expectedOutput);
    }
}