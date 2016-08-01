package com.test.language.spring;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JunitLanguageTest {

	@Autowired
	private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

    @Test
    public void testNullDataFile() throws Exception{
    	
    	 byte[] b = null;
    	 MockMultipartFile file  = new MockMultipartFile("file", "test.2", "text/plain", b);
         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFilesAndDetectLanguage")
                         .file(file).param("name", "test.1"))
                     .andExpect(status().is(200))
         			 .andExpect(content().string("The uploaded file test.1 is empty"));
    }
    
    @Test
    public void testEmptyFile() throws Exception{

    	 MockMultipartFile file  = new MockMultipartFile("file", "test.2", "text/plain", "".getBytes());
         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFilesAndDetectLanguage")
                         .file(file).param("name", "test.2"))
                     .andExpect(status().is(200))
         			 .andExpect(content().string("The uploaded file test.2 is empty"));
    }
    
    @Test
    public void testGermanFile() throws Exception{
    	
    	
    	 MockMultipartFile file  = new MockMultipartFile("file", "german.1", "text/plain", "An ihm lässt sich vieles über die Schrift ablesen".getBytes());
         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFilesAndDetectLanguage")
                         .file(file).param("name", "german"))
                     .andExpect(status().is(200))
                     .andExpect(content().string("The language of uploaded file german is de"));
    	
    }
    
    @Test
    public void testFrenchFile() throws Exception{
    	
    	// byte[] b = null;
    	 MockMultipartFile file  = new MockMultipartFile("file", "french.1", "text/plain", "Découvrez l’univers Mobile Orange avec une large gamme de téléphones mobiles,".getBytes());
         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFilesAndDetectLanguage")
                         .file(file).param("name", "french"))
                     .andExpect(status().is(200))
         			 .andExpect(content().string("The uploaded file french is fr"));
    }
    

}
