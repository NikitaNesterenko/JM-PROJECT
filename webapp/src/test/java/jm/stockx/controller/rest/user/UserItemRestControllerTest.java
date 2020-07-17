package jm.stockx.controller.rest.user;

import jm.stockx.ItemServiceImpl;
import jm.stockx.entity.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class UserItemRestControllerTest {
    private static final String url = "/rest/api/user/items/";

    @InjectMocks
    private UserItemRestController userItemRestController;

    private MockMvc mockMvc;

    @Mock
    private ItemServiceImpl itemService;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userItemRestController).build();
    }


    @Test
    public void getItemByIdTest() throws Exception {
        Long testId_1 = 1L;
        String testName = "Name";
        Item item1 = new Item();
        item1.setId(testId_1);
        item1.setName(testName);

        when(itemService.get(testId_1)).thenReturn(item1);
        mockMvc.perform(get(url + "{id}", testId_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(testId_1))
                .andExpect(jsonPath("$.name").value(testName));

        verify(itemService, times(1)).get(testId_1);
        verifyNoMoreInteractions(itemService);
    }

    @Test
    public void getAllItemsTest() throws Exception {
        List<Item> items = Arrays.asList(new Item(), new Item());

        when(itemService.getAll()).thenReturn(items);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(items.size())));

        verify(itemService, times(1)).getAll();
    }

}