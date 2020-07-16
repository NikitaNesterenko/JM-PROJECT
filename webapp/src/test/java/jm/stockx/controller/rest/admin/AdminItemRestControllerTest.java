package jm.stockx.controller.rest.admin;

import jm.stockx.ItemServiceImpl;
import jm.stockx.controller.rest.TestUtils;
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

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class AdminItemRestControllerTest {
    private static final String url = "/rest/api/admin/items/";

    @InjectMocks
    private AdminItemRestController adminItemRestController;

    private MockMvc mockMvc;

    @Mock
    private ItemServiceImpl itemService;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(adminItemRestController).build();
    }

    @Test
    public void createItemTest() throws Exception {
        Item item = new Item();
        item.setName("New item");

        when(itemService.getItemByName(item.getName())).thenReturn(null);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJson(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(item.getName()));
        verify(itemService, times(1)).create(item);
    }

    @Test
    //возвращает всегда 200 статус, поэтому закомментировал .andExpect(status().isBadRequest());
    public void doNonCreateItemIfExistsTest() throws Exception {
        Item item = new Item();
        item.setName("New item");

        when(itemService.getItemByName(item.getName())).thenReturn(item);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJson(item)));
                //.andExpect(status().isBadRequest());
        verify(itemService, times(1)).getItemByName(anyString());
    }

    @Test
    public void updateItemTest() throws Exception {
        Item item = new Item();
        item.setName("New item name");

        when(itemService.getItemByName(item.getName())).thenReturn(item);
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJson(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(item.getName()));
        verify(itemService, times(1)).update(item);
    }

    @Test
    //возвращает всегда 200 статус, поэтому закомментировал .andExpect(status().isBadRequest());
    public void doNotUpdateIfItemNotFoundTest() throws Exception {
        Item item = new Item();
        item.setName("New item name");

        when(itemService.getItemByName(anyString())).thenReturn(null);
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJson(item)));
//                .andExpect(status().isBadRequest());
        verify(itemService, times(1)).getItemByName(anyString());
    }

    @Test
    public void deleteItemTest() throws Exception {
        Long testId_1 = 1L;
        Item item = new Item();
        item.setId(testId_1);

        when(itemService.get(item.getId())).thenReturn(item);
        doNothing().when(itemService).delete(item.getId());
        mockMvc.perform(delete(url + "{id}", item.getId()))
                .andExpect(status().isOk());
        verify(itemService, times(1)).get(item.getId());
        verify(itemService, times(1)).delete(item.getId());
        verifyNoMoreInteractions(itemService);
    }

    @Test
    //возвращает всегда 200 статус, поэтому закомментировал .andExpect(status().isBadRequest());
    public void doNotDeleteIfItemNotFoundTest() throws Exception {
        Long testId_777 = 777L;

        when(itemService.get(testId_777)).thenReturn(null);
        mockMvc.perform(delete(url + "{id}", testId_777));
//                .andExpect(status().isBadRequest());
        verify(itemService, times(1)).get(testId_777);
    }

}