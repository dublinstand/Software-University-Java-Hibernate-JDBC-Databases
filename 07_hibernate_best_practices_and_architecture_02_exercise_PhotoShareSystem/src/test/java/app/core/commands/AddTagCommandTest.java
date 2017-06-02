package app.core.commands;

import app.domain.model.Tag;
import app.service.TagService;
import app.serviceimpl.TagServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class AddTagCommandTest {

    @Mock
    private TagService tagServiceMock;

    //set up the tagServiceMock to use Mockito and be mocked and initialize tagServiceMock
    @Before
    public void setUp() throws Exception {
        this.tagServiceMock = Mockito.mock(TagServiceImpl.class);
    }

    @Test
    public void execute() throws Exception {
        Tag expected = new Tag();
        expected.setName("#LoveIt");
        Mockito.when(this.tagServiceMock.findByName("#LoveIt"))
                .thenReturn(new Tag("#LoveIt"));
        Tag actual = this.tagServiceMock.findByName("#LoveIt");
        Assert.assertEquals(expected, actual);
    }
}