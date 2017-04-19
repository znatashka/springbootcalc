package u26c4.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import u26c4.builders.ResultBuilder;
import u26c4.models.CalcResult;
import u26c4.services.CalcService;

import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
@AutoConfigureMockMvc(secure = false)
public class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CalcService calcService;

    private CalcResult result;

    @Before
    public void setUp() throws Exception {
        result = new ResultBuilder()
                .buildError("Test Error")
                .buildCalculation(45.67)
                .buildRPN("Reverse Polish Notation")
                .buildFormattedExpression("Formatted Expression")
                .build();
    }

    @Test
    public void calculation() throws Exception {
        // PREPARE
        when(calcService.execute(null)).thenReturn(result);

        // ACT
        MvcResult result = mockMvc.perform(post("/calculation"))
                .andExpect(status().isOk())
                .andExpect(view().name("result :: calculation"))
                .andExpect(model().attributeExists("result"))
                .andExpect(model().attribute("result", hasProperty("error", is("Test Error"))))
                .andExpect(model().attribute("result", hasProperty("calculation", is(45.67))))
                .andExpect(model().attribute("result", hasProperty("rpn", is("Reverse Polish Notation"))))
                .andExpect(model().attribute("result", hasProperty("formattedExpression", is("Formatted Expression"))))
                .andDo(print())
                .andReturn();

        // ASSERT
        MockHttpServletResponse mockResponse = result.getResponse();
        assertEquals(mockResponse.getContentType(), "text/html;charset=UTF-8");

        Collection<String> headerNames = mockResponse.getHeaderNames();
        assertNotNull(headerNames);
        assertEquals(1, headerNames.size());
        assertEquals("Check for Content-Type header", "Content-Type", headerNames.iterator().next());

        String contentAsString = mockResponse.getContentAsString();
        assertTrue(contentAsString.contains("Formatted Expression: Formatted Expression"));
        assertTrue(contentAsString.contains("Reverse Polish Notation: Reverse Polish Notation"));
        assertTrue(contentAsString.contains("Result: 45.67"));
        assertTrue(contentAsString.contains("Errors: Test Error"));

        verify(calcService, times(1)).execute(null);
        verifyNoMoreInteractions(calcService);
    }
}